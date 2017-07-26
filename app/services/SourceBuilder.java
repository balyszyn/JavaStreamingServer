package services;

import akka.stream.javadsl.Source;

import java.io.IOException;

/**
 * Created by limpid on 7/26/17.
 */
public interface SourceBuilder<T> {

     Source<T, ?> getSource( ) throws IOException;
}
