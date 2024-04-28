package com.dhirajb7.recipe.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneralExceptionGenerator {
	
	private int status;
	
	private String message;
	
	private String timeStamp;
	
}
