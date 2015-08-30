package va.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import x.command.Command;
import x.page.PageLink;
import x.ui.IForm;
import x.uiwidget.XComponent;

public final class VaForm
        extends VerticalLayout
    implements IForm
{
    private final PageLink link;
    private Command back;
    private Button backButton;

    VaForm(PageLink link) {
        this.link = link;
        this.backButton = backButton();
        setMargin(true);
    }

    @Override
    public void layout(XComponent layout) {
        removeAllComponents();
        addComponent(titleLabel());
        addComponent(backButton);
        addComponent(VaUIRenderer.render(layout));
        show();
    }

    private Label titleLabel() {
        Label label = new Label();
        label.setValue(link.title());
        return label;
    }

    private Button backButton() {
        Button button = new Button("<");
        button.addListener(new Listener() {
            @Override
            public void componentEvent(Event event) {
                back.go();
            }
        });
        return button;
    }

    @Override
    public void show() {
        display().show(this);
    }

    @Override
    public void setBackCommand(Command back) {
        this.back = back;
        backButton.setEnabled(back != null);
    }

    @Override
    public void showBack() {
        show();
    }

    @Override
    public String getTitle() {
        return link.title();
    }

    @Override
    public PageLink getScreenLink() {
        return link;
    }

    private VaDisplay display() {
        return VaDisplay.of();
    }

}
