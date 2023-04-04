package com.dent.clinic.services;

import com.dent.clinic.exceptions.NotFoundException;
import com.dent.clinic.entities.Meet;
import com.dent.clinic.repositories.MeetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetService {

    @Autowired
    private final MeetRepository meetRepository;

    public MeetService(MeetRepository meetRepository) {
        this.meetRepository = meetRepository;
    }

    @Autowired
    private PatientService patientService;

    @Autowired
    private DentistService dentistService;

    public Meet createMeet(Meet meet){
        Long patientId = meet.getPatient().getId();
        Long dentistId = meet.getDentist().getId();

        if(!patientService.existsById(patientId)){
            throw new NotFoundException("Patient not found with ID: " +patientId);
        }

        if(!dentistService.existsById(dentistId)){
            throw new NotFoundException("Dentist not found with ID: " +dentistId);
        }

        return meetRepository.save(meet);
    }
    public Meet updateMeet(Long id, Meet updatedMeet){
        Meet meet = meetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Meet not found by ID: " + id));

        meet.setDateTime(updatedMeet.getDateTime());
        meet.setTitle(updatedMeet.getTitle());
        meet.setDentist(updatedMeet.getDentist());
        meet.setPatient(updatedMeet.getPatient());

        return meetRepository.save(meet);
    }

    public void deleteMeet(Long id){
        if (!meetRepository.existsById(id)){
            throw new NotFoundException("Meet not found with ID: " + id);
        }
        meetRepository.deleteById(id);
    }

}
