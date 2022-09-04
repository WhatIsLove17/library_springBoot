package org.whatIsLove.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whatIsLove.models.Book;
import org.whatIsLove.models.Person;
import org.whatIsLove.repositories.PeopleRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;


    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    public Person findByBook(Book book){
        return peopleRepository.findByPersonBooks(book);
    }

    @Transactional
    public void save(Person person){
        person.setCreatedAt(new Date());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
