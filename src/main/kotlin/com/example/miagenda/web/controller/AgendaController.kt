package com.example.miagenda.web.controller

import com.example.miagenda.repository.ContactoRepository
import com.example.miagenda.web.form.ContactoForm
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
class AgendaController(
        private val contactoRepository: ContactoRepository
) {

    @GetMapping("")
    fun index(): ModelAndView {
        val contactos = contactoRepository
                .findAll(Sort.by("nombres", "apellidos"))

        return ModelAndView("index")
                .addObject("contactos", contactos)
    }

    @GetMapping("contactos/{id}")
    fun ver(@PathVariable id: Int): ModelAndView {
        val contacto = contactoRepository
                .getOne(id)

        return ModelAndView("ver")
                .addObject("contacto", contacto)
    }

    @GetMapping("contactos/nuevo")
    fun nuevo(): ModelAndView {
        val contactoForm = ContactoForm()

        return ModelAndView("form")
                .addObject("contactoForm", contactoForm)
    }

    @PostMapping("contactos/nuevo")
    fun crear(@Valid contactoForm: ContactoForm,
              bindingResult: BindingResult,
              redirectAttributes: RedirectAttributes
    ): ModelAndView {
        if (bindingResult.hasErrors()) {
            return ModelAndView("form")
                    .addObject("contactoForm", contactoForm)
        }
        val contacto = contactoForm.toContacto()

        contactoRepository.save(contacto)
        redirectAttributes.addFlashAttribute("success", "El nuevo contacto ha sido creado.")

        return ModelAndView("redirect:/")
    }

    @GetMapping("contactos/{id}/editar")
    fun editar(@PathVariable id: Int): ModelAndView {
        val contacto = contactoRepository
                .getOne(id)

        val contactoForm = ContactoForm(contacto)

        return ModelAndView("form")
                .addObject("contactoForm", contactoForm)
                .addObject(contacto)
    }

    @PostMapping("contactos/{id}/editar")
    fun actualizar(
            @PathVariable id: Int,
            @Valid contactoForm: ContactoForm,
            bindingResult: BindingResult,
            redirectAttributes: RedirectAttributes
    ): ModelAndView {
        if (bindingResult.hasErrors()) {
            return ModelAndView("form")
                    .addObject("contactoForm", contactoForm)
        }
        val contacto = contactoRepository
                .getOne(id)

        contactoForm.updateContacto(contacto)
        contactoRepository.save(contacto)
        redirectAttributes.addFlashAttribute("success", "El contacto ha sido actualizado.")

        return ModelAndView("redirect:/")
    }

    @PostMapping("contactos/{id}/eliminar")
    fun eliminar(@PathVariable id: Int, redirectAttributes: RedirectAttributes): ModelAndView {
        val contacto = contactoRepository
                .getOne(id)

        contactoRepository.delete(contacto)
        redirectAttributes.addFlashAttribute("success", "El contacto ha sido eliminado.")

        return ModelAndView("redirect:/")
    }

}