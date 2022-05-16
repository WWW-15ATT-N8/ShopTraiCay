package com.se.ecofruits.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Cart_FK implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "userID", nullable=false)
    private int userID;
	
	@Column(name = "productID", nullable=false)
	private int productID;

	public Cart_FK(int userID, int productID) {
		super();
		this.userID = userID;
		this.productID = productID;
	}

	public Cart_FK() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(productID, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart_FK other = (Cart_FK) obj;
		return productID == other.productID && userID == other.userID;
	}
	
	
}
