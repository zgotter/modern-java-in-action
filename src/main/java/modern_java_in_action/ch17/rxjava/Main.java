package modern_java_in_action.ch17.rxjava;

import io.reactivex.Observable;
import modern_java_in_action.ch17.TempInfo;

import java.util.concurrent.TimeUnit;

public class Main {
    private static void run1() {
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
        onePerSec.subscribe(i -> System.out.println(TempInfo.fetch("New York")));
    }

    private static void run2() {
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
        onePerSec.blockingSubscribe(i -> System.out.println(TempInfo.fetch("New York")));
    }

    public static void run3() {
        // 매 초마다 뉴욕의 온도 보고를 방출하는 Observable 만들기
        Observable<TempInfo> observable = TempObservable.getTemperature("New York");

        // 단순 Observer로 위 Observable에 가입해서 온도 출력하기
        observable.blockingSubscribe(new TempObserver());
    }

    public static void run4() {
        Observable<TempInfo> observable = TempObservable.getCelsiusTemperatures(
                "New York", "Chicago", "San Francisco"
        );

        observable.blockingSubscribe(new TempObserver());
    }

    public static void main(String[] args) {
//        run1();
//        run2();
//        run3();
        run4();
    }
}
