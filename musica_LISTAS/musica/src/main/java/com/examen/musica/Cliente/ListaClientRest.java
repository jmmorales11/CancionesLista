package com.examen.musica.Cliente;

import com.examen.musica.Model.Canciones;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="musica",url="localhost:8002/api/canciones")
public interface ListaClientRest {
    @GetMapping("/{id}")
    Canciones getStudentById(@PathVariable Long id);
}
