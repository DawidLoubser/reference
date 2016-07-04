package za.co.solms.example;

/** A response to an enquiry for book availability, indicating the number
 * of books available, as well as the estimated delivery time.
 */
public class BookAvailabilityResponse 
{	
	public int getNumberInStock() {
		return numberInStock;
	}
	
	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}
	
	public double getEstimatedDeliveryTimeInDays() {
		return estimatedDeliveryTimeInDays;
	}
	
	public void setEstimatedDeliveryTimeInDays(double estimatedDeliveryTimeInDays) {
		this.estimatedDeliveryTimeInDays = estimatedDeliveryTimeInDays;
	}
	
	private int numberInStock;
	private double estimatedDeliveryTimeInDays;
}
