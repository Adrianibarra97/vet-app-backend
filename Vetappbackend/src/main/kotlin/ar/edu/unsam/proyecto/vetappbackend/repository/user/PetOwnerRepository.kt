package ar.edu.unsam.proyecto.vetappbackend.repository.user

import org.springframework.data.repository.CrudRepository
import ar.edu.unsam.proyecto.vetappbackend.domain.user.PetOwner

interface PetOwnerRepository : CrudRepository<PetOwner, Int>