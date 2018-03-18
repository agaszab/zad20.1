package pl.javastart.exercise.mockito;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.when;

public class ShopControllerTest {

    private ShopControllerTest mock;

    @Mock
    ShopRepository shopRepository;

    private ShopController shopController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        Shop shop = new Shop(0, stock);
        when(shopRepository.findShop()).thenReturn(shop);
        shopController = new ShopController(shopRepository);

    }

    @Test
    public void shouldSell(){
        Map<Item,Integer> stock=new HashMap<>();
        Item item1=new Item("chleb",1,32,true);
        Item item2=new Item("mleko",1,312,true);
        stock.put(item1,2);
        stock.put(item2,1);
        Human human=new Human ("Jan", 24, "nauczyciel", 75);

       shopController.sellItem(human, "mleko");

        Mockito.verify(shopController).sellItem(human, "mleko");

    }

    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoungling()  {
        // given
        Human humanChild=new Human ("Ania", 3, "children", 15);
        // when
       shopController.sellItem(humanChild, "Piwo");

    }

    @Test(expected = OutOfStockException.class)
    public void shouldNoProduct(){
        Map<Item,Integer> MockStock=new HashMap<>();
        Item item1=new Item("chleb",1,32,true);
        Item item2=new Item("mleko",1,312,true);
        MockStock.put(item1,2);
        MockStock.put(item2,1);
        Human human=new Human ("Jan", 24, "Policjant", 75);


        shopController.sellItem(human, "sok");
    }


    @Test
    public void slouldNodSellPolicemanIllegal(){
     Map<Item,Integer> stock=new HashMap<>();
     Item item =new Item("papierosy",18,32,false);
     stock.put(item,4);
     Human human=new Human ("Jan", 24, "Policjant", 75);
     shopController.sellItem(human, "papierosy");

    }

    @Test (expected = NoMoney.class)
    public void slouldNodSellNoMoney(){
        Map<Item,Integer> stock=new HashMap<>();
        Item item=new Item("chleb",1,32,true);
        stock.put(item,4);
        Human human=new Human ("Jan", 24, "nauczyciel", 5);
    }

    @Test
    public void shouldFindItemByName(){
        Map<Item,Integer> stock=new HashMap<>();
        Item item1=new Item("chleb",1,32,true);
        Item item2=new Item("mleko",1,312,true);
        stock.put(item1,2);
        stock.put(item2,1);
        Shop shop = new Shop(2000, stock);

        boolean stan;

        stan=shop.hasItem("chleb");
        Assert.assertThat(stan, CoreMatchers.is(true));

    }

    @Test
    public void shouldNotFindItemByName(){
        Map<Item,Integer> stock=new HashMap<>();
        Item item1=new Item("chleb",1,32,true);
        Item item2=new Item("mleko",1,312,true);
        stock.put(item1,2);
        stock.put(item2,1);
        Shop shop = new Shop(2000, stock);

        boolean stan;

        stan=shop.hasItem("cukier");
        Assert.assertThat(stan, CoreMatchers.is(false));

    }

}
