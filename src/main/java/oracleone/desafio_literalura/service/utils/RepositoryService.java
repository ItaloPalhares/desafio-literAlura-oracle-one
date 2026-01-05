package oracleone.desafio_literalura.service.utils;

import jakarta.transaction.Transactional;
import oracleone.desafio_literalura.model.Authors;
import oracleone.desafio_literalura.model.Books;
import oracleone.desafio_literalura.repository.BooksRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepositoryService {

    private final BooksRepository repository;

    public RepositoryService (BooksRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public void saveReplacingBook(Books newBook) {

        repository.deleteByTitleIgnoreCase(newBook.getTitle());
        repository.flush();
        repository.save(newBook);
    }

    public Optional<Books> findByTitle(String title) {
        return repository.findByTitleIgnoreCase(title);
    }
    public void save(Books book) {
        repository.save(book);
    }

    public Optional<Books> findByTitleIgnoreCase(String title) {
        return repository.findByTitleIgnoreCase(title);
    }

    public List<Books> getAllBooks() {
       return repository.findAll();
    }

    public void findAuthorsActiveByYear(int year){
        List<Authors> authors = repository.findAuthorsAliveByYear(year);

        authors.forEach(a -> {
            System.out.println("Author: " + a.getName());
            System.out.println("From: " + a.getBirthYear() + " to " +
                    (a.getDeathYear() != null ? a.getDeathYear() : "present"));
            System.out.println("--------------");
        });

    }

    public List<Books> findBooksByLanguage(String language) {
        List<Books> books = repository.findBooksByLanguage(language);
        return books;


    }
}
