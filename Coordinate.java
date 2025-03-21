public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, char column) {
        column = Character.toUpperCase(column);
        if (row < 0 || row > 9 || column < 'A' || column > 'J'){
            System.out.println("Invalid input. Please enter a number (0-9) followed by a letter (A-J).");
        }
        this.row = row;
        this.col = column - 'A';
    }
    public int getRow(){
        return row;
    }

    public char getColumn(){
        return (char) ('A' + col);
    }
    @Override
    public String toString(){
        return "(" + row + ", " + getColumn() + ")";
    }

}
