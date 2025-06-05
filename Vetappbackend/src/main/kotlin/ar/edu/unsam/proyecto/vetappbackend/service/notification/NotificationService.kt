package ar.edu.unsam.proyecto.vetappbackend.service.notification

import ar.edu.unsam.proyecto.vetappbackend.domain.notification.Notification
import ar.edu.unsam.proyecto.vetappbackend.domain.notification.NotificationFactory
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.notification.NotificationRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationService: BaseService<Notification> {

    @Autowired lateinit var notificationRepository: NotificationRepository

    override fun getAll(): List<Notification> {
        return this.notificationRepository.findAll().toList()
    }

    override fun getOneById(idNotification: Int): Notification {
        return this.notificationRepository.findById(idNotification).orElseThrow {
            NotFoundException("No se encontró la mascota indicada: $idNotification")
        }
    }

    override fun create(newNotification: Notification) {
        this.notificationRepository.save(newNotification)
    }

    override fun update(updateNotification: Notification) {
        this.notificationRepository.save(updateNotification)
    }

    override fun delete(deleteNotification: Notification) {
        this.notificationRepository.delete(deleteNotification)
    }

    fun getAllNotificationsVet(idVet: Int): List<Notification>{
        return  this.notificationRepository.findByVetId(idVet)
    }

    fun getAllNotificationsPetOwner(idPetOwner: Int): List<Notification>{
        return  this.notificationRepository.findByPetOwnerId(idPetOwner)
    }

    fun createNotificationMedicalShift(shift: MedicalShift, type: TypeOfNotification) {
        val notification = NotificationFactory().fromMedicalShift(shift, type)
        this.notificationRepository.save(notification)
    }

}