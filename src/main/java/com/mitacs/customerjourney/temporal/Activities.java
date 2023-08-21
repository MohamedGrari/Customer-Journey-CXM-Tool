package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.Product;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.springframework.stereotype.Component;

@Component
@ActivityInterface
public interface Activities {
    @ActivityMethod
    void inviteToSubscribe();

    @ActivityMethod
    void communicateWithChatbot();

    @ActivityMethod
    boolean isNewPurchaseCheck(Product product, Customer customer);

    void recommendSimilarProducts(Product targetedProduct);

    void recommendPersonalisedProducts(Product targetedProduct, Customer customer);
}
