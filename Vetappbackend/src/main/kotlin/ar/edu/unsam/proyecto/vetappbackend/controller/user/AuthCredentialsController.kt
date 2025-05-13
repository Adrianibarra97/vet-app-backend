package ar.edu.unsam.proyecto.vetappbackend.controller.user

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.*
import ar.edu.unsam.proyecto.vetappbackend.dto.user.*
import ar.edu.unsam.proyecto.vetappbackend.domain.user.*
import ar.edu.unsam.proyecto.vetappbackend.service.user.*

@RestController
@CrossOrigin("*")
@RequestMapping("/auth-credentials")
class AuthCredentialsController {

    @Autowired private lateinit var userDataService: UserDataService

    @Autowired private lateinit var authCredentialsService: AuthCredentialsService

    @PostMapping("/login")
    fun loginUser(@RequestBody authCredentialsLoginDTO: AuthCredentialsLoginDTO): AuthCredentialsResponseDTO {
        return authCredentialsService.login(authCredentialsLoginDTO)
    }

    @GetMapping("/get-all")
    fun getAll(): List<AuthCredentialsDTO> {
        return this.authCredentialsService.getAll().map { it.toDTO() }
    }

    @GetMapping("/get-one-by-id")
    fun getOneById(@RequestParam idAuthCredentials: Int): AuthCredentialsDTO {
        return this.authCredentialsService.getOneById(idAuthCredentials).toDTO()
    }

    @PutMapping("/update")
    fun update(@RequestBody authCredentialsDTO: AuthCredentialsDTO, @RequestParam idUserData: Int) {
        val userData: UserData = this.userDataService.getOneById(idUserData)
        val authCredentials = authCredentialsDTO.fromJSON(userData)
        authCredentialsService.update(authCredentials)
    }

}