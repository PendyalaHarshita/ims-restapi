package com.wellsfargo.training.ims.controller;

import com.wellsfargo.training.ims.exception.ResourceNotFoundException;
import com.wellsfargo.training.ims.model.Address;
import com.wellsfargo.training.ims.model.Dealer;
import com.wellsfargo.training.ims.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
/*Spring MVC provides @CrossOrigin annotation that marks the annotated method or type as permitting cross-origin requests.
        The CORS (Cross-Origin Resource Sharing) allows a webpage to request additional resources into the browser from other domains
        such as API data using AJAX, font files, style sheets etc.*/
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class DealerController {
    @Autowired
    private DealerService dealerService;
    /* ResponseEntity represents an HTTP response, including headers, body, and status. */
    @PostMapping("/reg")
    public ResponseEntity<String> creDealer(@Validated @RequestBody Dealer d){
        try{
            Address address = d.getAddress();
            address.setDealer(d);
            d.setAddress(address);
            Dealer registeredDealer = dealerService.registerDealer(d);
            if (registeredDealer!=null){
                return ResponseEntity.ok("Registration Successful");
            }
            else{
                return ResponseEntity.badRequest().body("Registration failed");
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occured"+e.getMessage());
        }
    }
    //Open Postman and make POST request - http://localhost:8085/ims/login
    @PostMapping("/login")
    public Boolean loginDealer(@Validated @RequestBody Dealer d) throws ResourceNotFoundException{
        Boolean isLoggedIn = false;
        String email = d.getEmail();
        String password = d.getPassword();
        Dealer dealer = dealerService.loginDealer(email).orElseThrow(()->
                new ResourceNotFoundException("Dealer not found for this Email Id: "));
        if (email.equals(dealer.getEmail())&&password.equals(dealer.getPassword())) {
            isLoggedIn=true;
        }

        return isLoggedIn;

    }
}
