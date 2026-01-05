package oracleone.desafio_literalura.view;

import oracleone.desafio_literalura.service.utils.Functions;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {
    private Functions functions;
    private Scanner scanner = new Scanner(System.in);

    public Menu(Functions functions) {
        this.functions = functions;
    }

    public void runMenu(){
        int run = 1;

        while (run != 0){

            showOptionsMenu();
            System.out.println("Choose option...");
            var selectedOption = Integer.parseInt(scanner.nextLine());

            switch (selectedOption){

                case 1:
                    System.out.println("Enter Book title: ");
                    String title = scanner.nextLine();
                    functions.findBookByTitle(title, scanner);
                    break;
                case 2:
                    functions.listSearchedBooks();
                    break;
                case 3:
                    functions.listSearchedAuthors();
                    break;
                case 4:
                    System.out.println("Enter Year to Filter Search: ");
                    var year = Integer.parseInt(scanner.nextLine());
                    functions.searchAuthorsAliveByYear(year);
                    break;
                case 5:
                    System.out.println("Choose Language");
                    var language = scanner.nextLine();
                    functions.searchByLanguage(language);
                    break;
                case 0:
                    System.out.println("Shutting down...");
                    run = 0;
                    break;
                default:
                    System.out.println("invalid option");
            }

        }
        }





    public void showOptionsMenu(){
        System.out.println(   """
                --------------
                Selection Menu
                --------------
                
                1 - Find Book By Title
                
                2 - Books Already Searched
                
                3 - Authors Already Searched
                
                4 - Authors Active by year
                
                5 - Search Books By Language
                
                
                0 - Close Program
                
                --------------
                
                """);
    }
    }



