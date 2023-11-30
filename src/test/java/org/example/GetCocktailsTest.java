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
        String responseString = "{\n" +
                "  \"drinks\": [\n" +
                "    {\n" +
                "      \"idDrink\": \"12130\",\n" +
                "      \"strDrink\": \"Scooter\",\n" +
                "      \"strDrinkAlternate\": null,\n" +
                "      \"strTags\": null,\n" +
                "      \"strVideo\": null,\n" +
                "      \"strCategory\": \"Ordinary Drink\",\n" +
                "      \"strIBA\": null,\n" +
                "      \"strAlcoholic\": \"Alcoholic\",\n" +
                "      \"strGlass\": \"Cocktail glass\",\n" +
                "      \"strInstructions\": \"Shake all ingredients well with cracked ice, strain into a cocktail glass, and serve.\",\n" +
                "      \"strInstructionsES\": null,\n" +
                "      \"strInstructionsDE\": \"Alle Zutaten gut mit gebrochenem Eis schütteln, in ein Cocktailglas abseihen und servieren.\",\n" +
                "      \"strInstructionsFR\": null,\n" +
                "      \"strInstructionsIT\": \"Shakerare bene tutti gli ingredienti con ghiaccio tritato, filtrare in una coppetta da cocktail e servire.\",\n" +
                "      \"strInstructionsZH-HANS\": null,\n" +
                "      \"strInstructionsZH-HANT\": null,\n" +
                "      \"strDrinkThumb\": \"https://www.thecocktaildb.com/images/media/drink/twuptu1483388307.jpg\",\n" +
                "      \"strIngredient1\": \"Brandy\",\n" +
                "      \"strIngredient2\": \"Amaretto\",\n" +
                "      \"strIngredient3\": \"Light cream\",\n" +
                "      \"strIngredient4\": null,\n" +
                "      \"strIngredient5\": null,\n" +
                "      \"strIngredient6\": null,\n" +
                "      \"strIngredient7\": null,\n" +
                "      \"strIngredient8\": null,\n" +
                "      \"strIngredient9\": null,\n" +
                "      \"strIngredient10\": null,\n" +
                "      \"strIngredient11\": null,\n" +
                "      \"strIngredient12\": null,\n" +
                "      \"strIngredient13\": null,\n" +
                "      \"strIngredient14\": null,\n" +
                "      \"strIngredient15\": null,\n" +
                "      \"strMeasure1\": \"1 oz \",\n" +
                "      \"strMeasure2\": \"1 oz \",\n" +
                "      \"strMeasure3\": \"1 oz \",\n" +
                "      \"strMeasure4\": null,\n" +
                "      \"strMeasure5\": null,\n" +
                "      \"strMeasure6\": null,\n" +
                "      \"strMeasure7\": null,\n" +
                "      \"strMeasure8\": null,\n" +
                "      \"strMeasure9\": null,\n" +
                "      \"strMeasure10\": null,\n" +
                "      \"strMeasure11\": null,\n" +
                "      \"strMeasure12\": null,\n" +
                "      \"strMeasure13\": null,\n" +
                "      \"strMeasure14\": null,\n" +
                "      \"strMeasure15\": null,\n" +
                "      \"strImageSource\": null,\n" +
                "      \"strImageAttribution\": null,\n" +
                "      \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "      \"dateModified\": \"2017-01-02 20:18:27\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

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
        String responseString = "{\n" +
                "  \"drinks\": [\n" +
                "    {\n" +
                "      \"idDrink\": \"1107\",\n" +
                "      \"strDrink\": \"Margarita\",\n" +
                "      \"strDrinkAlternate\": null,\n" +
                "      \"strTags\": null,\n" +
                "      \"strVideo\": null,\n" +
                "      \"strCategory\": \"Ordinary Drink\",\n" +
                "      \"strIBA\": null,\n" +
                "      \"strAlcoholic\": \"Alcoholic\",\n" +
                "      \"strGlass\": \"Cocktail glass\",\n" +
                "      \"strInstructions\": \"Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten only the outer rim and sprinkle the salt on it. The salt should present to the lips of the imbiber and never mix into the cocktail. Shake the other ingredients with ice, then carefully pour into the glass.\",\n" +
                "      \"strInstructionsES\": null,\n" +
                "      \"strInstructionsDE\": \"Alle Zutaten gut mit gebrochenem Eis schütteln, in ein Cocktailglas abseihen und servieren.\",\n" +
                "      \"strInstructionsFR\": null,\n" +
                "      \"strInstructionsIT\": \"Shakerare bene tutti gli ingredienti con ghiaccio tritato, filtrare in una coppetta da cocktail e servire.\",\n" +
                "      \"strInstructionsZH-HANS\": null,\n" +
                "      \"strInstructionsZH-HANT\": null,\n" +
                "      \"strDrinkThumb\": \"https://www.thecocktaildb.com/images/media/drink/twuptu1483388307.jpg\",\n" +
                "      \"strIngredient1\": \"Brandy\",\n" +
                "      \"strIngredient2\": \"Amaretto\",\n" +
                "      \"strIngredient3\": \"Light cream\",\n" +
                "      \"strIngredient4\": null,\n" +
                "      \"strIngredient5\": null,\n" +
                "      \"strIngredient6\": null,\n" +
                "      \"strIngredient7\": null,\n" +
                "      \"strIngredient8\": null,\n" +
                "      \"strIngredient9\": null,\n" +
                "      \"strIngredient10\": null,\n" +
                "      \"strIngredient11\": null,\n" +
                "      \"strIngredient12\": null,\n" +
                "      \"strIngredient13\": null,\n" +
                "      \"strIngredient14\": null,\n" +
                "      \"strIngredient15\": null,\n" +
                "      \"strMeasure1\": \"1 oz \",\n" +
                "      \"strMeasure2\": \"1 oz \",\n" +
                "      \"strMeasure3\": \"1 oz \",\n" +
                "      \"strMeasure4\": null,\n" +
                "      \"strMeasure5\": null,\n" +
                "      \"strMeasure6\": null,\n" +
                "      \"strMeasure7\": null,\n" +
                "      \"strMeasure8\": null,\n" +
                "      \"strMeasure9\": null,\n" +
                "      \"strMeasure10\": null,\n" +
                "      \"strMeasure11\": null,\n" +
                "      \"strMeasure12\": null,\n" +
                "      \"strMeasure13\": null,\n" +
                "      \"strMeasure14\": null,\n" +
                "      \"strMeasure15\": null,\n" +
                "      \"strImageSource\": null,\n" +
                "      \"strImageAttribution\": null,\n" +
                "      \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "      \"dateModified\": \"2017-01-02 20:18:27\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

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
        String responseString = "{\n" +
                "    \"drinks\": [\n" +
                "        {\n" +
                "            \"idDrink\": \"17222\",\n" +
                "            \"strDrink\": \"A1\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Cocktail\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Pour all ingredients into a cocktail shaker, mix and serve over ice into a chilled glass.\",\n" +
                "            \"strInstructionsES\": \"Vierta todos los ingredientes en una coctelera, mezcle y sirva con hielo en un vaso fr\\u00edo.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten in einen Cocktailshaker geben, mischen und \\u00fcber Eis in ein gek\\u00fchltes Glas servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare tutti gli ingredienti in uno shaker, mescolare e servire con ghiaccio in un bicchiere freddo.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/2x8thr1504816928.jpg\",\n" +
                "            \"strIngredient1\": \"Gin\",\n" +
                "            \"strIngredient2\": \"Grand Marnier\",\n" +
                "            \"strIngredient3\": \"Lemon Juice\",\n" +
                "            \"strIngredient4\": \"Grenadine\",\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 3\\/4 shot \",\n" +
                "            \"strMeasure2\": \"1 Shot \",\n" +
                "            \"strMeasure3\": \"1\\/4 Shot\",\n" +
                "            \"strMeasure4\": \"1\\/8 Shot\",\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-09-07 21:42:09\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"13501\",\n" +
                "            \"strDrink\": \"ABC\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Shot\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Shot glass\",\n" +
                "            \"strInstructions\": \"Layered in a shot glass.\",\n" +
                "            \"strInstructionsES\": \"Coloque todos los ingredientes en un vaso de chupito.\",\n" +
                "            \"strInstructionsDE\": \"Schichtaufbau in einem Schnapsglas.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versa in ordine di lettera i vari ingredienti. 1\\/3 del bicchiere va riempito con l'Amaretto, 1\\/3 di Baileys e il restante di Cognac.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/tqpvqp1472668328.jpg\",\n" +
                "            \"strIngredient1\": \"Amaretto\",\n" +
                "            \"strIngredient2\": \"Baileys irish cream\",\n" +
                "            \"strIngredient3\": \"Cognac\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1\\/3 \",\n" +
                "            \"strMeasure2\": \"1\\/3 \",\n" +
                "            \"strMeasure3\": \"1\\/3 \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2016-08-31 19:32:08\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17225\",\n" +
                "            \"strDrink\": \"Ace\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Cocktail\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Martini Glass\",\n" +
                "            \"strInstructions\": \"Shake all the ingredients in a cocktail shaker and ice then strain in a cold glass.\",\n" +
                "            \"strInstructionsES\": \"Agite todos los ingredientes en una coctelera con hielo y entonces cu\\u00e9lelos sobre un vaso enfriado.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten in einem Cocktailshaker mit Eis sch\\u00fctteln und dann in einem kalten Glas abseihen.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Shakerare tutti gli ingredienti in uno shaker e ghiaccio, quindi filtrare in un bicchiere freddo.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/l3cd7f1504818306.jpg\",\n" +
                "            \"strIngredient1\": \"Gin\",\n" +
                "            \"strIngredient2\": \"Grenadine\",\n" +
                "            \"strIngredient3\": \"Heavy cream\",\n" +
                "            \"strIngredient4\": \"Milk\",\n" +
                "            \"strIngredient5\": \"Egg White\",\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"2 shots \",\n" +
                "            \"strMeasure2\": \"1\\/2 shot \",\n" +
                "            \"strMeasure3\": \"1\\/2 shot \",\n" +
                "            \"strMeasure4\": \"1\\/2 shot\",\n" +
                "            \"strMeasure5\": \"1\\/2 Fresh\",\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-09-07 22:05:06\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"14610\",\n" +
                "            \"strDrink\": \"ACID\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Shot\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Shot glass\",\n" +
                "            \"strInstructions\": \"Poor in the 151 first followed by the 101 served with a Coke or Dr Pepper chaser.\",\n" +
                "            \"strInstructionsES\": \"Vierta primero el Bacardi 151, seguido del Wild Turkey 101 y sirva con Coca-Cola o Dr Pepper.\",\n" +
                "            \"strInstructionsDE\": \"Gering den 151 gefolgt von der 101, die mit einer Cola oder Dr. Pepper Chaser serviert wird.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versa prima il Bacardi 151 seguito dal 101, servito con una Coca-Cola.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/xuxpxt1479209317.jpg\",\n" +
                "            \"strIngredient1\": \"151 proof rum\",\n" +
                "            \"strIngredient2\": \"Wild Turkey\",\n" +
                "            \"strIngredient3\": null,\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 oz Bacardi \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": null,\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2016-11-15 11:28:37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17837\",\n" +
                "            \"strDrink\": \"Adam\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": \"Alcoholic,Holiday\",\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"In a shaker half-filled with ice cubes, combine all of the ingredients. Shake well. Strain into a cocktail glass.\",\n" +
                "            \"strInstructionsES\": \"Mezclar todos los ingredientes en una coctelera con cubitos de hielo hasta la mitad. Agitar bien. Colar el contenido en un vaso de c\\u00f3ctel.\",\n" +
                "            \"strInstructionsDE\": \"In einem Shaker, der halb mit Eisw\\u00fcrfeln gef\\u00fcllt ist, alle Zutaten vermengen. Gut sch\\u00fctteln. In ein Cocktailglas abseihen.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"In uno shaker riempito a met\\u00e0 con cubetti di ghiaccio, unire tutti gli ingredienti.Agitare bene.Filtrare in un bicchiere da cocktail.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/v0at4i1582478473.jpg\",\n" +
                "            \"strIngredient1\": \"Dark rum\",\n" +
                "            \"strIngredient2\": \"Lemon juice\",\n" +
                "            \"strIngredient3\": \"Grenadine\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"2 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": \"1 tsp \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-09-02 11:29:29\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"13938\",\n" +
                "            \"strDrink\": \"AT&T\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Highball Glass\",\n" +
                "            \"strInstructions\": \"Pour Vodka and Gin over ice, add Tonic and Stir\",\n" +
                "            \"strInstructionsES\": \"Vierta el vodka y la ginebra sobre el hielo, agregue la t\\u00f3nica y revuelva.\",\n" +
                "            \"strInstructionsDE\": \"Wodka und Gin \\u00fcber das Eis gie\\u00dfen, Tonic hinzuf\\u00fcgen und umr\\u00fchren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare la vodka e il gin sul ghiaccio, aggiungere l'acqua tonica e mescolare\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/rhhwmp1493067619.jpg\",\n" +
                "            \"strIngredient1\": \"Absolut Vodka\",\n" +
                "            \"strIngredient2\": \"Gin\",\n" +
                "            \"strIngredient3\": \"Tonic water\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": \"4 oz \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-04-24 22:00:19\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17833\",\n" +
                "            \"strDrink\": \"A. J.\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Shake ingredients with ice, strain into a cocktail glass, and serve.\",\n" +
                "            \"strInstructionsES\": \"Agite bien los ingredientes con hielo, cu\\u00e9lelos en una copa de c\\u00f3ctel y sirva.\",\n" +
                "            \"strInstructionsDE\": null,\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Shakerare gli ingredienti con ghiaccio, filtrare in una coppetta da cocktail e servire.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/l74qo91582480316.jpg\",\n" +
                "            \"strIngredient1\": \"Applejack\",\n" +
                "            \"strIngredient2\": \"Grapefruit juice\",\n" +
                "            \"strIngredient3\": null,\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 1\\/2 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": null,\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-09-02 11:16:58\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"15266\",\n" +
                "            \"strDrink\": \"Avalon\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Highball glass\",\n" +
                "            \"strInstructions\": \"Fill a tall glass with ice. Layer the Finlandia Vodka, lemon and apple juices, Pisang Ambon, and top up with lemonade. Stir slightly and garnish with a spiralled cucumber skin and a red cherry. The cucumber provides zest and looks attractive. This drink, created by Timo Haimi, took first prize in the 1991 Finlandia Vodka Long Drink Competition.\",\n" +
                "            \"strInstructionsES\": \"Llena un vaso alto con hielo. Agregue el vodka, los jugos de lim\\u00f3n y manzana, el licor de pl\\u00e1tano Pisang Ambon y complete con limonada. Revuelva ligeramente y adorne con una c\\u00e1scara de pepino en espiral y una cereza roja. El pepino proporciona entusiasmo y se ve atractivo. Esta bebida, creada por Timo Haimi, gan\\u00f3 el primer premio en el Concurso de Tragos Largos de Vodka de Finlandia de 1991.\",\n" +
                "            \"strInstructionsDE\": \"F\\u00fcllen Sie ein hohes Glas mit Eis. Legen Sie den Finlandia Wodka, Zitronen- und Apfels\\u00e4fte, Pisang Ambon und geben Sie Limonade dazu. Leicht umr\\u00fchren und mit einer spiralf\\u00f6rmigen Gurkenhaut und einer roten Kirsche garnieren. Die Gurke sorgt f\\u00fcr Sch\\u00e4rfe und sieht attraktiv aus. Dieses von Timo Haimi kreierte Getr\\u00e4nk erhielt 1991 den ersten Preis beim Finlandia Wodka Long Drink Wettbewerb.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Riempi un bicchiere alto di ghiaccio.\\r\\nVersare la Vodka, succhi di limone, mela, Pisang Ambon o un liquore alla banana e completare con la limonata.\\r\\nMescolare leggermente e guarnire con una buccia di cetriolo a spirale e una ciliegia rossa.\\r\\nIl cetriolo fornisce la scorza e sembra attraente.\\r\\nQuesta bevanda, creata da Timo Haimi, ha vinto il primo premio nel 1991 Finlandia Vodka Long Drink Competition.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/3k9qic1493068931.jpg\",\n" +
                "            \"strIngredient1\": \"Vodka\",\n" +
                "            \"strIngredient2\": \"Pisang Ambon\",\n" +
                "            \"strIngredient3\": \"Apple juice\",\n" +
                "            \"strIngredient4\": \"Lemon juice\",\n" +
                "            \"strIngredient5\": \"Lemonade\",\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"3 parts\",\n" +
                "            \"strMeasure2\": \"1 part \",\n" +
                "            \"strMeasure3\": \"6 parts \",\n" +
                "            \"strMeasure4\": \"1 1\\/2 part \",\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-04-24 22:22:11\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"15106\",\n" +
                "            \"strDrink\": \"Apello\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Other \\/ Unknown\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Non alcoholic\",\n" +
                "            \"strGlass\": \"Collins Glass\",\n" +
                "            \"strInstructions\": \"Stirr. Grnish with maraschino cherry.\",\n" +
                "            \"strInstructionsES\": \"Mezclar. Decorar con cereza marrasquino.\",\n" +
                "            \"strInstructionsDE\": \"R\\u00fchren. Mit Maraschinokirsche garnieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare tutti gli ingredienti in un mixing glass. Mescolate.\\r\\nGuarnire con una ciliegia al maraschino.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/uptxtv1468876415.jpg\",\n" +
                "            \"strIngredient1\": \"Orange juice\",\n" +
                "            \"strIngredient2\": \"Grapefruit juice\",\n" +
                "            \"strIngredient3\": \"Apple juice\",\n" +
                "            \"strIngredient4\": \"Maraschino cherry\",\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"4 cl \",\n" +
                "            \"strMeasure2\": \"3 cl \",\n" +
                "            \"strMeasure3\": \"1 cl \",\n" +
                "            \"strMeasure4\": \"1 \",\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2016-07-18 22:13:35\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17839\",\n" +
                "            \"strDrink\": \"Affair\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Highball glass\",\n" +
                "            \"strInstructions\": \"Pour schnapps, orange juice, and cranberry juice over ice in a highball glass. Top with club soda and serve.\",\n" +
                "            \"strInstructionsES\": \"Vierta el Schnapps, el jugo de naranja y el jugo de ar\\u00e1ndano sobre hielo en un vaso alto. Complete con el agua carbonatada y sirva.\",\n" +
                "            \"strInstructionsDE\": \"Gie\\u00dfen Sie Schnaps, Orangensaft und Cranberrysaft \\u00fcber Eis in ein Highball-Glas. Mit Club-Soda \\u00fcberziehen und servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare la grappa, il succo d'arancia e il succo di mirtillo rosso sul ghiaccio in un bicchiere highball. Completare con la soda club e servire.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/h5za6y1582477994.jpg\",\n" +
                "            \"strIngredient1\": \"Strawberry schnapps\",\n" +
                "            \"strIngredient2\": \"Orange juice\",\n" +
                "            \"strIngredient3\": \"Cranberry juice\",\n" +
                "            \"strIngredient4\": \"Club soda\",\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"2 oz \",\n" +
                "            \"strMeasure2\": \"2 oz \",\n" +
                "            \"strMeasure3\": \"2 oz \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-09-01 10:05:34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17835\",\n" +
                "            \"strDrink\": \"Abilene\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Highball glass\",\n" +
                "            \"strInstructions\": \"Pour all of the ingredients into a highball glass almost filled with ice cubes. Stir well.\",\n" +
                "            \"strInstructionsES\": \"Coloque todos los ingredientes en un vaso alto casi lleno de cubitos de hielo. Sacudir bien.\",\n" +
                "            \"strInstructionsDE\": null,\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare tutti gli ingredienti in un bicchiere highball riempito di cubetti di ghiaccio. Mescolare bene.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/smb2oe1582479072.jpg\",\n" +
                "            \"strIngredient1\": \"Dark rum\",\n" +
                "            \"strIngredient2\": \"Peach nectar\",\n" +
                "            \"strIngredient3\": \"Orange juice\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 1\\/2 oz \",\n" +
                "            \"strMeasure2\": \"2 oz \",\n" +
                "            \"strMeasure3\": \"3 oz \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-04-29 09:44:25\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"11023\",\n" +
                "            \"strDrink\": \"Almeria\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"In a shaker half-filled with ice cubes, combine all of the ingredients. Shake well. Strain into a cocktail glass.\",\n" +
                "            \"strInstructionsES\": \"En una coctelera llena hasta la mitad con cubitos de hielo, mezcla todos los ingredientes. Agitar bien. Colar el contenido en un vaso de c\\u00f3ctel.\",\n" +
                "            \"strInstructionsDE\": \"In einem Shaker, der halb mit Eisw\\u00fcrfeln gef\\u00fcllt ist, alle Zutaten vermengen. Gut sch\\u00fctteln. In ein Cocktailglas abseihen.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"In uno shaker riempito a met\\u00e0 con cubetti di ghiaccio, unire tutti gli ingredienti.Agitare bene.Filtrare in un bicchiere da cocktail.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/rwsyyu1483388181.jpg\",\n" +
                "            \"strIngredient1\": \"Dark rum\",\n" +
                "            \"strIngredient2\": \"Kahlua\",\n" +
                "            \"strIngredient3\": \"Egg white\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"2 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": \"1 \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-01-02 20:16:21\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17228\",\n" +
                "            \"strDrink\": \"Addison\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Cocktail\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Martini Glass\",\n" +
                "            \"strInstructions\": \"Shake together all the ingredients and strain into a cold glass.\",\n" +
                "            \"strInstructionsES\": \"Agitar todos los ingredientes y colar la mezcla en un vaso enfriado.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten vermengen und in ein kaltes Glas abseihen.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Shakerare insieme tutti gli ingredienti e filtrare in un bicchiere freddo.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/yzva7x1504820300.jpg\",\n" +
                "            \"strIngredient1\": \"Gin\",\n" +
                "            \"strIngredient2\": \"Vermouth\",\n" +
                "            \"strIngredient3\": null,\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 1\\/2 shot \",\n" +
                "            \"strMeasure2\": \"1 1\\/2 shot \",\n" +
                "            \"strMeasure3\": null,\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-09-07 22:38:20\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"11046\",\n" +
                "            \"strDrink\": \"Applecar\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Shake all ingredients with ice, strain into a cocktail glass, and serve.\",\n" +
                "            \"strInstructionsES\": \"Agitar todos los ingredientes con hielo, colar en un vaso de c\\u00f3ctel y servir.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten mit Eis sch\\u00fctteln, in ein Cocktailglas abseihen und servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Shakerare tutti gli ingredienti con ghiaccio, filtrare in una coppetta da cocktail e servire.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/sbffau1504389764.jpg\",\n" +
                "            \"strIngredient1\": \"Applejack\",\n" +
                "            \"strIngredient2\": \"Triple sec\",\n" +
                "            \"strIngredient3\": \"Lemon juice\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": \"1 oz \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-09-02 23:02:44\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17836\",\n" +
                "            \"strDrink\": \"Acapulco\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Old-fashioned glass\",\n" +
                "            \"strInstructions\": \"Combine and shake all ingredients (except mint) with ice and strain into an old-fashioned glass over ice cubes. Add the sprig of mint and serve.\",\n" +
                "            \"strInstructionsES\": \"Mezcle y agite todos los ingredientes (excepto la menta) con hielo y cu\\u00e9lelos en un vaso de rocas sobre cubitos de hielo. A\\u00f1adir una ramita de menta y servir.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten (au\\u00dfer Minze) mit Eis mischen und sch\\u00fctteln und in ein old-fashioned Glas \\u00fcber Eisw\\u00fcrfel abseihen. Den Minzzweig dazugeben und servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Unire e scuotere tutti gli ingredienti (tranne la menta) con ghiaccio e filtrare in un bicchiere vecchio stile su cubetti di ghiaccio.Aggiungere il rametto di menta e servire.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/il9e0r1582478841.jpg\",\n" +
                "            \"strIngredient1\": \"Light rum\",\n" +
                "            \"strIngredient2\": \"Triple sec\",\n" +
                "            \"strIngredient3\": \"Lime juice\",\n" +
                "            \"strIngredient4\": \"Sugar\",\n" +
                "            \"strIngredient5\": \"Egg white\",\n" +
                "            \"strIngredient6\": \"Mint\",\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 1\\/2 oz \",\n" +
                "            \"strMeasure2\": \"1 1\\/2 tsp \",\n" +
                "            \"strMeasure3\": \"1 tblsp \",\n" +
                "            \"strMeasure4\": \"1 tsp \",\n" +
                "            \"strMeasure5\": \"1 \",\n" +
                "            \"strMeasure6\": \"1 \",\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-09-02 11:26:16\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17840\",\n" +
                "            \"strDrink\": \"Affinity\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"In a mixing glass half-filled with ice cubes, combine all of the ingredients. Stir well. Strain into a cocktail glass.\",\n" +
                "            \"strInstructionsES\": \"En un vaso mezclador lleno hasta la mitad con cubitos de hielo, combine todos los ingredientes. Sacudir bien. Colar en un vaso de c\\u00f3ctel.\",\n" +
                "            \"strInstructionsDE\": \"In einem Mischglas, das halb mit Eisw\\u00fcrfeln gef\\u00fcllt ist, alle Zutaten vermengen. Gut umr\\u00fchren. In ein Cocktailglas abseihen.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"In un mixing glass riempito a met\\u00e0 con cubetti di ghiaccio, unire tutti gli ingredienti. Filtrare in un bicchiere da cocktail. Mescolare bene.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/wzdtnn1582477684.jpg\",\n" +
                "            \"strIngredient1\": \"Scotch\",\n" +
                "            \"strIngredient2\": \"Sweet Vermouth\",\n" +
                "            \"strIngredient3\": \"Dry Vermouth\",\n" +
                "            \"strIngredient4\": \"Orange bitters\",\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 1\\/2 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": \"1 oz \",\n" +
                "            \"strMeasure4\": \"2 dashes \",\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2017-09-07 21:44:05\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"17180\",\n" +
                "            \"strDrink\": \"Aviation\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": \"IBA,Classic\",\n" +
                "            \"strVideo\": \"https:\\/\\/www.youtube.com\\/watch?v=oOHH0GGglyM\",\n" +
                "            \"strCategory\": \"Cocktail\",\n" +
                "            \"strIBA\": \"Unforgettables\",\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Add all ingredients into cocktail shaker filled with ice. Shake well and strain into cocktail glass. Garnish with a cherry.\",\n" +
                "            \"strInstructionsES\": \"Agregue todos los ingredientes a una coctelera llena de hielo. Agitar bien y colar en un vaso de c\\u00f3ctel. Decorar con una cereza.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten in den mit Eis gef\\u00fcllten Cocktailshaker geben. Gut sch\\u00fctteln und in ein Cocktailglas abseihen. Mit einer Kirsche garnieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Aggiungi tutti gli ingredienti in uno shaker pieno di ghiaccio.\\r\\nShakerare bene e filtrare in una coppetta da cocktail.\\r\\nGuarnire con una ciliegia.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/trbplb1606855233.jpg\",\n" +
                "            \"strIngredient1\": \"Gin\",\n" +
                "            \"strIngredient2\": \"lemon juice\",\n" +
                "            \"strIngredient3\": \"maraschino liqueur\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"4.5 cl\",\n" +
                "            \"strMeasure2\": \"1.5 cl\",\n" +
                "            \"strMeasure3\": \"1.5 cl\",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": \"https:\\/\\/www.flickr.com\\/photos\\/mstyne\\/27859363080\\/in\\/dateposted\\/\",\n" +
                "            \"strImageAttribution\": \"Michael Styne https:\\/\\/www.flickr.com\\/photos\\/mstyne\\/\",\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-11-04 09:55:28\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"15182\",\n" +
                "            \"strDrink\": \"After sex\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Highball glass\",\n" +
                "            \"strInstructions\": \"Pour the vodka and creme over some ice cubes in a tall glass and fill up with juice. to make it beuty full make the top of the glass with a grenadine and sugar\",\n" +
                "            \"strInstructionsES\": \"Vierta el vodka y la crema de pl\\u00e1tano sobre unos cubitos de hielo en un vaso alto y rell\\u00e9nelo con el jugo de naranja. Para que quede bonito, pruebe a escarchar el borde de la copa con granadina y az\\u00facar.\",\n" +
                "            \"strInstructionsDE\": \"Gie\\u00dfen Sie den Wodka und die Sahne \\u00fcber einige Eisw\\u00fcrfel in ein hohes Glas und f\\u00fcllen Sie ihn mit Saft. Damit es voll ist, verzieren Sie die Oberseite des Glases mit einer Grenadine und Zucker.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare la vodka e la crema su alcuni cubetti di ghiaccio in un bicchiere alto e riempire di succo.\\r\\nPer renderlo bello, versare nella parte superiore del bicchiere un po\\u2019 di granatina e nei bordi mettere un po\\u2019 di zucchero\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/xrl66i1493068702.jpg\",\n" +
                "            \"strIngredient1\": \"Vodka\",\n" +
                "            \"strIngredient2\": \"Creme de Banane\",\n" +
                "            \"strIngredient3\": \"Orange juice\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"2 cl \",\n" +
                "            \"strMeasure2\": \"1 cl \",\n" +
                "            \"strMeasure3\": null,\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-04-24 22:18:22\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"16311\",\n" +
                "            \"strDrink\": \"Applejack\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Cocktail\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Add all ingredients into mixing glass, chill and strain into cocktail glass\",\n" +
                "            \"strInstructionsES\": \"A\\u00f1adir todos los ingredientes en un vaso mezclador, enfriar y colar en un vaso de c\\u00f3ctel.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten in das Mischglas geben, k\\u00fchlen und in das Cocktailglas abseihen.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Aggiungere tutti gli ingredienti nel mixing glass, raffreddare e filtrare in una coppetta da cocktail\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/sutyqp1479209062.jpg\",\n" +
                "            \"strIngredient1\": \"Jack Daniels\",\n" +
                "            \"strIngredient2\": \"Midori melon liqueur\",\n" +
                "            \"strIngredient3\": \"Sour mix\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 oz \",\n" +
                "            \"strMeasure2\": \"1\\/2 oz \",\n" +
                "            \"strMeasure3\": \"2 oz \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2016-11-15 11:24:22\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"12560\",\n" +
                "            \"strDrink\": \"Afterglow\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Cocktail\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Non alcoholic\",\n" +
                "            \"strGlass\": \"Highball Glass\",\n" +
                "            \"strInstructions\": \"Mix. Serve over ice.\",\n" +
                "            \"strInstructionsES\": \"Mezcla. Servir con hielo.\",\n" +
                "            \"strInstructionsDE\": \"Mischen. Auf Eis servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Servire con ghiaccio.Mescolare.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/vuquyv1468876052.jpg\",\n" +
                "            \"strIngredient1\": \"Grenadine\",\n" +
                "            \"strIngredient2\": \"Orange juice\",\n" +
                "            \"strIngredient3\": \"Pineapple juice\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 part \",\n" +
                "            \"strMeasure2\": \"4 parts \",\n" +
                "            \"strMeasure3\": \"4 parts \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2016-07-18 22:07:32\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"13162\",\n" +
                "            \"strDrink\": \"Afternoon\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Coffee \\/ Tea\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Collins Glass\",\n" +
                "            \"strInstructions\": \"Build into a suiting glass, with no ice. Cream on top if wanted. Served directly.\",\n" +
                "            \"strInstructionsES\": \"Preparar en un vaso adecuado, sin hielo. Crema encima, si gustas. Servido directamente.\",\n" +
                "            \"strInstructionsDE\": \"In ein passendes Glas geben, ohne Eis. Auf Wunsch mit Sahne \\u00fcberziehen. Direkt serviert.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Incorpora un bicchiere adatto, senza ghiaccio.\\r\\nCrema in cima se lo si desidera.\\r\\nServito direttamente.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/vyrurp1472667777.jpg\",\n" +
                "            \"strIngredient1\": \"Kahlua\",\n" +
                "            \"strIngredient2\": \"Baileys irish cream\",\n" +
                "            \"strIngredient3\": \"Frangelico\",\n" +
                "            \"strIngredient4\": \"Coffee\",\n" +
                "            \"strIngredient5\": \"Cream\",\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 cl \",\n" +
                "            \"strMeasure2\": \"1 cl \",\n" +
                "            \"strMeasure3\": \"1 1\\/2 \",\n" +
                "            \"strMeasure4\": \"4 cl hot \",\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2016-08-31 19:22:57\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"11014\",\n" +
                "            \"strDrink\": \"Alexander\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": \"IBA,Classic,Dairy\",\n" +
                "            \"strVideo\": \"https:\\/\\/www.youtube.com\\/watch?v=qEhRK_v2w2g\",\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": \"Unforgettables\",\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Shake all ingredients with ice and strain contents into a cocktail glass. Sprinkle nutmeg on top and serve.\",\n" +
                "            \"strInstructionsES\": \"Agite todos los ingredientes con hielo y cuele el contenido en una copa de c\\u00f3ctel. Espolvorea nuez moscada encima y sirve.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten mit Eis sch\\u00fctteln und in ein Cocktailglas abseihen. Muskatnuss dar\\u00fcber streuen und servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Shakerare tutti gli ingredienti con ghiaccio e filtrare il contenuto in un bicchiere da cocktail. Cospargere di noce moscata e servire.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/0clus51606772388.jpg\",\n" +
                "            \"strIngredient1\": \"Gin\",\n" +
                "            \"strIngredient2\": \"Creme de Cacao\",\n" +
                "            \"strIngredient3\": \"Light cream\",\n" +
                "            \"strIngredient4\": \"Nutmeg\",\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1\\/2 oz \",\n" +
                "            \"strMeasure2\": \"1\\/2 oz white \",\n" +
                "            \"strMeasure3\": \"2 oz \",\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": \"https:\\/\\/www.pxfuel.com\\/en\\/free-photo-expqp\",\n" +
                "            \"strImageAttribution\": \"pxfuel.com\",\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-11-04 09:50:39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"12756\",\n" +
                "            \"strDrink\": \"Autodaf\\u00e9\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Highball glass\",\n" +
                "            \"strInstructions\": \"Mix and fill up with soda water. Drunk by finns on a sunny day any time of the year and day.\",\n" +
                "            \"strInstructionsES\": \"Mezclar y completar con agua con gas. Bebido por los finlandeses en un d\\u00eda soleado en cualquier \\u00e9poca del a\\u00f1o y del d\\u00eda.\",\n" +
                "            \"strInstructionsDE\": \"Mischen und mit Sodawasser auff\\u00fcllen. Getrunken von Finnen an einem sonnigen Tag zu jeder Jahreszeit und an jedem Tag.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Mescolare e riempire con acqua di seltz. Bevuto dai finlandesi in una giornata di sole in qualsiasi momento dell'anno e del giorno.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/7dkf0i1487602928.jpg\",\n" +
                "            \"strIngredient1\": \"Vodka\",\n" +
                "            \"strIngredient2\": \"Lime juice\",\n" +
                "            \"strIngredient3\": \"Soda water\",\n" +
                "            \"strIngredient4\": null,\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"4 cl \",\n" +
                "            \"strMeasure2\": \"1 dash \",\n" +
                "            \"strMeasure3\": null,\n" +
                "            \"strMeasure4\": null,\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-02-20 15:02:08\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"11021\",\n" +
                "            \"strDrink\": \"Allegheny\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": null,\n" +
                "            \"strVideo\": null,\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": null,\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Cocktail glass\",\n" +
                "            \"strInstructions\": \"Shake all ingredients (except lemon peel) with ice and strain into a cocktail glass. Top with the twist of lemon peel and serve.\",\n" +
                "            \"strInstructionsES\": \"Agite todos los ingredientes (excepto el twist de lim\\u00f3n) con hielo y cuele en un vaso de c\\u00f3ctel. Cubra con el twist de lim\\u00f3n y sirva.\",\n" +
                "            \"strInstructionsDE\": \"Alle Zutaten (au\\u00dfer Zitronenschale) mit Eis sch\\u00fctteln und in ein Cocktailglas abseihen. Mit der Drehung der Zitronenschale garnieren und servieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Shakerare tutti gli ingredienti (tranne la buccia di limone) con ghiaccio e filtrare in una coppetta da cocktail.Completare con la scorza di limone e servire.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/uwvyts1483387934.jpg\",\n" +
                "            \"strIngredient1\": \"Dry Vermouth\",\n" +
                "            \"strIngredient2\": \"Bourbon\",\n" +
                "            \"strIngredient3\": \"Blackberry brandy\",\n" +
                "            \"strIngredient4\": \"Lemon juice\",\n" +
                "            \"strIngredient5\": \"Lemon peel\",\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 oz \",\n" +
                "            \"strMeasure2\": \"1 oz \",\n" +
                "            \"strMeasure3\": \"1 1\\/2 tsp \",\n" +
                "            \"strMeasure4\": \"1 1\\/2 tsp \",\n" +
                "            \"strMeasure5\": \"1 twist of \",\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": null,\n" +
                "            \"strImageAttribution\": null,\n" +
                "            \"strCreativeCommonsConfirmed\": \"No\",\n" +
                "            \"dateModified\": \"2017-01-02 20:12:14\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idDrink\": \"15941\",\n" +
                "            \"strDrink\": \"Americano\",\n" +
                "            \"strDrinkAlternate\": null,\n" +
                "            \"strTags\": \"IBA,Classic\",\n" +
                "            \"strVideo\": \"https:\\/\\/www.youtube.com\\/watch?v=TmeUJ2g3ogM\",\n" +
                "            \"strCategory\": \"Ordinary Drink\",\n" +
                "            \"strIBA\": \"Unforgettables\",\n" +
                "            \"strAlcoholic\": \"Alcoholic\",\n" +
                "            \"strGlass\": \"Collins glass\",\n" +
                "            \"strInstructions\": \"Pour the Campari and vermouth over ice into glass, add a splash of soda water and garnish with half orange slice.\",\n" +
                "            \"strInstructionsES\": \"Vierta el Campari y el vermut con hielo en el vaso. A\\u00f1adir un poco de agua con gas y decorar con media rodaja de naranja.\",\n" +
                "            \"strInstructionsDE\": \"Den Campari und den Wermut \\u00fcber Eis in ein Glas gie\\u00dfen, einen Spritzer Sodawasser hinzuf\\u00fcgen und mit einer halben Orangenscheibe garnieren.\",\n" +
                "            \"strInstructionsFR\": null,\n" +
                "            \"strInstructionsIT\": \"Versare Campari e vermut su ghiaccio in un bicchiere, aggiungere un goccio di acqua di seltz e guarnire con mezza fetta d'arancia.\",\n" +
                "            \"strInstructionsZH-HANS\": null,\n" +
                "            \"strInstructionsZH-HANT\": null,\n" +
                "            \"strDrinkThumb\": \"https:\\/\\/www.thecocktaildb.com\\/images\\/media\\/drink\\/709s6m1613655124.jpg\",\n" +
                "            \"strIngredient1\": \"Campari\",\n" +
                "            \"strIngredient2\": \"Sweet Vermouth\",\n" +
                "            \"strIngredient3\": \"Lemon peel\",\n" +
                "            \"strIngredient4\": \"Orange peel\",\n" +
                "            \"strIngredient5\": null,\n" +
                "            \"strIngredient6\": null,\n" +
                "            \"strIngredient7\": null,\n" +
                "            \"strIngredient8\": null,\n" +
                "            \"strIngredient9\": null,\n" +
                "            \"strIngredient10\": null,\n" +
                "            \"strIngredient11\": null,\n" +
                "            \"strIngredient12\": null,\n" +
                "            \"strIngredient13\": null,\n" +
                "            \"strIngredient14\": null,\n" +
                "            \"strIngredient15\": null,\n" +
                "            \"strMeasure1\": \"1 oz \",\n" +
                "            \"strMeasure2\": \"1 oz red \",\n" +
                "            \"strMeasure3\": \"Twist of \",\n" +
                "            \"strMeasure4\": \"Twist of \",\n" +
                "            \"strMeasure5\": null,\n" +
                "            \"strMeasure6\": null,\n" +
                "            \"strMeasure7\": null,\n" +
                "            \"strMeasure8\": null,\n" +
                "            \"strMeasure9\": null,\n" +
                "            \"strMeasure10\": null,\n" +
                "            \"strMeasure11\": null,\n" +
                "            \"strMeasure12\": null,\n" +
                "            \"strMeasure13\": null,\n" +
                "            \"strMeasure14\": null,\n" +
                "            \"strMeasure15\": null,\n" +
                "            \"strImageSource\": \"https:\\/\\/commons.wikimedia.org\\/wiki\\/File:Martini_Americano.jpg\",\n" +
                "            \"strImageAttribution\": \"Author - Cher37 https:\\/\\/commons.wikimedia.org\\/wiki\\/File:Martini_Americano.jpg\",\n" +
                "            \"strCreativeCommonsConfirmed\": \"Yes\",\n" +
                "            \"dateModified\": \"2016-11-04 09:52:06\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        stubFor(get(urlPathMatching("/api/json/v1/1/search.php*"))
                .withQueryParam("f", equalTo("a"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(responseString)));

        Drink drink = service.getCocktailByFirstLetter("a").get(0);

        verify(getRequestedFor(urlPathMatching("/api/json/v1/1/search.php.*"))
                .withQueryParam("s", equalTo("a")));

        assertEquals("a1", drink.getName());
        assertEquals("17222", drink.getId());
        assertEquals("Pour all ingredients into a cocktail shaker, mix and serve over ice into a chilled glass.", drink.getInstruction());
    }

}
