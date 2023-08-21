package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.model.enums.Message;
import com.mitacs.customerjourney.service.FrontendClient;
import com.mitacs.customerjourney.service.TemporalService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ActivitiesImpl implements Activities {

    private final FrontendClient frontendClient;
    private final TemporalService temporalService;

    public ActivitiesImpl(FrontendClient frontendClient, TemporalService temporalService) {
        this.frontendClient = frontendClient;
        this.temporalService = temporalService;
    }

    @Override
    public void inviteToSubscribe() {
        try {
        frontendClient.sendMessage(Message.INVITE_TO_SUBSCRIBE);

    } catch (Exception e){
        System.out.println("e = " + e);
    }
    }

    @Override
    public void communicateWithChatbot() {
        try {
        frontendClient.sendMessage(Message.COMMUNICATE_WITH_CHATBOT);

        } catch (Exception e){
            System.out.println("e = " + e);
        }
    }

    @Override
    public boolean isNewPurchaseCheck(Product product, Customer customer) {
        return temporalService.isNewPurchaseCheck(product, customer);
    }

    @Override
    public void recommendSimilarProducts(Product targetedProduct) {
        List<Product> recommendedProducts = temporalService.recommendSimilarProducts(targetedProduct);
        frontendClient.sendRecommendedProducts(recommendedProducts);
    }

    @Override
    public void recommendPersonalisedProducts(Product targetedProduct, Customer customer) {
        List<Product> recommendedProducts = temporalService.recommendPersonalisedProducts(targetedProduct);
        frontendClient.sendRecommendedProducts(recommendedProducts);
    }
}
