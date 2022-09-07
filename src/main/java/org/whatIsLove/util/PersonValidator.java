package org.whatIsLove.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.whatIsLove.models.Person;
import org.whatIsLove.services.PeopleService;


@Component
public class PersonValidator implements Validator {
    final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Person recievedPerson = peopleService.findByEmail(person.getEmail());
        if (recievedPerson != null)
            errors.rejectValue("email", "", "Человек с таким email уже существует");
    }
}
