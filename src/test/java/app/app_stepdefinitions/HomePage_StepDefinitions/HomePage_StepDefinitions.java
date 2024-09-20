package app.app_stepdefinitions.HomePage_StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import app.app_page_objects.HomePage_PageObjects.HomePage;
import utlis.UtlisManager;

public class HomePage_StepDefinitions {

	public UtlisManager utlisManager; // making utlis as global
	public HomePage homepage;

	public HomePage_StepDefinitions(UtlisManager utlisManager) throws Exception // creating a constructor to use utlis
																				// class methods
	{
		this.utlisManager = new UtlisManager();
		this.homepage = utlisManager.appPageobjectManager.getHomePage();
	}

	@Then("navigate to homepage screen")
	public void navigate_to_homepage_screen() throws InterruptedException {
		homepage.navigate_to_homepage();
	}

	@Then("click on notification bell icon$")
	public void click_on_notification_bell_icon() throws InterruptedException {
		homepage.click_on_navigation_to_bell_icon();
	}

	@And("click on close notifications x button$")
	public void click_on_close_notifications_x_button() throws InterruptedException {
		homepage.click_on_close_notifications_x_button();
	}

	@Then("click on sales icon")
	public void click_on_sales_icon() throws InterruptedException {
		homepage.click_on_sales_icon();
	}

	@Then("click on download toggle button")
	public void click_on_download_toggle_button() throws InterruptedException {
		homepage.click_on_download_toggle_button();
	}

	@And("click on cancel in download toggle button popup$")
	public void click_on_cancel_in_download_toggle_button_popup() throws InterruptedException {
		homepage.click_on_cancel_in_download_toggle_button_popup();
	}

	@Then("click on site card in homepage")
	public void click_on_site_card_in_homepage() throws InterruptedException {
		homepage.click_on_site_card_in_homepage();
	}

}
