package org.whatIsLove.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.whatIsLove.models.Person;
import org.whatIsLove.repositories.PeopleRepository;
import org.whatIsLove.security.PersonDetails;


@Service
public class PersonDetailsService implements UserDetailsService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = peopleRepository.findByEmail(username);

        if (person == null)
            throw new UsernameNotFoundException("User with such email is not found!");

        return new PersonDetails(person);
    }
}
