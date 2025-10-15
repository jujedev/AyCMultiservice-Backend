package com.aycmultiservice.repository;

import com.aycmultiservice.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByTarjetaId(Long tarjetaId);
}
