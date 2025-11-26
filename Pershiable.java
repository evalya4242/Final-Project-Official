public class Perishableitem extends Item
{
    protected String expiryDate;

    public String getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    public Perishableitem(String itemId, String itemmName, double price, int quantity, String expiryDate)
    {
        super(itemId, itemName, price, quantity);
        this.expiryDate = expiryDate;
    }

    @Override
    public void displayItemInfo()
    {
        super.displayItemInfo();  
        System.out.println("Expiry Date: " + expiryDate);
    }
}
