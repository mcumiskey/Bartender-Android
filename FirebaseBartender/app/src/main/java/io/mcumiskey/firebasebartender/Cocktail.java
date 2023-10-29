package io.mcumiskey.firebasebartender;

public class Cocktail {
    private String cocktailName;
    private String cocktailIngredients;
    private String cocktailInstructions;
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getCocktailName() {
        return cocktailName;
    }
    public String getCocktailIngredients() {
        return cocktailIngredients;
    }
    public String getCocktailInstructions() {
        return cocktailInstructions;
    }
    public Cocktail(String name, String ingredients, String steps) {
        this.cocktailName = name;
        this.cocktailIngredients = ingredients;
        this.cocktailInstructions = steps;
    }
    public Cocktail(){
    }
}