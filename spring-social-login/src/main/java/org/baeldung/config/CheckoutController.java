package org.baeldung.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/checkout")
public class CheckoutController {
 
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
 
    @RequestMapping(method = RequestMethod.GET)
    public String checkout(Model model) {
    	System.out.println("ANTES DEL RETURN #######");
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        System.out.println("ANTES DEL RETURN #######");
        return "checkout";
    }
}