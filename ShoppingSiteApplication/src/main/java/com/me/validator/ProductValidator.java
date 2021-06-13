package com.me.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.Product;

public class ProductValidator implements Validator{
	public boolean supports(Class aClass) {
		return aClass.equals(Product.class);
	}

	private Pattern pattern;  
	 private Matcher matcher;  
	
	 private static final  
	 String STRING_PATTERN = "[a-zA-Z]+";
	
	public void validate(Object obj, Errors errors) {
		Product newProduct = (Product) obj;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "error.invalid.productName", "productName Required");
		 if (!(newProduct.getProductName() != null && newProduct.getProductName().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(newProduct.getProductName());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("productName", "productName.containNonChar",  
			      "Enter a valid productName");  
			   }  
			  }
		 
		 
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.description", "Product Description Required");
		 if (!(newProduct.getDescription() != null && newProduct.getDescription().isEmpty())) {  
			   pattern = Pattern.compile(STRING_PATTERN);  
			   matcher = pattern.matcher(newProduct.getDescription());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("description", "description.containNonChar",  
			      "Enter a valid product description");  
			   }  
			  }
	}
}
