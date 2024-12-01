package hybrid_model;

public class OfferTableModel {
	//offer
	private String offer_id;
	private String offer_price;
	
	//user
	private String user_id;
	private String username;
	
	//item
	private String item_id;
	private String item_name;
	private String item_size;
	private String item_price;
	private String item_category;
	
	public OfferTableModel(String offer_id, String user_id, String username, String item_id, String item_name,
			String item_size, String item_price, String offer_price, String item_category) {
		super();
		this.offer_id = offer_id;
		this.user_id = user_id;
		this.username = username;
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_size = item_size;
		this.item_price = item_price;
		this.offer_price = offer_price;
		this.item_category = item_category;
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
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the offer_price
	 */
	public String getOffer_price() {
		return offer_price;
	}
	/**
	 * @param offer_price the offer_price to set
	 */
	public void setOffer_price(String offer_price) {
		this.offer_price = offer_price;
	}
	
}
