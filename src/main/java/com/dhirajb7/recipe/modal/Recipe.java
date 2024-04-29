package com.dhirajb7.recipe.modal;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipeId;

	@Column(nullable = false, name = "recipe_name", unique = true)
	private String name;

	@Column(nullable = false, name = "recipe_image_prefix")
	private String imagePrefix;

	@Lob
	@Column(name = "recipe_image", nullable = false)
	private byte[] image;

	@Column(name = "recipe_description")
	private String description;

	@Column(name = "recipe_steps", nullable = false)
	private List<String> steps;

	@OneToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_fk", referencedColumnName = "recipeId")
	private List<Ingredient> ingredients;

	@Column(name = "is_veg")
	@Setter(value = AccessLevel.NONE) // Prevents Lombok from generating a setter
	private boolean veg;

	public Recipe(Long recipeId, String name, String imagePrefix, byte[] image, String description, List<String> steps,
			List<Ingredient> ingredients) {
		super();
		this.recipeId = recipeId;
		this.name = name;
		this.imagePrefix = imagePrefix;
		this.image = image;
		this.description = description;
		this.steps = steps;
		this.ingredients = ingredients;
		this.veg = ingredients.stream().allMatch(ing -> ing.isVeg());
	}

}
