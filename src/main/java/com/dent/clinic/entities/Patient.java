package com.dent.clinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")

public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    @CreationTimestamp
    private LocalDate entryDate;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Meet> meets = new HashSet<>();


}
