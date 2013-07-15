package com.pearson.baristamatic.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_RECIPE")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Drink drink;					// One drink to one recipe
	private Set<Ingredient> ingredients;	// Ingredients in drink
	private int parts;						// Parts of ingredients in drink
		
	// No-argument constructor supplied for Hibernate
	protected Recipe() { }

	public Recipe(Drink drink, Set<Ingredient> ingredients, int parts) {
		this.drink = drink;
		this.ingredients = ingredients;
		this.parts = parts;
	}

	@OneToOne
	@JoinColumn(name="DRINK_ID")
	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	@ManyToOne
	@JoinColumn(name="INGREDIENT_ID")
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	@Column(name="PARTS")
	public int getParts() {
		return parts;
	}
	
	public void setParts(int parts) {
		this.parts = parts;
	}
}
