package com.github.reapy_;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
   @JsonProperty("MESSAGE")
   String message;
   @JsonProperty("Priority")
    int priority;
   @JsonProperty("_PID")
    int pid;
   @JsonProperty("_TRANSPORT")
    String transport;
   @JsonProperty("_COMM")
    String comm;

    

}
