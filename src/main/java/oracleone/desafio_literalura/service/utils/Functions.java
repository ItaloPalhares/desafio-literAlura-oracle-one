package oracleone.desafio_literalura.service.utils;

import jakarta.transaction.Transactional;
import oracleone.desafio_literalura.model.Books;
import oracleone.desafio_literalura.model.DTO.BooksDTO;
import oracleone.desafio_literalura.model.DTO.ResultsDTO;
import oracleone.desafio_literalura.service.requests.APIConsumer;
import oracleone.desafio_literalura.service.requests.Converter;
import org.springframework.stereotype.Service;
import oracleone.desafio_literalura.repository.BooksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class Functions {

    private RepositoryService repository;
    private APIConsumer consumer;
    private Converter converter;
    private static final String URL_GUTENDEX = "https://gutendex.com/books/";
    private static final String SEARCH = "?search=";
    private String json;
    private List<BooksDTO> searchedBooks = new ArrayList<>();

    public Functions(APIConsumer consumer,
                     Converter converter,
                     RepositoryService repository) {

        this.consumer = consumer;
        this.converter = converter;
        this.repository = repository;
    }


    public void findBookByTitle(String title, Scanner scanner) {
        json = consumer.getData(URL_GUTENDEX+ SEARCH + title.replace(" ", "+"));
        BooksDTO booksDTO = getBooksDTO(title);
        if(booksDTO != null){
            searchedBooks.add(booksDTO);
            System.out.println(booksDTO);

            System.out.println("Save this on database?\n"
                    + "1 - yes | 2 - no");
            var saveAnswer = Integer.parseInt(scanner.nextLine());
            switch (saveAnswer){
                case 1:
                    Books book = new Books(booksDTO);
                    Optional<Books> existingBook =
                            repository.findByTitleIgnoreCase(book.getTitle());
                    if(existingBook.isPresent()){
                        repository.saveReplacingBook(book);
                        break;

                    } else {
                        repository.save(book);
                        break;
                    }


                case 2:
                    System.out.println("Returning to Main Menu");
                    break;

            }

        } if(booksDTO == null){
            System.out.println("Book Not Found");
        }


    }

    public void listSearchedBooks(){
        System.out.println("--------------" +
                "Log of Searched Books:" +
                "--------------");
        List<Books> booksList = repository.getAllBooks();
        booksList.forEach(System.out::println);
    }

    private BooksDTO getBooksDTO(String title) {
        ResultsDTO resultsDTO = converter.convertData(json, ResultsDTO.class);
        BooksDTO bookDTO = resultsDTO.booksDTOList().stream()
                .filter(book ->  book.title().toUpperCase().contains(title.toUpperCase()))
                .findFirst().orElse(null);
        return bookDTO;

    }

    public void listSearchedAuthors() {
        System.out.println("--------------" + "Log of Searched Authors:" + "--------------");
        System.out.println("----------------------------");

        List<Books> booksList = repository.getAllBooks();
        booksList.forEach(Books::printAuthors);


//        searchedBooks.forEach(book -> {
//            System.out.println("Title: " + book.title());
//
//            book.author().forEach(author -> {
//                System.out.println("Author: " + author.name());
//            });
//
//            System.out.println("----------------------------");
//        });



    }

    public void searchAuthorsAliveByYear(int year) {
        System.out.println("--------------" + "Searched Authors Active by Year:" + "--------------");
        repository.findAuthorsActiveByYear(year);

//        searchedBooks.stream()
//                .flatMap(book -> book.author().stream())
//                .filter(author ->
//                        author.deathYear() == null ||
//                                Integer.parseInt(author.deathYear()) >= year
//                )
//                .forEach(author -> {
//                    System.out.println("Author: " + author.name());
//                    System.out.println("from: " + author.birthYear() + " to " +
//                            (author.deathYear() != null ? author.deathYear() : "present"));
//                    System.out.println("--------------");
//                });



    }

    public void searchByLanguage(String language) {
        List<Books> booksList = repository.findBooksByLanguage(language);
        System.out.println("There are "+ booksList.size() + " books in that language.");
        booksList.forEach(System.out::println);

    }
}

