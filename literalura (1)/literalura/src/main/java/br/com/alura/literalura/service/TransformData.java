package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class TransformData implements ITransformData {

    // ObjectMapper converte os dados json vindo da serie
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T theDataFromApi(String json, Class<T> classe) {
        if (json == null || json.trim().isEmpty()) {
            throw new IllegalArgumentException("JSON string is null or empty");
        }

        try {
            return mapper.readValue(json, classe); // lê o objeto mapeado e retorna um json em forma de classe.
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    public <T> List<T> gettingTheList(String json, Class<T> classe) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public String extractingJson(String json, String object){
        try {
            JsonNode jsonCompleto = mapper.readTree(json);
            JsonNode jsonLivro = jsonCompleto.path("results");
            return jsonLivro.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}