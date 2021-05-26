package com.digitalinnovation.personapi.repository;

import com.digitalinnovation.personapi.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
