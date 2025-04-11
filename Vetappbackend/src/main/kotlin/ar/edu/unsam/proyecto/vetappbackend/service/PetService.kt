package ar.edu.unsam.proyecto.vetappbackend.service

import ar.edu.unsam.proyecto.vetappbackend.domain.Pet
import ar.edu.unsam.proyecto.vetappbackend.repository.PetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class PetService: BaseService<Pet> {

    @Autowired
    lateinit var petRepository: PetRepository


    override fun getAll(): List<Pet> =
        this.petRepository.getAll().toList()

    override fun getOneById(objectId: Int): Pet {
        TODO("Not yet implemented")
    }

    override fun delete(anObject: Pet) {
        TODO("Not yet implemented")
    }

    override fun update(anObject: Pet) {
        TODO("Not yet implemented")
    }

    override fun create(anObject: Pet) {
        TODO("Not yet implemented")
    }


    fun getAllByName(name: String): List<Pet> =
        this.petRepository.getAllByName(name)

    fun getAllByShiftToday(date: LocalDate): List<Pet> {
        return petRepository.getAllByShiftToday(date)
    }
}