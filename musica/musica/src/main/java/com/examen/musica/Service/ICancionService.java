package com.examen.musica.Service;

import com.examen.musica.Entity.Cancion;

import java.util.List;
import java.util.Optional;

public interface ICancionService {
    List<Cancion> getAllCancion();

    Optional<Cancion> getCancionById(Long id);

    Cancion saveCancion(Cancion cancion);

    void deleteCancionById(Long id);

    Cancion updateCancion(Cancion cancion, Long id);

    Optional<Cancion> addCancio(Cancion cancion, Long id);
}
