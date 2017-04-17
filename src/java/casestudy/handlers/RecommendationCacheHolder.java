package casestudy.handlers;

/**
 *
 * A simple unique instance class to hold a cache.
 *
 * NOTE: Thread safety is only rudimentary implemented here.
 *       Using a thread safe cache implementation would be
 *       needed for real world here.
 *
 * Created by koduck on 4/17/17.
 */
public class RecommendationCacheHolder {

    private static NotThreadSafeRecommendationCache RECO_CACHE = new NotThreadSafeRecommendationCache();

    public static synchronized NotThreadSafeRecommendationCache getRecoCache() {
        return RECO_CACHE;
    }

    public static synchronized void setRecoCache(final NotThreadSafeRecommendationCache newCache) {
        RECO_CACHE = newCache;
    }
}
