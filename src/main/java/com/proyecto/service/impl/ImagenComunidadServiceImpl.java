package com.proyecto.service.impl;

import com.proyecto.dao.ImagenComunidadDao;
import com.proyecto.domain.ImagenComunidad;
import com.proyecto.service.ImagenComunidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenComunidadServiceImpl implements ImagenComunidadService {

    @Autowired
    private ImagenComunidadDao imagenComunidadDao;

    @Override
    @Transactional(readOnly = true)
    public List<ImagenComunidad> getImagenesComunidad(){
        var list=imagenComunidadDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public ImagenComunidad getImagenComunidad(ImagenComunidad imagenComunidad) {
        return imagenComunidadDao.findById(imagenComunidad.getIdImagenComunidad()).orElse(null);
    }

    @Override
    @Transactional
    public void save(ImagenComunidad imagenComunidad) {
        imagenComunidadDao.save(imagenComunidad);
    }

    @Override
    @Transactional
    public void delete(ImagenComunidad imagenComunidad) {
        imagenComunidadDao.delete(imagenComunidad);
    }
}
