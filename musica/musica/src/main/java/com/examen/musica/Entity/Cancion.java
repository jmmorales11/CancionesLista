package com.examen.musica.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "cancion")
@Entity
public class Cancion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cancion")
    private long id;

    @NotEmpty(message = "El titulo no puede estar vacío")
    @Column(name = "titulo", nullable = false)
    private String titulo;

    @NotEmpty(message = "El nombre del artista no puede estar vacío")
    @Column(name = "artista", nullable = false)
    private String artista;

    @NotEmpty(message = "El genero no puede estar vacío")
    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "creacion", nullable = false)
    private LocalDateTime creacion;

    public long getId() {
        return id;
    }

    public @NotEmpty(message = "El genero no puede estar vacío") String getGenero() {
        return genero;
    }

    public void setGenero(@NotEmpty(message = "El genero no puede estar vacío") String genero) {
        this.genero = genero;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotEmpty(message = "El titulo no puede estar vacío") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotEmpty(message = "El titulo no puede estar vacío") String titulo) {
        this.titulo = titulo;
    }

    public @NotEmpty(message = "El nombre del artista no puede estar vacío") String getArtista() {
        return artista;
    }

    public void setArtista(@NotEmpty(message = "El nombre del artista no puede estar vacío") String artista) {
        this.artista = artista;
    }



    public LocalDateTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalDateTime creacion) {
        this.creacion = creacion;
    }
}
