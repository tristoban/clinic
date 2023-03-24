package com.dent.clinic.services;


import com.dent.clinic.entities.Patient;
import com.dent.clinic.exceptions.NotFoundException;
import com.dent.clinic.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public boolean existsById(Long id){
        return patientRepository.existsById(id);
    }

    public Patient createPatient(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient searchById(Long id){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found by ID: " + id));
        return patient;
    }

    public List<Patient> searchAll(){
        return patientRepository.findAll();
    }


    public Patient updatePatient(Long id, Patient updatedPatient){
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Patient not found by ID: " + id));

        patient.setName(updatedPatient.getName());
        patient.setLastName(updatedPatient.getLastName());
        patient.setDni(updatedPatient.getDni());
        patient.setEntryDate(updatedPatient.getEntryDate());
        patient.setAddress(updatedPatient.getAddress());

        return patientRepository.save(patient);
    }

    public void deletePatient(Long id){
        if (!patientRepository.existsById(id)){
            throw new NotFoundException("Patient not found by ID: " + id);
        }
        patientRepository.deleteById(id);
    }

}
