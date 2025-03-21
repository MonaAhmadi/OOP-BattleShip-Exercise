import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int SIZE = 10;
    private char[][] grid;
    private List<Ship> ships;

    public Board() {
        grid = new char[SIZE][SIZE];
        ships = new ArrayList<>();

        // مقداردهی اولیه گرید با '-'
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '-';
            }
        }
    }

    public boolean addShip(Ship ship) {
        for (Coordinate coord : ship.getCoordinates()) {
            int row = coord.getRow();
            int col = coord.getColumn() - 'A';

            // بررسی اینکه خانه خالی است
            if (grid[row][col] != '-') {
                return false; // محل اشغال شده، کشتی قابل قرار دادن نیست
            }
        }

        // اضافه کردن کشتی به لیست
        ships.add(ship);
        for (Coordinate coord : ship.getCoordinates()) {
            int row = coord.getRow();
            int col = coord.getColumn() - 'A';
            grid[row][col] = 'S'; // نمایش کشتی در گرید
        }

        return true;
    }

    public boolean attack(Coordinate shot) {
        int row = shot.getRow();
        int col = shot.getColumn() - 'A';

        if (grid[row][col] == 'S') { // برخورد با کشتی
            grid[row][col] = 'X'; // علامت‌گذاری اصابت
            for (Ship ship : ships) {
                if (ship.isHit(shot) && ship.isSunk()) {
                    System.out.println(ship.getName() + " غرق شد!");
                }
            }
            return true; // Hit
        } else if (grid[row][col] == '-') { // آب
            grid[row][col] = 'O'; // علامت‌گذاری خطا (Miss)
            return false; // Miss
        }

        return false; // قبلاً به این مختصات شلیک شده
    }

    public void printBoard() {
        System.out.print("  ");
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
