package com.pluralsight.datamodels;

public class SignatureSandwich extends Sandwich {

    // property of a signature sandwich
    private String name;

    // constructor
    public SignatureSandwich(String name) {
        // default
        super(4, "White", false);
        this.name = name.toLowerCase().trim();

        // signature sandwich templates
        switch (this.name) {
            case "blt":
                setSize(8);
                setBreadType("White");
                setToasted(true);
                setName("BLT");
                this.addTopping(new Meat("Bacon", false, 0));
                this.addTopping(new Cheese("Cheddar", false, 0));
                this.addTopping(new RegularTopping("Lettuce", false, 0));
                this.addTopping(new RegularTopping("Tomato", false, 0));
                this.addTopping(new RegularTopping("Ranch", false, 0));
                break;
            case "philly cheese steak":
                setSize(8);
                setBreadType("White");
                setToasted(true);
                setName("Philly Cheese Steak");
                this.addTopping(new Meat("Steak", false, 0));
                this.addTopping(new Cheese("American", false, 0));
                this.addTopping(new RegularTopping("Peppers", false, 0));
                this.addTopping(new RegularTopping("Mayo", false, 0));
                break;
            case "chicken bacon ranch":
                setSize(12);
                setBreadType("Wheat");
                setToasted(true);
                setName("Chicken Bacon Ranch");
                this.addTopping(new Meat("Chicken", false, 0));
                this.addTopping(new Meat("Bacon", false, 0));
                this.addTopping(new Cheese("Swiss", false, 0));
                this.addTopping(new RegularTopping("Lettuce", false, 0));
                this.addTopping(new RegularTopping("Tomato", false, 0));
                this.addTopping(new RegularTopping("Onions", false, 0));
                this.addTopping(new RegularTopping("Ranch", false, 0));
                break;
            case "veggie deluxe":
                setSize(8);
                setBreadType("Wrap");
                setToasted(false);
                setName("Veggie Deluxe");
                this.addTopping(new Cheese("Provolone", false, 0));
                this.addTopping(new RegularTopping("Lettuce", false, 0));
                this.addTopping(new RegularTopping("Peppers", false, 0));
                this.addTopping(new RegularTopping("Cucumbers", false, 0));
                this.addTopping(new RegularTopping("Pickles", false, 0));
                this.addTopping(new RegularTopping("Olives", false, 0));
                this.addTopping(new RegularTopping("Mushrooms", false, 0));
                this.addTopping(new RegularTopping("Vinaigrette", false, 0));
                break;
            case "italian sub":
                setSize(12);
                setBreadType("White");
                setToasted(true);
                setName("Italian Sub");
                this.addTopping(new Meat("Salami", false, 0));
                this.addTopping(new Meat("Ham", false, 0));
                this.addTopping(new Cheese("Cheddar", false, 0));
                this.addTopping(new RegularTopping("Lettuce", false, 0));
                this.addTopping(new RegularTopping("Peppers", false, 0));
                this.addTopping(new RegularTopping("Tomato", false, 0));
                this.addTopping(new RegularTopping("Onions", false, 0));
                this.addTopping(new RegularTopping("Mustard", false, 0));
                this.addTopping(new RegularTopping("Mayo", false, 0));
                break;
            case "smoky bbq club":
                setSize(8);
                setBreadType("Rye");
                setToasted(true);
                setName("Smoky BBQ Club");
                this.addTopping(new Meat("Chicken", false, 0));
                this.addTopping(new Meat("Bacon", false, 0));
                this.addTopping(new Cheese("Cheddar", false, 0));
                this.addTopping(new RegularTopping("Pickles", false, 0));
                this.addTopping(new RegularTopping("Onions", false, 0));
                this.addTopping(new RegularTopping("BBQ", false, 0));
                break;
            case "jack of all sandwiches":
                setSize(12);
                setBreadType("White");
                setToasted(true);
                setName("Jack of All Sandwiches");
                this.addTopping(new Meat("Ham", false, 0));
                this.addTopping(new Meat("Chicken", false, 0));
                this.addTopping(new Meat("Salami", false, 0));
                this.addTopping(new Cheese("Provolone", false, 0));
                this.addTopping(new Cheese("Cheddar", false, 0));
                this.addTopping(new RegularTopping("Lettuce", false, 0));
                this.addTopping(new RegularTopping("Pickles", false, 0));
                this.addTopping(new RegularTopping("Peppers", false, 0));
                this.addTopping(new RegularTopping("Jalapeños", false, 0));
                this.addTopping(new RegularTopping("Onions", false, 0));
                this.addTopping(new RegularTopping("Chipotle", false, 0));
                this.addTopping(new RegularTopping("Ranch", false, 0));
                break;
        }
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // display signature sandwich
    @Override
    public String getSandwich() {
        // creates string builder
        StringBuilder sandwichBuilder = new StringBuilder();
        // appends signature sandwich name
        sandwichBuilder.append(this.name).append(":\n");
        // appends bread size, inch, and bread type (4 inch wheat)
        sandwichBuilder.append(" ").append(this.getSize()).append(" inch ").append(this.getBreadType());
        // if the sandwich is toasted then appends toasted, if not toasted doesn't append it
        if (this.isToasted()) sandwichBuilder.append(" Toasted");
        // returns the full string to display
        return sandwichBuilder.toString();
    }
}
