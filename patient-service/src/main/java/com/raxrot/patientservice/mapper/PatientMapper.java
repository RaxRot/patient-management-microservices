package com.raxrot.patientservice.mapper;

import com.raxrot.patientservice.dto.PatientRequestDTO;
import com.raxrot.patientservice.dto.PatientResponseDTO;
import com.raxrot.patientservice.model.Patient;

import java.time.LocalDate;

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

    public static Patient toModel(PatientRequestDTO dto){
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setAddress(dto.getAddress());
        patient.setDateOfBirth(parseDate(dto.getDateOfBirth()));
        patient.setRegisteredDate(parseDate(dto.getRegisteredDate()));
        return patient;
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            throw new IllegalArgumentException("Date must be in format yyyy-MM-dd");
        }
    }
}
