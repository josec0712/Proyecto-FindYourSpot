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
@Table(name="favorito")

public class Favorito implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_favorito")
    private long idFavorito;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="id_propiedad")
    Propiedad propiedad;

    public Favorito() {
    }
    
    
}

