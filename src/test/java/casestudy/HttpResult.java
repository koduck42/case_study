package casestudy;

/**
 * Utility class to conveniently wrap and http response
 * Created by koduck on 4/17/17.
 */
public class HttpResult {

    private final int httpResponseStatusCode;
    private final String contentType;
    private final String responseString;

    public HttpResult(
            final int statusCode,
            final String contentType,
            final String responseString
    ) {
        this.httpResponseStatusCode = statusCode;
        this.contentType = contentType;
        this.responseString = responseString;
    }

    public int getHttpResponseStatusCode() {
        return httpResponseStatusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public String getResponseString() {
        return responseString;
    }
}
