package oracleone.desafio_literalura.model;

import jakarta.persistence.*;
import oracleone.desafio_literalura.model.DTO.BooksDTO;

import java.util.List;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private Authors authors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "book_languages",
            joinColumns = @JoinColumn(name = "book_id")
    )
    @Column(name = "language")
    private List<String> language;

    private Double downloadCount;


    public Books(){
    }

    public Books(BooksDTO booksDTO){
        this.title = booksDTO.title();
        this.authors = booksDTO.author().stream()
                .findFirst()
                .map(a-> new Authors(a.name(), a.birthYear(), a.deathYear()))
                .orElse(null);
        this.language = booksDTO.languages();

        this.downloadCount = booksDTO.downloadCount();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return

                ", title: '" + title + '\'' + " author: " + authors.getName() + ", language: " + language;
    }

    public void printAuthors(){
        System.out.println("Author: " + this.authors.getName() + ", from " + this.authors.getBirthYear() + " to " +
                this.authors.getDeathYear());
    }
}
