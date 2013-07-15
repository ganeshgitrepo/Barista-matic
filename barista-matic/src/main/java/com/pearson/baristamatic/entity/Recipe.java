package com.pearson.baristamatic.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BARISTA_RECIPE")
public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long recipePK;					// Surrogate primary key
	private Drink drink;
	private Set<Ingredient> ingredients;
	private int parts;						// Parts of ingredients in drink
		
	// No-argument constructor supplied for Hibernate
	protected Recipe() { }

	public Recipe(Drink drink, Set<Ingredient> ingredients, int parts) {
		this.setDrink(drink);
		this.setIngredients(ingredients);
		this.parts = parts;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RECIPE_PK")
	public long getRecipePK() {
		return recipePK;
	}

	public void setRecipePK(long recipePK) {
		this.recipePK = recipePK;
	}

	@ManyToOne
	public Drink getDrink() {
		return drink;
	}

	public void setDrink(Drink drink) {
		this.drink = drink;
	}

	@OneToMany(mappedBy="ingredientId")
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
	
	@SuppressWarnings("unused")
	@Embeddable
	private class RecipePK implements Serializable {
		private static final long serialVersionUID = 1L;
		
		private Drink drink;					// One drink to one recipe
		private Set<Ingredient> ingredients;	// Ingredients in drink
		
		protected RecipePK() { }
		
		public RecipePK(Drink drink, Set<Ingredient> ingredients) {
			this.drink = drink;
			this.ingredients = ingredients;
		}
		
		@JoinColumn(name="DRINK_ID")
		public Drink getDrink() {
			return drink;
		}

		public void setDrink(Drink drink) {
			this.drink = drink;
		}
		
		@JoinColumn(name="INGREDIENT_ID")
		public Set<Ingredient> getIngredients() {
			return ingredients;
		}

		public void setIngredients(Set<Ingredient> ingredients) {
			this.ingredients = ingredients;
		}
	}
}
