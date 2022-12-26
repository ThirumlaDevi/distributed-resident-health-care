package service.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Class to store the order stock returned by the quotation services
 * The expected printed form of the response when provided to the customer can look something like the following
 *      | medicine name | is needed stock available (yes/no)| stock available |
 *      -----------------------------------------------------------------------
 *      |{medicine name}| yes / no                          | {stock count}   |
 *      |{medicine name}| yes / no                          | {stock count}   |
 *      |{medicine name}| yes / no                          | {stock count}   |
 * 
 * Can the medicine be delivered to home: {can_home_deliver}
 * Time of availability: {time_of_availability}
 * Sum total price: {total_price}
 * Comments: {comment}
 */
@Builder
@Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Data
public class StockResponse implements Serializable {

	private static final long serialVersionUID = 1L; 
	List<AvailableStock> medicines = new ArrayList<AvailableStock>(); //  list of meds

	boolean can_home_deliver;
	String time_of_availability;
	Double total_price = 0.0;	
    String comment;

	public List<AvailableStock> getMedicines() {
		return medicines;
	}

	public boolean isCan_home_deliver() {
		return can_home_deliver;
	}

	public String getTime_of_availability() {
		return time_of_availability;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public StockResponse(List<AvailableStock> medicines, boolean can_home_deliver, String time_of_availability, 
						 Double total_price, String comment) {
		this.medicines = medicines;
		this.can_home_deliver = can_home_deliver;
		this.time_of_availability = time_of_availability;
		this.total_price = 0.0;
		this.comment = comment;
	}
	
	public StockResponse() {}

	// public void setMedicines(List<AvailableStock> medicines) {
	// 	this.medicines = medicines;
	// }

	public void addMedicines(AvailableStock medicine) {
		this.medicines.add(medicine);
	}

	public void setCan_home_deliver(boolean can_home_deliver) {
		this.can_home_deliver = can_home_deliver;
	}

	public void setTime_of_availability(String time_of_availability) {
		this.time_of_availability = time_of_availability;
	}

	@Override
	public String toString() {
		return "StockResponse [medicines=" + medicines + ", can_home_deliver=" + can_home_deliver
				+ ", time_of_availability=" + time_of_availability + ", total_price=" + total_price + ", comment="
				+ comment + "]";
	}
}
