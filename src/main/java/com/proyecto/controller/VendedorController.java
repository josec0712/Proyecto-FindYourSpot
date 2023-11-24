package com.proyecto.controller;

import com.proyecto.service.VendedorService;
import com.proyecto.domain.Vendedor;
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
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private VendedorService VendedorService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var vendedor = VendedorService.getVendedores();
        model.addAttribute("vendedor", vendedor);
        model.addAttribute("totalVendedores", vendedor.size());
        return "/vendedor/listado";
    }

    @GetMapping("/nuevo")
    public String vendedorNuevo(Vendedor vendedor) {
        return "/vendedor/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String vendedorGuardar(Vendedor vendedor,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            VendedorService.save(vendedor);
            vendedor.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "vendedor",
                            vendedor.getIdVendedor()));
        }
        VendedorService.save(vendedor);
        return "redirect:/vendedor/listado";
    }

    @GetMapping("/eliminar/{idVendedor}")
    public String vendedorEliminar(Vendedor vendedor) {
        VendedorService.delete(vendedor);
        return "redirect:/vendedor/listado";
    }

    @GetMapping("/modificar/{idVendedor}")
    public String vendedorModificar(Vendedor vendedor, Model model) {
        vendedor = VendedorService.getVendedor(vendedor);
        model.addAttribute("vendedor", vendedor);
        return "/vendedor/modifica";
    }
}
