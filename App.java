import java.util.Scanner;

public class MainApp {

    public static void printMenu() {
        System.out.println("1. Add new product");
        System.out.println("2. View all products");
        System.out.println("3. Update product");
        System.out.println("4. Delete product");
        System.out.println("5. Search product by ID");
        System.out.println("6. Search products by NAME (advanced feature)");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        boolean running = true;

        while (running) {
            printMenu();

            try {
                int choice = Integer.parseInt(input.nextLine());

                if (choice == 1) {
                    System.out.print("Enter Item ID: ");
                    String id = input.nextLine();

                    System.out.print("Enter Item Name: ");
                    String name = input.nextLine();

                    System.out.print("Enter Price: ");
                    double price = Double.parseDouble(input.nextLine());

                    System.out.print("Enter Quantity: ");
                    int quantity = Integer.parseInt(input.nextLine());

                    if (price < 0) {
                        throw new InvalidInputException("Price cannot be negative.");
                    }
                    if (quantity < 0) {
                        throw new InvalidInputException("Quantity cannot be negative.");
                    }

                    System.out.print("Is this a perishable item? (y/n): ");
                    String ans = input.nextLine();

                    Item it;
                    if (ans.equalsIgnoreCase("y")) {
                        System.out.print("Enter Expiry Date: ");
                        String expiry = input.nextLine();
                        it = new PerishableItem(id, name, price, quantity, expiry);
                    } else {
                        it = new Item(id, name, price, quantity);
                    }

                    manager.addItem(it);
                    System.out.println("Item added successfully.");

                } else if (choice == 2) {
                    manager.viewAllItems();

                } else if (choice == 3) {
                    System.out.print("Enter Item ID to update: ");
                    String id = input.nextLine();

                    System.out.print("Enter new Price: ");
                    double price = Double.parseDouble(input.nextLine());

                    System.out.print("Enter new Quantity: ");
                    int quantity = Integer.parseInt(input.nextLine());

                    if (price < 0 || quantity < 0) {
                        throw new InvalidInputException("Price and quantity must be non-negative.");
                    }

                    manager.updateItem(id, price, quantity);

                } else if (choice == 4) {
                    System.out.print("Enter Item ID to delete: ");
                    String id = input.nextLine();
                    manager.deleteItem(id);

                } else if (choice == 5) {
                    System.out.print("Enter Item ID to search: ");
                    String id = input.nextLine();
                    manager.searchItem(id);

                } else if (choice == 6) {
                    System.out.print("Enter part of product name to search: ");
                    String productTitle = input.nextLine();
                    manager.searchProductsByName(productTitle);

                } else if (choice == 7) {
                    System.out.println("Exiting program. Goodbye!");
                    running = false;

                } else {
                    System.out.println("Invalid choice. Please enter 1–7.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers only where required.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InventoryFullException e) {
                System.out.println(e.getMessage());
            }

            System.out.println();
        }

        input.close();
    }
}
