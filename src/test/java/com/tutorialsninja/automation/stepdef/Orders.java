package com.tutorialsninja.automation.stepdef;

import static org.junit.Assert.assertTrue;

import com.tutorialsninja.automation.framework.*;
import com.tutorialsninja.automation.pages.*;

import io.cucumber.java.en.*;

public class Orders {
	
	HeadersSection headersSection = new HeadersSection();
	LoginPage loginPage = new LoginPage();
	SearchResultPage searchResultPage = new SearchResultPage();
	ProductPage productPage = new ProductPage();
	ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
	CheckoutPage checkoutPage = new CheckoutPage();
	OrderSuccessPage orderSuccessPage = new OrderSuccessPage();
	
	@Given("^I login to the application$")
	public void i_login_to_the_application() {

		Browser.openApplicationURL();
		HeadersSection.navigateToLoginPage();
		LoginPage.doLogin();
	}

	@When("^I add any product to Bag and checkout$")
	public void i_add_any_product_to_bag_and_checkout() {

		HeadersSection.searchProduct();
		SearchResultPage.addFirstProductInTheSearchResultsToCart();
		// den Zwischenschritt braucht man, wenn der Einkaufswagen leer ist, da
		// anderes als im Kurs, das Produkt nicht in den Einkaufswagen gepackt wird
		ProductPage.clickAddToCartButton();
		HeadersSection.navigateToShoppingCartPage();
		ShoppingCartPage.navigateToCheckoutPage();
	}

	@And("^I place an order$")
	public void i_place_an_order() {

		CheckoutPage.placeAnOrder();
	}

	@Then("^I should see that the order is placed successfully$")
	public void i_should_see_that_the_order_is_placed_successfully() {

		Waits.waitUntilElementLocated(10, OrderSuccessPage.successBreadcrumb);
		assertTrue(Elements.isDisplayed(OrderSuccessPage.successBreadcrumb));
	}
}
