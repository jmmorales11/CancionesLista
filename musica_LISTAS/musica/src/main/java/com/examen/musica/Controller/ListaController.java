package com.examen.musica.Controller;

import com.examen.musica.Entity.ListaReproduccion;
import com.examen.musica.Model.Canciones;
import com.examen.musica.Service.ListaService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/lista")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping
    public List<ListaReproduccion> getAllCanciones() {
        return listaService.getAllListaReproduccion();
    }

    // Get course by id
    @GetMapping("/{id}")
    public Optional<ListaReproduccion> getCancioById(@PathVariable("id") Long id) {
        return listaService.getListaReproduccionById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCancion(@Valid @RequestBody ListaReproduccion lista, BindingResult result) {
        if(result.hasErrors()){

            return error(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(listaService.saveListaReproduccion(lista));
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
        listaService.deleteListaReproduccionnById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  updateCancion(@Valid @RequestBody ListaReproduccion lista, @PathVariable("id") Long id, BindingResult result){
        if(result.hasErrors()){

            return error(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaService.updateListaReproduccion(lista,id));

    }

    @PostMapping("/asignar-cancion/{id}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Canciones canciones, @PathVariable Long id){
        Optional<Canciones> o;
        try{
            o= listaService.addCanciones(canciones,id);

        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje","No existe la cancion con el "+ id +" o error en la comunicacion "+ e.getMessage()));

        }
        if (o.isPresent()){
            return  ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/lista-canciones/{id}")
    public ResponseEntity<List<Canciones>> getCancionesByLista(@PathVariable("id") Long id) {
        List<Canciones> canciones = listaService.getCancionesByListaId(id);
        if (canciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(new Canciones())); // Si no hay canciones, responde con lista vacía
        }
        return ResponseEntity.ok(canciones); // Devuelve las canciones de la lista
    }
    @GetMapping("/listas-con-cancion/{id}")
    public ResponseEntity<List<ListaReproduccion>> getListasByCancion(@PathVariable("id") Long cancionId) {
        List<ListaReproduccion> listas = listaService.getListasByCancionId(cancionId);
        if (listas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(new ListaReproduccion())); // Si no hay listas, responde con lista vacía
        }
        return ResponseEntity.ok(listas);
    }

    @DeleteMapping("/eliminar-cancion/{listaId}/{cancionId}")
    public ResponseEntity<?> removeCancion(@PathVariable("listaId") Long listaId, @PathVariable("cancionId") Long cancionId) {
        try {
            listaService.removeCancionFromLista(listaId, cancionId);
            return ResponseEntity.status(HttpStatus.OK).body("Canción eliminada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje", e.getMessage()));
        }
    }
    @GetMapping("/listas-con-canciones")
    public ResponseEntity<List<ListaReproduccion>> getAllListasConCanciones() {
        List<ListaReproduccion> listasConCanciones = listaService.getAllListasConCanciones();

        if (listasConCanciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonList(new ListaReproduccion()));  // Si no hay listas, responde con lista vacía
        }

        return ResponseEntity.ok(listasConCanciones);  // Devuelve todas las listas con las canciones asociadas
    }




}
