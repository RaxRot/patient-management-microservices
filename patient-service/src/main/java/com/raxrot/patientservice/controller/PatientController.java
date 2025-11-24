package com.raxrot.patientservice.controller;

import com.raxrot.patientservice.dto.PatientRequestDTO;
import com.raxrot.patientservice.dto.PatientResponseDTO;
import com.raxrot.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){
        return new ResponseEntity<>(patientService.createPatient(patientRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }
}
