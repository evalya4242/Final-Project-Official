public class InventoryManager {

    protected Item[] items = new Item[50];
    protected int count = 0;

    public void addItem(Item it) throws InventoryFullException {
        if (count < items.length) {
            items[count++] = it;
        } else {
            throw new InventoryFullException("Inventory is MAXED OUT");
        }
    }

    public void viewAllItems() {
        if (count == 0) {
            System.out.println("No items in inventory.");
        } else {
            for (int i = 0; i < count; i++) {
                items[i].displayItemInfo();
                System.out.println("================================");
            }
        }
    }

    public void searchItem(String id) throws ItemNotFoundException {
        Item found = findItemById(id);
        System.out.println("Item found:");
        found.displayItemInfo();
    }

    public void updateItem(String id, double newPrice, int newQuantity)
            throws ItemNotFoundException {

        Item found = findItemById(id);
        found.setPrice(newPrice);
        found.setQuantity(newQuantity);
        System.out.println("Item updated successfully.");
    }

    public void deleteItem(String id) throws ItemNotFoundException {
        int index = -1;

        for (int i = 0; i < count; i++) {
            if (items[i].getItemId().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            throw new ItemNotFoundException("Item with ID " + id + " not found.");
        }

        for (int i = index; i < count - 1; i++) {
            items[i] = items[i + 1];
        }

        items[count - 1] = null;
        count--;

        System.out.println("Item deleted successfully.");
    }

    protected Item findItemById(String id) throws ItemNotFoundException {
        for (int i = 0; i < count; i++) {
            if (items[i].getItemId().equals(id)) {
                return items[i];
            }
        }
        throw new ItemNotFoundException("Item with ID " + id + " not found.");
    }

    public void searchProductsByName(String productTitle) {
        String search = productTitle.toLowerCase();
        boolean exists = false;

        for (int i = 0; i < count; i++) {
            String productName = items[i].getItemName().toLowerCase();

            if (productName.contains(search)) {
                if (!exists) {
                    System.out.println("Matching products:");
                }
                System.out.println("---------------------");
                items[i].displayItemInfo();
                exists = true;
            }
        }

        if (!exists) {
            System.out.println("No products found with name containing: " + productTitle);
        } else {
            System.out.println("---------------------");
        }
    }
}
