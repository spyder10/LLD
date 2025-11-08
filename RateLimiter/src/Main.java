//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import Entities.User;
import Enums.RateLimiterType;
import Repository.BucketStore;
import Repository.InMemoryBucketStore;
import Service.RateLimiterConfig;
import Service.RateLimiterManager;
import Service.RateLimitingAlgoFactory;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiterConfig rateLimiterConfig = new RateLimiterConfig(5);
        BucketStore bucketStore = new InMemoryBucketStore();
        RateLimitingAlgoFactory rateLimitingAlgoFactory = new RateLimitingAlgoFactory(
                bucketStore, rateLimiterConfig);

        RateLimiterManager rateLimiterManager = new RateLimiterManager(
                RateLimiterType.TOKEN_BUCKET,
                rateLimitingAlgoFactory);

        User user1 = new User("Alice");
        User user2 = new User("Bob");

        for(int i=0; i<25;i++){
            boolean allowReq1 = rateLimiterManager.canAllowRequest(user1,1);
            System.out.println(allowReq1);
            Thread.currentThread().sleep(100);
        }
    }
}
