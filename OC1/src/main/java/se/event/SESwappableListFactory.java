package se.event;

import x.event.SwappableList;

import java.util.List;

public class SESwappableListFactory
    implements SwappableList.Factory
{
    @Override
    public SwappableList from(List list) {
        SESwappableList swappable = new SESwappableList();
        swappable.become(list);
        return swappable;
    }
}
