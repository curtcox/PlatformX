package c1.screens;

import com.codename1.ui.Display;
import com.codename1.ui.plaf.UIManager;
import common.screen.ScreenLink;
import fake.FakeUIManager;
import c1.ui.C1Form;
import common.ui.IForm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public final class FakeUI {
    
    static {
        try {
            init();
        } catch (InterruptedException e) {
            throw new ExceptionInInitializerError(e);
        } catch (ExecutionException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static IForm newForm() {
        return new C1Form(ScreenLink.of("test")) {
            @Override
            public UIManager getUIManager(){
                return FakeUIManager.of();
            }
        };
    }

    static Display display() {
        return Display.getInstance();
    }
    
    public static <T> T onEDT(Callable<T> callable) throws InterruptedException, ExecutionException {
        FutureTask<T> future = new FutureTask(callable);
        FakeUI.display().invokeAndBlock(future);
        return future.get();
    }

    public static void flushEDT() throws InterruptedException, ExecutionException {
        Callable callable = new Callable(){
            public Object call() throws Exception {
                return null;
            }
        };
        onEDT(callable);
    }

    static void init() throws ExecutionException, InterruptedException {
        FakeUI.onEDT(new Callable(){
            public Object call() throws Exception {
                FakeUI.newForm();
                return null;
            }
        });
    }

}