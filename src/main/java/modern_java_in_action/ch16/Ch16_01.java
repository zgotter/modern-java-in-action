package modern_java_in_action.ch16;

import java.util.concurrent.*;

public class Ch16_01 {
    public static void main(String[] args) {
        // 스레드 풀에 태스크를 제출하려면 ExecutorService를 만들어야 한다.
        ExecutorService executor = Executors.newCachedThreadPool();

        // Callable을 ExecutorService로 제출한다.
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation(); // 시간이 오래 걸리는 작업은 다른 스레드에서 비동기적으로 실행
            }
        });

        // 비동기 작업을 수행하는 동안 다른 작업을 한다.
        doSomethingElse();

        try {
            // 비동기 작업의 결과를 가져온다.
            // 결과가 준비되어 있지 않으면 호출 스레드가 블록된다.
            // 하지만 최대 1초까지만 기다린다.
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException ee) {
            // 계산 중 예외 발생
            throw new RuntimeException(ee);
        } catch (InterruptedException ie) {
            // 현재 스레드에서 대기 중 인터럽트 발생
            throw new RuntimeException(ie);
        } catch (TimeoutException te) {
            // Future가 완료되기 전에 타임아웃 발생
            throw new RuntimeException(te);
        }
    }

    public static Double doSomeLongComputation() {
        return 1.0;
    };

    public static void doSomethingElse() {

    };
}
