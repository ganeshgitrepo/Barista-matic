package com.pearson.baristamatic.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_RECIPE")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Drink drink;					// Corresponding recipe the drink belongs to
	private Ingredient ingredient;			// Corresponding ingredient
	private int part;						// Parts of ingredients in drink

	// No-argument constructor supplied for Hibernate
	protected Recipe() { }

	public Recipe(Drink drink, Ingredient ingredient, int part) {
		this.drink = drink;
		this.ingredient = ingredient;
		this.part = part;
	}

	@Id
	@OneToOne
	public Drink getDrink() {
		return this.drink;
	}
	
	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	@Id
	@ManyToOne
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Column(name="PART", nullable=false)
	public int getPart() {
		return part;
	}

	public void setPart(int part) {
		this.part = part;
	}
}
