package io.github.suzp1984.rx.demos;

import rx.Observable;
import rx.Observer;

import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        for(IRxRunner runner : ServiceLoader.load(IRxRunner.class)) {
            runner.run();
        }
    }
}
