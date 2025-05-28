package com.pluralsight.ui;

import com.pluralsight.datamodels.Order;

import java.util.Scanner;

public class UserInterface {

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    // declare Order class
    private static Order order;

    // private constructor
    private UserInterface() {}

    // displays screens and shop options to user
    public static void display() throws InterruptedException {

        // calls welcome message only when loaded up
        welcomeMessage();

        boolean homeRunning = true;
        while (homeRunning) {
            int homeScreenOption = homeScreen();
            // home screen
            switch (homeScreenOption) {
                // create a new order
                case 1:
                    boolean orderRunning = true;
                    while (orderRunning) {
                        int orderScreenOption = orderScreen();
                        // order screen
                        switch (orderScreenOption) {
                            // adds a sandwich
                            case 1:
                                addSandwich();
                                break;
                            // adds a drink
                            case 2:
                                addDrink();
                                break;
                            // adds chips
                            case 3:
                                addChips();
                                break;
                            // adds dessert
                            case 4:
                                addDessert();
                                break;
                            // checks out and creates receipt
                            case 5:
                                checkout();
                                continue;
                            // cancels order and returns to home screen
                            case 0:
                                System.out.println("Canceling order... Returning back to home screen!");
                                orderRunning = false;
                                break;
                            // invalid choice
                            default:
                                System.out.println("Invalid option - Please choose one of the valid options!");
                                break;
                        }
                    }
                    // break for home screen
                    break;
                // exit the app
                case 0:
                    // calls exit message
                    exitMessage();
                    homeRunning = false;
                    break;
                // invalid choice
                default:
                    System.out.println("Invalid option - Please choose one of the valid options!");
                    break;
            }
        }
    }

    // -------------------------------------------------------------------------------------------------------

    // displays home screen
    public static int homeScreen() {

        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        ~ Home Screen ~       ┃
                ┃==============================┃
                ┃   How can I help you today?  ┃
                ┃          1) New Order        ┃
                ┃          0) Exit             ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);

        while (true) {
            System.out.print("Enter Option: ");
            if (userInput.hasNextInt()) {
                return userInput.nextInt();
            } else {
                System.out.println("Please enter numeric number between 0-1!");
                userInput.nextLine();
            }
        }
    }

    // displays order screen
    public static int orderScreen() {

        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃       ~ Order Screen ~       ┃
                ┃==============================┃
                ┃   What are you hungry for?   ┃
                ┃        1) Add Sandwich       ┃
                ┃        2) Add Drink          ┃
                ┃        3) Add Chips          ┃
                ┃        4) Add Dessert        ┃
                ┃        5) Checkout           ┃
                ┃        0) Cancel Order       ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);

        while (true) {
            System.out.print("Enter Option: ");
            if (userInput.hasNextInt()) {
                return userInput.nextInt();
            } else {
                System.out.println("Please enter numeric number between 0-5!");
                userInput.nextLine();
            }
        }
    }

    // -------------------------------------------------------------------------------------------------------

    public static void addSandwich() {

    }

    public static void addDrink() {

    }

    public static void addChips() {

    }

    public static void addDessert() {

    }

    public static void checkout() {

    }

    // -------------------------------------------------------------------------------------------------------

    // displays welcome message
    public static void welcomeMessage() throws InterruptedException {

        // loading bar
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(30);
            System.out.print("\rLoading... " + i + "%");
        }

        // greeting message
        System.out.print("\n");
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        ~ Welcome to ~        ┃
                ┃  ~ Jack of All Sandwiches ~  ┃
                ┃           🥪🃏🃏🥪           ┃
                ┃   ~ We Deal in Delicious! ~  ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);

        Thread.sleep(1500);
    }

    // displays exit message
    public static void exitMessage() throws InterruptedException {

        // exit message
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃   ~ You've folded for now ~  ┃
                ┃  ~ but the flavor's still ~  ┃
                ┃       ~ in the deck! ~       ┃
                ┃           🃏🥪🥪🃏           ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);

        // closing bar
        System.out.print("Shutting down");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(500);
            System.out.print(".");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------------------------------------
    // lists of all menu items and their prices

    public static void listOfBreadSizes() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃      ~ Sandwich Sizes ~      ┃
                ┃==============================┃
                ┃       4 Inch     $5.50       ┃
                ┃       8 Inch     $7.00       ┃
                ┃       12 Inch    $8.50       ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfBreads() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃          ~ Breads ~          ┃
                ┃==============================┃
                ┃    White        Rye          ┃
                ┃    Wheat        Wrap         ┃
                ┃    Multigrain   Italian Herb ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void toasted() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃    ~ Regular or Toasted ~    ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfMeats() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃           ~ Meats ~          ┃
                ┃==============================┃
                ┃ Size:      Price:     Extra: ┃
                ┃ 4"         $1.00      $0.50  ┃
                ┃ 8"         $2.00      $1.00  ┃
                ┃ 12"        $3.00      $1.50  ┃
                ┃==============================┃
                ┃     Steak       Ham          ┃
                ┃     Salami      Roast Beef   ┃
                ┃     Chicken     Bacon        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfCheeses() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃         ~ Cheeses ~          ┃
                ┃==============================┃
                ┃ Size:      Price:     Extra: ┃
                ┃ 4"         $0.75      $0.30  ┃
                ┃ 8"         $1.50      $0.60  ┃
                ┃ 12"        $2.25      $0.90  ┃
                ┃==============================┃
                ┃     American    Provolone    ┃
                ┃     Cheddar     Swiss        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfRegularToppings() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃     ~ Included Toppings ~    ┃
                ┃==============================┃
                ┃     Lettuce      Peppers     ┃
                ┃     Onions       Tomatoes    ┃
                ┃     Jalapeños    Cucumbers   ┃
                ┃     Pickles      Guacamole   ┃
                ┃     Mushrooms    Olives      ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfSauces() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃          ~ Sauces ~          ┃
                ┃==============================┃
                ┃    Mayo           Mustard    ┃
                ┃    Ketchup        Ranch      ┃
                ┃    Chipotle       BBQ        ┃
                ┃    Vinaigrette    Sriracha   ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfDrinks() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃          ~ Drinks ~          ┃
                ┃==============================┃
                ┃       Small      $2.00       ┃
                ┃       Medium     $2.50       ┃
                ┃       Large      $3.00       ┃
                ┃==============================┃
                ┃    Coke         Pepsi        ┃
                ┃    Sprite       Dr Pepper    ┃
                ┃    Diet Coke    Mt Dew       ┃
                ┃    Sweet Tea    Unsweet Tea  ┃
                ┃    Juice        Water        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfChips() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃          ~ Chips ~           ┃
                ┃==============================┃
                ┃            $1.50             ┃
                ┃==============================┃
                ┃    Lay's        SunChips     ┃
                ┃    Doritos      Ruffles      ┃
                ┃    Cheetos      Fritos       ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }

    public static void listOfDesserts() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃         ~ Desserts ~         ┃
                ┃==============================┃
                ┃            $1.50             ┃
                ┃==============================┃
                ┃       Vanilla Ice Cream      ┃
                ┃      Chocolate Ice Cream     ┃
                ┃    12" Cookie    Brownies    ┃
                ┃ Rice Krispies    Cheesecake  ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }
}
