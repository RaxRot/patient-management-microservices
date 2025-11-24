package com.raxrot.patientservice.service;

import com.raxrot.patientservice.dto.PatientResponseDTO;
import com.raxrot.patientservice.mapper.PatientMapper;
import com.raxrot.patientservice.model.Patient;
import com.raxrot.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientResponseDTO> getAllPatients() {
       List<Patient>patients = patientRepository.findAll();
       return patients.stream().map(PatientMapper::toDto).toList();
    }
}
