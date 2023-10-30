package com.proyecto.service;

import com.proyecto.domain.Comentario;
import java.util.List;

public interface ComentarioService {
    
    public List<Comentario> getComentarios();
    
    public Comentario getComentario(Comentario comentario);
    
    public void save(Comentario comentario);
    
    public void delete(Comentario comentario);
}