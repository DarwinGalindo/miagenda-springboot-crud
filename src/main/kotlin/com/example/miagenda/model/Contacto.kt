package com.example.miagenda.model

import com.example.miagenda.model.enums.Sexo
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "contactos")
data class Contacto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idcontacto")
        val id: Int? = null,

        @Column(name = "nombres")
        var nombres: String,

        @Column(name = "apellidos")
        var apellidos: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "sexo")
        var sexo: Sexo,

        @Column(name = "fechanac")
        var fechaNacimiento: LocalDate? = null,

        @Column(name = "telefono")
        var telefono: String? = null,

        @Column(name = "celular")
        var celular: String? = null,

        @Column(name = "email")
        var email: String? = null,

        @Column(name = "direccion")
        var direccion: String? = null,

        @Transient
        var nombreCompleto: String? = null
) {
    @PostLoad
    fun postLoad() {
        nombreCompleto = "$nombres $apellidos"
    }
}