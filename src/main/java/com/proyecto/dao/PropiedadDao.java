package com.proyecto.dao;

import com.proyecto.domain.Propiedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropiedadDao extends  JpaRepository <Propiedad, Long>{
    
}
