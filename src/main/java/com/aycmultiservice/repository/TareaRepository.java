package com.aycmultiservice.repository;

import com.aycmultiservice.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByTarjetaId(Long tarjetaId);
}
