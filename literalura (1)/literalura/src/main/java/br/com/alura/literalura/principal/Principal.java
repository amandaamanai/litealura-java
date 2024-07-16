import br.com.alura.literalura.model.Author;
import br.com.alura.literalura.model.Books;
import br.com.alura.literalura.model.DataBooks;
import br.com.alura.literalura.repository.Repository;
import br.com.alura.literalura.service.ConsumeApi;
import br.com.alura.literalura.service.TransformData;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private ConsumeApi consumeApi = new ConsumeApi();

    private TransformData transformData = new TransformData();

    private Scanner reading = new Scanner(System.in);

    private Repository repository;

    private String urlApi = "https://gutendex.com/books/?search=";

    public Principal(Repository repository) {
        this.repository = repository;
    }

    public void exibeMenu(){
        int opcao = -1;
        while (opcao != 0){
            String menu = """
                \n-------------------------------------------
                       *** Escolha uma das opções ***
                -------------------------------------------
                1 - Buscar livros por título
                2 - Listar livros cadastrados
                3 - Listar Autores cadastrados
                4 - Listar Autores vivos em determinado ano
                5 - Listar Livros em determinado idioma
                6 - Listar Livros em um determinado idioma             
                
                0 - Sair
                -------------------------------------------
                """;
            try {
                System.out.println(menu);
                opcao = reading.nextInt();
                reading.nextLine();

                switch (opcao){
                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresVivosNoAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        quantidadeLivrosPorIdioma();
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            }catch (InputMismatchException e){
                System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
                reading.nextLine();
            }
        }
    }

    private void buscarLivro() {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        var nomeLivro = reading.nextLine();
        System.out.println("Buscando...");
        String enderecoBusca = urlApi.concat(nomeLivro.replace(" ", "+").toLowerCase().trim());

        String json = consumeApi.theDataFromApi(enderecoBusca);
        String jsonLivro = transformData.extractingJson(json, "results");

        List<DataBooks> livrosDTO = transformData.gettingTheList(jsonLivro, DataBooks.class);

        if (livrosDTO.size() > 0) {
            Books books = new Books(livrosDTO.get(0));

            //Verifica se o Autor já está cadastrado
            Author autor = repository.buscarAutorPeloNome(books.getAutor().getAuthor());
            if (autor != null) {
                books.setAutor(null);
                repository.save(books);
                books.setAutor(autor);
            }
            books = repository.save(books);
            System.out.println(books);
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private void listarLivros() {
        List<Books> livros = repository.findAll();
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Author> autores = repository.buscarAutores();
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosNoAno() {
        try {
            System.out.println("Digite o ano:");
            int ano = reading.nextInt();
            reading.nextLine();

            List<Author> autores = repository.buscarAutoresVivosNoAno(Year.of(ano));
            autores.forEach(System.out::println);
        }catch (InputMismatchException e){
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            reading.nextLine();
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Digite o idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        String idioma = reading.nextLine();
        List<Books> livros = repository.findByIdioma(idioma);
        if (!livros.isEmpty()){
            livros.forEach(System.out::println);
        }else{
            System.out.println("Não exite livros nesse idioma cadastrado");
        }
    }

    private void quantidadeLivrosPorIdioma() {
        System.out.println("""
                Digite o idioma para busca
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        String idioma = reading.nextLine();
        Integer quantidadeIdioma = repository.countByIdioma(idioma);
        System.out.printf("O idioma %s tem %d livros cadastrado\n", idioma, quantidadeIdioma);
    }

}