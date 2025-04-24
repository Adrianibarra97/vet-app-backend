package ar.edu.unsam.proyecto.vetappbackend.domain.user
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.persistence.*

@Entity
@Table(name = "user_data")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
    JsonSubTypes.Type(value = PetOwner::class, name =  "PETOWNER"),
    JsonSubTypes.Type(value = Vet::class, name =  "VET")
)
@Inheritance(strategy= InheritanceType.JOINED)
abstract class UserData {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var name: String = ""
    var surname: String = ""
    var username: String = ""
    var password: String = ""
    var typeOfUser: TypeOfUser? = null
}

enum class TypeOfUser { PETOWNER, VET }