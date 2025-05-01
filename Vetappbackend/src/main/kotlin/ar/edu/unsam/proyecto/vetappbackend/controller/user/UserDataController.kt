package ar.edu.unsam.proyecto.vetappbackend.controller.user

import ar.edu.unsam.proyecto.vetappbackend.service.user.PetOwnerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin("*")
@RequestMapping("/user-data")
class UserDataController {
    @Autowired private lateinit var userDataService: UserDataService

}