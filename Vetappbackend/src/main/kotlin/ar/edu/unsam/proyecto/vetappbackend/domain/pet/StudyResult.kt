package ar.edu.unsam.proyecto.vetappbackend.domain.pet
import ar.edu.unsam.proyecto.vetappbackend.domain.type.TypeOfStudyResult
import java.time.LocalDate
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "study_result")
class StudyResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    var date: LocalDate? = null

    var fileUrl: String? = null

    var description: String? = null

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    var type: TypeOfStudyResult? = null

    @ManyToOne @JoinColumn(referencedColumnName = "id", name = "id_pet")
    @OnDelete(action = OnDeleteAction.CASCADE)
    var pet: Pet? = null

}