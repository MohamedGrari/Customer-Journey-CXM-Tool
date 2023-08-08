package com.mitacs.customerjourney.temporal;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.springframework.stereotype.Component;

@Component
@ActivityInterface
public interface Activities {
    @ActivityMethod
    void inviteToSubscribe();

    void communicateWithChatbot();
}
