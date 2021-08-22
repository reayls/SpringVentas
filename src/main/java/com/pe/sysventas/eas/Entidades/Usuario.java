/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Deary
 */
@Data
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuario")
     long id;
    
    @Column(name="email")
     String email;
    
    @Column(name="password")
     String password;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idcliente", referencedColumnName = "idcliente")
    private Cliente cliente;
}
