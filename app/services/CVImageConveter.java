package services;

import javafx.scene.image.*;
import org.bytedeco.javacpp.FlyCapture2;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by limpid on 7/26/17.
 */
public class CVImageConveter {
    public static BufferedImage convertToFile(org.bytedeco.javacv.Frame frame){
        Java2DFrameConverter converter = new Java2DFrameConverter();
        return converter.convert(frame);
    }
}


