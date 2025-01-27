package com.examen.musica.Repository;

import com.examen.musica.Entity.ListaCanciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IListaCancionesRepository extends JpaRepository<ListaCanciones, Long> {
}
