package com.proyecto.service;

import java.io.IOException;
import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author jafet
 */
public interface ReporteService {

    public ResponseEntity<Resource>
            generarReporte(
                    String reporte,
                    Map<String, Object> parametros,
                    String tipo)throws IOException;
    
}
