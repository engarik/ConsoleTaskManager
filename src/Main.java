import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandHandler handler = new CommandHandler();

        do handler.handleCommand(scanner.nextLine());
        while (handler.isRunning());

    }
}
