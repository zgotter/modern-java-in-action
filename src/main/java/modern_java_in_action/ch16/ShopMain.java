package modern_java_in_action.ch16;

public class ShopMain {
    public static void main(String[] args) {
        Shop shop = new Shop("BestShop");
        String price = shop.getPrice("Product Name");
        System.out.println(price);
    }
}
