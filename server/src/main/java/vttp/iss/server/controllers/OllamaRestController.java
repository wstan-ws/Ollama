package vttp.iss.server.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp.iss.server.services.OllamaService;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api")
public class OllamaRestController {

    @Autowired
    private OllamaService ollamaSvc;

    @GetMapping
    public ResponseEntity<String> getResponseFromOllama(@RequestParam(required = true) String message) {

        JsonObject object = null;

        try {
            String response = ollamaSvc.chatWithOllama(message);
            System.out.println(response);
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();
            object = objBuilder.add("message", response).build();
        } catch (OllamaBaseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(object.toString());
    }
    
}
