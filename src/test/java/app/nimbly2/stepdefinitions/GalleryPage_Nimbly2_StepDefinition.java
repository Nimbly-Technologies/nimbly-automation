package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.GalleryPage2;
import io.cucumber.java.en.And;
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
	@And("^validate by default library and all tabs are selected$")
	public void validate_by_default_library_and_all_tabs_are_selected() throws InterruptedException {
		gallerypage2.validateDefaultAllAndLibraryTabsSelected();
	}
	@And("^validate user can download image by clicking on the attachment$")
	public void validate_user_can_download_image_by_clicking_on_the_attachment() throws InterruptedException {
		gallerypage2.downloadAttachment();
	}
	@And("^validate select all popup$")
	public void validate_select_all_popup() {
		gallerypage2.validateSelectAllPopup();
	}
	@And("^verify user can navigate to issues by clicking go to issue on attachment$")
	public void verify_user_can_navigate_to_issues_by_clicking_go_to_issue_on_attachments() {
		gallerypage2.navigateBackToIssues();
	}
	@And("^download attachments under all, days and months$")
	public void download_attachments_under_all_days_months() throws InterruptedException {
		gallerypage2.downloadAttachmentsUnderAllDaysAndMonths();
	}
}
