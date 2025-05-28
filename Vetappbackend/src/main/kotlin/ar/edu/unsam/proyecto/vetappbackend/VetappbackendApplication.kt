package ar.edu.unsam.proyecto.vetappbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableScheduling
class VetappbackendApplication

fun main(args: Array<String>) {
    runApplication<VetappbackendApplication>(*args)
}
