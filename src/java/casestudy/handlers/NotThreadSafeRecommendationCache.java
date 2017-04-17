package casestudy.handlers;

import java.util.*;

/**
 *
 * A Cache containing Recommendations
 *
 * TODO: Actually back it with a database.
 * NOTE: Current implementation is not thread safe. For
 *       production use, make thread safe implemenation.
 * Created by koduck on 4/17/17.
 */
public class NotThreadSafeRecommendationCache {

    private static final List<String> NO_RECO = new ArrayList<>();
    private final Map<String, List<String>> recommendationsByCustomerId = new HashMap<>();

    public void add(final String customerId, final String... recommendations) {
        final List<String> recommendationList = Arrays.asList(recommendations);
        this.add(customerId, recommendationList);
    }

    public void add(final String customerId, final List<String> recommendations) {
        this.recommendationsByCustomerId.put(customerId, recommendations);
    }

    public List<String> getRecommendationsForCustomer(final String customerId) {
        if (!this.recommendationsByCustomerId.containsKey(customerId)) {
           return NO_RECO;
        }
        return this.recommendationsByCustomerId.get(customerId);
    }

}
