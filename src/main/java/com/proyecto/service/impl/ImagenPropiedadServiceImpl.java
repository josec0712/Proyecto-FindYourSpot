package com.proyecto.service.impl;

import com.proyecto.dao.ImagenPropiedadDao;
import com.proyecto.domain.ImagenPropiedad;
import com.proyecto.service.ImagenPropiedadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenPropiedadServiceImpl implements ImagenPropiedadService {

    @Autowired
    private ImagenPropiedadDao imagenPropiedadDao;

    @Override
    @Transactional(readOnly = true)
    public List<ImagenPropiedad> getImagenesPropiedad(){
        var list=imagenPropiedadDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ImagenPropiedad getImagenPropiedad(ImagenPropiedad imagenPropiedad) {
        return imagenPropiedadDao.findById(imagenPropiedad.getIdImagenPropiedad()).orElse(null);
    }

    @Override
    @Transactional
    public void save(ImagenPropiedad imagenPropiedad) {
        imagenPropiedadDao.save(imagenPropiedad);
    }

    @Override
    @Transactional
    public void delete(ImagenPropiedad imagenPropiedad) {
        imagenPropiedadDao.delete(imagenPropiedad);
    }
}
