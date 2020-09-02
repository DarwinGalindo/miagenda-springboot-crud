package com.example.miagenda.repository

import com.example.miagenda.model.Contacto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactoRepository : JpaRepository<Contacto, Int>