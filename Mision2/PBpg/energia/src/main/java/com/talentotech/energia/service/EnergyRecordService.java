package com.talentotech.energia.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.talentotech.energia.repository.*;
import com.talentotech.energia.model.*;
import com.talentotech.energia.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyRecordService {

    private final EnergyRecordRepository energyRecordRepository;
    private final PowerPlantRepository powerPlantRepository;
    private final MeasurementTypeRepository measurementTypeRepository;

    public EnergyRecord save(EnergyRecord record) {

        // Validar año
        if (record.getYear() == null || record.getYear() < 1900) {
            throw new ResourceNotFoundException("Invalid year");
        }

        // Validar mes
        if (record.getMonth() == null || record.getMonth() < 1 || record.getMonth() > 12) {
            throw new ResourceNotFoundException("Month must be between 1 and 12");
        }

        // Validar valor
        if (record.getValue() == null || record.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResourceNotFoundException("Value must be positive");
        }

        Long plantId = record.getPowerPlant().getId();
        Long measurementTypeId = record.getMeasurementType().getId();

        PowerPlant plant = powerPlantRepository.findById(plantId)
                .orElseThrow(() -> new ResourceNotFoundException("PowerPlant not found"));

        MeasurementType measurementType = measurementTypeRepository.findById(measurementTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("MeasurementType not found"));

        // Validar registro único
        if (energyRecordRepository
                .existsByPowerPlantIdAndYearAndMonthAndMeasurementTypeId(
                        plantId,
                        record.getYear(),
                        record.getMonth(),
                        measurementTypeId
                )) {
            throw new ResourceNotFoundException("EnergyRecord already exists for this plant, month and type");
        }

        record.setPowerPlant(plant);
        record.setMeasurementType(measurementType);

        return energyRecordRepository.save(record);
    }

    public List<EnergyRecord> findAll() {
        return energyRecordRepository.findAll();
    }

    public EnergyRecord findById(Long id) {
        return energyRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EnergyRecord not found"));
    }

    public List<EnergyRecord> findByPlant(Long plantId) {
        return energyRecordRepository.findByPowerPlantId(plantId);
    }
}
