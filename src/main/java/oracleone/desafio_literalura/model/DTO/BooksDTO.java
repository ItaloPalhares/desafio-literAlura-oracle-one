package oracleone.desafio_literalura.model.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksDTO(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<AuthorsDTO> author,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double downloadCount
) {
}
