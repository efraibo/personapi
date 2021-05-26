package com.digitalinnovation.personapi.service;

import com.digitalinnovation.personapi.dto.request.PersonDto;
import com.digitalinnovation.personapi.dto.response.MessageResponseDto;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.exception.PersonNotFoundException;
import com.digitalinnovation.personapi.mapper.PersonMapper;
import com.digitalinnovation.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDto createPerson(PersonDto personDto) {
        Person savedPerson = personRepository.saveAndFlush(personMapper.toModel(personDto));
        return createMessageResponse(savedPerson.getId(), "Create person with ID: ");
    }

    public List<PersonDto> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream().map(personMapper::toDto).collect(Collectors.toList());
    }

    public PersonDto findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDto(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDto updateById(Long id, PersonDto personDto) throws PersonNotFoundException {
        verifyIfExists(personDto.getId());

        Person updatedPerson
                = personRepository.saveAndFlush(personMapper.toModel(personDto));
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID: ");
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDto createMessageResponse(Long id, String message) {
        return MessageResponseDto
                .builder()
                .message(message + id)
                .build();
    }
}
