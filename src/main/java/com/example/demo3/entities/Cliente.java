package com.example.demo3.entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="cedula")
    private Long document;

    @Column(name="nombre")
    private String name;

    @Column(name="email")
    private String email;

    public Cliente(String name, Long document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
    }

    public Cliente() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", document=" + document +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}