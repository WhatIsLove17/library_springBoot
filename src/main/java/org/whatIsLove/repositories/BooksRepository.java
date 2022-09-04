package org.whatIsLove.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.whatIsLove.models.Book;
import org.whatIsLove.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);

    List<Book> findAllByNameStartingWith(String bookName);

}
