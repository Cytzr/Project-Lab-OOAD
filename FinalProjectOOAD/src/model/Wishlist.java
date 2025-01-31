package model;

//model based on the wishlist table
public class Wishlist {
	private String wishlist_id;
	private String item_id;
	private String user_id;
	
	public Wishlist(String wishlist_id, String item_id, String user_id) {
		super();
		this.wishlist_id = wishlist_id;
		this.item_id = item_id;
		this.user_id = user_id;
	}
	/**
	 * @return the wishlist_id
	 */
	public String getWishlist_id() {
		return wishlist_id;
	}
	/**
	 * @param wishlist_id the wishlist_id to set
	 */
	public void setWishlist_id(String wishlist_id) {
		this.wishlist_id = wishlist_id;
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
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
