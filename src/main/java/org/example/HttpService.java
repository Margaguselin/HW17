package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class HttpService {

    private final HttpClient httpClient;

    private final ObjectMapper mapper = new ObjectMapper();

    private final String domain;

    private static final String BASE_PATH = "/api/json/v1/1";

    private final Logger logger = LoggerFactory.getLogger(HttpService.class);
    public HttpService(HttpClient httpClient, String domain) {
        this.httpClient = httpClient;
        this.domain = domain;
    }
    public List<Drink> getRandomCocktail(){
        logger.info("Get random cocktail request started");
        URI uri = URI.create(domain + BASE_PATH + "/random.php");
        return getCocktails(uri);
    }

    public List<Drink> getCocktailByName(String name){
        logger.info("Get cocktail by name started and name is " + name);
        URI uri = URI.create(domain + BASE_PATH + "/search.php?s=" + name);
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        return getCocktails(uri);
    }

    public List<Drink> getCocktailByFirstLetter(String letter){
        logger.info("Get cocktail by first letter is " + letter);
        URI uri = URI.create(domain + BASE_PATH + "/search.php?f=" + letter);
        return getCocktails(uri);
    }

    public  List<Drink> getCocktails(URI uri) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(uri).build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Get cocktails response is" + response.body());
            JsonNode node = mapper.readValue(response.body(), JsonNode.class);
            JsonNode drinks = node.get("drinks");
            return mapper.readerForListOf(Drink.class).readValue(drinks);

        } catch (Exception e) {
            logger.error("Error getting cocktails");
            throw new RuntimeException(e);
        }
    }
}
