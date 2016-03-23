package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Observer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "--- Rxjava samples ---" );
        Integer[] intArray = new Integer[] {1, 2, 3, 4, 5};
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
    }
}
