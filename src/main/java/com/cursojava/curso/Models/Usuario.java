package com.cursojava.curso.Models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
@ToString
public class Usuario {
    @Getter
    @Setter
    @Column(name="id")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Getter
    @Setter
    @Column(name="nombre")
    private String nombre;
    @Getter
    @Setter
    @Column(name="apellidos")
    private String apellidos;
    @Getter
    @Setter
    @Column(name="email")
    private String email;
    @Getter
    @Setter
    @Column(name="telefono")
    private String telefono;
    @Getter
    @Setter
    @Column(name="password")
    private String contrasenia;


}
