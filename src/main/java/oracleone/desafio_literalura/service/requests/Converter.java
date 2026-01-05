package oracleone.desafio_literalura.service.requests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Converter  implements IConverter{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertData(String json, Class<T> classType) {
        try{
            return mapper.readValue(json, classType);
        } catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
