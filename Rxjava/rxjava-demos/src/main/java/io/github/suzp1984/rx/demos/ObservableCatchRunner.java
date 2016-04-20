package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * Created by jacobsu on 4/20/16.
 */
public class ObservableCatchRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("Observable Catch");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();

                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onError(new Throwable("Manully Error"));

                for (int i = 5; i < 10; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onCompleted();
            }
        }).onErrorResumeNext(Observable.range(15, 3))
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Catch completed"));

        System.out.println("Observable retry");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();

                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onError(new Throwable("Manully Error"));

                for (int i = 5; i < 10; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onCompleted();
            }
        }).retry(1).subscribe(x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Retry completed"));

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();

                int t = (int) (Math.random() * 6);
                for (int i = 0; i < t; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onError(new Throwable("Manully Error"));

                for (int i = t; i < 10; i++) {
                    subscriber.onNext(i);
                }

                subscriber.onCompleted();
            }
        }).retry(new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {

                return integer < 3;
            }
        }).subscribe(x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Retry completed"));


    }
}

