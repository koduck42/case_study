package casestudy.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Upload Customer Recommendations.
 *
 * Created by koduck on 4/17/17.
 */
public class UploadCustomerRecommendationsHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange t) throws IOException {

        final String csv = getCsvFromRequest(t);
        final NotThreadSafeRecommendationCache newCache = parseCsv(csv);
        RecommendationCacheHolder.setRecoCache(newCache);

        HttpHandlerUtil.sendResponse(t, HttpStatus.SC_OK, "Ok.");
    }

    private String getCsvFromRequest(final HttpExchange t) throws IOException {
        final BufferedInputStream bis = new BufferedInputStream(t.getRequestBody());
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(bis, bos);

        return bos.toString();
    }

    private NotThreadSafeRecommendationCache parseCsv(final String csv) throws IOException {
        final CSVParser csvParser = CSVParser.parse(csv, CSVFormat.DEFAULT);

        final NotThreadSafeRecommendationCache newCache = new NotThreadSafeRecommendationCache();

        for (final CSVRecord csvRecord : csvParser.getRecords() ) {
            final String customerId = csvRecord.get(0);

            /*
             * We only parse active recos.
             */
            final boolean isActive = getBoolean(csvRecord.get(1));
            if (!isActive) {
                continue;
            }

            /*
             * Parse the recos.
             */
            final List<String> recos = new ArrayList<>();
            for (int i=2; i <= 11; i++) {
                recos.add(csvRecord.get(i));
            }

            newCache.add(customerId, recos);
        }

        return newCache;
    }

    private boolean getBoolean(final String boolStr) {
        if (boolStr == null) {
            return false;
        }

        return Boolean.parseBoolean(boolStr);
    }

}
