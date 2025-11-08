package Repository;

import Entities.Bucket;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBucketStore implements BucketStore{
    private final ConcurrentHashMap<UUID, Bucket> userBuckets = new ConcurrentHashMap<>();

    @Override
    public Bucket getOrCreate(UUID userId, long nowMillis) {
        // For New user
        // This won't work in multithreading, since these lines of code are not atomic operation
        // Multiple threads can create 2 buckets.
//        if(!userBuckets.containsKey(user.getId())){
//            Bucket newUserBucket = new Bucket(currTimeStamp);
//            userBuckets.put(user.getId(), newUserBucket);
//        }
//        Bucket userBucket = userBuckets.get(user.getId());


        // This is atmoic operation, would work in multithreaded, when multiple threads competing for same bucket
        return userBuckets.computeIfAbsent(userId, id -> new Bucket(nowMillis));
    }
}
