package ar.edu.unsam.proyecto.vetappbackend.domain.notification
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import jakarta.persistence.*
import java.time.*

@Entity
class Notification(

    @ManyToOne (cascade = [CascadeType.MERGE])
    @JoinColumn(referencedColumnName = "id", name = "id_pet_owner")
    var petOwner: PetOwner? = null,

    @ManyToOne (cascade = [CascadeType.MERGE])
    @JoinColumn(referencedColumnName = "id", name = "id_vet")
    var vet: Vet? = null,

    @ManyToOne (cascade = [CascadeType.MERGE])
    @JoinColumn(referencedColumnName = "id", name = "id_pet")
    var pet: Pet? = null,

    var date: LocalDate? = null,

    var hour: LocalTime? = null,

    var notificationDate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    var type: TypeOfNotification? = null
) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

}