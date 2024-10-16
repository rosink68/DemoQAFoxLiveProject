package com.tutorialsninja.automation.stepdef;

import static org.junit.Assert.assertTrue;

import com.tutorialsninja.automation.base.Base;
import com.tutorialsninja.automation.framework.Elements;
import com.tutorialsninja.automation.pages.AccountSuccessPage;
import com.tutorialsninja.automation.pages.HeadersSection;
import com.tutorialsninja.automation.pages.RegisterPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class Register {
	
	HeadersSection headersSection = new HeadersSection();
	RegisterPage registerPage = new RegisterPage();
	AccountSuccessPage accountSuccessPage = new AccountSuccessPage();

	@Given("^I launch the application$")
	public void i_launch_the_application() {

		// die Applikation starten
		Base.driver.get("https://tutorialsninja.com/demo/");
	}
	
	@And("^I navigate to Account Registration page$")
	public void i_navigate_to_Account_Registration_page() {
	
		Elements.click(HeadersSection.myAccountLink);
		Elements.click(HeadersSection.register);
	}
	
	@When("^I provide all the below valid details$")
	public void i_provide_all_the_below_valid_details(DataTable dataTable) {

		RegisterPage.enterAllRegistrationDetails(dataTable, "unique");
	}
	
	@And("^I select the Privacy Policy$")
	public void i_select_the_Privacy_Policy() {

		Elements.click(RegisterPage.privacyPolicy);
	}
	
	@And("^I click on Continue button$")
	public void i_click_on_Continue_button() {

		Elements.click(RegisterPage.continueButton);
	}
	
	@Then("^I should see that the User Account has successfully created$")
	public void i_should_see_that_the_User_Account_has_successfully_created() {

		assertTrue(Elements.isDisplayed(AccountSuccessPage.successBreadcrumb));
	}	
	
	@Then("^I should see that the User Account is not created$")
	public void i_should_see_that_the_user_account_is_not_created() {

		// wenn das Wort "Register" noch angezeigt wird, befindet man sich noch auf der Registerseite
		// und es wurde kein neuer Account angelegt
		assertTrue(Elements.isDisplayed(RegisterPage.registerBreadcrumb));
	}

	@And("^I should see the error messages informing the user to fill the mandatory fields$")
	public void i_should_see_the_error_messages_informing_the_user_to_fill_the_mandatory_fields() {

		assertTrue(Elements.isDisplayed(RegisterPage.firstNameWarning));
		assertTrue(Elements.isDisplayed(RegisterPage.lastNameWarning));
		assertTrue(Elements.isDisplayed(RegisterPage.emailWarning));
		assertTrue(Elements.isDisplayed(RegisterPage.telephoneWarning));
		assertTrue(Elements.isDisplayed(RegisterPage.passwordWarning));
		assertTrue(Elements.isDisplayed(RegisterPage.mainWarning));
	}
	
	@And("^I subscribe to Newsletter$")
	public void i_subscribe_to_newsletter() {

		Elements.click(RegisterPage.yesToNewsletter);
	}
	
	@When("^I provide the below duplicate details into the fields$")
	public void i_provide_the_below_duplicate_details_into_the_fields(io.cucumber.datatable.DataTable dataTable) {

		RegisterPage.enterAllRegistrationDetails(dataTable, "duplicate");
	}
	
	@Then("^I should see the warning message stating that the user is already created$")
	public void i_should_see_the_warning_message_stating_that_the_user_is_already_created() {

		assertTrue(Elements.VerifyTextEquals(RegisterPage.mainWarning,"Warning: E-Mail Address is already registered!"));
	}

}
