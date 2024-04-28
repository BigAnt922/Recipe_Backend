package com.dhirajb7.recipe.exception.ingredients;

public class IngredientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IngredientNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngredientNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public IngredientNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public IngredientNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public IngredientNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
