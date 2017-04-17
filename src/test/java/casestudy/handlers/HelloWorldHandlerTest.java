package casestudy.handlers;

import casestudy.AbstractEndToEndTest;
import casestudy.HttpResult;
import casestudy.WebserviceTestUtil;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 *
 * A simple test verifying that the HelloWorldHandler
 * works as expected.
 *
 * Created by koduck on 4/17/17.
 */
public class HelloWorldHandlerTest extends AbstractEndToEndTest {


    @Test
    public void testHelloWorld() throws IOException {

        final HttpResult httpResponse = WebserviceTestUtil.sendGetRequest("/test");

        Assert.assertEquals(httpResponse.getHttpResponseStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(httpResponse.getContentType(), "");
        Assert.assertEquals(httpResponse.getResponseString(), "Hello World!");
    }
}
