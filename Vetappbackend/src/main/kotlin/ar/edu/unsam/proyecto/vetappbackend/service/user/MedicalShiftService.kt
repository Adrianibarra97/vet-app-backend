package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.domain.type.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.dto.filter.MedicalShiftFilter
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.MedicalShiftRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.service.mail.EmailService
import ar.edu.unsam.proyecto.vetappbackend.service.notification.*
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
        this.notificationService.createNotificationMedicalShift(newMedicalShift, TypeOfNotification.SHIFT_CREATE)
        try {
            this.sendEmail(newMedicalShift, TypeOfNotification.SHIFT_CREATE)
        } catch (e: Exception){
            println("Error al enviar el email: Creación de un turno: ${e.message}")
        }
    }

    override fun delete(medicalShiftDelete: MedicalShift) {
        this.medicalShiftRepository.delete(medicalShiftDelete)
        this.notificationService.createNotificationMedicalShift(medicalShiftDelete, TypeOfNotification.SHIFT_DELETE)
        try {
            this.sendEmail(medicalShiftDelete, TypeOfNotification.SHIFT_DELETE)
        } catch (e: Exception){
            println("Error al enviar el email: Eliminación de un turno: ${e.message}")
        }
    }

    override fun update(medicalShiftUpdate: MedicalShift) {
        this.getOneById(medicalShiftUpdate.id!!)
        this.medicalShiftRepository.save(medicalShiftUpdate)
        this.notificationService.createNotificationMedicalShift(medicalShiftUpdate, TypeOfNotification.SHIFT_UPDATE)
        try {
            this.sendEmail(medicalShiftUpdate, TypeOfNotification.SHIFT_UPDATE)
        } catch (e: Exception){
            println("Error al enviar el email: Actualización de un turno: ${e.message}")
        }

    }

    fun findAllByDate(date: LocalDate): List<MedicalShift> {
        return medicalShiftRepository.findAllByDate(date)
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

    private fun sendEmail(shift: MedicalShift, type: TypeOfNotification) {
        this.emailService.sendNotificationMedicalShift(
            shift.pet!!,
            shift.vet!!,
            shift.pet?.petOwner!!,
            shift.date!!,
            shift.hour!!,
            type
        )
    }

}