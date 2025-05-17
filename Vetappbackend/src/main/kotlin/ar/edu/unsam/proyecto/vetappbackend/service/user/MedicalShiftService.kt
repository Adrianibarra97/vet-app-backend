package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.domain.notification.Notification
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.MedicalShiftFilter
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.MedicalShiftRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.service.notification.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MedicalShiftService: BaseService<MedicalShift> {

    @Autowired lateinit var medicalShiftRepository: MedicalShiftRepository

    @Autowired lateinit var notificationService: NotificationService

    @Autowired lateinit var emailService: EmailService

    override fun getOneById(idMedicalShift: Int): MedicalShift {
        return this.medicalShiftRepository.findById(idMedicalShift).orElseThrow {
            NotFoundException("No se encontró el turno indicado: $idMedicalShift")
        }
    }

    override fun getAll(): List<MedicalShift> {
        return medicalShiftRepository.findAll().toList()
    }

    override fun create(newMedicalShift: MedicalShift) {
        this.medicalShiftRepository.save(newMedicalShift)
    }

    override fun delete(medicalShiftDelete: MedicalShift) {
        this.emailService.sendAppointmentDelete(
            vetEmail = medicalShiftDelete.vet?.professionalEmail!!,
            ownerEmail = medicalShiftDelete.pet?.petOwner?.email!!,
            petName = medicalShiftDelete.pet?.name!!,
            appointmentDateTime = medicalShiftDelete.date.toString(),
        )
        val notification = Notification(
            petOwner = medicalShiftDelete.pet?.petOwner,
            vet = medicalShiftDelete.vet,
            pet = medicalShiftDelete.pet,
            date = medicalShiftDelete.date,
            hour = medicalShiftDelete.hour,
            notificationDate = LocalDate.now(),
            type = TypeOfNotification.SHIFT_DELETE
        )
        this.notificationService.create(notification)
        this.medicalShiftRepository.delete(medicalShiftDelete)
    }

    override fun update(medicalShiftUpdate: MedicalShift) {
        this.getOneById(medicalShiftUpdate.id!!)
        this.emailService.sendAppointmentUpdate(
            vetEmail = medicalShiftUpdate.vet?.professionalEmail!!,
            ownerEmail = medicalShiftUpdate.pet?.petOwner?.email!!,
            petName = medicalShiftUpdate.pet?.name!!,
            appointmentDateTime = medicalShiftUpdate.date.toString(),
        )
        val notification = Notification(
            petOwner = medicalShiftUpdate.pet?.petOwner,
            vet = medicalShiftUpdate.vet,
            pet = medicalShiftUpdate.pet,
            date = medicalShiftUpdate.date,
            hour = medicalShiftUpdate.hour,
            notificationDate = LocalDate.now(),
            type = TypeOfNotification.SHIFT_UPDATE
        )
        this.notificationService.create(notification)
        this.medicalShiftRepository.save(medicalShiftUpdate)
    }

    fun getMedicalShiftFilterVet(medicalShiftFilter: MedicalShiftFilter, idVet: Int): List<MedicalShift> {
        return medicalShiftRepository.getAllByFilterMedicalShiftVet(
            idVet,
            medicalShiftFilter.day,
            medicalShiftFilter.today,
            medicalShiftFilter.beginningOfWeek,
            medicalShiftFilter.endingOfWeek
        )
    }

    fun getMedicalShiftFilterPetOwner(medicalShiftFilter: MedicalShiftFilter, idPetOwer: Int): List<MedicalShift> {
        return medicalShiftRepository.getAllByFilterMedicalShiftPetOwner(
            idPetOwer,
            medicalShiftFilter.day,
            medicalShiftFilter.today,
            medicalShiftFilter.beginningOfWeek,
            medicalShiftFilter.endingOfWeek
        )
    }
}