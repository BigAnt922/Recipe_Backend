package com.dhirajb7.recipe.modal;

import java.util.List;

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

@Entity
@Table(name = "catagories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catagory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long catagoryId;

	@Column(nullable = false, name = "catagory_name", unique = true)
	private String name;

	@Column(nullable = false, name = "catagory_image_prefix")
	private String imagePrefix;

	@Lob
	@Column(name = "catagory_image", nullable = false)
	private byte[] image;

	@Column(name = "catagory_description")
	private String description;

	private List<String> openCloseTimings;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "catagory_recipe", joinColumns = @JoinColumn(name = "catagory_id", referencedColumnName = "catagoryId"), inverseJoinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId"))
	private List<Recipe> recipes;
}
