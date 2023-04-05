package com.dent.clinic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dentists")

public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String lastName;
    private String license;
    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Meet> meets = new HashSet<>();

}
