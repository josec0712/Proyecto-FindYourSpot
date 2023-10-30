package com.proyecto.dao;

import com.proyecto.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioDao extends  JpaRepository <Comentario, Long>{
    
}
