package pl.javastart.exercise.mockito;

import java.util.Map;

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

    void playCashSound() {
        PlaySound playSound = new PlaySound();
        playSound.play("https://www.youtube.com/watch?v=Wj_OmtqVLxY");

        /* zakładamy, że ta metoda odtwarza dźwięk https://www.youtube.com/watch?v=Wj_OmtqVLxY, nie musimy jej implementować,
        sprawdzamy tylko czy została uruchomiona */
    }

    public boolean hasItem(String itemName) {
        // TODO dodaj kod sprawdzający czy sklep ma w asortymencie przedmot o danej nazwie

      /*   for(Item item: stock.keySet()) {
           if (item.getName().equals(itemName)) return true;
        }*/

        if (stock.containsKey(itemName) && (stock.get(itemName)>0)) {
            return true;
        } else
        return false;
    }

    public Item findItemByName(String itemName) {
        // TODO dodaj kod wyszukujący przedmiot po jego nazwie

        for(Item item: stock.keySet()) {
            if (item.getName().equals(itemName)) return item ;
        }

        return null;
    }

    public int getMoney() {
        return money;
    }

    public Map<Item, Integer> getStock() {
        return stock;
    }


}
