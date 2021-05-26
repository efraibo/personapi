package com.digitalinnovation.personapi.service;

import com.digitalinnovation.personapi.dto.MessageResponseDto;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDto createPerson(Person person) {
        Person savedPerson = personRepository.saveAndFlush(person);
        return MessageResponseDto
                .builder()
                .message("Create person with ID: " + savedPerson.getId())
                .build();
    }
}
