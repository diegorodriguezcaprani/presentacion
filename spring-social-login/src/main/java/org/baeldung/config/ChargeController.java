package org.baeldung.config;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

import org.baeldung.config.ChargeRequest.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import datatypes.DatosJson;


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
    
    @CrossOrigin(origins="*")
    @PostMapping("/chargePPV")
    public String chargePPV(ChargeRequest chargeRequest, Model model,HttpServletRequest request, @RequestParam("name") String nombre, @RequestParam("idFacebook") String idFacebook
    		,@RequestParam("empresa") String empresa, @RequestParam("url") String url)
      throws StripeException {
    	
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        //return "redirect:" + "welcome.xhtml?exitoPPV=true&amp;titulo="+nombre;
        
        DatosJson dj = new DatosJson();
		dj.addParameter("empresa", empresa);
		dj.addParameter("idFacebook", idFacebook);
		dj.addParameter("titulo", nombre);
		
		Client client = ClientBuilder.newClient();
		Boolean respuesta = client
    	.target(url + "cliente/comprarPPV")
    	.request().post(Entity.json(dj), new GenericType<Boolean>() {});
		
        
        
        return "redirect:" + "welcome.xhtml?exitoPPV=true";
    }
 
    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex,HttpServletRequest request) {
        model.addAttribute("error", ex.getMessage());
        return "redirect:" + "welcome.xhtml?exito=false";
    }
}