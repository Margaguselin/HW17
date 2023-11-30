package org.example;


import java.net.http.HttpClient;
import java.time.Duration;

public class Main {
 public static void main(String[] args){
     HttpClient httpClient = HttpClient
             .newBuilder()
             .connectTimeout(Duration.ofSeconds(5))
             .build();
     HttpService service = new HttpService(httpClient, "https://www.thecocktaildb.com/");
     Drink randomDrink = service.getRandomCocktail().get(0);
     System.out.println("ID " + randomDrink.getId() + ", Name " + randomDrink.getName() + ", Instruction " + randomDrink.getInstruction());



     Drink margarita = service.getCocktailByName("Margarita").get(5);
     System.out.println("ID " + margarita.getId() + ", Name " + margarita.getName() + ", Instruction " + margarita.getInstruction());

     Drink drinkByFirstLetter = service.getCocktailByFirstLetter("a").get(6);
     System.out.println("ID " + drinkByFirstLetter.getId() + ", Name " + drinkByFirstLetter.getName() + ", Instruction " + drinkByFirstLetter.getInstruction());
    }

}
