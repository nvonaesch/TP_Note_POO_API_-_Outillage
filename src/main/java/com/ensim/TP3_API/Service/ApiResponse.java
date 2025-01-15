package com.ensim.TP3_API.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private List<Feature> features;

    public List<Feature> getFeatures() {
        return features;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Feature {
        private Properties properties;
        private Geometry geometry; 

        public Properties getProperties() {
            return properties;
        }

        public Geometry getGeometry() { 
            return geometry;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
        private String label;

        public String getLabel() {
            return label;
        }

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geometry {
        private List<Double> coordinates; 

        public List<Double> getCoordinates() {
            return coordinates;
        }

    }
}
