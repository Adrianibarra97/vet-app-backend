package ar.edu.unsam.proyecto.vetappbackend.controller.user

import ar.edu.unsam.proyecto.vetappbackend.domain.notification.Notification
import ar.edu.unsam.proyecto.vetappbackend.dto.notification.NotificationResponseDTO
import ar.edu.unsam.proyecto.vetappbackend.service.notification.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("/notification")
class NotificationController {

    @Autowired private lateinit var notificationService: NotificationService

    @PutMapping("/update")
    fun update(@RequestBody notification: NotificationResponseDTO) {
        val updatedNotification: Notification = this.notificationService.getOneById(notification.id)
        updatedNotification.apply {
            wasRead = notification.wasRead
        }
        this.notificationService.update(updatedNotification)
    }
}