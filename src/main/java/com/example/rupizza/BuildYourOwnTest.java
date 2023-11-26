package com.example.rupizza;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
* This class is a tester class for the BuildYourOwn class, it tests the price() method
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class BuildYourOwnTest {

    /**
    * The inputs are size = small; sauce = tomato; toppings = pepperoni, mushroom, onion; no extra sauce but yes to extra cheese
    */
    @Test
    public void testSmallBuildYourOwnPizza() {
        Size size = Size.small;
        Sauce sauce = Sauce.tomato;
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.Pepperoni, Topping.Mushroom, Topping.Onion));
        boolean extraSauce = true;
        boolean extraCheese = false;

        Pizza pizza = PizzaMaker.createPizza("buildyourown", size, sauce, toppings, extraSauce, extraCheese);

        // Small base price: $8.99 + $1 for extra sauce
        Assert.assertEquals(9.99, pizza.price(), 0.01);
    }

    /**
    * The inputs are size = medium; sauce = tomato; toppings = pepperoni, mushroom, onion; no extra sauce but yes for extra sauce
    */
    @Test
    public void testMediumBuildYourOwnPizza() {
        Size size = Size.medium;
        Sauce sauce = Sauce.tomato;
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.Pepperoni, Topping.Mushroom, Topping.Onion));
        boolean extraSauce = false;
        boolean extraCheese = true;

        Pizza pizza = PizzaMaker.createPizza("buildyourown", size, sauce, toppings, extraSauce, extraCheese);

        // Medium base price: $8.99 + $2 for medium size + $1 for extra cheese
        Assert.assertEquals(11.99, pizza.price(), 0.01);
    }

    /**
    * The inputs are size = large; sauce = alfredo; toppings = sausage, green pepper, onion; yes to extra cheese and sauce
    */
    @Test
    public void testLargeBuildYourOwnPizza() {
        Size size = Size.large;
        Sauce sauce = Sauce.alfredo;
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.Sausage, Topping.GreenPepper, Topping.Onion));
        boolean extraSauce = true;
        boolean extraCheese = true;

        Pizza pizza = PizzaMaker.createPizza("buildyourown", size, sauce, toppings, extraSauce, extraCheese);

        // Large base price: $8.99 + $4 for large size + $1 for extra sauce + $1 for extra cheese
        Assert.assertEquals(14.99, pizza.price(), 0.01);
    }

    /**
    * The inputs are size = medium; sauce = tomato; toppings = pepperoni, mushroom, onion, green pepper and ham; no extra cheese or sauce
    */
    @Test
    public void testBuildYourOwnPizzaWithExtraToppings() {
        Size size = Size.medium;
        Sauce sauce = Sauce.tomato;
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.Pepperoni, Topping.Mushroom, Topping.Onion, Topping.GreenPepper, Topping.Ham));
        boolean extraSauce = false;
        boolean extraCheese = false;

        Pizza pizza = PizzaMaker.createPizza("buildyourown", size, sauce, toppings, extraSauce, extraCheese);

        // Medium base price: $8.99 + $2 for medium size + $2.98 for 2 extra toppings
        Assert.assertEquals(13.97, pizza.price(), 0.01);
    }
    
    /**
    * The inputs are size = large; sauce = tomato; toppings = pepperoni, mushroom, onion; yes to extra cheese and sauce
    */
    @Test
    public void testBuildYourOwnPizzaWithExtraCheese() {
        Size size = Size.large;
        Sauce sauce = Sauce.tomato;
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.Pepperoni, Topping.Mushroom, Topping.Onion));
        boolean extraSauce = true;
        boolean extraCheese = true;

        Pizza pizza = PizzaMaker.createPizza("buildyourown", size, sauce, toppings, extraSauce, extraCheese);

        // Large base price: $8.99 + $4 for large size + $1 for extra sauce + $1 for extra cheese
        Assert.assertEquals(14.99, pizza.price(), 0.01);
    }

    /**
    * The inputs are size = small; sauce = tomato; toppings = pepperoni, mushroom, onion; no extra cheese or sauce
    */
    @Test
    public void testBuildYourOwnPizzaWithNoExtras() {
        Size size = Size.small;
        Sauce sauce = Sauce.tomato;
        ArrayList<Topping> toppings = new ArrayList<>(Arrays.asList(Topping.Pepperoni, Topping.Mushroom, Topping.Onion));
        boolean extraSauce = false;
        boolean extraCheese = false;

        Pizza pizza = PizzaMaker.createPizza("buildyourown", size, sauce, toppings, extraSauce, extraCheese);

        // Small base price: $8.99
        Assert.assertEquals(8.99, pizza.price(), 0.01);
    }
}
