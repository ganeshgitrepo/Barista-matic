package com.pearson.baristamatic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_DRINK")
public class Drink implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long drinkId;				// Unique drink ID
	private String drinkName;			// Name of drink
	private Recipe recipe;				// Drink recipe
	private double cost;				// Associated drink cost
	private int sales;					// Number of times the drink has been purchased
	
	// No-argument constructor supplied for Hibernate
	protected Drink() { }
	
	public Drink(long drinkId, String drinkName, Recipe recipe, double cost, int sales) {
		this.drinkId = drinkId;
		this.drinkName = drinkName;
		this.recipe = recipe;
		this.cost = cost;
		this.sales = sales;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DRINK_ID")
	public long getDrinkId() {
		return drinkId;
	}
	
	public void setDrinkId(long drinkId) {
		this.drinkId = drinkId;
	}
	
	@Column(name="DRINK_NAME")
	public String getDrinkName() {
		return drinkName;
	}
	
	public void setDrinkName(String drinkName) {
		this.drinkName = drinkName;
	}
	
	@JoinColumn(name="RECIPE_DRINK_ID")
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Column(name="COST")
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	@Column(name="SALES")
	public int getSales() {
		return sales;
	}
	
	public void setSales(int sales) {
		this.sales = sales;
	}
}
