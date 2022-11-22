package worldOfZuul.Domain;

import java.util.HashMap;
import java.util.Set;

public class Inventory {
    ///Inventory that stores the trash player picked up in the game
    private HashMap<String, Item> trash;

    public Inventory() {
        trash = new HashMap<String, Item>();
    }

    public void addTrash(String string, Item item) {
        trash.put(string, item);
    }

    public void removeTrash (Item item) {
        trash.remove(item);
    }

    public String getInventoryString() {
        String returnString = "Din affaldspose:";
        Set<String> keys = trash.keySet();
        for (String trash : keys) {
            returnString += "\n" + "- " + trash;
        }
        return returnString;
    }
}
