package com.digitalinnovation.personapi.controller;

import com.digitalinnovation.personapi.dto.request.PersonDto;
import com.digitalinnovation.personapi.dto.response.MessageResponseDto;
import com.digitalinnovation.personapi.exception.PersonNotFoundException;
import com.digitalinnovation.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createPerson(@RequestBody @Valid PersonDto personDto) {
        return personService.createPerson(personDto);
    }

    @GetMapping
    public List<PersonDto> listAll() {
        return personService.listAll();
    }

    @GetMapping("{id}")
    public PersonDto findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }
}
