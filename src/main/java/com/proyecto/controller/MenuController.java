package com.proyecto.controller;

import com.proyecto.domain.Propiedad;
import com.proyecto.service.PropiedadService;
import com.proyecto.domain.Comunidad;
import com.proyecto.service.ComunidadService;
import com.proyecto.domain.Vendedor;
import com.proyecto.service.VendedorService;
import com.proyecto.domain.Usuario;
import com.proyecto.service.UsuarioService;
import com.proyecto.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/Menu")
public class MenuController {
    
    @Autowired
    private PropiedadService propiedadService;
    @Autowired
    private ComunidadService comunidadService;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var usuarios = usuarioService.getUsuarios();
        var propiedades = propiedadService.getPropiedades();
        var comunidades = comunidadService.getComunidades();
       var vendedores = vendedorService.getVendedores();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("propiedades", propiedades);
        model.addAttribute("comunidades", comunidades);
        model.addAttribute("vendedores", vendedores);
        
        return "/pruebas/listado";
    }
     @GetMapping("/listado/{id_usuario}") 
     public String listado(Model model, Usuario usuario) {
         var nombre = usuarioService.getUsuario(usuario).getNombre();  
         var apellidos = usuarioService.getUsuario(usuario).getApellidos();      
         model.addAttribute("nombre", nombre);  
         //model.addAttribute("totalProductos", productos.size());   
         model.addAttribute("apellidos", apellidos);  
         return "/pruebas/listado";    }}
  