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

        System.out.println("--- Observable flatmap ---");
        Observable.range(1, 10).flatMap(x -> {
                    return Observable.just(x * x);
                }
        ).subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed"));

        System.out.println("--- Observable concatmap ---");
        Observable.range(1, 10).concatMap(x -> {
            return Observable.just(x * x);
        }).subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed"));


        System.out.println("--- Observable groupby ---");
        // Observable.range(1, 10).group
    }
}
