package c1.ui;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import x.page.PageLink;
import x.ui.IForm;
import fake.FakeUIManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class C1DisplayTest {

    @Test
    public void can_create() {
        new C1Display();
    }

    @Test
    public void getCurrent_returns_IForm_when_IForm() throws InterruptedException {
        Display.getInstance();
        FakeUIManager.of();
        C1Display display = new C1Display();
        new C1Form(PageLink.of("")).show();

        Thread.sleep(50);
        assertNotNull(display.getCurrent());
        assertTrue(display.getCurrent() instanceof IForm);
    }

    @Test
    public void getCurrent_returns_null_when_not_IForm() throws InterruptedException {
        Display.getInstance();
        FakeUIManager.of();
        C1Display display = new C1Display();
        new Form().show();

        Thread.sleep(50);
        assertNull(display.getCurrent());
        assertFalse(display.getCurrent() instanceof IForm);
    }

}