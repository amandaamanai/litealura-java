package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class DataBooks {

    import java.util.List;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record DataBooks(@JsonAlias("title") String titulo,
                            @JsonAlias("authors") List<DataAuthors> autor,
                            @JsonAlias("language") List<String> idioma) {


    }
}
