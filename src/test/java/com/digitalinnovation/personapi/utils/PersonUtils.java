package com.digitalinnovation.personapi.utils;

import com.digitalinnovation.personapi.dto.request.PersonDto;
import com.digitalinnovation.personapi.entity.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonUtils {
    private static final String FIRST_NAME = "Evandro";
    private static final String LAST_NAME = "Nascimento";
    private static final String CPF_NUMBER = "369.333.878-79";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2008, 10, 1);

    public static PersonDto createFakeDTO() {
        return PersonDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate("04-04-2010")
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity() {
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }

    public static List<Person> listAllFakeEntity() {
        List<Person> listPeople = new ArrayList<>();

        Person person = Person.builder()
                .id(2L)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf("012.345.678.90")
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
        listPeople.add(person);
        listPeople.add(createFakeEntity());

        return listPeople;
    }
}
