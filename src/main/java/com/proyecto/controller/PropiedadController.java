package com.proyecto.controller;

import com.proyecto.service.PropiedadService;
import com.proyecto.domain.Propiedad;
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
@RequestMapping("/propiedad")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var propiedades = propiedadService.getPropiedades();
        model.addAttribute("propiedades", propiedades);
        model.addAttribute("totalPropiedads", propiedades.size());
        return "/propiedad/listado";
    }

    @GetMapping("/nuevo")
    public String propiedadNuevo(Propiedad propiedad) {
        return "/propiedad/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String propiedadGuardar(Propiedad propiedad,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            propiedadService.save(propiedad);
            propiedad.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "propiedad",
                            propiedad.getIdPropiedad()));
        }
        propiedadService.save(propiedad);
        return "redirect:/propiedad/listado";
    }

    @GetMapping("/eliminar/{idPropiedad}")
    public String propiedadEliminar(Propiedad propiedad) {
        propiedadService.delete(propiedad);
        return "redirect:/propiedad/listado";
    }

    @GetMapping("/modificar/{idPropiedad}")
    public String propiedadModificar(Propiedad propiedad, Model model) {
        propiedad = propiedadService.getPropiedad(propiedad);
        model.addAttribute("propiedad", propiedad);
        return "/propiedad/modifica";
    }
}
