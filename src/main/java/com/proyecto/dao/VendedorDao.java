package com.proyecto.dao;

import com.proyecto.domain.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorDao extends  JpaRepository <Vendedor, Long>{
    
}
