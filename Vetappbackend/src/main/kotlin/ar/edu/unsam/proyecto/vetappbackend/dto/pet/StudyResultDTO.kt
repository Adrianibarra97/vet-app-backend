package ar.edu.unsam.proyecto.vetappbackend.dto.pet

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.*
import java.time.LocalDate

data class StudyResultDTO(
    val id: Int,
    val medicalHistoryId: Int,
    val date: String,
    val fileUrl: String,
    val interpretation: String,
    val type: String

)

fun StudyResult.toDTO(): StudyResultDTO {
    return StudyResultDTO(
        id = this.id!!,
        medicalHistoryId = this.medicalHistory?.id!!,
        date = this.date.toString(),
        fileUrl = this.fileUrl!!,
        interpretation = this.interpretation!!,
        type = this.type!!.name

    )
}

fun StudyResultDTO.fromJSON(studyResultDTO: StudyResultDTO, medicalHistory: MedicalHistory): StudyResult {
    val studyResult = StudyResult()
    studyResult.id = studyResultDTO.id
    studyResult.medicalHistory = medicalHistory
    studyResult.date = studyResultDTO.date.let { LocalDate.parse(it) }
    studyResult.fileUrl = studyResultDTO.fileUrl
    studyResult.interpretation = studyResultDTO.interpretation
    studyResult.type = TypeOfStudyResult.valueOf(studyResultDTO.type)

    return studyResult
}