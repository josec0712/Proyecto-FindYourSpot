package com.proyecto.service;

import com.proyecto.domain.Comunidad;
import java.util.List;

public interface ComunidadService {
    
    public List<Comunidad> getComunidades();
    
    public Comunidad getComunidad(Comunidad comunidad);
    
    public void save(Comunidad comunidad);
    
    public void delete(Comunidad comunidad);
}