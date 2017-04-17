package casestudy.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.List;

/**
 * Get Customer Recommendations.
 *
 * Created by koduck on 4/17/17.
 */
public class GetCustomerRecommendationsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {

        final String requestUri = t.getRequestURI().getRawPath();

        final String customerCommand = getCustomerCommand(requestUri);

        /*
         * This whole parameter handling is ugly. But I did not
         * want to use to many frameworks.
         *
         * So in real world I would use a framework. But for this example code
         * it will suffice.
         *
         * Check, if the command is correct.
         */
        if (!"/games/recommendations".equals(customerCommand)) {
            HttpHandlerUtil.sendResponse(t, HttpStatus.SC_NOT_FOUND, "Illegal customer command: " + customerCommand);
            return;
        }


        /*
         * Extract customer Id
         */
        final String customerId = getCustomerId(requestUri);

        /*
         * Get recommendations (if any)
         */
        final List<String> recommendations = RecommendationCacheHolder.getRecoCache().getRecommendationsForCustomer(customerId);


        /*
         * TODO: Limit recos according to parameter "count"
         */


        /*
         * Send response
         */
        HttpHandlerUtil.sendJsonResponse(t, HttpStatus.SC_OK, recommendations);
    }


    private String getCustomerCommand(final String uriPath) {
        final String[] tokens = uriPath.split("/", -1);

        StringBuilder cmd = new StringBuilder();
        for (int i = 3; i < tokens.length; i++) {
            cmd.append("/").append(tokens[i]);
        }
        return cmd.toString();
    }

    private String getCustomerId(final String uriPath) {
        final String[] tokens = uriPath.split("/", -1);
        final String customerId = tokens[2];
        return customerId;

    }

}
