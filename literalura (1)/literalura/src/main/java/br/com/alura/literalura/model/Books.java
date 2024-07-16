package br.com.alura.literalura.model;

import jakarta.persistence.*;

public class Books {
import jakarta.persistence.*;

    @Entity
    @Table(name = "livros")
    public class Books {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private String titulo;

        @ManyToOne(cascade = CascadeType.ALL)
        private Author autor;

        private String idioma;

        // método para pegar os dados da classe record DataBooks.
        public Books(DataBooks dataBooks) {
            this.titulo = dataBooks.titulo();
            Author autor = new Author(dataBooks.autor().get(0));
            this.autor = autor;
            this.idioma = dataBooks.idioma().get(0);

        }

        public Books(String titulo, Author autor, String idioma ) {
            this.titulo = titulo;
            this.autor = autor;
            this.idioma = idioma;

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Author getAutor() {
            return autor;
        }

        public void setAutor(Author autor) {
            this.autor = autor;
        }

        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }

        @Override
        public String toString() {
            return  "------------------ LIVRO ------------------" +
                    "\nTítulo:             " + titulo +
                    "\nAutor:              " + autor.getAuthor() +
                    "\nIdioma:             " + idioma +
                    "\n-------------------------------------------\n";
        }

    }
}
