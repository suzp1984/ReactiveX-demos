package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * Created by jacobsu on 4/20/16.
 */
public class ObservableConnectableRunner implements IRxRunner {
    @Override
    public void run() {

        System.out.println("Observable Connectable");
        ConnectableObservable<Integer> o;

        o = Observable.range(1, 5).publish();

        o.subscribe(x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("Connectable Completed"));

        System.out.println("Before Connect and after subscribe");

        o.connect();

        System.out.println("Observable RefCount");
        ConnectableObservable<Integer> refCount;
        refCount = Observable.range(1, 5).publish();

        refCount.refCount().subscribe(
                x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("RefCount completed")
        );
        refCount.connect();

        System.out.println("Observable replay");
        ConnectableObservable<Integer> replay;

        replay = Observable.range(1, 5).replay();
        replay.subscribe(
                x -> {
                    System.out.println(x);
                    if (x == 2) {
                        replay.subscribe(
                                m -> System.out.println("(replay 2) " + m),
                                e -> System.out.println(e.toString()),
                                () -> System.out.println("replay 2 completed")
                        );
                    }
                },
                e -> System.out.println(),
                () -> System.out.println("Replay Completed")
        );

        replay.connect();
    }
}
