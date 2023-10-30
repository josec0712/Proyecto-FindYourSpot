package com.proyecto.service;

import com.proyecto.domain.Propiedad;
import java.util.List;

public interface PropiedadService {
    
    public List<Propiedad> getPropiedades();
    
    public Propiedad getPropiedad(Propiedad propiedad);
    
    public void save(Propiedad propiedad);
    
    public void delete(Propiedad propiedad);
}