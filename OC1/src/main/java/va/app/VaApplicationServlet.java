package va.app;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/*", name = "VaApplicationServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = VaApplication.class, productionMode = false)
public final class VaApplicationServlet extends VaadinServlet {
}
