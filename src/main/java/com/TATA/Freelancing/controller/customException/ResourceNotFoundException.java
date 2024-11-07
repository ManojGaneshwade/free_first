package com.TATA.Freelancing.controller.customException;

public class ResourceNotFoundException extends RuntimeException{
	
	public ResourceNotFoundException(String errMsg)
	{
		super(errMsg);
	}

}
