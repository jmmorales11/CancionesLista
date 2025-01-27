package com.examen.musica.Controller;

import com.examen.musica.Entity.Cancion;
import com.examen.musica.Service.CancionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    @GetMapping
    public List<Cancion> getAllCanciones() {
        return cancionService.getAllCancion();
    }

    // Get course by id
    @GetMapping("/{id}")
    public Optional<Cancion> getCancioById(@PathVariable("id") Long id) {
        return cancionService.getCancionById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCancion(@Valid @RequestBody Cancion cancion, BindingResult result) {
        if(result.hasErrors()){

            return error(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(cancionService.saveCancion(cancion));
    }
    public ResponseEntity<?> error(BindingResult result){
        Map<String,String> errores = new HashMap<>();
        result.getFieldErrors().forEach(
                err-> errores.put(
                        err.getField(),err.getDefaultMessage()
                )
        );
        return ResponseEntity.badRequest().body(errores);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        cancionService.deleteCancionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateCancion(@Valid @RequestBody Cancion cancion, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){

            return error(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(cancionService.updateCancion(cancion,id));

    }

}
