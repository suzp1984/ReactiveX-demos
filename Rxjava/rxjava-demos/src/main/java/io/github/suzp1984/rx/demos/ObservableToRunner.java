package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jacobsu on 4/20/16.
 */
public class ObservableToRunner implements IRxRunner {
    @Override
    public void run() {

        System.out.println("Observable ToList");
        Observable.range(1, 5).toList()
                .subscribe(x -> System.out.println(x),
                        e -> System.out.println(e.toString()),
                        () -> System.out.println("ToList completed"));

        Observable.range(1, 5).toMap(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "M" + integer;
            }
        }).subscribe(x -> System.out.println(x),
                e -> System.out.println(e.toString()),
                () -> System.out.println("ToMap completed"));
    }
}
