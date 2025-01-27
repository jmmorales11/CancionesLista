# CRUD de Canciones y Listas de Reproducción

Este es un servicio RESTful para gestionar canciones y listas de reproducción. Los usuarios pueden realizar operaciones CRUD sobre las canciones y las listas, además de gestionar la relación entre ellas.

## CRUD de Canciones

### Obtener todas las canciones
- **Método:** `GET`
- **Endpoint:** `http://localhost:8002/api/canciones`

### Crear una nueva canción
- **Método:** `POST`
- **Endpoint:** `http://localhost:8002/api/canciones`
- **Cuerpo:**
```json
{
  "titulo": "Como el sol",
  "artista": "Ramon",
  "genero": "Pop"
}
## Eliminar una Canción

- **Método:** `DELETE`
- **Endpoint:** `http://localhost:8002/api/canciones/{id}`
  - **Ejemplo:** `http://localhost:8002/api/canciones/1`

Esta operación elimina una canción de la base de datos usando el `id` proporcionado en la URL.

---

## Actualizar una Canción

- **Método:** `PUT`
- **Endpoint:** `http://localhost:8002/api/canciones/{id}`
  - **Ejemplo:** `http://localhost:8002/api/canciones/1`

- **Cuerpo:**
```json
{
  "titulo": "Nuevo Título de Canción",
  "artista": "Nuevo Artista",
  "genero": "Nuevo Género"
}

## CRUD de Listas de Reproducción

### Obtener todas las listas de reproducción
- **Método:** `GET`
- **Endpoint:** `http://localhost:8003/api/lista`

### Crear una nueva lista de reproducción
- **Método:** `POST`
- **Endpoint:** `http://localhost:8003/api/lista`
- **Cuerpo:**
```json
{
  "nombreLista": "Lista Favorita",
  "descripcionLista": "Esta es una lista de reproducción con mis canciones favoritas."
}
## Eliminar una Lista de Reproducción

- **Método:** `DELETE`
- **Endpoint:** `http://localhost:8003/api/lista/{id}`
  - **Ejemplo:** `http://localhost:8003/api/lista/1`

Esta operación elimina una lista de reproducción de la base de datos utilizando el `id` proporcionado en la URL.

---

## Actualizar una Lista de Reproducción

- **Método:** `PUT`
- **Endpoint:** `http://localhost:8003/api/lista/{id}`
  - **Ejemplo:** `http://localhost:8003/api/lista/1`

## Asociación entre Canciones y Listas de Reproducción

### Agregar una canción a una lista de reproducción
- **Método:** `POST`
- **Endpoint:** `http://localhost:8003/api/lista/asignar-cancion/{idLista}`
  - **Ejemplo:** `http://localhost:8003/api/lista/asignar-cancion/4`
  
En la URL, debes incluir el `id` de la lista de reproducción a la que quieres agregar la canción. El cuerpo de la solicitud debe contener los detalles de la canción, que ya debe estar registrada en el sistema.

- **Cuerpo:**
```json
{
  "id": 1,
  "titulo": "Como el sol",
  "artista": "Ramon",
  "genero": "Pop"
}
### Listar las canciones de una lista de reproducción
- **Método:** `GET`
- **Endpoint:** `http://localhost:8003/api/lista/lista-canciones/{idLista}`
  - **Ejemplo:** `http://localhost:8003/api/lista/lista-canciones/1`
  
En la URL, debes incluir el `id` de la lista de reproducción para ver todas las canciones que pertenecen a esa lista.

### Listar las listas de reproducción que contienen una canción específica
- **Método:** `GET`
- **Endpoint:** `http://localhost:8003/api/lista/listas-con-cancion/{idCancion}`
  - **Ejemplo:** `http://localhost:8003/api/lista/listas-con-cancion/4`
  
En la URL, debes enviar el `id` de la canción para buscar en qué listas de reproducción aparece esa canción.

### Eliminar una canción de una lista de reproducción
- **Método:** `DELETE`
- **Endpoint:** `http://localhost:8003/api/lista/eliminar-cancion/{idLista}/{idCancion}`
  - **Ejemplo:** `http://localhost:8003/api/lista/eliminar-cancion/2/2`
  
En la URL, debes incluir primero el `id` de la lista de reproducción y luego el `id` de la canción que deseas eliminar de esa lista.
