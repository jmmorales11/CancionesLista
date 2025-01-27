package com.examen.musica.Service;

import com.examen.musica.Entity.ListaReproduccion;
import com.examen.musica.Model.Canciones;

import java.util.List;
import java.util.Optional;

public interface IListaService {
    List<ListaReproduccion> getAllListaReproduccion();

    Optional<ListaReproduccion> getListaReproduccionById(Long id);

    ListaReproduccion saveListaReproduccion(ListaReproduccion listaReproduccion);

    void deleteListaReproduccionnById(Long id);

    ListaReproduccion updateListaReproduccion(ListaReproduccion listaReproduccion, Long id);

    Optional<Canciones> addCanciones(Canciones listaR, Long id);
    public List<Canciones> getCancionesByListaId(Long id);
    public List<ListaReproduccion> getListasByCancionId(Long cancionId);
    public void removeCancionFromLista(Long listaId, Long cancionId);
    public List<ListaReproduccion> getAllListasConCanciones();
}
