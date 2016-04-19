package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by jacobsu on 3/24/16.
 */
public class ObservableTransformingRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("--- Observable buffer ---");
        Observable.range(1, 10).buffer(2).subscribe(x -> System.out.println("buffer: " + x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("buffer completed"));

        Observable.range(1, 10).buffer(2, 4).subscribe(x -> System.out.println("buffer.skip: " + x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("buffer.skip completed"));

        System.out.println("--- Observable flatmap ---");
        Observable.range(5, 8).flatMap(x -> {
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
        Observable.range(1, 10).groupBy(n -> n % 2 == 0)
                .subscribe(x -> {

                            x.subscribe(i -> {
                                        System.out.println(x.getKey() + ": " + i);
                                    },
                                    e -> {
                                    },
                                    () -> {
                                        System.out.println(x.getKey() + ": completed");
                                    });
                        },
                        e -> {
                            System.out.println(e.toString());
                        },
                        () -> {
                            System.out.println("groupBy completed");
                        });

        System.out.println("Observable Map");
        Observable.range(1, 5).map(i -> { return 10 * i;})
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("map oncompleted"));

        System.out.println("Observable Scan");
        Observable.range(1, 5).scan((x, y) -> x + y)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Scan completed"));

        System.out.println("Observable Window");
        Observable.range(1, 10).window(2)
                .subscribe(x -> {x.subscribe(i -> {System.out.println(i);},
                                i -> {System.out.println(i.toString());},
                                () -> {System.out.println("window internal completed");});
                        },
                        e -> {},
                        () -> {System.out.println("Window completed");});
    }
}
