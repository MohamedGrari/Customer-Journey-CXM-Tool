package com.mitacs.customerjourney.temporal;

import com.mitacs.customerjourney.model.Bike;
import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.enums.BikeType;
import com.mitacs.customerjourney.model.enums.Message;
import com.mitacs.customerjourney.model.enums.ProductType;
import com.mitacs.customerjourney.service.BikeService;
import com.mitacs.customerjourney.service.FrontendClient;
import com.mitacs.customerjourney.service.MailSenderC;
import com.mitacs.customerjourney.temporal.payloads.TargetedProductInfo;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ActivitiesImpl implements Activities {

    private final MailSenderC mailSenderC;
    private final FrontendClient frontendClient;
    private final BikeService bikeService;

    public ActivitiesImpl(MailSenderC mailSenderC, FrontendClient frontendClient, BikeService bikeService) {
        this.mailSenderC = mailSenderC;
        this.frontendClient = frontendClient;
        this.bikeService = bikeService;
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
    public void recommendProducts(TargetedProductInfo targetedProductInfo) {
//        if (targetedProductInfo.getTargetedProduct() == TargetedProduct.BIKE){
            List<Bike> recommendedBikes = bikeService.findBikesByType(convertProductTypeToBikeType(targetedProductInfo.getProductType()));
            mailSenderC.sendMail(new Customer("mohamed.grari@etudiant-fst.utm.tn", "Med", "rue de la montagne"), recommendedBikes, "Recommended Products!");
    }

    private BikeType convertProductTypeToBikeType(ProductType productType) {
        switch (productType) {
            case MOUNTAIN:
                return BikeType.MOUNTAIN;
            case ROAD:
                return BikeType.ROAD;
            case KIDS:
                return BikeType.KIDS;
            case HYBRID:
                return BikeType.HYBRID;
            case ELECTRIC:
                return BikeType.ELECTRIC;
            default:
                throw new IllegalArgumentException("Unsupported conversation");
        }
    }

    //    @Override
//    public boolean isNewPurchaseCheck(Product product, Customer customer) {
//        return temporalService.isNewPurchaseCheck(product, customer);
//    }
//
//    @Override
//    public void recommendSimilarProducts(Product targetedProduct) {
//        List<Product> recommendedProducts = temporalService.recommendSimilarProducts(targetedProduct);
//        frontendClient.sendRecommendedProducts(recommendedProducts);
//    }
//
//    @Override
//    public void recommendPersonalisedProducts(Product targetedProduct, Customer customer) {
//        List<Product> recommendedProducts = temporalService.recommendPersonalisedProducts(targetedProduct);
//        frontendClient.sendRecommendedProducts(recommendedProducts);
//    }
}
