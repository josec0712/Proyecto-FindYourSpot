package com.proyecto.controller;

import com.proyecto.domain.Favorito;
import com.proyecto.domain.Propiedad;
import com.proyecto.service.ComunidadService;
import com.proyecto.service.FavoritoService;
import com.proyecto.service.ImagenPropiedadService;
import com.proyecto.service.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ComunidadService comunidadService;
    
    @Autowired
    private FavoritoService favoritoService;
    
    @Autowired
    private PropiedadService propiedadService;

    @GetMapping("/info")
    public String listado(Model model) {
        var favoritos = favoritoService.getFavoritos();
        var propiedades = propiedadService.getPropiedades();
        Propiedad PropiedadMasPopular = null;
        long maxValue = 0;
        
        for(Favorito favorito : favoritos){
            if(favoritos.stream().filter(f -> f.getPropiedad().getNombre().equals(favorito.getPropiedad().getNombre())).count() >= maxValue){
                maxValue = favoritos.stream().filter(f -> f.getPropiedad().getNombre().equals(favorito.getPropiedad().getNombre())).count();
                PropiedadMasPopular = favorito.getPropiedad();
            }
        }
        
        model.addAttribute("PropiedadMasPopular", PropiedadMasPopular);
        model.addAttribute("propiedades", propiedades);
        return "/home/info";
    }
    
    @GetMapping("/ver/{idPropiedad}")
    public String verPropiedad(Propiedad propiedad, Model model) {
        propiedad = propiedadService.getPropiedad(propiedad);
        model.addAttribute("propiedad", propiedad);
        return "/home/ver";
    }
    
}