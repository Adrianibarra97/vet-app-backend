package ar.edu.unsam.proyecto.vetappbackend.service.user

import ar.edu.unsam.proyecto.vetappbackend.domain.user.LocationInfo
import ar.edu.unsam.proyecto.vetappbackend.error.NotFoundException
import ar.edu.unsam.proyecto.vetappbackend.repository.user.LocationInfoRepository
import ar.edu.unsam.proyecto.vetappbackend.service.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationInfoService: BaseService<LocationInfo> {

    @Autowired lateinit var locationInfoRepository: LocationInfoRepository

    override fun getAll(): List<LocationInfo> {
        return this.locationInfoRepository.findAll().toList()
    }

    override fun getOneById(idLocationInfo: Int): LocationInfo {
        return this.locationInfoRepository.findById(idLocationInfo).orElseThrow {
            NotFoundException("No se encontró al usuario indicado: $idLocationInfo")
        }
    }

    override fun create(locationInfo: LocationInfo) {
        this.locationInfoRepository.save(locationInfo)
    }

    override fun update(locationInfo: LocationInfo) {
        this.locationInfoRepository.save(locationInfo)
    }

    override fun delete(locationInfo: LocationInfo) {
        this.locationInfoRepository.delete(locationInfo)
    }

}