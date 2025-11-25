package com.raxrot.patientservice.service;

import com.raxrot.patientservice.dto.PatientRequestDTO;
import com.raxrot.patientservice.dto.PatientResponseDTO;
import com.raxrot.patientservice.dto.PatientUpdateDTO;
import com.raxrot.patientservice.exception.ApiException;
import com.raxrot.patientservice.mapper.PatientMapper;
import com.raxrot.patientservice.model.Patient;
import com.raxrot.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new ApiException("Patient with email " + patientRequestDTO.getEmail() + " already exists",
                    HttpStatus.CONFLICT);
        }

        Patient newPatient = PatientMapper.toModel(patientRequestDTO);
        Patient savedPatient = patientRepository.save(newPatient);
        return PatientMapper.toDto(savedPatient);
    }

    public List<PatientResponseDTO> getAllPatients() {
       List<Patient>patients = patientRepository.findAll();
       return patients.stream().map(PatientMapper::toDto).toList();
    }

    public PatientResponseDTO updatePatient(UUID id, PatientUpdateDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()-> new ApiException("Patient not found", HttpStatus.NOT_FOUND));
        if(!patient.getEmail().equals(patientRequestDTO.getEmail()) && patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new ApiException("Patient with email " + patientRequestDTO.getEmail() + " already exists",
                    HttpStatus.CONFLICT);
        }
        patient= PatientMapper.toModel(patient, patientRequestDTO);
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }
}
