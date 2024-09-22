import model.Identity;
import org.junit.jupiter.api.*;
import search.ReadFiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Set;

public class CepApiTeste {
    private static Set<Identity> datasFromFile;
    private static HttpClient httpClient;
    private HttpRequest request;

    private HttpResponse<String> response;

    @BeforeAll
    static void setListOfCPF() throws FileNotFoundException {
        httpClient = HttpClient.newHttpClient();
        datasFromFile = ReadFiles.readValues("C:/Users/Leonardo/OneDrive/Teste_Search_LDAP.xlsx");
    }

    @Test
    @DisplayName("Testa request com CEPs validos")
    void testCepApi() throws IOException, InterruptedException {

        for (Identity values : datasFromFile) {
            request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + values.getST() + "/json/")).GET().build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals(200, response.statusCode());
        }
    }

    @Test
    @DisplayName("Testa request com CEP invalido")
    void testWrongCepApi() throws IOException, InterruptedException {
        request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/0000/json/")).GET().build();
        response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Assertions.assertEquals(400, response.statusCode());

    }
}
