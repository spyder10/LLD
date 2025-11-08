package Repository;

import Entities.Bucket;
import java.util.UUID;

public interface BucketStore
{
    Bucket getOrCreate(UUID userId, long nowMillis);
}
