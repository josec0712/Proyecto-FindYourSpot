package com.proyecto.service.impl;

import com.proyecto.dao.ComunidadDao;
import com.proyecto.domain.Comunidad;
import com.proyecto.service.ComunidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComunidadServiceImpl implements ComunidadService {

    @Autowired
    private ComunidadDao comunidadDao;

    @Override
    @Transactional(readOnly = true)
    public List<Comunidad> getComunidades(){
        var list=comunidadDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Comunidad getComunidad(Comunidad comunidad) {
        return comunidadDao.findById(comunidad.getIdComunidad()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Comunidad comunidad) {
        comunidadDao.save(comunidad);
    }

    @Override
    @Transactional
    public void delete(Comunidad comunidad) {
        comunidadDao.delete(comunidad);
    }
}
