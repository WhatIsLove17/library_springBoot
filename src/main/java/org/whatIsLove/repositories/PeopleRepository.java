package org.whatIsLove.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.whatIsLove.models.Book;
import org.whatIsLove.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Person findByPersonBooks(Book book);

    Person findByEmail(String email);
}
