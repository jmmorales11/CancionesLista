package com.examen.musica.Service;


import com.examen.musica.Cliente.ListaClientRest;
import com.examen.musica.Entity.ListaCanciones;
import com.examen.musica.Entity.ListaReproduccion;
import com.examen.musica.Model.Canciones;
import com.examen.musica.Repository.IListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class ListaService implements  IListaService{

    @Autowired
    private IListaRepository listaRepository;
    @Autowired
    private ListaClientRest clientRest;
    @Override
    public List<ListaReproduccion> getAllListaReproduccion() {
        try{
            List<ListaReproduccion> cancion = listaRepository.findAll();
            if(cancion.isEmpty()){
                return new ArrayList<>();
            }
            return cancion;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ListaReproduccion> getListaReproduccionById(Long id) {
        try{
            Optional<ListaReproduccion> cancion = listaRepository.findById(id);
            if(cancion.isPresent()){
                return cancion;
            }else{
                throw new RuntimeException("Lista con "+id+" no encontrado");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al encontrar Lista con id " + id, e);
        }
    }

    @Override
    public ListaReproduccion saveListaReproduccion(ListaReproduccion listaReproduccion) {
        try {
            listaReproduccion.setCreacionLista(LocalDateTime.now());
            return listaRepository.save(listaReproduccion);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al guardar la lista", e);
        }
    }

    @Override
    public void deleteListaReproduccionnById(Long id) {
        try{
            Optional<ListaReproduccion> cancion = listaRepository.findById(id);
            if(cancion.isPresent()){
                listaRepository.deleteById(id);
            }else{
                throw new RuntimeException("Se ha eliminado la lista con "+id+".");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ListaReproduccion updateListaReproduccion(ListaReproduccion listaReproduccion, Long id) {
        try {
            Optional<ListaReproduccion> existingCancion = listaRepository.findById(id);
            if (existingCancion.isPresent()) {
                ListaReproduccion updateLista = existingCancion.get();
                updateLista.setNombreLista(listaReproduccion.getNombreLista());
                updateLista.setDescripcionLista(listaReproduccion.getDescripcionLista());

                return listaRepository.save(updateLista);
            } else {
                throw new RuntimeException("Lista con "+id+" no encontrado");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar lista " + id, e);
        }
    }

    @Override
    public Optional<Canciones> addCanciones(Canciones listaR, Long id) {
        Optional<ListaReproduccion> optional= listaRepository.findById(id);
        if(optional.isPresent()){
            Canciones lisTemp= clientRest.getStudentById(listaR.getId());
            ListaReproduccion listaReproduccion= optional.get();
            ListaCanciones listaCanciones= new ListaCanciones();

            listaCanciones.setCancionesid(lisTemp.getId());
            listaReproduccion.addListaCanciones(listaCanciones);
            listaRepository.save(listaReproduccion);
            return  Optional.of(lisTemp);
        }
        return Optional.empty();
    }

    @Override
    public List<Canciones> getCancionesByListaId(Long id) {
        Optional<ListaReproduccion> listaOpt = listaRepository.findById(id);
        if (listaOpt.isPresent()) {
            ListaReproduccion listaReproduccion = listaOpt.get();
            List<ListaCanciones> listaCanciones = listaReproduccion.listarCanciones();
            List<Canciones> canciones = new ArrayList<>();

            // Recorre las canciones asociadas a la lista
            for (ListaCanciones listaCancion : listaCanciones) {
                Canciones cancion = clientRest.getStudentById(listaCancion.getCancionesid()); // Recupera las canciones asociadas
                canciones.add(cancion);
            }
            return canciones;
        }
        return Collections.emptyList(); // Si no se encuentra la lista, devuelve una lista vacía
    }

    @Override
    public List<ListaReproduccion> getListasByCancionId(Long cancionId) {
        List<ListaReproduccion> listas = listaRepository.findAll();
        List<ListaReproduccion> listasConCancion = new ArrayList<>();
        for (ListaReproduccion lista : listas) {
            for (ListaCanciones listaCancion : lista.listarCanciones()) {
                if (listaCancion.getCancionesid() == cancionId) {
                    listasConCancion.add(lista);
                    break; // Si encontramos la canción, no es necesario seguir buscando en esta lista
                }
            }
        }
        return listasConCancion;
    }
    @Override
    public void removeCancionFromLista(Long listaId, Long cancionId) {
        Optional<ListaReproduccion> listaOpt = listaRepository.findById(listaId);
        if (listaOpt.isPresent()) {
            ListaReproduccion lista = listaOpt.get();
            ListaCanciones cancionToRemove = null;

            // Buscar la canción en la lista
            for (ListaCanciones listaCancion : lista.listarCanciones()) {
                if (listaCancion.getCancionesid() == cancionId) {
                    cancionToRemove = listaCancion;
                    break;
                }
            }

            if (cancionToRemove != null) {
                lista.removeCourseUser(cancionToRemove); // Elimina la canción
                listaRepository.save(lista); // Guarda los cambios
            } else {
                throw new RuntimeException("Canción con ID " + cancionId + " no encontrada en la lista");
            }
        } else {
            throw new RuntimeException("Lista con ID " + listaId + " no encontrada");
        }
    }

    @Override
    public List<ListaReproduccion> getAllListasConCanciones() {
        // Obtenemos todas las listas de reproducción
        List<ListaReproduccion> listas = listaRepository.findAll();

        // Para cada lista, vamos a obtener las canciones asociadas
        for (ListaReproduccion lista : listas) {
            lista.obtenerCanciones(clientRest); // Llamamos al método para cargar las canciones en la lista
        }

        return listas; // Devolvemos las listas con sus canciones
    }




}
