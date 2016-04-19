package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.functions.Func2;

import java.util.List;

/**
 * Created by jacobsu on 4/19/16.
 */
public class ObservableCombiningRunner implements IRxRunner {
    @Override
    public void run() {

        System.out.println("Obervable combineLatest");
        Observable.combineLatest(Observable.range(10, 4), Observable.range(10, 3),
                (integer, integer2) -> integer + ":" + integer2)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("combineLatest Completed"));


        // Observable.range(1, 5).join(Observable.range(5, 5), null, null, null);
        System.out.println("merge");
        Observable.merge(Observable.range(1, 5), Observable.range(5, 5))
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Merge completed"));

        System.out.println("startWith");
        Observable.range(1, 5).startWith(10).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("StartWith completed")
        );

        //Observable.switchOnNext()
        Observable.range(1, 5).zipWith(Observable.range(5, 5), (x, y) -> x + ":" + y)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("ZipWith completed"));
    }
}
