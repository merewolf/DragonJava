import data.Battle;
import httpclient.HttpClient;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        HttpClient httpClient = new HttpClient();
        Battle battle = new Battle();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        printMenu();
        while (!quit) {
            try {
                int action = scanner.nextInt();
                scanner.nextLine();
                switch (action) {
                    case 1:
                        battle.getResults(httpClient);
                        printMenu();
                        break;
                    case 2:
                        System.out.println("Thank you for playing");
                        quit = true;
                        break;
                    default:
                        printMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.reset();
                printMenu();
                scanner.next();
            }
        }
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("1 - to play\n" +
                "2 - to quit ");
    }
}