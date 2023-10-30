package com.proyecto.service;

import com.proyecto.domain.ImagenPropiedad;
import java.util.List;

public interface ImagenPropiedadService {
    
    public List<ImagenPropiedad> getImagenesPropiedad();
    
    public ImagenPropiedad getImagenPropiedad(ImagenPropiedad imagenPropiedad);
    
    public void save(ImagenPropiedad imagenPropiedad);
    
    public void delete(ImagenPropiedad imagenPropiedad);
}