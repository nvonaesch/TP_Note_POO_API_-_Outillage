package com.ensim.TP3_API.Controller;

import com.ensim.TP3_API.Service.AdresseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MeteoController {

    @Autowired
    private AdresseService adresseService;

    @PostMapping("/meteo")
    public String recevoirAdresse(@RequestParam("address") String address, Model model) throws JsonMappingException, JsonProcessingException {
        ArrayList<String> apiResponse = adresseService.getAdresseDetails(address);
        ArrayList<String> reponseMeteo;


        System.out.println(apiResponse);
        if(apiResponse.size() != 0){
            model.addAttribute("address", apiResponse.get(0));
            if(apiResponse.size() > 1){
                model.addAttribute("latitude", apiResponse.get(1));
                model.addAttribute("longitude", apiResponse.get(2));
                reponseMeteo = adresseService.getMeteo(apiResponse.get(1), apiResponse.get(2));

                model.addAttribute("datejour1", "Date: " + reponseMeteo.get(0));
                model.addAttribute("meteojour1", "Météo: " + reponseMeteo.get(1));
                model.addAttribute("tempminjour1", "°C min: "+ reponseMeteo.get(2) + "°C");
                model.addAttribute("tempmaxjour1", "°C max: "+ reponseMeteo.get(3) + "°C");
                model.addAttribute("heuressoleiljour1", "Nb heures soleil: "+ reponseMeteo.get(4));

                model.addAttribute("datejour2", "Date: " + reponseMeteo.get(5));
                model.addAttribute("meteojour2", "Météo: "+ reponseMeteo.get(6));
                model.addAttribute("tempminjour2", "°C min: "+ reponseMeteo.get(7) + "°C");
                model.addAttribute("tempmaxjour2", "°C max: "+ reponseMeteo.get(8) + "°C");
                model.addAttribute("heuressoleiljour2", "Nb heures soleil: "+ reponseMeteo.get(9));

                model.addAttribute("datejour3", "Date: " + reponseMeteo.get(10));
                model.addAttribute("meteojour3", "Météo: "+ reponseMeteo.get(11));
                model.addAttribute("tempminjour3", "°C min: "+ reponseMeteo.get(12) + "°C");
                model.addAttribute("tempmaxjour3", "°C max: "+ reponseMeteo.get(13) + "°C");
                model.addAttribute("heuressoleiljour3", "Nb heures soleil: "+ reponseMeteo.get(14));
                
                model.addAttribute("datejour4", "Date: " + reponseMeteo.get(15));
                model.addAttribute("meteojour4", "Météo: "+ reponseMeteo.get(16));
                model.addAttribute("tempminjour4", "°C min: "+ reponseMeteo.get(17) + "°C");
                model.addAttribute("tempmaxjour4", "°C max: "+ reponseMeteo.get(18) + "°C");
                model.addAttribute("heuressoleiljour4", "Nb heures soleil: "+ reponseMeteo.get(19));

            }
        } else {
            model.addAttribute(address, "Adresse inconnue");
        }
    
        return "meteo";
    }

}
