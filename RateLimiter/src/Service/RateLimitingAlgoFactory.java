package Service;

import Enums.RateLimiterType;
import RateLimitingStrategies.TokenBucketStrategy;
import Repository.BucketStore;

// Factory gets the store and config and passes it to new objects of strategy
// It passes only those from config which the strategy requires
public class RateLimitingAlgoFactory {
    private final BucketStore bucketStore;
    private final RateLimiterConfig config;

    public RateLimitingAlgoFactory(BucketStore bucketStore,  RateLimiterConfig rateLimiterConfig) {
        this.bucketStore = bucketStore;
        this.config = rateLimiterConfig;
    }

    public LimitStrategy GetStrategy(RateLimiterType rateLimiterType){
        switch (rateLimiterType){
            default -> {
                return new TokenBucketStrategy(config.refillRate(), bucketStore);
            }
        }
    }
}
