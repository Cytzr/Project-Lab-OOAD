package model;

public class Offer {
	
	private String offer_id;
	private String user_id;
	private String item_id;
	private int offer_price;
	private String status;
	private int actualPrice;
	private String itemName;
	public Offer(String offer_id, String user_id, String item_id, int offer_price, String status, int actualPrice, String itemName) {
		super();
		this.offer_id = offer_id;
		this.user_id = user_id;
		this.item_id = item_id;
		this.offer_price = offer_price;
		this.status = status;
		this.actualPrice = actualPrice;
		this.itemName = itemName;
	}	
	/**
	 * @return the offer_id
	 */
	public String getOffer_id() {
		return offer_id;
	}
	/**
	 * @param offer_id the offer_id to set
	 */
	public void setOffer_id(String offer_id) {
		this.offer_id = offer_id;
	}
	public int getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(int actualPrice) {
		this.actualPrice = actualPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getStatus() {
		return status;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}
	public String isStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	/**
	 * @return the offer_price
	 */
	public int getOffer_price() {
		return offer_price;
	}
	/**
	 * @param offer_price the offer_price to set
	 */
	public void setOffer_price(int offer_price) {
		this.offer_price = offer_price;
	}
	
}
