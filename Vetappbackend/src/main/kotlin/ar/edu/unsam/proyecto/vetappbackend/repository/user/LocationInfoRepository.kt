package ar.edu.unsam.proyecto.vetappbackend.repository.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.LocationInfo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationInfoRepository: CrudRepository<LocationInfo, Int> {
}
