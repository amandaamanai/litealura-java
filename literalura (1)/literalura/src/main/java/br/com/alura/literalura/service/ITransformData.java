package br.com.alura.literalura.service;

public interface ITransformData {
    <T> T theDataFromApi (String json, Class<T> classe);
}