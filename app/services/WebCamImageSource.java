package services;

import akka.actor.Cancellable;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import scala.concurrent.duration.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by limpid on 7/26/17.
 */
public class WebCamImageSource implements SourceBuilder<ByteString> {

    private FrameGrabber grabber;
    private CanvasFrame canvasFrame = new CanvasFrame("Test");

    public WebCamImageSource() throws FrameGrabber.Exception {
        buildGrabber();
    }

    private void buildGrabber() throws FrameGrabber.Exception {
        if (this.grabber == null) {
            this.grabber = FrameGrabber.createDefault(0);
            grabber.setImageWidth(500);
            grabber.setImageHeight(500);
            grabber.start();
        }
    }

    @Override
    public Source<ByteString, ?> getSource() throws IOException {
        final Source<ByteString, Cancellable> imageSource =
                Source.tick(Duration.Zero(), Duration.create(1000, TimeUnit.MILLISECONDS), ByteString.fromString("start"));
        return imageSource.map((tick) -> {
            Frame frame = grabber.grab();
            grabber.flush();
            BufferedImage bufferedImage = CVImageConveter.convertToFile(frame);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( bufferedImage, "jpg", baos );
            baos.flush();

            byte[] imageInByte = baos.toByteArray();

            baos.close();
            canvasFrame.showImage(
                    frame
            );
            ByteString response = ByteString.fromString("\n{{start}}\n");
            response = response.concat(ByteString.fromArray( imageInByte )).concat(ByteString.fromString("\n{{end}}\n"));
            return response;
        });
    }


}
