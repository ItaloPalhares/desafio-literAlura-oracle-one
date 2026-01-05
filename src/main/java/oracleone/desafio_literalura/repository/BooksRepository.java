package oracleone.desafio_literalura.repository;

import jakarta.transaction.Transactional;
import oracleone.desafio_literalura.model.Authors;
import oracleone.desafio_literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {
    Optional<Books> findByTitleIgnoreCase(String title);

    @Modifying
    @Transactional
    void deleteByTitleIgnoreCase(String title);

    @Query("""
    SELECT DISTINCT a
    FROM Books b
    JOIN b.authors a
    WHERE a.birthYear <= :year
      AND (a.deathYear IS NULL OR a.deathYear >= :year)
""")
    List<Authors> findAuthorsAliveByYear(@Param("year") Integer year);

    @Query("""
    SELECT DISTINCT b
    FROM Books b
    JOIN b.language l
    WHERE l = :language
""")
    List<Books> findBooksByLanguage(@Param("language") String language);


    List<Books> findAll();
}
