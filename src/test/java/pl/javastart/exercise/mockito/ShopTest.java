package pl.javastart.exercise.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShopTest {

    @Test
    public void shouldFindItemByName(){                // test findItemByName(String itemName)
        Map<Item,Integer> stock=new HashMap<>();
        Item item=new Item("cukier",1,32,true);
        stock.put(item,2);
        Shop shop = new Shop(2000, stock);
        shop.findItemByName("cukier");
        Assert.assertEquals(shop.findItemByName("cukier"), item);

    }

    @Test
    public void shouldNotFindItemByName(){
        Map<Item,Integer> stock=new HashMap<>();
        Item item=new Item("chleb",1,32,true);
        stock.put(item,2);
        Shop shop = new Shop(2000, stock);
        shop.findItemByName("cukier");
        Assert.assertEquals(shop.findItemByName("cukier"), null);

    }


    @Test
    public void shouldFindItemInShop(){      // test hasItem(String itemName)
        Map<Item,Integer> stock=new HashMap<>();
        Item item=new Item("chleb",1,32,true);
        stock.put(item,2);
        Shop shop = new Shop(2000, stock);
        boolean stan;
        stan=shop.hasItem("chleb");
        Assert.assertThat(stan, CoreMatchers.is(true));

    }


    @Test
    public void shouldNotFindItemInShop(){
        Map<Item,Integer> stock=new HashMap<>();
        Item item=new Item("cukier",1,32,true);
        stock.put(item,2);
        Shop shop = new Shop(2000, stock);
        boolean stan;
        stan=shop.hasItem("chleb");
        Assert.assertThat(stan, CoreMatchers.is(false));

    }
}
