package com.example.springjpagc.service;

import com.example.springjpagc.entity.Servicio;
import com.example.springjpagc.exceptions.ServicioExceptions;
import com.example.springjpagc.model.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


//clase donde manejamos los datos en la base de datos

@Service
public class ServicioService {
    @Autowired
   private final ServicioRepository servicioRepository;

    /*
     * método constructor para acceder al repositorio (métodos base de datos)
     * @param servicioRepository accedemos a la base de datos
     */

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    /**
     * /método lista todos los servicios de la base de datos
     * @return lista de todos los servicios
     */
    public List<Servicio> findAllService(){

        return (List<Servicio>) servicioRepository.findAll();
    }


    /**
     *
     * @param id para encontrar el servicio
     * @return un servicio
     */

    public ResponseEntity<Servicio> findServiceByID(Long id){

        Optional<Servicio> servicio =  servicioRepository.findById(id);
        //si existe el servicio lo retornamos
        if (servicio.isPresent()){
            return ResponseEntity.ok(servicio.get());

        }

        //si no existe el id en base de datos
        throw new NoSuchElementException("El id no existe en nuestra base de datos.");

    }

    public List<Servicio> findServiceByDate(@RequestBody Servicio servicio){

        System.out.println("fecha desde servicio " + servicio.getDate());

        return servicioRepository.findServiceByDate(servicio.getDate());
    }

    /**
     * metodo guarda un servicio en base de datos
     * @param servicio pasamos el servicio a guardar
     * @return el servicio guardado
     */
    public Servicio createService(Servicio servicio){
        //actualizamos la fecha del dia de hoy
        //servicio.setDate(LocalDateTime.now());

        //fechas introducidas por mi para realizar la búsqueda
        String date1 = "2018-11-07";
        String date2 = "2018-12-77";
        String date3 = "2018-12-27";
        servicio.setDate(LocalDate.parse(date1));

        return servicioRepository.save(servicio);
    }

    /**
     *
     * @param id del objeto a actualizar desde la base de datos
     * @param request los datos que vamos a actualizar en la base de datos
     */
    public void update(Long id, Servicio request){

        //mediante optional encapsulamos el objeto servicio y obtenemos los datos con findById
        Optional<Servicio> servicios = servicioRepository.findById(id);
        //creamos un objeto servicio al que le pasamos los servicios
        Servicio servicio = servicios.get();

        //comprobamos que existe el servicio
        if(servicios.isPresent()){

            //actualizamos mediante metodo set
            servicio.setMoto(request.getMoto());
            servicio.setCoche(request.getCoche());
            servicio.setName(request.getName());
        }

        //guardamos en la base de datos
        this.servicioRepository.save(servicio);


     }

    /**
     * CUANDO BORRAMOS UN ELEMENTO LA BASE DATOS QUEDA ALTERADA, LO INTERESANTE
     * SERA QUE LOS ID CAMBIEN REORDENANDO LOS DATOS, NECESITARE COMO CLAVE PRIMARIA LA FECHA
     * PARA LA BUSQUEDA
     * servicio que elimina un servicio por id.
     * @param id del objeto a eliminar de la base de datos.
     */
    public void delete(Long id){

        System.out.println(id);
        servicioRepository.deleteById(id);

    }

}


