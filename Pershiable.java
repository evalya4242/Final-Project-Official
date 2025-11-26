public class PerishableItem extends Item {

    protected String expiryDate;

    public PerishableItem(String itemId, String itemName, double price, int quantity, String expiryDate) {
        super(itemId, itemName, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public void displayItemInfo() {
        super.displayItemInfo();
        System.out.println("Expiry Date: " + expiryDate);
    }
}
