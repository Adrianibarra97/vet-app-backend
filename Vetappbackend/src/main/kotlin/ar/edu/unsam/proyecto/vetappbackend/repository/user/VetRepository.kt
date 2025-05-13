package ar.edu.unsam.proyecto.vetappbackend.repository.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.Vet
import org.springframework.data.repository.CrudRepository

interface VetRepository : CrudRepository<Vet, Int>