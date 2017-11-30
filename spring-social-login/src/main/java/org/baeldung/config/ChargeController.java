package org.baeldung.config;

import javax.servlet.http.HttpServletRequest;

import org.baeldung.config.ChargeRequest.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;


@Controller
public class ChargeController {
 
    @Autowired
    private StripeService paymentsService;
    
    @CrossOrigin(origins="*")
    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model,HttpServletRequest request)
      throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "redirect:" + "welcome.xhtml?exito=true";
    }
 
    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex,HttpServletRequest request) {
        model.addAttribute("error", ex.getMessage());
        return "redirect:" + "welcome.xhtml?exito=false";
    }
}