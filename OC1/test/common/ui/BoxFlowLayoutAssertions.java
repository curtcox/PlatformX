package common.ui;

import java.awt.*;

import static org.junit.Assert.assertEquals;

final class BoxFlowLayoutAssertions {

    final ColumnBoxFlowLayout layout;

    BoxFlowLayoutAssertions(ColumnBoxFlowLayout layout) {
        this.layout = layout;
    }

    void pointIndex(int x,int y, int index) {
        assertEquals(index,layout.getPointIndex(new Point(x,y)));
    }
}
