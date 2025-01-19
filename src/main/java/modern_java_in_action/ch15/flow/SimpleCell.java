package modern_java_in_action.ch15.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;
    private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    public void subscribe(Consumer<? super Integer> onNext) {
        subscribers.add(new Subscriber<>() {
            @Override
            public void onNext(Integer val) {
                onNext.accept(val);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {}

            @Override
            public void onSubscribe(Subscription subscription) {}
        });
    }

    private void notifyAllSubscribers() {
        // 새로운 값이 있음을 모든 구독자에게 알리는 메서드
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer newValue) {
        this.value = newValue; // 구독한 셀에 새 값이 생겼을 때 값을 갱신해서 반응함
        System.out.println(this.name + ":" + this.value); // 값을 콘솔로 출력하지만 실제로는 UI의 셀을 갱신할 수 있음
        notifyAllSubscribers(); // 값이 갱신되었음을 모든 구독자에게 알림
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    public static void main(String[] args) {
        SimpleCell c3 = new SimpleCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);

        c1.onNext(10);
        c2.onNext(20);
    }
}
