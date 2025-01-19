package br.com.mysterious.mysteriousapi.application.providers;

public interface HashProvider {
    String hash(String input);
    Boolean verify(String input, String hash);
}
