package novi.basics;

import static novi.basics.Main.PLAYERINPUT;

public class Game {

    private Field[] board;
    private int maxRounds;
    private int drawCount;

    private Player player1;
    private Player player2;
    private Player activePlayer;

    public Game(Player player1, Player player2) {

        board = new Field[9];
        for (int fieldIndex = 0; fieldIndex < 9; fieldIndex++) {
            board[fieldIndex] = new Field(fieldIndex + 1);
        }

        this.maxRounds = board.length;

        this.player1 = player1;
        this.player2 = player2;

        drawCount = 0;
        this.activePlayer = player1;
    }

    public void play() {

        printBoard();

        // starten met de beurt (maximaal 9 beurten)
        for (int round = 0; round < maxRounds; round++) {

            if (checkWin(board) == 'W') {

                // actieve speler vragen om een veld te kiezen (1 - 9)
                System.out.println(activePlayer.getName() + ", please choose a field");

                // gekozen veld van de actieve speler opslaan
                int chosenField = PLAYERINPUT.nextInt();
                int chosenIndex = chosenField - 1;

                // als het veld bestaat
                if (chosenIndex < 9 && chosenIndex >= 0) {
                    //als het veld leeg is, wanneer er geen token staat
                    if (board[chosenIndex].isEmpty()) {
                        // wanneer de speler een token op het bord kan plaatsen
                        // de token van de actieve speler op het gekozen veld plaatsen
                        board[chosenIndex].setToken(activePlayer.getToken());

                        // het nieuwe speelbord tonen
                        printBoard();

                        // als het spel gewonnen is
                        checkWin(board);
                        if (checkWin(board) == player1.getToken()) {
                            // tonen wie er gewonnen heeft (de actieve speler)
                            // de actieve speler krijgt een punt
                            // de scores van de spelers tonen
                            player1.addScore();
                            System.out.println();
                            System.out.println(player1.getName() + " wins!");
                            System.out.println();
                            System.out.println("Score " + player1.getName() + " = " + player1.getScore());
                            System.out.println("Score " + player2.getName() + " = " + player2.getScore());
                        } else if (checkWin(board) == player2.getToken()) {
                            player2.addScore();
                            System.out.println();
                            System.out.println(player2.getName() + " wins!");
                            System.out.println();
                            System.out.println("Score " + player1.getName() + " = " + player1.getScore());
                            System.out.println("Score " + player2.getName() + " = " + player2.getScore());
                        } else if (round == maxRounds - 1) {
                            // wanneer we in de laatste beurt zijn en niemand gewonnen heeft
                            // aantal keer gelijk spel ophogen
                            // aantal keer gelijk spel tonen
                            drawCount++;
                            System.out.println();
                            System.out.println("Tie!");
                            System.out.println("Amount of tie'd games: " + drawCount);
                        }

                        // de beurt doorgeven aan de volgende speler (van speler wisselen)
                        // als de actieve speler, speler 1 is:
                        if (activePlayer == player1) {
                            // maak de actieve speler, speler 2
                            activePlayer = player2;
                        } else {
                            // maak de actieve speler weer speler 1
                            activePlayer = player1;
                        }
                    } else {
                        maxRounds++;
                        System.out.println("this field is not available, choose another");
                    }

                }
                // als het veld niet bestaat
                else {

                    maxRounds++;
                    System.out.println("the chosen field does not exist, try again");
                }

                // -- terug naar het begin van de volgende beurt
            }
            // vragen of de spelers nog een keer willen spelen
            //1: nog een keer spelen
            //2: van spelers wisselen
            //3: afsluiten
            // speler keuze opslaan
            // bij keuze 1: terug naar het maken van het bord
            // bij keuze 2: terug naar de start van de applicatie en het vragen van spelernamen
            // bij keuze 3: het spel en de applicatie helemaal afsluiten
        }
    }


    public void printBoard() {
        for (int fieldIndex = 0; fieldIndex < board.length; fieldIndex++) {
            System.out.print(board[fieldIndex].getToken() + " ");
            // als we het tweede veld geprint hebben of het vijfde veld geprint hebben
            // dan gaan we naar de volgende regel
            if(fieldIndex == 2 || fieldIndex == 5) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public char checkWin(Field[] board) {

        boolean hor1 = board[0].getToken() == board[1].getToken() && board[1].getToken() == board[2].getToken();
        boolean hor2 = board[3].getToken() == board[4].getToken() && board[4].getToken() == board[5].getToken();
        boolean hor3 = board[6].getToken() == board[7].getToken() && board[7].getToken() == board[8].getToken();

        boolean ver1 = board[0].getToken() == board[3].getToken() && board[3].getToken() == board[6].getToken();
        boolean ver2 = board[1].getToken() == board[4].getToken() && board[4].getToken() == board[7].getToken();
        boolean ver3 = board[2].getToken() == board[5].getToken() && board[5].getToken() == board[8].getToken();

        boolean dia1 = board[0].getToken() == board[4].getToken() && board[4].getToken() == board[8].getToken();
        boolean dia2 = board[2].getToken() == board[4].getToken() && board[4].getToken() == board[6].getToken();

        if (hor1 || hor2 || hor3 || ver1 || ver2 || ver3 || dia1 || dia2) {
            return activePlayer.getToken();
        }
        return 'W';
    }

    public void showMenu(Game game) {
        System.out.println();
        System.out.println("Play again, with different players, press 1");
        System.out.println("Play again, press 2");
        System.out.println("Quit, press 3");

        String playerChoice = PLAYERINPUT.next();

        if (playerChoice.equals("1")) {
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
            Game newGame = new Game(player1, player2);
            newGame.play();
        } else if (playerChoice.equals("2")) {
            Game newGame = new Game(player1, player2);
            newGame.play();
        } else if (playerChoice.equals("3")) {

        }
    }
    }

