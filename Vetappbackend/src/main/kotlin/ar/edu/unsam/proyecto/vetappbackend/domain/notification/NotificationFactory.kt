package ar.edu.unsam.proyecto.vetappbackend.domain.notification
import ar.edu.unsam.proyecto.vetappbackend.domain.mail.MailContent
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.user.MedicalShift
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class NotificationFactory {

    //A notification is built that relates elements of a medical shift to save it in the database.
    fun fromMedicalShift(shift: MedicalShift, type: TypeOfNotification): Notification {


        val (subject, message) = MailContent().generateSubjectAndMessage(shift.pet!!, shift.hour!!, shift.date!!, type)

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