package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by jacobsu on 4/20/16.
 */
public class ObservableConditionalRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("Observable All");
        Observable.range(1, 5).all(x -> x > 0)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("All completed"));

        System.out.println("Observable Amb");
        Observable.amb(Observable.range(1, 5).delay(50, TimeUnit.MILLISECONDS),
                Observable.range(5, 5))
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Amb completed"));

        System.out.println("Observable contains");
        Observable.range(1, 5).contains(4)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("contains completed"));

        System.out.println("Observable DefaultIfEmpty");
        Observable.empty().defaultIfEmpty("oh, empty")
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("DefaultIfEmpty Completed"));

        System.out.println("Observable SequenceEqual");
        Observable.sequenceEqual(Observable.range(1, 5), Observable.range(1, 5))
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("sequence Equal completed"));

        System.out.println("Observalbe SkipUntil");
        Observable.range(1, 5).skipUntil(Observable.range(5, 7).subscribeOn(Schedulers.newThread()))
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("SkipUtil completed"));

        System.out.println("Observable SkipWhile");
        Observable.range(1, 5).skipWhile(x -> x < 3).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("SkipWhile Completed")
        );

        System.out.println("Observable TakeUntil");
        Observable.range(1, 5).takeUntil(x -> x > 3).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("TakeUntil completed")
        );

        System.out.println("Observable TakeWhile");
        Observable.range(1, 5).takeWhile(x -> x < 3).subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("TakeWhile completed")
        );
    }
}
