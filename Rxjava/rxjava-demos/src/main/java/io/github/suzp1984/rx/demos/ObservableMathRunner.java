package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacobsu on 4/20/16.
 */
public class ObservableMathRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("Observable concat");
        Observable.concat(Observable.range(1, 3), Observable.range(5, 2))
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("concat completed"));

        System.out.println("Observable count");
        Observable.range(1, 2).count()
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("count completed"));

        System.out.println("observable reduce");
        Observable.range(1, 5).reduce((x, y) -> x + y)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("reduce completed"));

        System.out.println("Observable collect");
        Observable.range(1, 5).collect(() -> new ArrayList<Integer>(),
                                        (l, i) -> l.add(i))
                .subscribe(x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Collect completed"));
    }
}
