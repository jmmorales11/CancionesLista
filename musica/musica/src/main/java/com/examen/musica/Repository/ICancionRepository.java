package com.examen.musica.Repository;

import com.examen.musica.Entity.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICancionRepository extends JpaRepository<Cancion, Long> {
}
