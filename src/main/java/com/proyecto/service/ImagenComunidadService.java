package com.proyecto.service;

import com.proyecto.domain.ImagenComunidad;
import java.util.List;

public interface ImagenComunidadService {
    
    public List<ImagenComunidad> getImagenesComunidad();
    
    public ImagenComunidad getImagenComunidad(ImagenComunidad imagenComunidad);
    
    public void save(ImagenComunidad imagenComunidad);
    
    public void delete(ImagenComunidad imagenComunidad);
}