package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by jacobsu on 4/20/16.
 */
public class ObservableUtilityRunner implements IRxRunner {
    @Override
    public void run() {
        System.out.println("Observable Delay");
        Observable.range(1, 5).delay(50, TimeUnit.MILLISECONDS)
                .subscribe(x -> System.out.println("delay " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Delay completed"));

        Observable.range(1, 5).delay(
                () -> Observable.just("Func0"),
                i -> Observable.just("Func1:" + i))
                .subscribe(x -> System.out.println("delay funcs " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Delay completed"));

        System.out.println("Observalbe Do");
        Observable.range(1, 5)
                .doOnNext(integer -> System.out.println("doOnNext: " + integer))
                .subscribe(x -> System.out.println("do " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Do completed"));

        System.out.println("Observable Materialize");
        Observable.range(1, 5).materialize().dematerialize()
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("materialize completed"));

        System.out.println("Observable ObserveOn: Thread #" + Thread.currentThread().getId());
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread #" + Thread.currentThread().getId());
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.newThread())
                .subscribe(x -> System.out.println("(Thread #" + Thread.currentThread().getId() + ") " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("ObserveOn completed"));

        System.out.println("Observable serialize");
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 11; i < 15; i++) {
                            subscriber.onNext(i);
                        }
                    }
                });

                t.start();

                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }

                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).serialize()
                .subscribe(x -> System.out.println("serialize: " + x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("Serialize completed"));


        System.out.println("Observale SubscribeOn Thread #" + Thread.currentThread().getId());
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onStart();
                for (int i = 0; i < 5; i++) {
                    System.out.println("onNext Thread #" + Thread.currentThread().getId());
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(
                x -> System.out.println("(thread #" + Thread.currentThread().getId() + ") " + x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("SubscribeOn Completed")
        );

        System.out.println("Observable timeinterval");
        Observable.range(1, 5).delay(50, TimeUnit.MILLISECONDS).timeInterval(Schedulers.newThread())
                .subscribe(x -> System.out.println("(time interval: " + x.getIntervalInMilliseconds() + ") " + x.getValue()),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("TimeInterval completed"));

        System.out.println("Observale TimeOut");
        Observable.range(1, 5).delay(50, TimeUnit.MILLISECONDS).timeout(10, TimeUnit.MILLISECONDS)
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("TimeOut completed"));

        System.out.println("Observable TimeStamp");
        Observable.just(1).delay(10, TimeUnit.MILLISECONDS).timestamp()
                .subscribe(x -> System.out.println("(TimeStamp: " + x.getTimestampMillis() + ") " + x.getValue()),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("TimeStamp completed"));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
