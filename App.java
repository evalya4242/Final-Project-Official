import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();
 

        while (true) {
            System.out.println("1. Add new product");
            System.out.println("2. View all products");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Search product by ID");
            System.out.println("6. Search products by NAME (advanced feature)"); 
            System.out.println("7. Exit");  
            
            while (true) {
                printMenu();
            
                int choice = 0;
            
                try {
                    System.out.print("Enter your choice: ");
                    choice = input.nextInt();
                    input.nextLine(); 
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a NUMBER.");
                    input.nextLine(); 
                    continue;         
                }            

                if (choice == 1) {
                    System.out.print("Enter Item ID: ");
                    String id = input.nextLine();

                    System.out.print("Enter Item Name: ");
                    String name = input.nextLine();

                    double price = 0;
                    try {
                        System.out.print("Enter Price: ");
                        price = input.nextDouble();
                    } catch (Exception e) {
                        System.out.println("Invalid price. Please enter a NUMBER.");
                        input.nextLine();
                        return;   
                    }
                    
                    int quantity = 0;
                    try {
                        System.out.print("Enter Quantity: ");
                        quantity = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid quantity. Please enter a NUMBER.");
                        input.nextLine();
                        return;
                    }

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
                    else if (choice == 6) {  
                        System.out.print("Enter part of product name to search: ");  
                        String namePart = input.nextLine();  
                        manager.searchProductsByName(namePart);  
                    }
                    else if (choice == 7) {  
                        System.out.println("Exiting program. Goodbye!");  
                        break;
                } else if (choice == 6) {
                    System.out.println("Exiting Inventory Management System...");
                    break;

                } else {
                    System.out.println("Invalid choice. Please enter 1–6.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter numbers only.");
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
