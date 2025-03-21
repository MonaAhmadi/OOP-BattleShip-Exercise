import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Player opponent;
    private Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);
        setupPlayers();
        placeShipsForPlayers();
    }

    private void setupPlayers() {
        System.out.print("نام بازیکن ۱: ");
        String name1 = scanner.nextLine();
        player1 = new Player(name1);

        System.out.print("نام بازیکن ۲: ");
        String name2 = scanner.nextLine();
        player2 = new Player(name2);

        currentPlayer = player1;
        opponent = player2;
    }

    private void placeShipsForPlayers() {
        Ship[] ships = {
                new Ship("Aircraft Carrier", 5),
                new Ship("Battleship", 4),
                new Ship("Submarine", 3),
                new Ship("Patrol Boat", 2)
        };

        for (Player player : new Player[]{player1, player2}) {
            System.out.println("\n🚢 نوبت " + player.getName() + " برای قرار دادن کشتی‌ها است.");
            for (Ship ship : ships) {
                player.placeShip(new Ship(ship.getName(), ship.getSize()));
                player.getBoard().printBoard();
            }
        }
    }

    public void start() {
        System.out.println("\n🎯 بازی شروع شد!");

        while (true) {
            System.out.println("\n🎯 نوبت " + currentPlayer.getName() + " است.");
            currentPlayer.takeTurn(opponent);

            if (checkWinCondition(opponent)) {
                System.out.println("\n🏆 " + currentPlayer.getName() + " برنده شد!");
                break;
            }

            switchPlayers();
        }
    }

    private boolean checkWinCondition(Player player) {

        return true;
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }
}