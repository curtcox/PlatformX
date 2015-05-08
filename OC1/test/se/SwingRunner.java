package se;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 <pre>
 @RunWith(SwingRunner.class)
 public class MyTest {
   ...
 }
 </pre>
 * https://community.oracle.com/thread/1350403
 */
public final class SwingRunner
    extends BlockJUnit4ClassRunner
{

    public SwingRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    public void run(final RunNotifier arg0) {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    SwingRunner.super.run(arg0);
                }
            });
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}