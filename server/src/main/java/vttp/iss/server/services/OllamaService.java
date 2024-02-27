package vttp.iss.server.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.types.OllamaModelType;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

@Service
public class OllamaService {

    public String chatWithOllama(String message) throws OllamaBaseException, IOException, InterruptedException {

        String host = "http://localhost:11434/";
        OllamaAPI ollamaAPI = new OllamaAPI(host);
        ollamaAPI.setVerbose(true);
        OllamaResult result =
                ollamaAPI.generate(OllamaModelType.MISTRAL, message, new OptionsBuilder().build());

        return result.getResponse();
    }
    
}
