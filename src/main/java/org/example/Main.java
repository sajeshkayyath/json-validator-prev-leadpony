package org.example;

import org.leadpony.justify.api.JsonSchema;
import org.leadpony.justify.api.JsonValidationService;

import javax.json.JsonReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Validating the json data with json schema!!");

        JsonValidationService service = JsonValidationService.newInstance();

        // Reads the JSON schema
        JsonSchema schema = service.readSchema(Paths.get("question.schema.json"));

        Path path = Paths.get("fake-question.json");
        // Parses the JSON instance by JsonParser

        CustomProblemHandler handler = new CustomProblemHandler(); // As how it is done in the sunbird inquiry currently
        try (JsonReader reader = service.createReader(path, schema, handler)) {
            reader.readValue();
            List<String> problemMessages = handler.getProblemMessages();
            problemMessages.forEach(System.out::println);
        }
    }
}