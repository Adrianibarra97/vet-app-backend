package ar.edu.unsam.proyecto.vetappbackend.dto.notification
import ar.edu.unsam.proyecto.vetappbackend.domain.notification.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import java.time.*

data class NotificationResponseDTO (
    val id: Int,
    val namePet: String,
    val nameVet: String,
    val namePetOwner: String,
    val date: String,
    val hour: String,
    val notificationDate: String,
    val type: String,
    val subject: String,
    val message: String,
    val professionalEmail: String,
    val professionalTelephone: String,
    val wasRead: Boolean
)

fun Notification.toDTO(): NotificationResponseDTO {
    return NotificationResponseDTO(
        id = this.id,
        namePet = this.pet?.name!!,
        nameVet = this.vet?.name!!,
        namePetOwner = this.petOwner?.name!!,
        date = this.date.toString(),
        hour = this.hour.toString(),
        notificationDate = this.notificationDate.toString(),
        type = this.type?.name!!,
        subject = this.subject!!,
        message = this.message!!,
        professionalEmail = this.vet?.professionalEmail!!,
        professionalTelephone = this.vet?.professionalTelephone!!,
        wasRead = this.wasRead
    )
}

data class NotificationRequestDTO(
    val id: Int,
    val idPet: String,
    val idVet: String,
    val idPetOwner: String,
    val date: String,
    val hour: String,
    val notificationDate: String,
    val type: String,
    val subject: String,
    val message: String,
    val wasRead: Boolean
)

fun NotificationRequestDTO.fromJSON(currentPet: Pet, currentVet: Vet, currentPetOwner: PetOwner ): Notification {
    val notificationDTO = this
    return Notification(
        petOwner = currentPetOwner,
        vet = currentVet,
        pet = currentPet,
        date = notificationDTO.date.let { LocalDate.parse(it) },
        hour = notificationDTO.hour.let { LocalTime.parse(it) },
        notificationDate = notificationDTO.notificationDate.let { LocalDate.parse(it) },
        type = TypeOfNotification.valueOf(notificationDTO.type),
        subject = notificationDTO.subject,
        message = notificationDTO.message,
        wasRead = notificationDTO.wasRead
    ).apply { id = currentPet.id }
}