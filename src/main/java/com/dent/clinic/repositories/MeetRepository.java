package com.dent.clinic.repositories;

import com.dent.clinic.entities.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetRepository extends JpaRepository<Meet, Long> {

}
