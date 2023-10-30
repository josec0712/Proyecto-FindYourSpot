package com.proyecto.service.impl;

import com.proyecto.dao.FavoritoDao;
import com.proyecto.domain.Favorito;
import com.proyecto.service.FavoritoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoDao favoritoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Favorito> getFavoritos(){
        var list=favoritoDao.findAll();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Favorito getFavorito(Favorito favorito) {
        return favoritoDao.findById(favorito.getIdFavorito()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Favorito favorito) {
        favoritoDao.save(favorito);
    }

    @Override
    @Transactional
    public void delete(Favorito favorito) {
        favoritoDao.delete(favorito);
    }
}
