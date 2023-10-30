package com.proyecto.service;

import com.proyecto.domain.Favorito;
import java.util.List;

public interface FavoritoService {
    
    public List<Favorito> getFavoritos();
    
    public Favorito getFavorito(Favorito favorito);
    
    public void save(Favorito favorito);
    
    public void delete(Favorito favorito);
}