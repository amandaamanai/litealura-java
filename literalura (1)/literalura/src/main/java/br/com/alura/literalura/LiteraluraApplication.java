package br.com.alura.literalura;


import br.com.alura.literalura.model.DataBooks;
import br.com.alura.literalura.service.ConsumeApi;
import br.com.alura.literalura.service.TransformData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}