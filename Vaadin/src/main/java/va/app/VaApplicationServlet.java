/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package va.app;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Curt
 */
@WebServlet(urlPatterns = "/*", name = "VaApplicationServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = VaApplication.class, productionMode = false)
public class VaApplicationServlet extends VaadinServlet {
    
}
