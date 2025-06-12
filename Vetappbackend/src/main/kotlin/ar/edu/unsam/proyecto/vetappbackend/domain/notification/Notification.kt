package ar.edu.unsam.proyecto.vetappbackend.domain.notification
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfNotification
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.*

@Entity
class Notification(

    var date: LocalDate? = null,

    var hour: LocalTime? = null,

    var subject: String? = null,

    var message: String? = null,

    var wasRead: Boolean = false,

    var notificationDate: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    var type: TypeOfNotification? = null,

    @ManyToOne (cascade = [CascadeType.MERGE])
    @JoinColumn(referencedColumnName = "id", name = "id_pet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var pet: Pet? = null,

    @ManyToOne (cascade = [CascadeType.MERGE])
    @JoinColumn(referencedColumnName = "id", name = "id_vet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var vet: Vet? = null,

    @ManyToOne (cascade = [CascadeType.MERGE])
    @JoinColumn(referencedColumnName = "id", name = "id_pet_owner")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var petOwner: PetOwner? = null

) {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

}