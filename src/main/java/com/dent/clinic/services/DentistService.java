package com.dent.clinic.services;

import com.dent.clinic.entities.Dentist;
import com.dent.clinic.exceptions.NotFoundException;
import com.dent.clinic.repositories.DentistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {
    private final DentistRepository dentistRepository;

    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }

    public boolean existsById(Long id){
        return dentistRepository.existsById(id);
    }

    public Dentist createDentist(Dentist dentist){
        return dentistRepository.save(dentist);
    }

    public Dentist updateDentist(Long id, Dentist dentistUpdated){
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Dentist not found by ID: " + id));

        dentist.setName(dentistUpdated.getName());
        dentist.setLastName(dentistUpdated.getLastName());
        dentist.setLicense(dentistUpdated.getLicense());

        return dentistRepository.save(dentist);
    }

    public Dentist searchById(Long id){
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(("Dentist not found by ID: "+id)));
        return dentist;
    }

    public List<Dentist> searchAll(){
        return dentistRepository.findAll();
    }

    public void deleteDentist(Long id){ //eliminado por objeto? findById devuelve el objeto a dentist?
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Dentist not found by ID: "+id));
        dentistRepository.delete(dentist);
    }


}
