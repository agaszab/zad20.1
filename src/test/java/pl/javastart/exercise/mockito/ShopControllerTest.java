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

    private PlaySound mock;

    @Mock
    ShopRepository shopRepository;
    PlaySound playSound;

    private ShopController shopController;
    private Map<Item, Integer> stock;
    private Shop shop;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        shop = new Shop(0, stock);
        when(shopRepository.findShop()).thenReturn(shop);
        shopController = new ShopController(shopRepository);
    }

    @Test
    public void shouldSell(){
        Item item=new Item("mleko",1,3,true);
        stock.put(item,1);
        Human human=new Human ("Jan", 24, "nauczyciel", 75);

       shopController.sellItem(human, "mleko");

        Mockito.verify(playSound).play("http://jakisdziwek");

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


    @Test (expected = Illegal.class)
    public void slouldNodSellPolicemanIllegal(){
     Item item =new Item("papierosy",18,32,false);
     stock.put(item,4);
     Human human=new Human ("Jan", 24, "Policjant", 175);
     shopController.sellItem(human, "papierosy");

    }

    @Test (expected = NoMoney.class)
    public void slouldNodSellNoMoney(){
        Item item=new Item("cukier",1,32,true);
        stock.put(item,4);
        Human human=new Human ("Jan", 24, "nauczyciel", 5);
        shopController.sellItem(human, "cukier");
    }

    @Test
    public void slouldTakeMoneyHuman(){                       // test czy zabrano pieniądze od klienta po sprzedaży
        // Map<Item,Integer> stock=new HashMap<>();
        Item item=new Item("cukier",1,5,true);
        stock.put(item,4);
        Human human=new Human ("Jan", 24, "nauczyciel", 25);
        shopController.sellItem(human, "cukier");
        Assert.assertThat(human.getMoney(), CoreMatchers.is(20));
    }

    @Test
    public void slouldAddMoneyShop(){                       // test czy zabrano pieniądze od klienta po sprzedaży
        Item item=new Item("cukier",1,5,true);
        stock.put(item,4);
       // Shop shop = new Shop(100, stock);
        Human human=new Human ("Jan", 24, "nauczyciel", 25);
        shopController.sellItem(human, "cukier");
        Assert.assertThat(shop.getMoney(), CoreMatchers.is(5));
    }


}
