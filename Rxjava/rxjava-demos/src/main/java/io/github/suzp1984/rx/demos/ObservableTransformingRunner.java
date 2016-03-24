package io.github.suzp1984.rx.demos;

import rx.Observable;

/**
 * Created by jacobsu on 3/24/16.
 */
public class ObservableTransformingRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("--- Observable buffer ---");
        Observable.range(1, 10).buffer(2).subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed"));
    }
}
