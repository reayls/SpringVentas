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
@Table(name="rol_user")
public class rol_user implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idrol_user")
    private long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_rol", referencedColumnName = "idrol")
    private Rol rol;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_user", referencedColumnName = "idusuario")
    private Usuario usuario;
}
