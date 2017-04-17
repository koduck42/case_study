package casestudy;

import casestudy.handlers.GetCustomerRecommendationsHandler;
import casestudy.handlers.UploadCustomerRecommendationsHandler;
import com.sun.net.httpserver.HttpServer;
import casestudy.handlers.HelloWorldHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * The casestudy.Webservice which is receiving the webservice calls.
 *
 * Created by koduck on 4/17/17.
 */
public class Webservice {

    /*
     * Store HttpServer in local field.
     *
     * Even if we are not doing it in the current example
     * we might want to shut it down or send other signals
     * to it later on.
     */
    private final HttpServer httpServer;

    public Webservice(final int httpPort) throws IOException {

        // create HttpServer
        this.httpServer = HttpServer.create(new InetSocketAddress(httpPort), 0);

        // register casestudy.handlers
        this.httpServer.createContext("/test", new HelloWorldHandler());
        this.httpServer.createContext("/customers", new GetCustomerRecommendationsHandler());
        this.httpServer.createContext("/recoUpload", new UploadCustomerRecommendationsHandler());

        this.httpServer.start();

        /*
         * Should probably use a logging framework here.
         *
         * But as it is an example and we want to keep it simple,
         * System.out.println will suffice.
         */
        System.out.println("casestudy.Webservice started. Listening on port " + httpPort);
    }

    public void shutdown() {
        System.out.println("casestudy.Webservice shutting down...");
        this.httpServer.stop(1);
    }



    public static void main(final String[] args) throws IOException {
        final Webservice webservice = new Webservice(8080);
    }
}
