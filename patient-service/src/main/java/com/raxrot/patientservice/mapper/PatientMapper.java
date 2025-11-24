package com.raxrot.patientservice.mapper;

import com.raxrot.patientservice.dto.PatientResponseDTO;
import com.raxrot.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toDto(Patient patient){
        PatientResponseDTO dto = new PatientResponseDTO();
        dto.setId(patient.getId().toString());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setAddress(patient.getAddress());
        dto.setDateOfBirth(patient.getDateOfBirth());
        return dto;
    }
}
