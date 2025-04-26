package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.pet.StudyResult
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.StudyResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudyResultService: BaseService<StudyResult> {

    @Autowired
    lateinit var studyResultRepository: StudyResultRepository

    override fun getAll(): List<StudyResult> {
        return this.studyResultRepository.findAll().toList()
    }

    override fun getOneById(studyResultId: Int): StudyResult {
        return this.studyResultRepository.findById(studyResultId).orElseThrow {
            NotFoundException("No se encontró el estudio indicado: $studyResultId")
        }
    }

    override fun create(newStudyResult: StudyResult) {
        this.studyResultRepository.save(newStudyResult)
    }

    override fun delete(studyResult: StudyResult) {
        this.studyResultRepository.delete(studyResult)
    }

    override fun update(studyResultUpdate: StudyResult) {
        val studyResult: StudyResult = this.getOneById(studyResultUpdate.id!!)
        studyResult.apply {
            this.id = studyResultUpdate.id
            this.medicalHistory = studyResultUpdate.medicalHistory
            this.date = studyResultUpdate.date
            this.fileUrl = studyResultUpdate.fileUrl
            this.interpretation = studyResultUpdate.interpretation
            this.type = studyResultUpdate.type
        }
        this.studyResultRepository.save(studyResult)
    }
}