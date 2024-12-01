package model;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Item {
	private String item_id;
	private String item_name;
	private String item_size;
	private String item_price;
	private String item_category;
	private String item_status;
	private String item_wishlist;
	private String item_offer_status;

	public Item(String item_id, String item_name, String item_size, String item_price, String item_category,
			String item_status, String item_wishlist, String item_offer_status) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_size = item_size;
		this.item_price = item_price;
		this.item_category = item_category;
		this.item_status = item_status;
		this.item_wishlist = item_wishlist;
		this.item_offer_status = item_offer_status;
	}

	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}

	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	/**
	 * @return the item_name
	 */
	public String getItem_name() {
		return item_name;
	}

	/**
	 * @param item_name the item_name to set
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	/**
	 * @return the item_size
	 */
	public String getItem_size() {
		return item_size;
	}

	/**
	 * @param item_size the item_size to set
	 */
	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}

	/**
	 * @return the item_price
	 */
	public String getItem_price() {
		return item_price;
	}

	/**
	 * @param item_price the item_price to set
	 */
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}

	/**
	 * @return the item_category
	 */
	public String getItem_category() {
		return item_category;
	}

	/**
	 * @param item_category the item_category to set
	 */
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}

	/**
	 * @return the item_status
	 */
	public String getItem_status() {
		return item_status;
	}

	/**
	 * @param item_status the item_status to set
	 */
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}

	/**
	 * @return the item_wishlist
	 */
	public String getItem_wishlist() {
		return item_wishlist;
	}

	/**
	 * @param item_wishlist the item_wishlist to set
	 */
	public void setItem_wishlist(String item_wishlist) {
		this.item_wishlist = item_wishlist;
	}

	/**
	 * @return the item_offer_status
	 */
	public String getItem_offer_status() {
		return item_offer_status;
	}

	/**
	 * @param item_offer_status the item_offer_status to set
	 */
	public void setItem_offer_status(String item_offer_status) {
		this.item_offer_status = item_offer_status;
	}
	
}
