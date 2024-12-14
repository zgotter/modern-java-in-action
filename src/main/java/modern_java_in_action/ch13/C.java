package modern_java_in_action.ch13;

//public class C implements B, A {
//    public static void main(String[] args) {
//        new C().hello();
//    }
//}


public class C extends D implements B, A {
    public static void main(String[] args) {
        new C().hello();
    }
}