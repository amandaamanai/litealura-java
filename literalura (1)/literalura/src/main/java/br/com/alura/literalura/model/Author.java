package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String author;


    private Year anoNascimento;

    private Year anoFalecimento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    List<Books> books = new ArrayList<>();

    public Author() {}

    public Author(DataAuthors dataAuthors) {
        this.author = dataAuthors.autor();
        this.anoNascimento = Year.of(dataAuthors.anoNascimento());
        this.anoFalecimento = Year.of(dataAuthors.anoFalecimento());
    }

    public Author(String author, Year anoNascimento, Year anoFalecimento  ) {
        this.author = author;
        this.anoNascimento = anoNascimento;
        this.anoFalecimento = anoFalecimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Year anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Year getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Year anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
    @Override
    public String toString() {
        return  "------------------- Autor -----------------" +
                "\nAutor:           " + author +
                "\nAno Nascimento:  " + anoNascimento +
                "\nAno Falecimento: " + anoFalecimento +
                "\nLivros:          " + books.stream().map(l -> l.getTitulo()).collect(Collectors.toList())+
                "\n-------------------------------------------\n";
    }
}