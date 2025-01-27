package com.examen.musica.Service;

import com.examen.musica.Entity.Cancion;
import com.examen.musica.Repository.ICancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CancionService implements ICancionService{

    @Autowired
    private ICancionRepository cancionRepository;

    @Override
    public List<Cancion> getAllCancion() {
        try{
            List<Cancion> cancion = cancionRepository.findAll();
            if(cancion.isEmpty()){
                return new ArrayList<>();
            }
            return cancion;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cancion> getCancionById(Long id) {
        try{
            Optional<Cancion> cancion = cancionRepository.findById(id);
            if(cancion.isPresent()){
                return cancion;
            }else{
                throw new RuntimeException("Cancion con "+id+" no encontrado");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al encontrar cancion con id " + id, e);
        }
    }

    @Override
    public Cancion saveCancion(Cancion cancion) {
        try {
            cancion.setCreacion(LocalDateTime.now());
            return cancionRepository.save(cancion);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al guardar el curso", e);
        }
    }

    @Override
    public void deleteCancionById(Long id) {
        try{
            Optional<Cancion> cancion = cancionRepository.findById(id);
            if(cancion.isPresent()){
                cancionRepository.deleteById(id);
            }else{
                throw new RuntimeException("Se ha eliminado el curso con "+id+".");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cancion updateCancion(Cancion cancion, Long id) {
        try {
            Optional<Cancion> existingCancion = cancionRepository.findById(id);
            if (existingCancion.isPresent()) {
                Cancion updateCancion = existingCancion.get();
                updateCancion.setTitulo(cancion.getTitulo());
                updateCancion.setArtista(cancion.getArtista());
                updateCancion.setGenero(cancion.getGenero());
                return cancionRepository.save(updateCancion);
            } else {
                throw new RuntimeException("Cancion con "+id+" no encontrado");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar cancion " + id, e);
        }
    }

    @Override
    public Optional<Cancion> addCancio(Cancion cancion, Long id) {
        return Optional.empty();
    }
}
