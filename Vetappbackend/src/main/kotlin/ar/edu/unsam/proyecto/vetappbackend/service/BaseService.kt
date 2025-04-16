package ar.edu.unsam.proyecto.vetappbackend.service

interface BaseService<T> {

    fun getAll(): List<T>

    fun getOneById(objectId: Int): T

    fun create(anObject: T)

    fun update(anObject: T)

    fun delete(anObject: T)
}