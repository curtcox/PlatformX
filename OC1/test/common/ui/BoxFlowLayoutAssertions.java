package common.ui;

import java.awt.*;

import static org.junit.Assert.assertEquals;

final class BoxFlowLayoutAssertions {

    final BoxFlowLayout layout;

    BoxFlowLayoutAssertions(BoxFlowLayout layout) {
        this.layout = layout;
    }

    void pointIndex(int x,int y, int index) {
        assertEquals(index,layout.getPointIndex(new Point(x,y)));
    }
}
