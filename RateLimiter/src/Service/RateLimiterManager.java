package Service;

import Entities.User;
import Enums.RateLimiterType;

public class RateLimiterManager {
    private RateLimiterType rateLimiterType;
    private RateLimitingAlgoFactory rateLimitingAlgoFactory;

    public RateLimiterManager(RateLimiterType rateLimiterType, RateLimitingAlgoFactory rateLimitingAlgoFactory) {
        this.rateLimiterType = rateLimiterType;
        this.rateLimitingAlgoFactory = rateLimitingAlgoFactory;
    }

    public boolean canAllowRequest(User user, int numTokensRequired){
        LimitStrategy rateLimitingAlgo = rateLimitingAlgoFactory.GetStrategy(rateLimiterType);
        return rateLimitingAlgo.allowRequest(user, System.currentTimeMillis(), numTokensRequired);
    }

}
