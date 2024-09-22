package services.impl;

import org.json.JSONException;
import org.json.JSONObject;
import services.ApiSearch;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiSearchImpl implements ApiSearch {
    private HttpRequest getHttpRequest(String cep) {
        return HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .GET()
                .build();
    }

    @Override
    public String requestAPI(String cep) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = getHttpRequest(cep);
        String ufValue = null;

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            JSONObject jsonObject = new JSONObject(responseBody);

            ufValue = jsonObject.getString("uf");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        } catch (JSONException ex) {
            ufValue = "SP";
        }

        return ufValue;
    }

}
