package ar.edu.unsam.proyecto.vetappbackend.repository.notification

import ar.edu.unsam.proyecto.vetappbackend.domain.notification.Notification
import org.springframework.data.repository.CrudRepository

interface NotificationRepository: CrudRepository<Notification, Int> {

    fun findByVetId(idVet: Int): List<Notification>

    fun findByPetOwnerId(idPetOwner: Int): List<Notification>

}