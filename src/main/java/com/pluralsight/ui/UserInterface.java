package com.pluralsight.ui;

import com.pluralsight.datamodels.*;
import com.pluralsight.utility.ReceiptFileManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {

    // creates scanner for user input
    static Scanner userInput = new Scanner(System.in);

    // declare Order class
    private static Order order;

    // private constructor
    private UserInterface() {
    }

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
                                break;
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

        // eats leftover
        userInput.nextLine();

        // prompt for customer name
        String customerName = "";
        while (true) {
            System.out.print("Could I have a name for the order? ");
            customerName = userInput.nextLine().trim();
            if (customerName.isEmpty()) {
                System.out.println("Please enter a name!");
            } else {
                break;
            }
        }

        // prompt for here or to-go (take out)
        boolean isTakeOut = false;
        while (true) {
            System.out.print("Is this for Here or To-Go? ");
            String choice = userInput.nextLine().toLowerCase().trim();
            if (choice.contains("to")) {
                isTakeOut = true;
                break;
            } else if (choice.contains("here")) {
                isTakeOut = false;
                break;
            } else {
                System.out.println("Please enter either here or to-go!");
            }
        }

        // creates the new order
        order = new Order(customerName, isTakeOut);

        // lists bread sizes and prompts for bread size
        listOfBreadSizes();
        int breadSize = 0;
        while (true) {
            System.out.print("Enter choice of bread size: ");
            // input validation and checks if size equals correct bread sizes
            if (userInput.hasNextInt()) {
                breadSize = userInput.nextInt();
                userInput.nextLine();
                if (breadSize == 4 || breadSize == 8 || breadSize == 12) {
                    break;
                } else {
                    System.out.println("Please enter either 4, 8, or 12 inches!");
                }
            } else {
                System.out.println("Please enter a number!");
                userInput.nextLine();
            }
        }

        // list of breads and prompts for bread type
        listOfBreads();
        String breadType = "";
        while (true) {
            System.out.print("Enter choice of bread: ");
            // takes user input and matches input to get the correct bread type
            if (userInput.hasNext()) {
                breadType = userInput.nextLine().trim().toLowerCase();
                if (breadType.startsWith("whi")) {
                    breadType = "White";
                    break;
                } else if (breadType.startsWith("whe")) {
                    breadType = "Wheat";
                    break;
                } else if (breadType.startsWith("r")) {
                    breadType = "Rye";
                    break;
                } else if (breadType.startsWith("wr")) {
                    breadType = "Wrap";
                    break;
                } else {
                    System.out.println("Please enter a valid bread type!");
                }
            }
        }

        // prompts for regular or toasted
        toasted();
        boolean isToasted = false;
        while (true) {
            System.out.print("Enter your choice: ");
            String choice = userInput.nextLine().toLowerCase().trim();
            if (choice.contains("toast")) {
                isToasted = true;
                break;
            } else if (choice.contains("reg")) {
                isToasted = false;
                break;
            } else {
                System.out.println("Please enter either regular or toasted!");
            }
        }

        // creates a new sandwich using user input
        Sandwich sandwich = new Sandwich(breadSize, breadType, isToasted);

        // lists meats method and map listing meats
        listOfMeats();
        Map<String, String> meatList = new HashMap<>();
        meatList.put("st", "Steak");
        meatList.put("h", "Ham");
        meatList.put("sa", "Salami");
        meatList.put("ro", "Roast Beef");
        meatList.put("ch", "Chicken");
        meatList.put("ba", "Bacon");

        // prompts user to choose meat types and extras until done is entered
        while (true) {
            System.out.print("Enter choice of meat (Type \"done\" after picking meats!): ");
            String meatChoice = userInput.nextLine().toLowerCase().trim();

            // stops loop if done
            if (meatChoice.equals("done")) break;

            // calls method for matching user input to meat list
            String meatType = lookupItem(meatChoice, meatList);

            // if the type is null, it restarts loop
            if (meatType == null) {
                System.out.println("Please enter a meat type!");
                continue;
            }

            // prompts user for extra meats
            boolean isExtraMeat = false;
            int extraMeat = 0;
            while (true) {
                System.out.print("Would you like extra " + meatType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();
                // if yes then how many extras
                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraMeat = true;
                    System.out.print("Enter how many extra (1, 2 , 3, etc): ");
                    if (userInput.hasNextInt()) {
                        extraMeat = userInput.nextInt();
                        userInput.nextLine();
                        break;
                    }
                    // if no then nothing is added and moves on
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra meat added!");
                    isExtraMeat = false;
                    break;
                } else {
                    System.out.println("Please enter Yes or No!");
                }
            }

            // adds meat toppings to sandwich
            sandwich.addTopping(new Meat(meatType, isExtraMeat, extraMeat));
        }

        // lists cheeses
        listOfCheeses();
        Map<String, String> cheeseList = new HashMap<>();
        cheeseList.put("am", "American");
        cheeseList.put("pr", "Provolone");
        cheeseList.put("ch", "Cheddar");
        cheeseList.put("sw", "Swiss");

        // prompts user to add cheeses and if any extras
        while (true) {
            System.out.print("Enter choice of cheese (Type \"done\" after picking cheese!): ");
            String cheeseChoice = userInput.nextLine().toLowerCase().trim();

            if (cheeseChoice.equals("done")) break;

            // matching user input with list
            String cheeseType = lookupItem(cheeseChoice, cheeseList);

            if (cheeseType == null) {
                System.out.println("Please enter a cheese type!");
                continue;
            }

            // prompts user for extra cheese and how many or if no extra
            boolean isExtraCheese = false;
            int extraCheese = 0;
            while (true) {
                System.out.print("Would you like extra " + cheeseType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();

                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraCheese = true;
                    System.out.print("Enter how many extra (1, 2 , 3, etc): ");
                    if (userInput.hasNextInt()) {
                        extraCheese = userInput.nextInt();
                        userInput.nextLine();
                        break;
                    }
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra cheese added!");
                    isExtraCheese = false;
                    break;
                } else {
                    System.out.println("Please enter Yes or No!");
                }
            }

            // adds cheese toppings to sandwich
            sandwich.addTopping(new Cheese(cheeseType, isExtraCheese, extraCheese));
        }

        // lists regular toppings
        listOfRegularToppings();
        Map<String, String> toppingList = new HashMap<>();
        toppingList.put("le", "Lettuce");
        toppingList.put("pe", "Peppers");
        toppingList.put("on", "Onions");
        toppingList.put("to", "Tomatoes");
        toppingList.put("ja", "Jalapeños");
        toppingList.put("cu", "Cucumbers");
        toppingList.put("pi", "Pickles");
        toppingList.put("gu", "Guacamole");
        toppingList.put("mu", "Mushrooms");
        toppingList.put("ol", "Olives");

        // prompts user to add regular toppings and if any extras
        while (true) {
            System.out.print("Enter choice of regular toppings (Type \"done\" after picking topping!): ");
            String toppingChoice = userInput.nextLine().toLowerCase().trim();

            if (toppingChoice.equals("done")) break;

            String toppingType = lookupItem(toppingChoice, toppingList);

            if (toppingType == null) {
                System.out.println("Please enter a topping type!");
                continue;
            }

            boolean isExtraTopping = false;
            int extraTopping = 0;
            while (true) {
                System.out.print("Would you like extra " + toppingType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();

                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraTopping = true;
                    System.out.print("Enter how many extra (1, 2 , 3, etc): ");
                    if (userInput.hasNextInt()) {
                        extraTopping = userInput.nextInt();
                        userInput.nextLine();
                        break;
                    }
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra toppings added!");
                    isExtraTopping = false;
                    break;
                } else {
                    System.out.println("Please enter Yes or No!");
                }
            }

            // adds regular toppings to sandwich
            sandwich.addTopping(new RegularTopping(toppingType, isExtraTopping, extraTopping));
        }

        // lists sauces
        listOfSauces();
        Map<String, String> sauceList = new HashMap<>();
        sauceList.put("ma", "Mayo");
        sauceList.put("mu", "Mustard");
        sauceList.put("ke", "Ketchup");
        sauceList.put("ra", "Ranch");
        sauceList.put("ch", "Chipotle");
        sauceList.put("b", "BBQ");
        sauceList.put("v", "Vinaigrette");
        sauceList.put("s", "Sriracha");

        // prompts user to add sauces and if any extras
        while (true) {
            System.out.print("Enter choice of sauces (Type \"done\" after picking sauces!): ");
            String sauceChoice = userInput.nextLine().toLowerCase().trim();

            if (sauceChoice.equals("done")) break;

            String sauceType = lookupItem(sauceChoice, sauceList);

            if (sauceType == null) {
                System.out.println("Please enter a sauce type!");
                continue;
            }

            boolean isExtraSauce = false;
            int extraSauce = 0;
            while (true) {
                System.out.print("Would you like extra " + sauceType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();

                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraSauce = true;
                    System.out.print("Enter how many extra (1, 2 , 3, etc): ");
                    if (userInput.hasNextInt()) {
                        extraSauce = userInput.nextInt();
                        userInput.nextLine();
                        break;
                    }
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra sauces added!");
                    isExtraSauce = false;
                    break;
                } else {
                    System.out.println("Please enter Yes or No!");
                }
            }

            // adds sauce toppings to sandwich
            sandwich.addTopping(new RegularTopping(sauceType, isExtraSauce, extraSauce));
        }

        // adds completed sandwich to the order
        order.addSandwich(sandwich);
        // displays sandwich created
        System.out.println(sandwich.getSandwich() + " " + sandwich.getToppings() + " was added to the order!");
    }

    public static void addDrink() {
        // eats leftover
        userInput.nextLine();

        // displays list of drinks and creates map list for user input
        listOfDrinks();
        Map<String, String> drinkList = new HashMap<>();
        drinkList.put("co", "Coke");
        drinkList.put("p", "Pepsi");
        drinkList.put("sp", "Sprite");
        drinkList.put("dr", "Dr Pepper");
        drinkList.put("di", "Diet Coke");
        drinkList.put("m", "Mt Dew");
        drinkList.put("sw", "Sweet Tea");
        drinkList.put("un", "Unsweet Tea");
        drinkList.put("j", "Juice");
        drinkList.put("w", "Water");

        String drinkName = "";
        while (true) {
            // prompts user to enter a drink or cancel to not add a drink
            System.out.print("Enter a drink (Type \"Cancel\" to cancel drink order): ");
            String drinkChoice = userInput.nextLine().toLowerCase().trim();
            // returns back to screen
            if (drinkChoice.equals("cancel")) {
                return;
            }
            // finds matching drink with user input
            drinkName = lookupItem(drinkChoice, drinkList);
            // if its null it'll ask again
            if (drinkName == null) {
                System.out.println("Please enter a valid drink!");
            } else {
                break;
            }
        }

        String drinkSize = "";
        while (true) {
            // prompts user to enter a drink size
            System.out.print("Enter a drink size: ");
            drinkSize = userInput.nextLine().toLowerCase().trim();
            // gets user input to match to drink size
            if (drinkSize.startsWith("s")) {
                drinkSize = "Small";
                break;
            } else if (drinkSize.startsWith("m")) {
                drinkSize = "Medium";
                break;
            } else if (drinkSize.startsWith("l")) {
                drinkSize = "Large";
                break;
            } else {
                System.out.print("Please enter a drink size!");
            }
        }
        // creates a new drink and adds it to the order
        Drink drink = new Drink(drinkName, drinkSize);
        order.addSide(drink);
        // displays drink
        System.out.println(drink + " was added to the order!");
    }

    public static void addChips() {
        // eats leftover
        userInput.nextLine();

        // displays list of chips and creates map list for user input
        listOfChips();
        Map<String, String> chipList = new HashMap<>();
        chipList.put("l", "Lay's");
        chipList.put("s", "SunChips");
        chipList.put("d", "Doritos");
        chipList.put("r", "Ruffles");
        chipList.put("c", "Cheetos");
        chipList.put("f", "Fritos");

        String chipName = "";
        while (true) {
            // prompts user to enter chips or cancel to not add chips
            System.out.print("Enter chips (Type \"Cancel\" to cancel chips order): ");
            String chipChoice = userInput.nextLine().toLowerCase().trim();
            // returns back to screen
            if (chipChoice.equals("cancel")) {
                return;
            }
            // finds matching chips with user input
            chipName = lookupItem(chipChoice, chipList);
            // if its null it'll ask again
            if (chipName == null) {
                System.out.println("Please enter valid chips!");
            } else {
                break;
            }
        }
        // creates new chips and adds it to the order and then displays
        Chips chips = new Chips(chipName);
        order.addSide(chips);
        System.out.println(chips + " was added to order!");
    }

    public static void addDessert() {
        // eats leftover
        userInput.nextLine();

        // displays list of dessert and creates map list for user input
        listOfDesserts();
        Map<String, String> dessertList = new HashMap<>();
        dessertList.put("van", "Vanilla Ice Cream");
        dessertList.put("cho", "Chocolate Ice Cream");
        dessertList.put("12", "12\" Cookie");
        dessertList.put("b", "Brownies");
        dessertList.put("r", "Rice Krispies");
        dessertList.put("che", "Cheesecake");

        String dessertName = "";
        while (true) {
            // prompts user to enter dessert or cancel to not add dessert
            System.out.print("Enter a dessert (Type \"Cancel\" to cancel dessert order): ");
            String dessertChoice = userInput.nextLine().toLowerCase().trim();
            // returns back to screen
            if (dessertChoice.equals("cancel")) {
                return;
            }
            // finds matching dessert with user input
            dessertName = lookupItem(dessertChoice, dessertList);
            // if its null it'll ask again
            if (dessertName == null) {
                System.out.println("Please enter valid dessert!");
            } else {
                break;
            }
        }
        // creates new dessert and adds it to the order and then displays
        Dessert dessert = new Dessert(dessertName);
        order.addSide(dessert);
        System.out.println(dessert + " was added to order!");
    }

    public static void checkout() {
        // eats leftover
        userInput.nextLine();

        System.out.println("Here is your current order:");
        System.out.print(order.getOrder());

        System.out.print("\n\nEnter \"Confirm\" to checkout or \"Cancel\" to cancel order: ");
        String option = userInput.nextLine().toLowerCase().trim();

        if (option.equals("confirm")) {
            System.out.println("Order confirmed, thank you!");
            ReceiptFileManager fileManager = new ReceiptFileManager();
            fileManager.saveReceipt(order);
        } else if (option.equals("cancel")) {
            System.out.println("Order canceled, come back again!");
            order = null;
        } else {
            System.out.println("Please either \"Confirm\" or \"Cancel\" order!");
        }
    }

    // -------------------------------------------------------------------------------------------------------

    // displays welcome message
    public static void welcomeMessage() throws InterruptedException {

        // loading bar
//        for (int i = 0; i <= 100; i++) {
//            Thread.sleep(30);
//            System.out.print("\rLoading... " + i + "%");
//        }

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

        //Thread.sleep(1500);
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

    // helper method to compare user input to a list of toppings and match them
    public static String lookupItem(String choice, Map<String, String> list) {
        for (Map.Entry<String, String> entry : list.entrySet()) {
            if (choice.startsWith(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    // -------------------------------------------------------------------------------------------------------
    // lists of all menu items and their prices

    public static void listOfBreadSizes() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        ~ Bread Sizes ~       ┃
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
                ┃      White        Rye        ┃
                ┃      Wheat        Wrap       ┃
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
