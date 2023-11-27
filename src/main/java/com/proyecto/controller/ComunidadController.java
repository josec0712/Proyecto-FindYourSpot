package com.proyecto.controller;

import com.proyecto.service.ComunidadService;
import com.proyecto.domain.Comunidad;
import com.proyecto.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/comunidad")
public class ComunidadController {

    @Autowired
    private ComunidadService comunidadService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var comunidades = comunidadService.getComunidades();
        model.addAttribute("comunidades", comunidades);
        model.addAttribute("totalComunidads", comunidades.size());
        return "/comunidad/listado";
    }

    @GetMapping("/nuevo")
    public String comunidadNuevo(Comunidad comunidad) {
        return "/comunidad/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String comunidadGuardar(Comunidad comunidad,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            comunidadService.save(comunidad);
            comunidad.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "comunidad",
                            comunidad.getIdComunidad()));
        }
        comunidadService.save(comunidad);
        return "redirect:/comunidad/listado";
    }

    @GetMapping("/eliminar/{idComunidad}")
    public String comunidadEliminar(Comunidad comunidad) {
        comunidadService.delete(comunidad);
        return "redirect:/comunidad/listado";
    }

    @GetMapping("/modificar/{idComunidad}")
    public String comunidadModificar(Comunidad comunidad, Model model) {
        comunidad = comunidadService.getComunidad(comunidad);
        model.addAttribute("comunidad", comunidad);
        return "/comunidad/modifica";
    }
}
