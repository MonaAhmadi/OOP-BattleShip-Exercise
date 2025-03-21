import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private Scanner scanner;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public Board getBoard() {
        return board;
    }

    public void placeShip(Ship ship) {
        while (true) {
            System.out.println(name + "، مختصات کشتی " + ship.getName() + " را وارد کن (مثلاً: 2 B افقی یا عمودی)");
            int row = scanner.nextInt();
            char col = scanner.next().charAt(0);
            String direction = scanner.next().toLowerCase(); // "افقی" یا "عمودی"

            if (placeShipOnBoard(ship, row, col, direction)) {
                System.out.println("✅ کشتی " + ship.getName() + " اضافه شد!");
                break;
            } else {
                System.out.println("❌ محل نامعتبر است! دوباره امتحان کن.");
            }
        }
    }

    private boolean placeShipOnBoard(Ship ship, int row, char col, String direction) {
        col = Character.toUpperCase(col);
        int colIndex = col - 'A';

        if (direction.equals("افقی")) {
            if (colIndex + ship.getSize() > 10) return false;
            for (int i = 0; i < ship.getSize(); i++) {
                ship.addCoordinate(new Coordinate(row, (char) (col + i)));
            }
        } else if (direction.equals("عمودی")) {
            if (row + ship.getSize() > 10) return false;
            for (int i = 0; i < ship.getSize(); i++) {
                ship.addCoordinate(new Coordinate(row + i, col));
            }
        } else {
            return false;
        }

        return board.addShip(ship);
    }

    public void takeTurn(Player opponent) {
        while (true) {
            System.out.println(name + "، نوبت توئه! مختصات حمله رو وارد کن (مثلاً: 3 C)");
            int row = scanner.nextInt();
            char col = scanner.next().charAt(0);

            Coordinate target = new Coordinate(row, col);
            if (opponent.getBoard().attack(target)) {
                System.out.println("💥 حمله موفق بود! 🎯");
            } else {
                System.out.println("💦 خطا! 🚫");
            }

            break;
        }
    }
}
