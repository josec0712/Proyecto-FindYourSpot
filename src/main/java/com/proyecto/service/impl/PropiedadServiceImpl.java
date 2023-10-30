package com.proyecto.service.impl;

import com.proyecto.dao.PropiedadDao;
import com.proyecto.domain.Propiedad;
import com.proyecto.service.PropiedadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PropiedadServiceImpl implements PropiedadService {

    @Autowired
    private PropiedadDao propiedadDao;

    @Override
    @Transactional(readOnly = true)
    public List<Propiedad> getPropiedades(){
        var list=propiedadDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Propiedad getPropiedad(Propiedad propiedad) {
        return propiedadDao.findById(propiedad.getIdPropiedad()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Propiedad propiedad) {
        propiedadDao.save(propiedad);
    }

    @Override
    @Transactional
    public void delete(Propiedad propiedad) {
        propiedadDao.delete(propiedad);
    }
}
