package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.GalleryPage2;
import io.cucumber.java.en.When;
import utlis.UtlisManager;

public class GalleryPage_Nimbly2_StepDefinition {
	public UtlisManager utlisManager; // Making utlisManager as global
	public GalleryPage2 gallerypage2;

	public GalleryPage_Nimbly2_StepDefinition(UtlisManager utlisManager) throws Exception {
		this.utlisManager = utlisManager;
		this.gallerypage2 = utlisManager.appPageobjectManager.getGalleryPagePage2();
	}
	@When("^navigates to the gallery$")
	public void navigates_to_the_gallery() throws InterruptedException {
		gallerypage2.navigateToGallery();
	}
}
