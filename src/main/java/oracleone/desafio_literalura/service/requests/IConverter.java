package oracleone.desafio_literalura.service.requests;

public interface IConverter {
    <T> T convertData(String json, Class<T> classType);
}
