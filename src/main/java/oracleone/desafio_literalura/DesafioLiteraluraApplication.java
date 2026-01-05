package oracleone.desafio_literalura;

import oracleone.desafio_literalura.service.utils.Functions;
import oracleone.desafio_literalura.view.Menu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraluraApplication implements CommandLineRunner {

    private final Menu menu;

    public DesafioLiteraluraApplication(Menu menu){
        this.menu = menu;
    }

	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        menu.runMenu();
    }
}
