package juborajsarker.mytourmate.service;

import juborajsarker.mytourmate.data.Channel;

/**
 * Created by dell on 1/22/2017.
 */

public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);

    void serviceFailure(Exception exception);
}
