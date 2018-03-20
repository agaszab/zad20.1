package pl.javastart.exercise.mockito;

import java.util.Map;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();

    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            if (item.getAgeRestriction() > human.getAge()) { throw new TooYoungException();
                } else if ((human.getJob().equals("Policjant")) && (!item.isLegal())) {
                throw new Illegal();
                } else if (human.getMoney() < item.getPrice()) { throw new NoMoney();
                } else { sell(human, item);
            }
        } else {  throw new OutOfStockException(); }


    }

    public void sell(Human human, Item item) {
        int humanCash = human.getMoney();
        human.setMoney(humanCash - item.getPrice());
        shop.setMoney(shop.getMoney() + item.getPrice());
        shop.getStock().replace(item, shop.getStock().get(item) - 1);
        if (shop.getStock().get(item) == 0) shop.getStock().remove(item);
        shop.playCashSound();
    }

}
