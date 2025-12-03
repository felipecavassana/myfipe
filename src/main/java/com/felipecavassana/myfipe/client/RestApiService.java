package com.felipecavassana.myfipe.client;

import com.felipecavassana.myfipe.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestApiService {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String get(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new ApiException(String.format("Error to call API: uri=[%s] | status=[%d]", uri, response.statusCode()));
            }

            return response.body();

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("Error to GET api: " + e.getMessage());
        }
    }
}
