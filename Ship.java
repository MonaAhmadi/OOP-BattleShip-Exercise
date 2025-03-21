import java.util.ArrayList;
import java.util.List;

public class Ship {
    private String name;
    private int size;
    private List<Coordinate> coordinates; // لیست مختصات کشتی
    private int hits; // تعداد ضربه‌های خورده‌شده

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.coordinates = new ArrayList<>();
        this.hits = 0;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void addCoordinate(Coordinate coord) {
        if (coordinates.size() < size) {
            coordinates.add(coord);
        } else {
            throw new IllegalStateException("Cannot add more coordinates than ship size!");
        }
    }

    public boolean isHit(Coordinate shot) {
        for (Coordinate coord : coordinates) {
            if (coord.getRow() == shot.getRow() && coord.getColumn() == shot.getColumn()) {
                hits++;
                return true; // ضربه خورده است
            }
        }
        return false; // خطا (Miss)
    }

    public boolean isSunk() {
        return hits >= size; // اگر تمام بخش‌ها ضربه خورده باشند، غرق شده
    }

    @Override
    public String toString() {
        return name + " (" + size + " cells) - " + (isSunk() ? "SUNK!" : "Active");
    }
}