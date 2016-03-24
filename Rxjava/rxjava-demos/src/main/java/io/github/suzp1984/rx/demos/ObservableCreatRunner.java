package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by jacobsu on 3/23/16.
 */
public class ObservableCreatRunner implements IRxRunner {
    public void run() {
        System.out.println("--- Observable from an array ---");
        final Integer[] intArray = new Integer[] {1, 2, 3, 4, 5};
        Observable.from(intArray).subscribe(new Observer<Integer>() {
            public void onCompleted() {
                System.out.println("completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("--- Observable from a list ---");
        List<Integer> intList = Arrays.asList(intArray);

        Observable.from(intList).subscribe(new Observer<Integer>() {
            public void onCompleted() {
                System.out.println("completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("--- Observable create from OnSubscribe class ---");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            public void call(Subscriber<? super Integer> subscriber) {
                for (Integer i : intArray) {
                    subscriber.onNext(i);
                }

                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Integer>() {
            public void onCompleted() {
                System.out.println("completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("--- Observable just  ---");
        Observable.just(intArray).subscribe(new Observer<Integer[]>() {
            public void onCompleted() {
                System.out.println("completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(Integer[] integers) {
                System.out.println(integers);
            }
        });

        System.out.println("--- Observable range ---");
        Observable.range(1, 5).subscribe(new Observer<Integer>() {
            public void onCompleted() {
                System.out.println("completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("--- Observable repeat ---");
        Observable.range(1, 5).repeat(2).subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed."));

        System.out.println("--- Observable interval ---");
        Observable.interval(250, TimeUnit.MILLISECONDS).subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed."));

        System.out.println("--- Observable timer ---");
        Observable.timer(1000, TimeUnit.MILLISECONDS).timestamp().subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed."));
        System.out.println("---- wait for 1 seconds ---");
    }
}
