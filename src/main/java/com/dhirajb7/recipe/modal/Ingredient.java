package com.dhirajb7.recipe.modal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Ingredients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientId;

	@Column(nullable = false, name = "ingredient_name", unique = true)
	private String name;

	@Column(nullable = false, name = "ingredient_image_prefix")
	private String imagePrefix;

	@Lob
	@Column(name = "ingredient_image", nullable = false)
	private byte[] image;

	@Column(name = "ingredient_description")
	private String description;

	@Column(name = "is_veg")
	private boolean veg;

	@ManyToMany(mappedBy = "ingredients")
	@ToString.Exclude
	@JsonIgnore
	private List<Recipe> recipes;

}
