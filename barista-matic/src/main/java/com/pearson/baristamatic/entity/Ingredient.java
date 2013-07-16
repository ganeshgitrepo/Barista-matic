package com.pearson.baristamatic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_INGREDIENT")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;

	private long ingredientId;					// Unique ingredient ID
	private String ingredientName;				// Name of ingredient
	private double ingredientCost;				// Cost to stock ingredient
	private int inventory;						// Current inventory
	private int purchases;						// Number of restocked ingredients
	
	// No-argument constructor supplied for Hibernate
	protected Ingredient() { }
	
	public Ingredient(long ingredientId, String ingredientName,
			double ingredientCost, int inventory, int purchases) {
		this.ingredientId = ingredientId;
		this.ingredientName = ingredientName;
		this.ingredientCost = ingredientCost;
		this.inventory = inventory;
		this.purchases = purchases;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="INGREDIENT_ID")
	public long getIngredientId() {
		return ingredientId;
	}
	
	public void setIngredientId(long ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	@Column(name="INGREDIENT_NAME", nullable=false)
	public String getIngredientName() {
		return ingredientName;
	}
	
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	@Column(name="COST", nullable=false)
	public double getIngredientCost() {
		return ingredientCost;
	}
	
	public void setIngredientCost(double ingredientCost) {
		this.ingredientCost = ingredientCost;
	}
	
	@Column(name="INVENTORY", nullable=false)
	public int getInventory() {
		return inventory;
	}
	
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
	@Column(name="PURCHASES", nullable=false)
	public int getPurchases() {
		return purchases;
	}
	
	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}
}