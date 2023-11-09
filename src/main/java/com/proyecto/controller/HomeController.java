import com.proyecto.domain.Usuario;
import com.proyecto.service.UsuarioService;
import com.proyecto.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        System.out.println("Hola");
        return "index"; // Return the name of your index.html template
    }
    
        @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;
    
    /**
     *
     * @param usuario
     * @param imagenFile
     * @param model
     * @return 
     */
    @PostMapping("/registrar")
    public String UsuarioRegistrar(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile, Model model) {
        try {
            if (!imagenFile.isEmpty()) {
                usuarioService.save(usuario);
                usuario.setRutaImagen(
                        firebaseStorageService.cargaImagen(
                                imagenFile,
                                "usuario",
                                usuario.getIdUsuario()));
            }
            usuarioService.save(usuario);

            model.addAttribute("message", "User registered successfully");
            return "redirect:/"; // Replace with the actual form view
        } catch (Exception e) {
            // Set error message
            model.addAttribute("error", "Error registering user: " + e.getMessage());
            return "redirect:/"; // Replace with the actual form view
        }
    }
}