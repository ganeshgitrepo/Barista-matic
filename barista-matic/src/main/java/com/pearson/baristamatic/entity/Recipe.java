package com.pearson.baristamatic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_RECIPE")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Drink drink;					// One drink to one recipe
	private Ingredient ingredient;			// Ingredients in drink
	private int parts;						// Parts of ingredients in drink
		
	// No-argument constructor supplied for Hibernate
	protected Recipe() { }

	public Recipe(Drink drink, Ingredient ingredient, int parts) {
		this.drink = drink;
		this.ingredient = ingredient;
		this.parts = parts;
	}

	@Id
	@JoinColumn(name="RECIPE_DRINK_ID", referencedColumnName="DRINK_ID")
	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	@ManyToOne
	@JoinColumn(name="INGREDIENT_ID")
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setingredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Column(name="PARTS")
	public int getParts() {
		return parts;
	}
	
	public void setParts(int parts) {
		this.parts = parts;
	}
}
