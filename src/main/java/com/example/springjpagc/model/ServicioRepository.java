package com.example.springjpagc.model;

import com.example.springjpagc.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    List<Servicio> findServiceByDate(LocalDate date);
}
