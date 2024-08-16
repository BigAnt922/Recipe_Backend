package com.dhirajb7.recipe.modal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GeneralExceptionGenerator {
	private int status;
	private String message;
	private String timeStamp;
}
