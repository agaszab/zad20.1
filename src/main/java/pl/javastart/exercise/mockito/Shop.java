package pl.javastart.exercise.mockito;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Shop {

    private int money;
    private Map<Item, Integer> stock;

    public Shop(int money, Map<Item, Integer> stock) {
        this.money = money;
        this.stock = stock;
    }
    public void setMoney(int money) {
        this.money = money;
    }


    void playCashSound() {           // zakładamy, że ta metoda odtwarza dźwięk
        PlaySound playSound = new PlaySound();
        playSound.play("https://www.youtube.com/watch?v=Wj_OmtqVLxY");
    }


    public boolean hasItem(String itemName) {     // czy w sklepie jest taki towar
        for(Item item: stock.keySet()) {
            if (item.getName().equals(itemName)) {return true;}
        }
            return false;
    }


    public Item findItemByName(String itemName) {      // wyszukujący przedmiot po jego nazwie
        for(Item item: stock.keySet()) {
            if (item.getName().equals(itemName)) {return item;}
        }
          return null;
    }


    public int getMoney() { return money; }


    public Map<Item, Integer> getStock() { return stock; }

}



