package casestudy.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by koduck on 4/17/17.
 */
public class HelloWorldHandler  implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "Hello World!";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
