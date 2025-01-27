package com.examen.musica.Entity;

import com.examen.musica.Cliente.ListaClientRest;
import com.examen.musica.Model.Canciones;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "lista_reproduccion")
@Entity
public class ListaReproduccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lista")
    private long id;

    @NotEmpty(message = "El nombre de la lista no puede estar vacío")
    @Size(max = 50, message = "El nombre de la lista no puede exceder los 50 caracteres")
    @Column(name = "nombre_lista", nullable = false)
    private String nombreLista;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
    @Column(name = "descripcion_lista")
    private String descripcionLista;

    @Column(name = "creacion_lista", nullable = false)
    private LocalDateTime creacionLista;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="lista_id")
    private List<ListaCanciones> listaCanciones;

    @Transient
    private List<Canciones> listaR;

    public ListaReproduccion() {
        listaR =new ArrayList<>();
        listaCanciones= new ArrayList<>();
    }
    public void addListaCanciones(ListaCanciones listacancion){
        listaCanciones.add(listacancion);
    }
    public void removeCourseUser(ListaCanciones listaCancion){
        listaCanciones.remove(listaCancion);
    }
    // Método para listar las canciones de la lista
    public List<ListaCanciones> listarCanciones() {
        return listaCanciones;
    }
    // Método para listar las listas de reproducción que contienen una canción específica
    public List<Canciones> obtenerCanciones(ListaClientRest clientRest) {
        listaR.clear();  // Limpiamos la lista antes de agregar las nuevas canciones
        for (ListaCanciones listaCancion : listaCanciones) {
            Canciones cancion = clientRest.getStudentById(listaCancion.getCancionesid());  // Llamada al cliente REST para obtener las canciones
            listaR.add(cancion);  // Añadimos la canción a la lista
        }
        return listaR;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotEmpty(message = "El nombre de la lista no puede estar vacío") @Size(max = 50, message = "El nombre de la lista no puede exceder los 50 caracteres") String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(@NotEmpty(message = "El nombre de la lista no puede estar vacío") @Size(max = 50, message = "El nombre de la lista no puede exceder los 50 caracteres") String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres") String getDescripcionLista() {
        return descripcionLista;
    }

    public void setDescripcionLista(@Size(max = 255, message = "La descripción no puede exceder los 255 caracteres") String descripcionLista) {
        this.descripcionLista = descripcionLista;
    }

    public LocalDateTime getCreacionLista() {
        return creacionLista;
    }

    public void setCreacionLista(LocalDateTime creacionLista) {
        this.creacionLista = creacionLista;
    }
}
