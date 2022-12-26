package service.core;

/**
 * Class to define the state of available Stock response. 
 */
public class AvailableStock {
	public AvailableStock(String medicineName, int stockAvailable, boolean isNeededStockAvailable, 
						Double price) {
		this.medicineName = medicineName;
		this.stockAvailable = stockAvailable;
		this.isNeededStockAvailable = isNeededStockAvailable;
		this.price = price;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public int getStockAvailable() {
		return stockAvailable;
	}

	public AvailableStock(){}

	String medicineName;
	int stockAvailable;	
	boolean isNeededStockAvailable;
	Double price;
	
	@Override
	public String toString() {
		return "AvailableStock [medicineName=" + medicineName + ", stockAvailable=" + stockAvailable
				+ ", isNeededStockAvailable=" + isNeededStockAvailable + ", price=" + price + "]";
	}
}
