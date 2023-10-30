package com.proyecto.service.impl;

import com.proyecto.dao.VendedorDao;
import com.proyecto.domain.Vendedor;
import com.proyecto.service.VendedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    private VendedorDao vendedorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vendedor> getVendedores(){
        var list=vendedorDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Vendedor getVendedor(Vendedor vendedor) {
        return vendedorDao.findById(vendedor.getIdVendedor()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Vendedor vendedor) {
        vendedorDao.save(vendedor);
    }

    @Override
    @Transactional
    public void delete(Vendedor vendedor) {
        vendedorDao.delete(vendedor);
    }
}
