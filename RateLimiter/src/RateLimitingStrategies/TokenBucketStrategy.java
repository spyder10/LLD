package RateLimitingStrategies;

import Entities.Bucket;
import Entities.User;
import Repository.BucketStore;
import Service.LimitStrategy;

import java.util.*;

public class TokenBucketStrategy implements LimitStrategy {
    private final int refillRate;
    private final BucketStore bucketStore;

    // It injects the configs and the bucket store (stateful entity or storage from an interface)
    // Injecting storage from repository pattern keeps the strategy stateless.
    // It allows us to test the strategy with Mocked bucket stores as well.
    public TokenBucketStrategy(int refillRate, BucketStore bucketStore) {
        this.refillRate = refillRate;
        this.bucketStore = bucketStore;
    }

    @Override
    public boolean allowRequest(User user, long currTimeStamp, int numTokenRequired){
        // Argument check
        if (user == null || user.getId() == null){
            throw new IllegalArgumentException("User and UserId must not be null");
        }
        if(numTokenRequired < 0){
            throw new IllegalArgumentException("NumTokensRequired cannot be negative");
        }

        // Get the user bucket from storage
        Bucket userBucket = bucketStore.getOrCreate(user.getId(), currTimeStamp);

        synchronized (userBucket){
            refillTokens(userBucket, currTimeStamp);
            if(numTokenRequired <= userBucket.getCurrSize()){
                int newCurrSize = userBucket.getCurrSize() - numTokenRequired;
                userBucket.setCurrSize(newCurrSize);
                return true;
            }
            else {
                return false;
            }
        }
    }

    void refillTokens(Bucket bucket, long currTimeStamp){
        long lastFilledTimeStamp = bucket.getLastRefilledTime();
        long elapsedTime = currTimeStamp - lastFilledTimeStamp;

        // No time has passed or clock skew
        if(elapsedTime<=0){
            return;
        }

        long distinctIntegralSecElapsed = elapsedTime / 1000L;

        // Not enough time has passed to refill
        if(distinctIntegralSecElapsed==0){
            return;
        }

        long numTokensToBeRefilled = distinctIntegralSecElapsed * (long) refillRate;
        int newSize = (int)Math.min((long)bucket.getCurrSize() + numTokensToBeRefilled, (long)bucket.getMaxCapacity());

        // LastModifiedTime would be updated to now only when we actually put a token
        // A token would be put only on Integer timestamps. At 300ms, numTokensToBeRefilled would be 0 (300/1000*refillRate)
        // At more than 1000sec, since elaspedTime would become more than 1sec, bucket would be refilled to maxCapacity
        // Till 1sec, currSize would protect to not exceed requests more than maxcapacity

//         if(newSize > bucket.getCurrSize()){              // We filled a token
//            bucket.setLastRefilledTime(currTimeStamp);
//         }
//         bucket.setCurrSize(newSize);

        // Lets say we filled tokens at 2.3sec. RefillRate is 5 per sec.
        // We would refill 2 sec * 5 = 10, lets say maxCap=10 as well, 10 tokens would be refilled
        // But we updated timestamp to now (to 2.3sec), essentially loosing 0.3sec worth of tokens
        // So best would be to update with the partial progress towards next refill

//        if (newSize > bucket.getCurrSize()){
//            bucket.setLastRefilledTime(lastFilledTimeStamp + 1000L * distinctIntegralSecElapsed);
//        }

        bucket.setCurrSize(newSize);

        // Advance by credited time (preserves leftover ms toward next refill)
        // The checking of not enough elapse time has already been done above
        bucket.setLastRefilledTime(lastFilledTimeStamp + distinctIntegralSecElapsed * 1000L);
    }
}
