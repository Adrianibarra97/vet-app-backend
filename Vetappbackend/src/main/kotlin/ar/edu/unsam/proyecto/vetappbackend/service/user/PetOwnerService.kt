package ar.edu.unsam.proyecto.vetappbackend.service.user

import jakarta.transaction.*
import ar.edu.unsam.proyecto.vetappbackend.service.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.PetOwnerRepository


@Service
class PetOwnerService : BaseService<PetOwner> {

    @Autowired lateinit var authCredentialsService: AuthCredentialsService

    @Autowired lateinit var petOwnerRepository: PetOwnerRepository

    override fun getOneById(idPetOwner: Int): PetOwner {
        return this.petOwnerRepository.findById(idPetOwner).orElseThrow {
            NotFoundException("No se encontró al petOwner indicado: $idPetOwner")
        }
    }

    override fun getAll(): List<PetOwner> {
        return this.petOwnerRepository.findAll().toList()
    }

    override fun delete(petOwner: PetOwner) {
        this.petOwnerRepository.delete(petOwner)
    }

    override fun create(petOwner: PetOwner) {
        this.authCredentialsService.verifyCreate(petOwner.authCredentials!!)
        this.petOwnerRepository.save(petOwner)
    }

    @Transactional
    override fun update(petOwner: PetOwner) {
        this.authCredentialsService.verifyUpdate(petOwner.authCredentials!!)
        this.petOwnerRepository.save(petOwner)
    }

}