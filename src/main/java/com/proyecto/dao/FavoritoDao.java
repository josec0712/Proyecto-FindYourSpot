package com.proyecto.dao;

import com.proyecto.domain.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoDao extends  JpaRepository <Favorito, Long>{
    
}
