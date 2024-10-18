package com.tutorialsninja.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.automation.base.Base;
import com.tutorialsninja.automation.framework.Elements;

public class ProductPage {

	public ProductPage() {
		
		PageFactory.initElements(Base.driver, this);
	}
	
	@FindBy(id="button-cart")
	public static WebElement addToCartButton;
	
	public static void clickAddToCartButton() {
		
		Elements.click(addToCartButton);
	}

}
