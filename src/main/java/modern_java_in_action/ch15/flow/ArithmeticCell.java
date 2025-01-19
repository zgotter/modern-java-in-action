package modern_java_in_action.ch15.flow;

public class ArithmeticCell extends SimpleCell {
    private int left;
    private int right;

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(int left) {
        this.left = left;
        onNext(left + this.right); // 셀 값을 갱신하고 모든 구독자에 알림
    }

    public void setRight(int right) {
        this.right = right;
        onNext(this.left + right); // 셀 값을 갱신하고 모든 구독자에 알림
    }

    private static void test1() {
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);
    }

    private static void test2() {
        ArithmeticCell c5 = new ArithmeticCell("C5");
        ArithmeticCell c3 = new ArithmeticCell("C3");

        SimpleCell c4 = new SimpleCell("C4");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c3.subscribe(c5::setLeft);
        c4.subscribe(c5::setRight);

        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);
        c4.onNext(1);
        c4.onNext(3);
    }

    public static void main(String[] args) {
        test1();
        System.out.println("-----------------------");
        test2();
    }
}
