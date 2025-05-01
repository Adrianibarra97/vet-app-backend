package ar.edu.unsam.proyecto.vetappbackend.service.pet

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.domain.pet.StudyResult
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.pet.StudyResultRepository

@Service
class StudyResultService: BaseService<StudyResult> {

    @Autowired lateinit var studyResultRepository: StudyResultRepository

    override fun getOneById(studyResultId: Int): StudyResult {
        return this.studyResultRepository.findById(studyResultId).orElseThrow {
            NotFoundException("No se encontró el estudio indicado: $studyResultId")
        }
    }

    override fun getAll(): List<StudyResult> {
        return this.studyResultRepository.findAll().toList()
    }

    override fun create(newStudyResult: StudyResult) {
        this.studyResultRepository.save(newStudyResult)
    }

    override fun delete(studyResult: StudyResult) {
        this.studyResultRepository.delete(studyResult)
    }

    override fun update(studyResultUpdate: StudyResult) {
        this.getOneById(studyResultUpdate.id!!)
        this.studyResultRepository.save(studyResultUpdate)
    }

}