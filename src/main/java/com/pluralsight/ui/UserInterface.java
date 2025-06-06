package com.pluralsight.ui;

import com.pluralsight.datamodels.*;
import com.pluralsight.utility.ReceiptFileManager;

import java.util.*;

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
                            // adds a signature sandwich
                            case 2:
                                addSignatureSandwich();
                                break;
                            // adds a drink
                            case 3:
                                addDrink();
                                break;
                            // adds chips
                            case 4:
                                addChips();
                                break;
                            // adds dessert
                            case 5:
                                addDessert();
                                break;
                            // checks out and creates receipt
                            case 6:
                                // if true then breaks out of loop and clears order
                                if (checkout()) {
                                    order = null;
                                    orderRunning = false;
                                }
                                break;
                                // cancels and clears order and returns to home screen
                            case 0:
                                System.out.println("Canceling order... Returning back to home screen!");
                                orderRunning = false;
                                order = null;
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
                ┃        2) Add Signature      ┃
                ┃        3) Add Drink          ┃
                ┃        4) Add Chips          ┃
                ┃        5) Add Dessert        ┃
                ┃        6) Checkout           ┃
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
    // puts together the order as user chooses sandwiches and sides

    public static void addSandwich() {
        // eats leftover
        userInput.nextLine();

        // creates the new order is there already isn't one
        if (order == null) {
            order = new Order(getCustomerName(), isTakeOut());
        }

        boolean addingSandwich = true;

        while (addingSandwich) {
            // creates a new sandwich using user input
            Sandwich sandwich = new Sandwich(getBreadSize(), getBreadType(), isToasted());

            // loops through toppings and adds topping to sandwich
            for (Meat meat : meatToppings()) {
                sandwich.addTopping(meat);
            }
            for (Cheese cheese : cheeseToppings()) {
                sandwich.addTopping(cheese);
            }
            for (RegularTopping regularTopping : regularToppings()) {
                sandwich.addTopping(regularTopping);
            }
            for (RegularTopping sauce : sauceToppings()) {
                sandwich.addTopping(sauce);
            }

            // adds completed sandwich to the order
            order.addSandwich(sandwich);
            // displays sandwich created
            System.out.println(sandwich.getSandwich() + " " + sandwich.getFormattedToppings() + " was added to the order!");

            // prompt if user wants to add another sandwich
            System.out.print("\nAdd another sandwich? (Yes/No): ");
            String choice = userInput.nextLine().toLowerCase().trim();

            if (choice.equals("yes") || choice.equals("y")) {
                System.out.println("Creating new sandwich!");
            } else if (choice.equals("no") || choice.equals("n")){
                System.out.println("Returning back to Order Screen!");
                addingSandwich = false;
            }
        }
    }

    public static void addSignatureSandwich() {
        // eats leftover
        userInput.nextLine();

        // displays list of signature sandwiches
        listOfSignatureSandwiches();

        // creates a new order if user didn't add their own sandwich
        if (order == null) {
            order = new Order(getCustomerName(), isTakeOut());
        }

        // creates map for matching user input
        Map<String, String> signatureList = new HashMap<>();
        signatureList.put("blt", "BLT");
        signatureList.put("ph", "Philly Cheese Steak");
        signatureList.put("ch", "Chicken Bacon Ranch");
        signatureList.put("veg", "Veggie Deluxe");
        signatureList.put("it", "Italian Sub");
        signatureList.put("sm", "Smoky BBQ Club");
        signatureList.put("j", "Jack of All Sandwiches");

        String signatureName = "";
        while (true) {
            // prompts user to enter signature sandwich or cancel
            System.out.print("Enter signature sandwich (Enter \"Cancel\" to cancel signature sandwich order): ");
            String signatureChoice = userInput.nextLine().toLowerCase().trim();
            // returns back to screen
            if (signatureChoice.equals("cancel")) {
                return;
            }
            // matches user input to signature sandwich names
            signatureName = lookupItem(signatureChoice, signatureList);

            if (signatureName == null) {
                System.out.println("Please enter valid signature sandwich!");
            } else {
                break;
            }
        }

        // creates signature sandwich and adds it the either existing order or new order
        SignatureSandwich signatureSandwich = new SignatureSandwich(signatureName);
        order.addSandwich(signatureSandwich);
        // displays signature sandwich
        System.out.println("Signature Sandwich: " + signatureName + " was added!");
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
            System.out.print("Enter a drink (Enter \"Cancel\" to cancel drink order): ");
            String drinkChoice = userInput.nextLine().toLowerCase().trim();
            // returns back to screen
            if (drinkChoice.equals("cancel")) {
                return;
            }
            // finds matching drink with user input
            drinkName = lookupItem(drinkChoice, drinkList);
            // if its null it'll ask again
            if (drinkName == null) {
                System.out.println("Please enter valid drink!");
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
                System.out.println("Please enter valid drink size!");
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
            System.out.print("Enter chips (Enter \"Cancel\" to cancel chips order): ");
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
            System.out.print("Enter a dessert (Enter \"Cancel\" to cancel dessert order): ");
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

    public static boolean checkout() {
        // eats leftover
        userInput.nextLine();

        // prompts user with order details
        System.out.println("Here is your current order:\n");
        System.out.print(order.getOrder());

        // prompts user to confirm order or cancel order
        System.out.print("\n\nEnter \"Confirm\" to checkout or \"Cancel\" to cancel order: ");
        String option = userInput.nextLine().toLowerCase().trim();

        // if its confirm then writes a receipt
        if (option.equals("confirm")) {
            System.out.println("Order confirmed, thank you!");
            ReceiptFileManager fileManager = new ReceiptFileManager();
            fileManager.saveReceipt(order);
            //returns back to home screen instead of order screen
            return true;
            // if cancel it goes back to order screen to continue adding items
        } else if (option.equals("cancel")) {
            System.out.println("Returning back to Order Screen!");
        } else {
            System.out.println("Please either enter \"Confirm\" or \"Cancel\" order!");
        }
        // if cancels returns to order screen
        return false;
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
    // gets each piece and topping of a sandwich to build

    public static String getCustomerName() {
        // prompt for customer name
        String customerName = "";
        while (true) {
            System.out.print("Could I get a name for the order? ");
            customerName = userInput.nextLine().trim();
            if (customerName.isEmpty()) {
                System.out.println("Please enter valid name!");
            } else {
                return customerName;
            }
        }
    }

    public static boolean isTakeOut() {
        // prompt for here or to-go (take out)
        boolean isTakeOut = false;
        while (true) {
            System.out.print("Is this for Here or To-Go? ");
            String choice = userInput.nextLine().toLowerCase().trim();
            // if togo then true
            if (choice.contains("to")) {
                isTakeOut = true;
                return isTakeOut;
            } else if (choice.contains("here")) {
                // returns false
                return isTakeOut;
            } else {
                System.out.println("Please enter either here or to-go!");
            }
        }
    }

    public static int getBreadSize() {
        // displays list of bread sizes
        listOfBreadSizes();

        // prompts user for bread size
        int breadSize = 0;
        while (true) {
            System.out.print("Enter choice of bread size: ");
            // checks if bread size equals correct bread sizes (4, 8, 12)
            if (userInput.hasNextInt()) {
                breadSize = userInput.nextInt();
                userInput.nextLine();
                if (breadSize == 4 || breadSize == 8 || breadSize == 12) {
                    return breadSize;
                } else {
                    System.out.println("Please enter valid size of 4, 8, or 12 inches!");
                }
            } else {
                System.out.println("Please enter valid numeric number!");
                userInput.nextLine();
            }
        }
    }

    public static String getBreadType() {
        // displays list of breads
        listOfBreads();

        // prompts user for bread type
        String breadType = "";
        while (true) {
            System.out.print("Enter choice of bread: ");
            // takes user input and matches input to get the correct bread type
            if (userInput.hasNext()) {
                breadType = userInput.nextLine().trim().toLowerCase();
                if (breadType.startsWith("whi")) {
                    breadType = "White";
                    return breadType;
                } else if (breadType.startsWith("whe")) {
                    breadType = "Wheat";
                    return breadType;
                } else if (breadType.startsWith("r")) {
                    breadType = "Rye";
                    return breadType;
                } else if (breadType.startsWith("wr")) {
                    breadType = "Wrap";
                    return breadType;
                } else {
                    System.out.println("Please enter valid bread type!");
                }
            }
        }
    }

    public static boolean isToasted() {
        // displays toasted message
        toasted();

        // prompts for regular or toasted
        boolean isToasted = false;
        while (true) {
            System.out.print("Enter your choice: ");
            String choice = userInput.nextLine().toLowerCase().trim();
            // if toasted then true
            if (choice.contains("to")) {
                isToasted = true;
                return isToasted;
            } else if (choice.contains("reg")) {
                // returns false
                return isToasted;
            } else {
                System.out.println("Please enter either regular or toasted!");
            }
        }
    }

    public static List<Meat> meatToppings() {
        // displays list of meats
        listOfMeats();

        // creates map for matching user input to meat toppings
        Map<String, String> meatList = new HashMap<>();
        meatList.put("st", "Steak");
        meatList.put("h", "Ham");
        meatList.put("sa", "Salami");
        meatList.put("ro", "Roast Beef");
        meatList.put("ch", "Chicken");
        meatList.put("ba", "Bacon");

        // creates a list of meats
        List<Meat> meats = new ArrayList<>();

        // prompts user to choose meat types and extras until done is entered
        while (true) {
            System.out.print("Enter choice of meat (Enter \"done\" when done picking meats!): ");
            String meatChoice = userInput.nextLine().toLowerCase().trim();

            // stops loop if equals done
            if (meatChoice.equals("done")) break;

            // calls method for matching user input to meat list
            String meatType = lookupItem(meatChoice, meatList);

            // if the type is null, it restarts loop
            if (meatType == null) {
                System.out.println("Please enter valid meat type!");
                continue;
            }

            // displays meat added
            System.out.println("Meat Topping: " + meatType + " was added!");

            // prompts user for extra meats
            boolean isExtraMeat = false;
            int extraMeat = 0;
            while (true) {
                System.out.print("Would you like extra " + meatType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();
                // if yes then how many extras
                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraMeat = true;
                    System.out.print("Enter how much extra " + meatType + "? (1, 2, etc): ");
                    if (userInput.hasNextInt()) {
                        extraMeat = userInput.nextInt();
                        userInput.nextLine();
                        System.out.println(extraMeat + "x extra " + meatType + " added!");
                        break;
                    }
                    // if no then nothing is added and moves on
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra " + meatType + " added!");
                    isExtraMeat = false;
                    break;
                } else {
                    System.out.println("Please enter either (Yes/No)!");
                    userInput.nextLine();
                }
            }

            // adds meat to meats topping list
            meats.add(new Meat(meatType, isExtraMeat, extraMeat));
        }
        // returns list of meats
        return meats;
    }

    public static List<Cheese> cheeseToppings() {
        // displays list of cheeses
        listOfCheeses();

        // map to match
        Map<String, String> cheeseList = new HashMap<>();
        cheeseList.put("am", "American");
        cheeseList.put("pr", "Provolone");
        cheeseList.put("ch", "Cheddar");
        cheeseList.put("sw", "Swiss");

        // new list of cheeses
        List<Cheese> cheeses = new ArrayList<>();

        // prompts user to add cheeses and if any extras
        while (true) {
            System.out.print("Enter choice of cheese (Enter \"done\" when done picking cheese!): ");
            String cheeseChoice = userInput.nextLine().toLowerCase().trim();

            if (cheeseChoice.equals("done")) break;

            // matching user input with list
            String cheeseType = lookupItem(cheeseChoice, cheeseList);

            if (cheeseType == null) {
                System.out.println("Please enter valid cheese type!");
                continue;
            }

            // displays cheese added
            System.out.println("Cheese Topping: " + cheeseType + " was added!");

            // prompts user for extra cheese and how many or if no extra
            boolean isExtraCheese = false;
            int extraCheese = 0;
            while (true) {
                System.out.print("Would you like extra " + cheeseType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();

                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraCheese = true;
                    System.out.print("Enter how much extra " + cheeseType + "? (1, 2, etc): ");
                    if (userInput.hasNextInt()) {
                        extraCheese = userInput.nextInt();
                        userInput.nextLine();
                        System.out.println(extraCheese + "x extra " + cheeseType + " added!");
                        break;
                    }
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra " + cheeseType + " added!");
                    isExtraCheese = false;
                    break;
                } else {
                    System.out.println("Please enter either (Yes/No)!");
                    userInput.nextLine();
                }
            }

            // adds cheese toppings to sandwich
            cheeses.add(new Cheese(cheeseType, isExtraCheese, extraCheese));
        }
        return cheeses;
    }

    public static List<RegularTopping> regularToppings() {
        // displays list of regular toppings
        listOfRegularToppings();

        // map to match
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

        // new list of regular toppings
        List<RegularTopping> regularToppings = new ArrayList<>();

        // prompts user to add regular toppings and if any extras
        while (true) {
            System.out.print("Enter choice of regular topping (Enter \"done\" when done picking topping!): ");
            String toppingChoice = userInput.nextLine().toLowerCase().trim();

            if (toppingChoice.equals("done")) break;

            // looks through map and matches user input
            String toppingType = lookupItem(toppingChoice, toppingList);

            if (toppingType == null) {
                System.out.println("Please enter valid topping type!");
                continue;
            }

            // displays topping added
            System.out.println("Regular Topping: " + toppingType + " was added!");

            // prompts user for extra toppings
            boolean isExtraTopping = false;
            int extraTopping = 0;
            while (true) {
                System.out.print("Would you like extra " + toppingType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();

                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraTopping = true;
                    System.out.print("Enter how much extra " + toppingType + "? (1, 2, etc): ");
                    if (userInput.hasNextInt()) {
                        extraTopping = userInput.nextInt();
                        userInput.nextLine();
                        System.out.println(extraTopping + "x extra " + toppingType + " added!");
                        break;
                    }
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra " + toppingType + " added!");
                    isExtraTopping = false;
                    break;
                } else {
                    System.out.println("Please enter either (Yes/No)!");
                    userInput.nextLine();
                }
            }

            // adds regular toppings to sandwich
            regularToppings.add(new RegularTopping(toppingType, isExtraTopping, extraTopping));
        }
        return regularToppings;
    }

    public static List<RegularTopping> sauceToppings() {
        // displays list of sauces
        listOfSauces();
        // map to match
        Map<String, String> sauceList = new HashMap<>();
        sauceList.put("ma", "Mayo");
        sauceList.put("mu", "Mustard");
        sauceList.put("ke", "Ketchup");
        sauceList.put("ra", "Ranch");
        sauceList.put("ch", "Chipotle");
        sauceList.put("b", "BBQ");
        sauceList.put("v", "Vinaigrette");
        sauceList.put("s", "Sriracha");

        // list of sauces
        List<RegularTopping> sauces = new ArrayList<>();

        // prompts user to add sauces and if any extras
        while (true) {
            System.out.print("Enter choice of sauces (Enter \"done\" when done picking sauces!): ");
            String sauceChoice = userInput.nextLine().toLowerCase().trim();

            if (sauceChoice.equals("done")) break;

            String sauceType = lookupItem(sauceChoice, sauceList);

            if (sauceType == null) {
                System.out.println("Please enter valid sauce type!");
                continue;
            }

            // displays sauce added
            System.out.println("Sauce Topping: " + sauceType + " was added!");

            boolean isExtraSauce = false;
            int extraSauce = 0;
            while (true) {
                System.out.print("Would you like extra " + sauceType + "? (Yes/No): ");
                String choice = userInput.nextLine().toLowerCase().trim();

                if (choice.equals("yes") || choice.equals("y")) {
                    isExtraSauce = true;
                    System.out.print("Enter how much extra " + sauceType + "? (1, 2, etc): ");
                    if (userInput.hasNextInt()) {
                        extraSauce = userInput.nextInt();
                        userInput.nextLine();
                        System.out.println(extraSauce + "x extra " + sauceType + " added!");
                        break;
                    }
                } else if (choice.equals("no") || choice.equals("n")) {
                    System.out.println("No extra " + sauceType + " added!");
                    isExtraSauce = false;
                    break;
                } else {
                    System.out.println("Please enter either (Yes/No)!");
                    userInput.nextLine();
                }
            }

            // adds sauce toppings to sandwich
            sauces.add(new RegularTopping(sauceType, isExtraSauce, extraSauce));
        }
        return sauces;
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

    public static void listOfSignatureSandwiches() {
        System.out.print("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃   ~ Signature Sandwiches ~   ┃
                ┃==============================┃
                ┃ BLT                    $10.50┃
                ┃ Philly Cheese Steak    $10.50┃
                ┃ Chicken Bacon Ranch    $16.75┃
                ┃ Veggie Deluxe          $8.50 ┃
                ┃ Italian Sub            $16.75┃
                ┃ Smoky BBQ Club         $12.50┃
                ┃ Jack of All Sandwiches $22.00┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
                """);
    }
}
