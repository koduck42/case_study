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
public class UploadCustomerRecommendationsHandlerTest extends AbstractEndToEndTest {


    @Test
    public void testEnsureUploadWorks() throws IOException {

        /*
         * First ensure the recos are currently as expected
         */
        GetCustomerRecommendationsHandlerTest.assertCustomerRecommendations(
                "111111",
                "[\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"]");
        GetCustomerRecommendationsHandlerTest.assertCustomerRecommendations("111112", "[]");

        /*
         * Now upload a different reco set and try again
         */
        uploadCsvRecos(TEST_CSV_SIMPLE2);

        // customer 111111 should have different recos
        GetCustomerRecommendationsHandlerTest.assertCustomerRecommendations(
                "111111",
                "[\"blackjack\",\"baccarat\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"]");

        // customer 111112 should now have recos
        GetCustomerRecommendationsHandlerTest.assertCustomerRecommendations(
                "111112",
                "[\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"]");

    }

    /*
     * NOTE: Missing tests for illegal corner cases:
     * - Encoding and illegal characters.
     * - missing recos (not all 10 recos provided)
     * - CSV-Header handling.
     * - etc.
     */

    public static void uploadCsvRecos(final String recoCsv) throws IOException {
        WebserviceTestUtil.sendPostRequest("/recoUpload", recoCsv);
    }

    private static final String TEST_CSV_SIMPLE2 = "\"CUSTOMER_NUMBER\",\"RECOMMENDATION_ACTIVE\",\"REC1\",\"REC2\",\"REC3\", REC4\",\"REC5\",\"REC6\",\"REC7\",\"REC8\",\"REC9\",\"REC10\"\n"+
            "\"111111\",\"true\",\"blackjack\",\"baccarat\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\n"+
            "\"111112\",\"true\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\n" +
            "\"111113\",\"true\",\"bingo\",\"cashwheel\",\"cashbuster\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\"\n"+
            "\"111114\",\"false\",\"brilliant\",\"citytrio\",\"crossword\",\"sevenwins\",\"sudoku\",\"sofortlotto\",\"hattrick\",\"bingo\",\"cashwheel\",\"cashbuster\"\n";


}
