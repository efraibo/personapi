package com.digitalinnovation.personapi.service;

import com.digitalinnovation.personapi.dto.request.PersonDto;
import com.digitalinnovation.personapi.dto.response.MessageResponseDto;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.mapper.PersonMapper;
import com.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDto createPerson(PersonDto personDto) {
        Person savedPerson = personRepository.saveAndFlush(personMapper.toModel(personDto));
        return MessageResponseDto
                .builder()
                .message("Create personDto with ID: " + savedPerson.getId())
                .build();
    }

    public List<PersonDto> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDto).collect(Collectors.toList());
    }
}
