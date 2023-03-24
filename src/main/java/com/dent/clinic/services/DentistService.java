package com.dent.clinic.services;

import com.dent.clinic.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DentistService {
    private final DentistRepository dentistRepository;

    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public boolean existsById(Long id){
        return dentistRepository.existsById(id);
    }
}
