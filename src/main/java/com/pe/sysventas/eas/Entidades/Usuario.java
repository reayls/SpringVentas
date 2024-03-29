/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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
    @JoinColumn(name="fk_personal", referencedColumnName = "idpersonal")
    private Personal personal;
        
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="rol_user", 
            joinColumns=@JoinColumn(name="fk_user"), 
            inverseJoinColumns = @JoinColumn(name="fk_rol") )
    private Set<Rol> roles;   

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    } 

}
