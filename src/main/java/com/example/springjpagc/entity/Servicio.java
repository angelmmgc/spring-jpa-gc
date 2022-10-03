package com.example.springjpagc.entity;



import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name="servicio")
public class Servicio {

    //NECESITARE COMO CLAVE PRIMARIA LA FECHA

    //PARA LA BÚSQUEDA

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "no puede deja el campo vacio")
    @NotBlank(message = "el nombre es requerido")
    private String name;


    @Min(1)//con min y max controlamos que sea un numero y sea positivo
    @Max(1000)
    private double moto;

    @Min(1)
    @Max(1000)
    private double coche;


    /*
    @JsonFormat(pattern = ("yyyy/MM/dd"))
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    */

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;


    public Servicio(){}

    public Servicio(Long id, String name, double moto, double coche, LocalDate date) {
        Id = id;
        this.name = name;
        this.moto = moto;
        this.coche = coche;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public double getMoto() {
        return moto;
    }

    public void setMoto(double moto) {
        this.moto = moto;
    }

    public double getCoche() {
        return coche;
    }

    public void setCoche(double coche) {
        this.coche = coche;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


}
