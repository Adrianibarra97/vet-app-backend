package ar.edu.unsam.proyecto.vetappbackend.domain.notification
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import java.time.LocalDate

class NotificationFactory {
    fun fromMedicalShift(shift: MedicalShift, type: TypeOfNotification): Notification {
        val notificationContent = NotificationContent(shift.pet!!, shift.hour!!, shift.date!!, type)
        val (subject, message) = notificationContent.generateSubjectAndMessage()
        return Notification(
            date = shift.date,
            hour = shift.hour,
            subject = subject,
            message = message,
            notificationDate = LocalDate.now(),
            type = type,
            pet = shift.pet,
            vet = shift.vet,
            petOwner = shift.pet?.petOwner
        )
    }
}
