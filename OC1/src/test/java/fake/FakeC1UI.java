package fake;

import com.codename1.ui.Display;
import com.codename1.ui.plaf.UIManager;
import x.page.PageLink;
import c1.ui.C1Form;
import x.ui.IForm;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public final class FakeC1UI {
    
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
        return new C1Form(PageLink.of("test")) {
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
        FakeC1UI.display().invokeAndBlock(future);
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
        FakeC1UI.onEDT(new Callable(){
            public Object call() throws Exception {
                FakeC1UI.newForm();
                return null;
            }
        });
    }

}
