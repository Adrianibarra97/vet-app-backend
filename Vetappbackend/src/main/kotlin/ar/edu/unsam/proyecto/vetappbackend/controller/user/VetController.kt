package ar.edu.unsam.proyecto.vetappbackend.controller.user
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*

@RestController
@CrossOrigin("*")
@RequestMapping("/vet")
class VetController {

    @Autowired private lateinit var vetService: VetService

    @GetMapping("/get-all")
    fun getAll(): List<VetDTO> {
        return this.vetService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idVet: Int): VetDTO {
        return this.vetService.getOneById(idVet).toDTO()
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam idVet: Int) {
        val vetForDelete: Vet = this.vetService.getOneById(idVet)
        this.vetService.delete(vetForDelete)
    }

    @PostMapping("/create")
    fun create(@RequestBody vetDTO: VetDTO) {
        this.vetService.create(vetDTO.fromJSON())
    }

    @PutMapping("/update")
    fun update(@RequestBody vetDTO: VetDTO) {
        this.vetService.update(vetDTO.fromJSON())
    }

}