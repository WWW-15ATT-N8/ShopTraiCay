package com.se.ecofruits.dao;

import java.util.List;

import com.se.ecofruits.entity.Image;

public interface ImageDAO {
	public List<Image> getImages();
	public List<Image> getImagesByProductID(int productID);
	public Image getImage(int imageID);
	public void saveImage(Image image);
	public void updateImage(Image image);
	public void deleteImage(Image image);
}
