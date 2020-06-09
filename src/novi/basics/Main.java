package novi.basics;
import java.util.Scanner;

public class Main {

    public static Scanner PLAYERINPUT = new Scanner(System.in);

    public static void main(String[] args) {


        // -- vanaf hier gaan we het spel opnieuw spelen met andere spelers (nadat op het eind  keuze 2 gekozen is)
        // de 1e speler om zijn naam vragen
        System.out.println("Player 1, what is your name?");
        // de naam van de 1e speler opslaan
        String player1Name = PLAYERINPUT.next();
        Player player1 = new Player(player1Name, 'X');
        // de 2e speler om zijn naam vragen
        System.out.println("Player 2, what is your name?");
        // de naam van de 2e speler opslaan
        String player2Name = PLAYERINPUT.next();
        Player player2 = new Player(player2Name, 'O');

        Game game = new Game(player1, player2);
        game.play();

        while(game.keepPlaying) {
            game.showMenu(game);
        }
    }
}