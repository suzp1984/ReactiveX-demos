package io.github.suzp1984.rx.demos;

import rx.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by jacobsu on 4/19/16.
 */
public class ObservableFilteringRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("Filtering Debounce");
        Observable.range(1, 10).debounce(20, TimeUnit.MILLISECONDS)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("debounce oncompleted"));

        System.out.println("Filtering Distinct");
        Observable.from(new Integer[] {2, 3, 3, 2, 1, 5, 1, 2, 3, 5})
                .subscribe(x -> System.out.println("Distinct: " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Distinct completed"));

        System.out.println("Filtering ElementAt");
        Observable.range(1, 5).elementAt(2).subscribe(
                x -> System.out.println("ElementAt " + x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("ElementAt completed")
        );

        System.out.println("Filter");
        Observable.range(1, 10).filter(x -> { return x % 2 == 0;})
                .subscribe(x -> System.out.println("Filter " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Filter completed"));

        System.out.println("Filtering first");
        Observable.range(1, 10).first(x -> {return x % 3 == 0;}).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("first completed")
        );

        System.out.println("Filtering ignore Elements");
        Observable.range(1, 10).ignoreElements()
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("ignore Elements completed"));

        System.out.println("Filtering Last");
        Observable.range(1, 10).last(x -> {return x % 3 == 0;})
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Last completed"));

        System.out.println("Filtering Sample");
        Observable.range(1, 10).sample(2, TimeUnit.MICROSECONDS)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Sample completed"));

        System.out.println("Filtering Skip");
        Observable.range(1, 5).skip(3).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Skip completed"));

        System.out.println("Filtering SkipLast");
        Observable.range(1, 5).skipLast(3).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("SkipLast completed")
        );

        System.out.println("Filtering Take");
        Observable.range(1, 5).take(2).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Take completed")
        );

        System.out.println("Filtering TakeLast");
        Observable.range(1, 5).takeLast(2).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("TakeLast Completed")
        );
    }
}
