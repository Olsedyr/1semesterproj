package worldOfZuul;

import java.util.ArrayList;

public class Inventory {
    ///Inventory that stores the trash player picked up in the game, need remove HashMap Item method in the Room class
    private ArrayList<Item> items;
    Inventory (ArrayList<Item> items) {
        this.items = items;
    }
    Inventory () {
        this(new ArrayList<Item>());
    }
    public void addItem (Item item) {
        if (!items.contains(item)) {
            items.add(item);
        }
    }
    public void removeItem (Item item) {
        items.remove(item);
    }
    public double getInventory () {
        double total = 0.0;
        for (Item item: items) {
            total += item.getItemPoints();
        }
        return total;
    }
    public void printInventory () {
        System.out.println("Trash Bag:");
        for (Item item: items) {
            System.out.println(" - "+item);
        }
    }
}
