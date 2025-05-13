package ar.edu.unsam.proyecto.vetappbackend.dto.pet
import java.time.LocalDate
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import ar.edu.unsam.proyecto.vetappbackend.domain.type.*

data class StudyResultDTO(
    val id: Int,
    val date: String,
    val fileUrl: String,
    val description: String,
    val type: String
)

fun StudyResult.toDTO(): StudyResultDTO {
    return StudyResultDTO(
        id = this.id!!,
        date = this.date.toString(),
        fileUrl = this.fileUrl!!,
        description = this.description!!,
        type = this.type!!.name
    )
}

fun StudyResultDTO.fromJSON(currentPet: Pet): StudyResult {
    val studyResultDTO = this
    return StudyResult().apply {
        id = studyResultDTO.id
        date = LocalDate.parse(studyResultDTO.date.toString())
        fileUrl = studyResultDTO.fileUrl
        description = studyResultDTO.description
        type = TypeOfStudyResult.valueOf(studyResultDTO.type.toString())
        pet = currentPet
    }
}