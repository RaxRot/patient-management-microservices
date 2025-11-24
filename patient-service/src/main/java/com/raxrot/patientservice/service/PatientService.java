package com.raxrot.patientservice.service;

import com.raxrot.patientservice.dto.PatientRequestDTO;
import com.raxrot.patientservice.dto.PatientResponseDTO;
import com.raxrot.patientservice.exception.ApiException;
import com.raxrot.patientservice.mapper.PatientMapper;
import com.raxrot.patientservice.model.Patient;
import com.raxrot.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new ApiException("Patient with email " + patientRequestDTO.getEmail() + " already exists",
                    HttpStatus.BAD_REQUEST);
        }

        Patient newPatient = PatientMapper.toModel(patientRequestDTO);
        Patient savedPatient = patientRepository.save(newPatient);
        return PatientMapper.toDto(savedPatient);
    }

    public List<PatientResponseDTO> getAllPatients() {
       List<Patient>patients = patientRepository.findAll();
       return patients.stream().map(PatientMapper::toDto).toList();
    }
}
