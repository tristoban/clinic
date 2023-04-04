package com.dent.clinic.services;

import com.dent.clinic.entities.Dentist;
import com.dent.clinic.entities.Patient;
import com.dent.clinic.exceptions.NotFoundException;
import com.dent.clinic.entities.Meet;
import com.dent.clinic.repositories.MeetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Meet createMeet(Meet meet) {
        Long patientId = meet.getPatient().getId();
        Long dentistId = meet.getDentist().getId();

        Patient patient = patientService.searchById(patientId);
        Dentist dentist = dentistService.searchById(dentistId);

        if (patient == null || dentist == null) {
            throw new IllegalArgumentException("Patient or Dentist not found");
        }


        meet.setPatient(patient);
        meet.setDentist(dentist);
        meet.setDate(meet.getDate());
        return meetRepository.save(meet);
    }


    public Meet updateMeet(Long id, Meet updatedMeet){
        Meet meet = meetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Meet not found by ID: " + id));

        meet.setTitle(updatedMeet.getTitle());
        meet.setDentist(updatedMeet.getDentist());
        meet.setPatient(updatedMeet.getPatient());

        return meetRepository.save(meet);
    }

    public List<Meet> findAll() {
        return meetRepository.findAll();
    }

    public void deleteMeet(Long id){
        if (!meetRepository.existsById(id)){
            throw new NotFoundException("Meet not found with ID: " + id);
        }
        meetRepository.deleteById(id);
    }

}
