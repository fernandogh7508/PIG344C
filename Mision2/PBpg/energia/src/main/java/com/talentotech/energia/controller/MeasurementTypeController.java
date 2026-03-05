package com.talentotech.energia.controller;
import com.talentotech.energia.service.MeasurementTypeService;
import com.talentotech.energia.model.MeasurementType;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
@RestController
@RequestMapping("/api/measurement-type")
@RequiredArgsConstructor
public class MeasurementTypeController {

    private final MeasurementTypeService measurementTypeService;

    @PostMapping
    public MeasurementType create(@RequestBody MeasurementType measurementType) {
        return measurementTypeService.save(measurementType);
    }

    @GetMapping
    public List<MeasurementType> findAll() {
        return measurementTypeService.findAll();
    }

    @GetMapping("/{id}")
    public MeasurementType findById(@PathVariable Long id) {
        return measurementTypeService.findById(id);
    }
}