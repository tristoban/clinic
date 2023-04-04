package com.dent.clinic.controllers;


import com.dent.clinic.entities.Meet;
import com.dent.clinic.services.MeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meet")
public class MeetController {

    @Autowired
    private MeetService meetService;

    @PostMapping
    public ResponseEntity<Meet> createMeet(@RequestBody Meet meet) {
        return ResponseEntity.ok(meetService.createMeet(meet));
    }

    @GetMapping
    public ResponseEntity<List<Meet>> getAllMeets() {
        return ResponseEntity.ok(meetService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meet> updateMeet(@PathVariable Long id, @RequestBody Meet updatedMeet) {
        Meet meet = meetService.updateMeet(id, updatedMeet);
        return ResponseEntity.ok(meet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeet(@PathVariable Long id) {
        meetService.deleteMeet(id);
        return ResponseEntity.noContent().build();
    }
}