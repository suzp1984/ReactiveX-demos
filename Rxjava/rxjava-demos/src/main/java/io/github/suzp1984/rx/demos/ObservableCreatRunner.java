package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by jacobsu on 3/23/16.
 */
public class ObservableCreatRunner implements IRxRunner {
    public void run() {
        System.out.println( "--- Observable from an array ---" );
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
    }
}
