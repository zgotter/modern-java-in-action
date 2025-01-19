package modern_java_in_action.ch16;

import static modern_java_in_action.ch16.Util.delay;
import static modern_java_in_action.ch16.Util.format;

public class Discount {
    public enum Code {
        NONE(0),
        SILVER(5),
        GOLD(10),
        PLATINUM(15),
        DIAMOND(20);

        private final int percentage;

        Code(int percentage) { this.percentage = percentage; }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay(); // Discount 서비스의 응답 지연을 흉내낸다.
        return format(price * (100 - code.percentage) / 100);
    }
}
