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
public class GetCustomerRecommendationsHandlerTest extends AbstractEndToEndTest {


    @Test
    public void testGetRecommendations() throws IOException {

        assertCustomerRecommendations("111111", "[\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"]");
        assertCustomerRecommendations("111112", "[]");
    }

    @Test
    public void testGetRecommendationsNoRecosFound() throws IOException {

        assertCustomerRecommendations("42", "[]");
    }

    @Test
    public void testIllegalCustomerCommand() throws IOException {

        final HttpResult httpResponse = WebserviceTestUtil.sendGetRequest("/customers/111111/foo");

        Assert.assertEquals(httpResponse.getHttpResponseStatusCode(), HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(httpResponse.getContentType(), "");
        Assert.assertEquals(httpResponse.getResponseString(), "Illegal customer command: /foo");
    }

    public static void assertCustomerRecommendations(final String customerId, final String expectedRecos) throws IOException {
        final HttpResult httpResponse = WebserviceTestUtil.sendGetRequest("/customers/"  +  customerId +   "/games/recommendations?count=5");

        Assert.assertEquals(httpResponse.getHttpResponseStatusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(httpResponse.getContentType(), "");
        Assert.assertEquals(httpResponse.getResponseString(), expectedRecos);
    }
}
