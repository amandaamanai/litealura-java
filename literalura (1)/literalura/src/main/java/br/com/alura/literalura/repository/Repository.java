package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;
import java.util.List;

public class Repository {


    public interface Repository extends JpaRepository<Books,Long> {
        List<Books> findByIdioma (String idoma);

        Integer countByIdioma(String idioma);

        @Query("SELECT a FROM Books B JOIN B.autor a")
        List<Author> buscarAuthors();

        @Query("SELECT a FROM Livro l JOIN l.autor a")
        List<Author> buscarAutores();

        @Query ("SELECT a FROM Livro l JOIN l.autor a WHERE a.anoNascimento <= :ano and a.anoFalecimento >= :ano")
        List<Author> buscarAutoresVivosNoAno(Year ano);

        @Query ("SELECT a FROM Livro l JOIN l.autor a WHERE a.autor = :autor")
        Author buscarAutorPeloNome(String autor);

    }
}
