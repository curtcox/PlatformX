package oc1.screenparts;

import com.codename1.capture.Capture;
import com.codename1.components.SpanButton;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

/**
 * Button that captures an image from the device camera and uses it for the
 * button face.
 * @author Curt
 */
final class CaptureImageButton
    extends SpanButton
{
    CaptureImageButton() {
        super("Capture Image");
        addListener();
    }
    
    private void addListener() {
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String photo = capturePhoto();
                if (photo!=null) {
                    setIcon(photo);
                }
            }
        });
    }
    
    private void setIcon(String photo) {
        try {
            setIcon(Image.createImage(photo));
            getComponentForm().revalidate();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String capturePhoto() {
        return Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
    }
}
