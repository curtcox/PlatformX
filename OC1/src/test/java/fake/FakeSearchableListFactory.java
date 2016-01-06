package fake;

import x.event.LiveList;
import x.uilist.IXListCell;
import x.uiwidget.XComponent;
import x.uiwidget.XSearchableList;

public class FakeSearchableListFactory
    implements XSearchableList.Factory
{
    @Override
    public XSearchableList.Builder builder() {
        return new XSearchableList.Builder(){
            @Override
            public XSearchableList.Builder items(LiveList items) {
                return this;
            }

            @Override
            public XSearchableList.Builder action(XComponent action) {
                return this;
            }

            @Override
            public XSearchableList.Builder configurer(IXListCell.ConfigProducer configurer) {
                return this;
            }

            @Override
            public XSearchableList build() {
                return new FakeSearchableList();
            }
        };
    }
}
