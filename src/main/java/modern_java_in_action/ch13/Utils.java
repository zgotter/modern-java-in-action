package modern_java_in_action.ch13;

import java.util.List;

public class Utils {
    public static void paint(List<Resizable> l) {
        l.forEach(r -> {
            r.setAbsoluteSize(42, 42);
            r.draw();
        });

//        l.forEach(r -> { r.setAbsoluteSize(2, 2); });
    }
}
