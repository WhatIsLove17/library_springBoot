package org.whatIsLove.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whatIsLove.models.Book;
import org.whatIsLove.models.Person;
import org.whatIsLove.repositories.BooksRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Page<Book> findAll(int books_per_page, int page, boolean sort_by_year) {
        if (sort_by_year)
            return booksRepository.findAll(PageRequest.of(page, books_per_page, Sort.by("year")));
        else
            return booksRepository.findAll(PageRequest.of(page, books_per_page));
    }

    public List<Book> findAll(boolean sort_by_year){
        if (sort_by_year)
            return booksRepository.findAll(Sort.by("year"));
        else
            return booksRepository.findAll();
    }

    public List<Book> findAllBooksByName(String bookName){
        return booksRepository.findAllByNameStartingWith(bookName);
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }

    @Transactional
    public void save(Book book) {
        book.setTakenAt(new Date());
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
