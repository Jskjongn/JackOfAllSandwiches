# 🥪 Jack of All Sandwiches
> _“We Deal in Delicious.”_  
A customizable sandwich shop terminal app built in Java, with support for signature sandwiches, receipt generation, and a fully interactive CLI menu system.

---

## 📦 Overview

**Jack of All Sandwiches** is a Java console application that allows users to:
- Build custom sandwiches with meats, cheeses, toppings, sauces
- Choose from prebuilt signature sandwiches
- Add sides like drinks, chips, and desserts
- View a running receipt and save the order to a file
- Fully navigate the ordering experience via CLI

---

## ✨ Core Features

- 🔧 **Custom Sandwich Builder**: Select bread type, size, toasted preference, and all toppings
- 🃏 **Signature Sandwiches**: Choose pre-configured recipes like BLT or Philly Cheesesteak
- 🥤 **Add Sides**: Drinks, chips, and desserts
- 🧾 **Dynamic Receipt**: Itemized output with tax and timestamp, saved to file
- 🔁 **Multiple Sandwiches per Order**: Loop-based flow lets users add 1+ sandwiches
- 💾 **Order Saving**: Receipts saved as `.txt` files with unique timestamps
- ✅ **JUnit 5 Tests**: Unit tests for core logic like pricing, toppings, and orders

---

## 🧠 OOP Design & Principles

### ✅ Single Responsibility Principle (SRP)
Each class handles one clear role (e.g., `Sandwich`, `Order`, `UserInterface`, `FileManager`).

### 🧱 Abstract Classes
- `Topping` is an abstract class, extended by:
    - `RegularTopping`
    - `PremiumTopping`, which is extended by `Meat` and `Cheese`

### 🧩 Interface
- `Priceable`: implemented by `Sandwich`, `Sides`, `Drink`, `Dessert` to support polymorphic pricing.

### 🧾 ReceiptFileManager
- Writes a `.txt` receipt to a `/resources/receipts/` directory using a timestamp-based filename.

---

## 🧪 Unit Testing (JUnit 5)

Test coverage includes:
- 🧮 Topping pricing logic
- 🥪 Sandwich topping counts and price
- 🧾 Order total calculations
- 📄 File output (manually tested)

## 📁 Project Structure
```bash
com.pluralsight
├── datamodels
│   ├── Sandwich.java
│   ├── Order.java
│   ├── Topping.java (abstract)
│   ├── PremiumTopping.java (abstract)
│   ├── Meat.java, Cheese.java
│   ├── RegularTopping.java
│   ├── Drink.java, Chips.java, Dessert.java
├── interfaces
│   └── Priceable.java
├── fileio
│   └── ReceiptFileManager.java
├── ui
│   └── UserInterface.java
├── SignatureSandwich.java
└── SandwichApp.java
```

## Project Diagram
```mermaid
classDiagram
    class Order {
        -String orderName
        -List~Sandwich~ sandwiches
        -List~Priceable~ sides
        +addSandwich(Sandwich)
        +addSide(Priceable)
        +getTotal()
        +getOrder()
    }

    class Sandwich {
        -int size
        -String breadType
        -boolean isToasted
        -List~Topping~ toppings
        +addTopping(Topping)
        +getPrice()
    }

    class Topping {
        <<abstract>>
        -String name
        -boolean isExtra
        +getPrice(int)
    }

    class PremiumTopping {
        <<abstract>>
        +getPrice(int)
    }

    class Meat
    class Cheese
    class RegularTopping

    class Priceable {
        <<interface>>
        +getPrice()
    }

    class Drink
    class Chips
    class Dessert

    class ReceiptFileManager {
        +saveReceipt(Order)
    }

    class UserInterface {
        +display()
        +addSandwich()
        +checkout()
    }

    Order --> Sandwich
    Order --> Priceable
    Sandwich --> Topping
    Topping <|-- PremiumTopping
    PremiumTopping <|-- Meat
    PremiumTopping <|-- Cheese
    Topping <|-- RegularTopping
    Sandwich --> Priceable
    Drink --> Priceable
    Chips --> Priceable
    Dessert --> Priceable
