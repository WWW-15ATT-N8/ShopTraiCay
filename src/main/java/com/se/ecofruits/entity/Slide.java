package com.se.ecofruits.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Slide {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int slideID;
	@Column(name = "image")
	private String image;
	public int getSlideId() {
		return slideID;
	}
	public void setSlideId(int slideID) {
		this.slideID = slideID;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Slide(int slideID, String image) {
		super();
		this.slideID = slideID;
		this.image = image;
	}
	
	
	
}
