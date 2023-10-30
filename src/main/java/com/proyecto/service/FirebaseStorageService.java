package com.proyecto.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    final String BucketName = "findyourspot-4670f";

    final String rutaSuperiorStorage = "findyourspot";

    final String rutaJsonFile = "firebase";
    
    final String archivoJsonFile = "findyourspot-4670f-firebase-adminsdk-51z43-40666655ad.json";
}