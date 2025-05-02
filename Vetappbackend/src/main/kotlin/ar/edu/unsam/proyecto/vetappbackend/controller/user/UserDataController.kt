package ar.edu.unsam.proyecto.vetappbackend.controller.user

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*

@RestController
@CrossOrigin("*")
@RequestMapping("/user-data")
class UserDataController {

    @Autowired private lateinit var userDataService: UserDataService

    @PostMapping("/login")
    fun loginUser(@RequestBody userLoginDTO: UserDataLoginDTO): UserDataResponseDTO {
        return userDataService.login(userLoginDTO)
    }

    @GetMapping("/get-all")
    fun getAll(): List<UserDataDTO> {
        return this.userDataService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idUserData: Int): UserDataDTO {
        return this.userDataService.getOneById(idUserData).toDTO()
    }

    @PostMapping("/create-vet")
    fun create(@RequestBody userDataDTO: UserDataDTO) {
        this.userDataService.create(userDataDTO.fromJSON())
    }

    @PutMapping("update-vet")
    fun update(@RequestBody userDataDTO: UserDataDTO) {
        this.userDataService.update(userDataDTO.fromJSON())
    }

    @DeleteMapping("delete-vet")
    fun delete(@RequestParam idUserData: Int) {
        val vetForUserData: UserData = this.userDataService.getOneById(idUserData)
        this.userDataService.delete(vetForUserData)
    }

}