package Service;

import Entities.User;

public interface LimitStrategy {
    boolean allowRequest(User user, long currTimeStamp, int numTokensRequired);
}
