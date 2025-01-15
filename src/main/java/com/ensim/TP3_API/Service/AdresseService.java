package com.ensim.TP3_API.Service;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdresseService {

    ArrayList<String> reponse = new ArrayList<String>();

    public ArrayList<String> getAdresseDetails(String address) throws JsonMappingException, JsonProcessingException {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl("https://api-adresse.data.gouv.fr/search/")
                .queryParam("q", address).queryParam("limit", 1)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String json = response.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            ApiResponse apiResponse = objectMapper.readValue(json, ApiResponse.class);

            if (apiResponse.getFeatures() != null && !apiResponse.getFeatures().isEmpty()) {
                ApiResponse.Feature firstFeature = apiResponse.getFeatures().get(0);

                if (firstFeature.getGeometry() != null) {
                    double longitude = firstFeature.getGeometry().getCoordinates().get(0);
                    double latitude = firstFeature.getGeometry().getCoordinates().get(1);
                    reponse.clear();
                    reponse.add(firstFeature.getProperties().getLabel());
                    reponse.add(String.valueOf(latitude));
                    reponse.add(String.valueOf(longitude));

                    return reponse;
                }
                reponse.clear();
                reponse.add(firstFeature.getProperties().getLabel());
                return reponse;
            } else {
                return reponse;
            }
        }
        return reponse;
    }

    public ArrayList<String> getMeteo(String latitude, String longitude) {
        String apiUrl = "https://api.meteo-concept.com/api/forecast/daily";
        String token = "d7551be4048999a668a8fdd8afe53c9f8dd15168c02a77aa978aec8c96c94102";

        ArrayList<String> infoMeteo = new ArrayList<String>();
        String url = String.format("%s?token=%s&lat=%s&lon=%s", apiUrl, token, latitude, longitude);

        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);

            JsonNode forecastArray = root.path("forecast");
            if (forecastArray.isArray() && forecastArray.size() >= 2) {
                JsonNode day1 = forecastArray.get(0);
                JsonNode day2 = forecastArray.get(1);
                JsonNode day3 = forecastArray.get(2);
                JsonNode day4 = forecastArray.get(3);

                System.out.println(forecastArray);
                infoMeteo.add(String.valueOf(day1.path("datetime").asText()));
                infoMeteo.add(String.valueOf(getWeatherDescription(day1.path("weather").asInt())));
                infoMeteo.add(String.valueOf(day1.path("tmin").asInt()));
                infoMeteo.add(String.valueOf(day1.path("tmax").asInt()));
                infoMeteo.add(String.valueOf(day1.path("sun_hours").asInt()));

                infoMeteo.add(String.valueOf(day2.path("datetime").asText()));
                infoMeteo.add(String.valueOf(getWeatherDescription(day2.path("weather").asInt())));
                infoMeteo.add(String.valueOf(day2.path("tmin").asInt()));
                infoMeteo.add(String.valueOf(day2.path("tmax").asInt()));
                infoMeteo.add(String.valueOf(day2.path("sun_hours").asInt()));

                infoMeteo.add(String.valueOf(day3.path("datetime").asText()));
                infoMeteo.add(String.valueOf(getWeatherDescription(day3.path("weather").asInt())));
                infoMeteo.add(String.valueOf(day3.path("tmin").asInt()));
                infoMeteo.add(String.valueOf(day3.path("tmax").asInt()));
                infoMeteo.add(String.valueOf(day3.path("sun_hours").asInt()));

                infoMeteo.add(String.valueOf(day4.path("datetime").asText()));
                infoMeteo.add(String.valueOf(getWeatherDescription(day4.path("weather").asInt())));
                infoMeteo.add(String.valueOf(day4.path("tmin").asInt()));
                infoMeteo.add(String.valueOf(day4.path("tmax").asInt()));
                infoMeteo.add(String.valueOf(day4.path("sun_hours").asInt()));

                return infoMeteo;
            } else {
                return infoMeteo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return infoMeteo;
        }
    }

    private String getWeatherDescription(int weatherCode) {
        switch (weatherCode) {
            case 0: return "Ensoleillé";
            case 1: return "Peu nuageux";
            case 2: return "Ciel voilé";
            case 3: return "Nuageux";
            case 4: return "Très nuageux";
            case 5: return "Couvert";
            case 6: return "Brouillard";
            case 7: return "Brouillard givrant";
            case 10: return "Pluie faible";
            case 11: return "Pluie modérée";
            case 12: return "Pluie forte";
            case 13: return "Pluie faible verglaçante";
            case 14: return "Pluie modérée verglaçante";
            case 15: return "Pluie forte verglaçante";
            case 16: return "Bruine";
            case 20: return "Neige faible";
            case 21: return "Neige modérée";
            case 22: return "Neige forte";
            case 30: return "Pluie et neige mêlées faibles";
            case 31: return "Pluie et neige mêlées modérées";
            case 32: return "Pluie et neige mêlées fortes";
            case 40: return "Averses de pluie locales et faibles";
            case 41: return "Averses de pluie locales";
            case 42: return "Averses locales et fortes";
            case 43: return "Averses de pluie faibles";
            case 44: return "Averses de pluie";
            case 45: return "Averses de pluie fortes";
            case 46: return "Averses de pluie faibles et verglaçantes";
            case 47: return "Averses de pluie verglaçantes";
            case 48: return "Averses de pluie fortes et verglaçantes";
            case 60: return "Averses de neige localisées et faibles";
            case 61: return "Averses de neige localisées";
            case 62: return "Averses de neige localisées fortes";
            case 63: return "Averses de neige faibles";
            case 64: return "Averses de neige";
            case 65: return "Averses de neige fortes";
            case 66: return "Averses de neige et pluie mêlées faibles";
            case 67: return "Averses de neige et pluie mêlées";
            case 68: return "Averses de neige et pluie mêlées fortes";
            case 70: return "Orages faibles et locaux";
            case 71: return "Orages locaux";
            case 72: return "Orages fort et locaux";
            case 73: return "Orages faibles";
            case 74: return "Orages";
            case 75: return "Orages forts";
            case 76: return "Orages faibles et neigeux";
            case 77: return "Orages neigeux";
            case 78: return "Orages forts et neigeux";
            case 100: return "Tempête tropicale";
            default: return "Condition météo inconnue";
        }
    }
    
}
