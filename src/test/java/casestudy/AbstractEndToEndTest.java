package casestudy;

import casestudy.handlers.UploadCustomerRecommendationsHandlerTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

/**
 * Abstract class that makes defining
 * end to end tests easier.
 *
 * Created by koduck on 4/17/17.
 */
public class AbstractEndToEndTest {

    @BeforeSuite
    public void setup() throws IOException {
        // start webservice
        WebserviceTestUtil.init(8080);

        /*
         * Upload an initial set of recommendations
         */
        UploadCustomerRecommendationsHandlerTest.uploadCsvRecos(TEST_CSV_SIMPLE1);
    }

    private void assertCustomerRecos(final String customerId, final String exepctedRecos) {

    }

    @AfterSuite
    public void shutdown() {
        // stop webservice
        WebserviceTestUtil.shutdown();
    }

    private static final String TEST_CSV_SIMPLE1 = "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC1\",\"REC2\",\"REC3\", REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\n"+
            "\"111111\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\n"+
            "\"111112\",\"false\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\n";


}
