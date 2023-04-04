package com.dent.clinic.controllers;

import com.dent.clinic.entities.Dentist;
import com.dent.clinic.entities.Patient;
import com.dent.clinic.services.DentistService;
import com.dent.clinic.services.PatientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
        return ResponseEntity.ok(patientService.searchAll());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient){
        Patient patient = patientService.updatePatient(id, updatedPatient);
        return ResponseEntity.ok(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id){
        return ResponseEntity.ok(patientService.deletePatient(id));
    }

}













/*
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

*/