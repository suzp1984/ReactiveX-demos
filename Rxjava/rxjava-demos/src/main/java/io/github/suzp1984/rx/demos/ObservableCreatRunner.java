package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ObservableCreatRunner implements IRxRunner {
    public void run() {

        final Integer[] intArray = new Integer[] {1, 2, 3, 4, 5};
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

        Observable<Integer> deferObservable = Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {
                return Observable.from(intArray);
            }
        });

        System.out.println("Observale defer created, then wait a moment. Start next Observable first.");

        System.out.println("--- Observable from an array ---");
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

        System.out.println("subscribe defered observable again.");
        deferObservable.subscribe(new Observer<Integer>() {
            public void onCompleted() {
                System.out.println("defer observable completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        });

        System.out.println("Observable empty");
        Observable.empty().subscribe((Object t) -> {
                    System.out.println("onNext");
                },
                (Throwable e) -> {
                    System.out.println(e.toString());
                },
                () -> {
                    System.out.println("Empty Observable onCompleted");
                });

        System.out.println("Observable never");
        Observable.never().subscribe((Object t) -> {
                    System.out.println("onNext");
                },
                (Throwable e) -> {
                    System.out.println(e.toString());
                },
                () -> {
                    System.out.println("Never Observable onCompleted");
                });

        System.out.println("Observable Throw");
        Observable.error(new Throwable("Oh, Throw an error")).subscribe(
                (Object t) -> {
                    System.out.println("onNext");
                },
                (Throwable e) -> {
                    System.out.println(e.toString());
                },
                () -> {
                    System.out.println("Observable Error.");
                }
        );


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

        System.out.println("--- Observable interval ---");
        Observable.interval(100, TimeUnit.MILLISECONDS).subscribe(x -> System.out.println("interval " + x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed."));


        System.out.println("--- Observable just  ---");
        Observable.just("just").subscribe(new Observer<String>() {
            public void onCompleted() {
                System.out.println("just completed.");
            }

            public void onError(Throwable throwable) {
                System.out.println(throwable.toString());
            }

            public void onNext(String s) {
                System.out.println(s);
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
        Observable.range(6, 8).repeat(2).subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed."));

        System.out.println("Observable fromCallable");
        Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "From Callable";
            }
        }).subscribe(s -> {
                    System.out.println("from Callable: " + s);
                },
                e -> {
                    System.out.println(e.toString());
                },
                () -> {
                    System.out.println("fromCallable completed");
                });

        System.out.println("--- Observable timer ---");
        Observable.timer(500, TimeUnit.MILLISECONDS).timestamp().subscribe(x -> System.out.println(x),
                x -> System.out.println(x.toString()),
                () -> System.out.println("completed."));
        Observable.timer(500, TimeUnit.MILLISECONDS).subscribe(l -> { System.out.println("timer: " + l);},
                e -> { System.out.println(e.toString());},
                () -> {System.out.println("Observable Timer onCompleted");});

        System.out.println("---- wait for 1 seconds ---");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
