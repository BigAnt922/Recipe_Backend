package com.dhirajb7.recipe.modal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId"), inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "ingredientId"))
	private List<Ingredient> ingredients;

	@Column(name = "is_veg")
	private boolean veg;

	@ManyToMany(mappedBy = "recipes")
	@ToString.Exclude
	@JsonIgnore
	private List<Catagory> catagories;

}
