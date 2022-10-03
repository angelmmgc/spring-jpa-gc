package com.example.springjpagc.service.impl;

import com.example.springjpagc.entity.Servicio;
import com.example.springjpagc.model.ServicioRepository;
import com.example.springjpagc.service.DateTimeRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class DateTimeRepositoryImpl implements DateTimeRepository {


    @Override
    public Optional<Servicio> findServiceByID(LocalDateTime dateTime) {
        return Optional.empty();
    }
}
