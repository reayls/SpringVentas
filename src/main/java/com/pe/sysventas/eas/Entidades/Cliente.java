/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.Entidades;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author Deary
 */
@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcliente")
    @JsonView({Views.Clientes.class})
    private long id;
    
    @Column(name="nombre")
    @JsonView({Views.Clientes.class})
    private String nombre;
    
    @Column(name="apellido")
    @JsonView({Views.Clientes.class})
    private String apellido;
    
    @Column(name="direccion")
    @JsonView({Views.Clientes.class})
    private String direccion;
    
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "cliente")
    @JsonView({Views.ClientesVentas.class})
    private List<Venta> ventas;
    
}
