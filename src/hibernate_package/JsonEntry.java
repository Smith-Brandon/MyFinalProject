package hibernate_package;

// Used for JSON mapping
public class JsonEntry {
    private String name;
    private String symbol;
    private Float price;
    private Float avgY;
    private Float divY; 
    private int shares;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
    
    public Float getAvgY() {
        return avgY;
    }

    public void setAvgY(Float avgY) {
        this.avgY = avgY;
    }
    
    public Float getDivY() {
        return divY;
    }

    public void setDivY(Float divY) {
        this.divY = divY;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
    
    public String toString() {
		return "Stock Name: " + name + "\nStock Symbol: " + symbol + "\nPrice: " + price + "\nAverage Yield: " + avgY + "\nDividend Yield: " + divY + "\nShares Purchased: " + shares;
	}
}
