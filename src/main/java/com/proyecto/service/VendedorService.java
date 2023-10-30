package com.proyecto.service;

import com.proyecto.domain.Vendedor;
import java.util.List;

public interface VendedorService {
    
    public List<Vendedor> getVendedores();
    
    public Vendedor getVendedor(Vendedor vendedor);
    
    public void save(Vendedor vendedor);
    
    public void delete(Vendedor vendedor);
}