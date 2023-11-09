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
@RequestMapping("/admin")
public class AdminController {
    
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
//        var propiedades = propiedadService.getPropiedades();
//        var comunidades = comunidadService.getComunidades();
//        var vendedores = vendedorService.getVendedores();
        model.addAttribute("usuarios", usuarios);
//        model.addAttribute("propiedades", propiedades);
//        model.addAttribute("comunidades", comunidades);
//        model.addAttribute("vendedores", vendedores);
        
        return "admin/listado";
    }
    
    @GetMapping("/nuevo/usuario")
    public String usuarioNuevo(Usuario usuario) {
        return "/admin/usuario/modifica";
    }
    @GetMapping("/nuevo/propiedad")
    public String propiedadesNuevo(Propiedad propiedad) {
        return "/admin/propiedad/modifica";
    }
    @GetMapping("/nuevo/comunidad")
    public String comunidadesNuevo(Comunidad comunidad) {
        return "/admin/comunidad/modifica";
    }
    @GetMapping("/nuevo/vendedor")
    public String vendedoresNuevo(Vendedor vendedor) {
        return "/admin/vendedor/modifica";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    @PostMapping("/guardar/usuario")
    public String usuarioGuardar(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {        
        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario);
            usuario.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile, 
                            "usuario", 
                            usuario.getIdUsuario()));
        }
        usuarioService.save(usuario);
        return "redirect:/admin/listado";
    }
    @PostMapping("/guardar/propiedad")
    public String propiedadGuardar(Propiedad propiedad) {        
        propiedadService.save(propiedad);
        return "redirect:/admin/listado";
    }
    @PostMapping("/guardar/comunidad")
    public String comunidadGuardar(Comunidad comunidad) {        
        comunidadService.save(comunidad);
        return "redirect:/admin/listado";
    }
    @PostMapping("/guardar/vendedor")
    public String vendedorGuardar(Vendedor vendedor) {        
        vendedorService.save(vendedor);
        return "redirect:/admin/listado";
    }
    
    
    @GetMapping("/usuario/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/admin/listado";
    }
    @GetMapping("/eliminar/propiedad/{idPropiedad}")
    public String propiedadEliminar(Propiedad propiedad) {
        propiedadService.delete(propiedad);
        return "redirect:/admin/listado";
    }
    @GetMapping("/eliminar/comunidad/{idComunidad}")
    public String comunidadEliminar(Comunidad comunidad) {
        comunidadService.delete(comunidad);
        return "redirect:/admin/listado";
    }
    @GetMapping("/eliminar/vendedor/{idVendedor}")
    public String vendedorEliminar(Vendedor vendedor) {
        vendedorService.delete(vendedor);
        return "redirect:/admin/listado";
    }

    @GetMapping("/vendedor/modifica/{idVendedor}")
    public String vendedorModificar(Vendedor vendedor, Model model) {
        vendedor = vendedorService.getVendedor(vendedor);
        model.addAttribute("vendedor", vendedor);
        return "/admin/vendedor/modifica";
    }
    @GetMapping("/propiedad/modifica/{idPropiedad}")
    public String propiedadModificar(Propiedad propiedad, Model model) {
        propiedad = propiedadService.getPropiedad(propiedad);
        model.addAttribute("propiedad", propiedad);
        return "/admin/propiedad/modifica";
    }
    @GetMapping("/comunidad/modifica/{idComunidad}")
    public String comunidadModificar(Comunidad comunidad, Model model) {
        comunidad = comunidadService.getComunidad(comunidad);
        model.addAttribute("comunidad", comunidad);
        return "/admin/comunidad/modifica";
    }
    @GetMapping("/usuario/modifica/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/admin/usuario/modifica";
    }
}