package com.talentotech.energia.repository;
import com.talentotech.energia.model.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, Long> {

    Optional<MeasurementType> findByName(String name);

    boolean existsByName(String name);
}
