package org.example;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


@WireMockTest(httpPort = 4848)
public class GetCocktailsTest {

    private HttpClient client;
    private HttpService service;


    @BeforeEach
    public void initialize(WireMockRuntimeInfo wmRuntimeInfo) {
        client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
        service = new HttpService(client, "http://localhost:" + wmRuntimeInfo.getHttpPort());
    }

    @Test
    public void getRandomCocktailTest() {
        String responseString =TestUtility.getRandomCocktailResponseAsString();

        stubFor(get(urlPathEqualTo("/api/json/v1/1/random.php"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(responseString)));

        List<Drink> drinks = service.getRandomCocktail();

        verify(getRequestedFor(urlEqualTo("/api/json/v1/1/random.php")));

        assertEquals(1, drinks.size());
        assertEquals("Scooter", drinks.get(0).getName());
        assertEquals("12130", drinks.get(0).getId());
        assertEquals("Shake all ingredients well with cracked ice, strain into a cocktail glass, and serve.", drinks.get(0).getInstruction());
    }

    @Test
    public void getCocktailByNameTest() {
        String responseString = TestUtility.getCocktailsByNameResponseAsString();

        stubFor(get(urlPathMatching("/api/json/v1/1/search.php*"))
                .withQueryParam("s", equalTo("Margarita"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(responseString)));

        Drink drink = service.getCocktailByName("Margarita").get(0);

        verify(getRequestedFor(urlPathMatching("/api/json/v1/1/search.php.*"))
                .withQueryParam("s", equalTo("Margarita")));

        assertEquals("Margarita", drink.getName());
        assertEquals("1107", drink.getId());
        assertEquals("Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.", drink.getInstruction());
    }

    @Test
    public void getCocktailByFirstLetter() {
        String responseString =TestUtility.getCocktailsByFirstLetterResponseAsString();
        stubFor(get(urlPathMatching("/api/json/v1/1/search.php*"))
                .withQueryParam("f", equalTo("a"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(responseString)));

        Drink drink = service.getCocktailByFirstLetter("a").get(0);

        verify(getRequestedFor(urlPathMatching("/api/json/v1/1/search.php.*"))
                .withQueryParam("f", equalTo("a")));

        assertEquals("A1", drink.getName());
        assertEquals("17222", drink.getId());
        assertEquals("Pour all ingredients into a cocktail shaker, mix and serve over ice into a chilled glass.", drink.getInstruction());
    }

}
