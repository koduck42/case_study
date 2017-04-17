package casestudy;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility class that makes testing the webservice
 * easier.
 *
 * Basically it helps starting the webservice
 * at the beginning of the testsuite.
 *
 * And it helps posting http requests.
 *
 * Created by koduck on 4/17/17.
 */
public final class WebserviceTestUtil {

    static Webservice WEBSERVICE = null;
    static int HTTP_PORT;

    private WebserviceTestUtil() {
        // util classes should not have a constructor
    }

    public static synchronized void init(final int httpPort) throws IOException {
        if (WEBSERVICE != null) {
            throw new IllegalStateException("Illegal attempt to initialize casestudy.WebserviceTestUtil twice!");
        }

        HTTP_PORT = httpPort;
        WEBSERVICE = new Webservice(httpPort);
    }

    public static synchronized void shutdown() {
        if (WEBSERVICE == null) {
            throw new IllegalStateException("casestudy.WebserviceTestUtil has not yet been initialized. cannot shut down!");
        }

        WEBSERVICE.shutdown();
    }

    public static HttpResult sendGetRequest(final String uriPath) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpGet httpGet = new HttpGet("http://localhost:" + HTTP_PORT + uriPath);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            final HttpEntity entity = response.getEntity();

            String contentType = "";
            final Header contentTypeHeader = entity.getContentType();
            if (contentTypeHeader != null) {
                contentType = contentTypeHeader.getValue();
            }

            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            entity.writeTo(bos);

            return new HttpResult(
                    response.getStatusLine().getStatusCode(),
                    contentType,
                    bos.toString());

        } finally {
            response.close();
        }
    }

    public static HttpResult sendPostRequest(
            final String uriPath,
            final String payload) throws IOException
    {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        final HttpPost httpPost = new HttpPost("http://localhost:" + HTTP_PORT + uriPath);

        httpPost.setEntity(new StringEntity(payload));

        CloseableHttpResponse response = httpClient.execute(httpPost);

        try {
            final HttpEntity entity = response.getEntity();

            String contentType = "";
            final Header contentTypeHeader = entity.getContentType();
            if (contentTypeHeader != null) {
                contentType = contentTypeHeader.getValue();
            }

            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            entity.writeTo(bos);

            return new HttpResult(
                    response.getStatusLine().getStatusCode(),
                    contentType,
                    bos.toString());

        } finally {
            response.close();
        }
    }
}
