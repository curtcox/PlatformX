package va.uilist;

import com.vaadin.data.Property;

final class VaReadOnlyProperty
    implements Property
{
    private final Object value;

    VaReadOnlyProperty(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object o) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    @Override
    public Class getType() {
        return String.class;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public void setReadOnly(boolean b) {}
}
