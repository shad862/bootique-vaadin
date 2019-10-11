import java.util.ArrayList;
import java.util.WeakHashMap;

class CoffeeFlavour {
    private final String name;
    private static final WeakHashMap<String, CoffeeFlavour> CACHE = new WeakHashMap<>();

    // only intern() can call this constructor
    private CoffeeFlavour(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static CoffeeFlavour intern(String name) {
        synchronized (CACHE) {
            return CACHE.computeIfAbsent(name, CoffeeFlavour::new);
        }
    }

    public static int flavoursInCache() {
        synchronized (CACHE) {
            return CACHE.size();
        }
    }
}

@FunctionalInterface
interface Order {
    void serve();

    static Order of(String flavourName, int tableNumber) {
        CoffeeFlavour flavour = CoffeeFlavour.intern(flavourName);
        return () -> System.out.println("Serving " + flavour + " to table " + tableNumber);
    }
}

class CoffeeShop {
    private final ArrayList<Order> orders = new ArrayList<>();

    public void takeOrder(String flavour, int tableNumber) {
        orders.add(Order.of(flavour, tableNumber));
    }

    public void service() {
        orders.forEach(Order::serve);
    }
}

public class FlyWeight {
    public static void main(String[] args) throws InterruptedException {
        CoffeeShop shop = new CoffeeShop();
        String cap = new String("Cappuccino");
        String espresso = "Espresso";
        String frappe = "Frappe";

        shop.takeOrder(cap, 2);
        shop.takeOrder(frappe, 1);
        shop.takeOrder(espresso, 1);
        shop.takeOrder(frappe, 897);
        shop.takeOrder(cap, 97);
        shop.takeOrder(frappe, 3);
        shop.takeOrder(espresso, 3);
        shop.takeOrder(cap, 3);
        shop.takeOrder(espresso, 96);
        shop.takeOrder(frappe, 552);
        shop.takeOrder(cap, 121);
        shop.takeOrder(espresso, 121);
        cap = null;
        System.gc();
        Thread.sleep(10000);



        shop.service();
        System.out.println("CoffeeFlavor objects in cache: " + CoffeeFlavour.flavoursInCache());
    }
}