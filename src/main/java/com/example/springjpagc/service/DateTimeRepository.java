package com.example.springjpagc.service;

import com.example.springjpagc.entity.Servicio;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DateTimeRepository {
    Optional<Servicio> findServiceByID(LocalDateTime dateTime);

}
