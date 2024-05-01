package com.dhirajb7.recipe.modal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy = "catagory")
	@JsonIgnore
	private List<Recipe> recipes;

}
