package casestudy.handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * A simple util that helps with responding http.
 *
 * I prefer util classes over abstract classes.
 *
 * Created by koduck on 4/17/17.
 */
public class HttpHandlerUtil {

    public static void sendJsonResponse(
            final HttpExchange t,
            final int statusCode,
            final Object responseObject) throws IOException {

        Gson gson = new GsonBuilder().create();
        final String jsonResponse = gson.toJson(responseObject);

        sendResponse(t, statusCode, jsonResponse);
    }

    public static void sendResponse(
            final HttpExchange t,
            final int statusCode,
            final String response) throws IOException {

        t.sendResponseHeaders(statusCode, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
