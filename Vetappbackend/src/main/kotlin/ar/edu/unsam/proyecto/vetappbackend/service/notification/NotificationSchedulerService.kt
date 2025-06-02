package ar.edu.unsam.proyecto.vetappbackend.service.notification

import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.service.user.MedicalShiftService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class NotificationSchedulerService {

    @Autowired lateinit var medicalShiftService: MedicalShiftService

    @Autowired lateinit var notificationService: NotificationService

    @Autowired lateinit var emailService: EmailService

    @Scheduled(cron = "0 0 0 * * *", zone = "America/Argentina/Buenos_Aires") // cron = "sec min hour * * *, zone = "cont country province" "
    fun verifyMedicalShift() {
        val date = LocalDate.now()
        val medicalShifts = medicalShiftService.findAllByDate(date)

        if (medicalShifts.isNotEmpty()) {
            medicalShifts.forEach {
                emailService.sendAppointmentNotification(
                    it.pet!!,
                    it.vet!!,
                    it.pet?.petOwner!!,
                    it.date!!,
                    it.hour!!,
                    TypeOfNotification.SHIFT_REMINDER
                )
                notificationService.createNotification(it, TypeOfNotification.SHIFT_REMINDER)
            }
            println("✅ Notificaciones enviadas para los turnos del $date")
        } else {
            println("❌ No hay turnos para el $date")
        }
    }
}
