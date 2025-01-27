package com.examen.musica.Repository;

import com.examen.musica.Entity.ListaReproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IListaRepository extends JpaRepository<ListaReproduccion, Long> {
    //List<ListaReproduccion> findByListaCanciones_NombreCancion(String nombreCancion);
}
