package com.example.miagenda.web.form

import com.example.miagenda.model.Contacto
import com.example.miagenda.model.enums.Sexo
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.*

data class ContactoForm(
        @field:NotBlank
        @field:Size(max = 50)
        val nombres: String? = null,

        @field:NotBlank
        @field:Size(max = 50)
        val apellidos: String? = null,

        @field:NotNull
        val sexo: Sexo? = null,

        @field:Past
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        val fechaNacimiento: LocalDate? = null,

        @field:Size(max = 15)
        val telefono: String? = null,

        @field:Size(max = 15)
        val celular: String? = null,

        @field:Email
        @field:Size(max = 50)
        val email: String? = null,

        @field:Size(max = 100)
        val direccion: String? = null
) {

    constructor(contacto: Contacto): this(
            nombres = contacto.nombres,
            apellidos = contacto.apellidos,
            sexo = contacto.sexo,
            fechaNacimiento = contacto.fechaNacimiento,
            telefono = contacto.telefono,
            celular = contacto.celular,
            email = contacto.email,
            direccion = contacto.direccion
    )

    fun toContacto(): Contacto {
        return Contacto(
                nombres = nombres!!,
                apellidos = apellidos!!,
                sexo = sexo!!,
                fechaNacimiento = fechaNacimiento,
                telefono = telefono,
                celular = celular,
                email = email,
                direccion = direccion
        )
    }

    fun updateContacto(contacto: Contacto) {
        val form = this

        contacto.apply {
            nombres = form.nombres!!
            apellidos = form.apellidos!!
            sexo = form.sexo!!
            fechaNacimiento = form.fechaNacimiento
            telefono = form.telefono
            celular = form.celular
            email = form.email
            direccion = form.direccion
        }
    }
}