package com.dent.clinic.controllers;

import com.dent.clinic.entities.Dentist;
import com.dent.clinic.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {
    @Autowired
    private DentistService dentistService;

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentists(){
        return ResponseEntity.ok(dentistService.searchAll());
    }

    @PostMapping
    public ResponseEntity<Dentist> createDentist(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(dentistService.createDentist(dentist));
    }
}
