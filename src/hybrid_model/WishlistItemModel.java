package hybrid_model;

//hybrid of wishlist and item table which will be used in item display
public class WishlistItemModel {
	private String wishlist_id;
	private String item_id;
	private String user_id;
	private String item_name;
	private String item_category;
	private String item_size;
	private String item_price;
	
	
	public WishlistItemModel(String wishlist_id, String item_id, String user_id, String name, String category, String size, String price) {
		super();
		this.wishlist_id = wishlist_id;
		this.item_id = item_id;
		this.user_id = user_id;
		this.item_name = name;
		this.item_category = category;
		this.item_size = size;
		this.item_price = price;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_size() {
		return item_size;
	}
	public void setItem_size(String item_size) {
		this.item_size = item_size;
	}
	public String getItem_price() {
		return item_price;
	}
	public void setItem_price(String item_price) {
		this.item_price = item_price;
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
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	public String getWishlist_id() {
		return wishlist_id;
	}
	public void setWishlist_id(String wishlist_id) {
		this.wishlist_id = wishlist_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
