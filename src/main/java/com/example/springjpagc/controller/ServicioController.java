package com.example.springjpagc.controller;

import com.example.springjpagc.dto.ErrorResponse;
import com.example.springjpagc.entity.Servicio;
import com.example.springjpagc.service.ServicioService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Gestionamos las peticiones y las enviamos al servicio
 *
 */
@RestController
public class ServicioController {

    //propiedad ServicioRepositorio
    @Autowired
    ServicioService servicioService;

    Logger logger = LoggerFactory.getLogger(ServicioController.class);

    public ServicioController(){}
    //constructor
    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }


    /**
     * metodo recupera los datos de la base de datos
     * faltan controlar las excepciones o throws y eliminar la traza:
     *   la url no es correcta (ahora da Status 200 ok, hay que cambiarlo a not found 404)
     * @return
     */
    @GetMapping("/api/listAll")
    public List<Servicio> listarServicios(@NotNull HttpServletRequest request) {
        return servicioService.findAllService();

    }

    /**
     * metodo que devuelve un servicio por id
     * faltan controlar las excepciones o throws y eliminar la traza :
     *  que el id no exista (ahora da Status 200 ok, hay que cambiarlo a not found 404)
     *  que el id no sea correcto (ahora da Status 400 Bad Request )
     * @param id encontrar el servicio
     * @return un servicio
     */
   @GetMapping("/api/findServiceByID/{id}")
   public ResponseEntity<Servicio> findServiceByID(@PathVariable("id") @Size(max = 1) Long id ){

       return servicioService.findServiceByID(id);

   }

    @GetMapping("/api/findServiceByDate")
    public List<Servicio> findServiceByDate(@RequestBody Servicio servicio){


        return servicioService.findServiceByDate(servicio);

    }

    /**
     * metodo que inserta un nuevo servicio
     * falta controlar las excepciones
     * @param servicio lleva la etiqueta @RequestBody para recoger los datos de la peticion
     * @return podemos retornar lo que quereamos en esta caso el json con los datos
     */
    @PostMapping("/api/save")
    public ResponseEntity<Servicio> saveServicio(@Valid @RequestBody Servicio servicio){


        if (servicio.getId() != null ) {
           //log de informacion del error
            return ResponseEntity.badRequest().build();
        }


        servicio = servicioService.createService(servicio);
        //System.out.println("trying to create a servicio with id\"" +servicio.getDate());
        //podemmos devolver solo el status
        //return ResponseEntity.ok().build;
        return ResponseEntity.ok(servicio);
    }

    /**
     * ResponseEntity nos devolvera el estado de la respuesta, con build
     * construimos la respuesta
     * falta controlar las excepciones
     * @param id buscamos el servicio a actualizar
     *           al pasarlo por parámetro debe
     *           llevar la etiqueta @PathVariable("id")
     * @param servicio parámetro de tipo request que nos trae los datos de postman
     *                 debe de llevar la etiqueta @RequestBody para actualizarlos
     * @return una respuesta ok en caso de update válido
     */
    @PutMapping("/api/update/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable("id") Long id,@RequestBody Servicio servicio){

        //llamamos al servicio

        servicioService.update(id,servicio);
        return ResponseEntity.ok().build();
    }


    /**
     * método que borra un servicio según id
     * falta controlar las excepciones
     * @param id identificador para borrar el servicio, al pasarlo por parámetro debe
     *           llevar la etiqueta @PathVariable("id")
     * @return una respuesta en caso positivo
     */

    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<Servicio> deleteServicio(@PathVariable("id") Long id){

        servicioService.delete(id);
        return ResponseEntity.ok().build();
         }

    }

