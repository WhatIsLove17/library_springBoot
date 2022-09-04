package org.whatIsLove.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.whatIsLove.models.Book;
import org.whatIsLove.services.BooksService;
import org.whatIsLove.services.PeopleService;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    private final PeopleService peopleService;


    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @GetMapping
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") int books_per_page,
                        @RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "false") boolean sort_by_year,
                        @RequestParam(required = false) String book_name) {
        if (book_name != null){
            model.addAttribute("books", booksService.findAllBooksByName(book_name));
        }
        else {
            if (books_per_page == 0)
                if (sort_by_year)
                    model.addAttribute("books", booksService.findAll(sort_by_year));
                else
                    model.addAttribute("books", booksService.findAll());
            else
                model.addAttribute("books", booksService.findAll(books_per_page, page, sort_by_year));
        }
        return "books/index";
    }


    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id) {
        Book book = booksService.findOne(id);
        model.addAttribute("book", book);
        model.addAttribute("persons", peopleService.findAll());
        model.addAttribute("person", peopleService.findByBook(book));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        book.setOwner(null);
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") Book book, @PathVariable("id") int id,
                             BindingResult bindingResult) {
        Book recievedBook = booksService.findOne(id);
        if (book.getOwner() == null) {
            recievedBook.setOwner(null);
            booksService.update(id, recievedBook);
        } else {
            if (bindingResult.hasErrors())
                return "books/edit";
            book.setOwner(recievedBook.getOwner());
            booksService.update(id, book);
        }
        return "redirect:/books/" + book.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }
}
