package ar.edu.unsam.proyecto.vetappbackend.service.user
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import ar.edu.unsam.proyecto.vetappbackend.error.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import ar.edu.unsam.proyecto.vetappbackend.repository.user.*

@Service
class VetService: BaseService<Vet> {

    @Autowired lateinit var vetRepository: VetRepository

    @Autowired private lateinit var locationInfoService: LocationInfoService

    @Autowired private lateinit var authCredentialsService: AuthCredentialsService

    override fun getOneById(idVet: Int): Vet {
        return this.vetRepository.findById(idVet).orElseThrow {
            NotFoundException("No se encontró al veterinario indicado: $idVet")
        }
    }

    override fun getAll(): List<Vet> { return this.vetRepository.findAll().toList() }

    override fun delete(vet: Vet) { this.vetRepository.delete(vet) }

    override fun create(vet: Vet) { this.vetRepository.save(vet) }

    @Transactional
    override fun update(vet: Vet) { this.vetRepository.save(vet) }

}