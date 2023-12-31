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
@Table(name="comentario")

public class Comentario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_comentario")
    private long idComentario;
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name="id_comunidad")
    Comunidad comunidad;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    Usuario usuario;

    public Comentario() {
    }
    
    
}

