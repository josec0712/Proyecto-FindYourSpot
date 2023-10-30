/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="propiedad")

public class Propiedad implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_propiedad")
    private long idPropiedad;
    private long idVendedor;
    private long idComunidad;
    private String nombre;
    private String descripcion;
    private float precio;
    private String tipoPropiedad;
    private String ubicacion;
    private Date fechaPublicacion;
    
    public Propiedad() {
    }
    
    
}

