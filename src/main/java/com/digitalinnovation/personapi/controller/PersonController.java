package com.digitalinnovation.personapi.controller;

import com.digitalinnovation.personapi.dto.MessageResponseDto;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
