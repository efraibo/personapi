package com.digitalinnovation.personapi.service;

import com.digitalinnovation.personapi.dto.request.PersonDto;
import com.digitalinnovation.personapi.dto.response.MessageResponseDto;
import com.digitalinnovation.personapi.entity.Person;
import com.digitalinnovation.personapi.exception.PersonNotFoundException;
import com.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.digitalinnovation.personapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDtoTheReturnSavedMessage() {
        PersonDto personDto = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDto expectedMessageResponse = createExpectedMessageResponse(expectedSavedPerson.getId(), "Create person with ID: ");

        MessageResponseDto successMessage = personService.createPerson(personDto);

        assertEquals(expectedMessageResponse, successMessage);

    }

    @Test
    void testReturnAllPeoples() {

        List<Person> listPeople = listAllFakeEntity();
        when(personRepository.findAll()).thenReturn(listPeople);

        List<PersonDto> peopleListSuccess = personService.listAll();

        assertEquals(peopleListSuccess.size(), 2);
        assertEquals(peopleListSuccess.get(0).getFirstName(), "Evandro");
    }

    @Test
    void testGivenPersonIdTheReturnSavedPerson() throws PersonNotFoundException {
        Person expectedSavedPerson = createFakeEntity();
        Optional<Person> expectedOptionalSavedPerson = Optional.of(expectedSavedPerson);
        when(personRepository.findById(any())).thenReturn(expectedOptionalSavedPerson);

        PersonDto personSucess = personService.findById(expectedSavedPerson.getId());

        assertNotNull(personSucess);
        assertEquals(expectedSavedPerson.getCpf(), personSucess.getCpf());
        assertEquals(expectedSavedPerson.getFirstName(), personSucess.getFirstName());
    }

    @Test
    void testGivenPersonThenDeletedPerson() throws PersonNotFoundException {
        Person expectedSavedPerson = createFakeEntity();
        Optional<Person> expectedOptionalSavedPerson = Optional.of(expectedSavedPerson);
        when(personRepository.findById(1L)).thenReturn(expectedOptionalSavedPerson).thenReturn(null);

        personService.deleteById(expectedSavedPerson.getId());

        verify(personRepository, times(0)).delete(expectedSavedPerson);
    }

    @Test
    void testGivenPersonAndIdTheReturnUpdatedPerson() throws PersonNotFoundException {
        PersonDto personDto = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();
        Optional<Person> expectedOptionalSavedPerson = Optional.of(expectedSavedPerson);
        personDto.setId(1L);

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
        when(personRepository.findById(any())).thenReturn(expectedOptionalSavedPerson);

        MessageResponseDto expectedMessageResponse = createExpectedMessageResponse(expectedSavedPerson.getId(), "Updated person with ID: ");

        MessageResponseDto successMessage = personService.updateById(expectedSavedPerson.getId(), personDto);

        assertEquals(expectedMessageResponse, successMessage);
    }


    private MessageResponseDto createExpectedMessageResponse(Long id, String message) {
        return MessageResponseDto
                .builder()
                .message(message + id)
                .build();
    }

}