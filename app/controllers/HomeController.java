package controllers;

import akka.stream.javadsl.Source;
import akka.util.ByteString;
import com.google.inject.Inject;
import play.libs.EventSource;
import play.mvc.*;

import services.SourceBuilder;
import views.html.*;

import java.io.File;
import java.io.IOException;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private SourceBuilder sourceBuilder;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok("Discoperi Cam Test");
    }

    public Result webCamStream() throws IOException {
        Source<ByteString, ?> source = sourceBuilder.getSource();
        return ok().chunked(source);
    }
}
