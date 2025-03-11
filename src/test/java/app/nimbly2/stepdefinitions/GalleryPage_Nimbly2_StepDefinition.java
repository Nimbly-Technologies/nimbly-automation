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
	public void verify_user_can_navigate_to_issues_by_clicking_go_to_issue_on_attachments() throws InterruptedException {
		gallerypage2.navigateBackToIssues();
	}
	@And("^download attachments under all, days and months$")
	public void download_attachments_under_all_days_months() throws InterruptedException {
		gallerypage2.downloadAttachmentsUnderAllDaysAndMonths();
	}

	@And("^validate attachments visibility under all tab$")
	public void validate_attachments_visibility_under_all_tab() throws InterruptedException {
		gallerypage2.verifyAttachmentsVisibilityUnderAllTab();
	}

	@And("^validate attachments visibility under days tab$")
	public void validate_attachments_visibility_under_days_tab() throws InterruptedException {
		gallerypage2.verifyAttachmnetsVisibilityUnderDaysTab();
	}

	@And("^validate attachments visibility under months tab$")
	public void validate_attachments_visibility_under_months_tab() throws InterruptedException {
		gallerypage2.verifyAttachmnetsVisibilityUnderMonthTab();
	}

	@And("^validate attachments visibility under years tab$")
	public void validate_attachments_visibility_under_years_tab() throws InterruptedException {
		gallerypage2.verifyAttachmnetsVisibilityUnderYearsTab();
	}
	
	@And("^verify tile view per row$")
	public void verify_tile_view_per_row() throws InterruptedException {
		gallerypage2.verifyTileViewPerRow();
	}
	@And("^validate attachments visibility and grouped by sites under album$")
	public void validate_attachments_visibility_under_album() throws InterruptedException {
		gallerypage2.validateAttachmentsUnderAlbum();
	}
	
	@And("^verify multi select attachments under album$")
	public void verify_multi_select_attachments_under_album() throws InterruptedException {
		gallerypage2.multiSelectAttachmentsUnderAlbum();
	}
	
	@And("^verify filter and sort functionality for gallery$")
	public void verify_filter_functionality_for_gallery() throws InterruptedException {
		gallerypage2.verifyFilterAndSortFunctionality();
	}

	@And("^validate user can select and deselect and collapse attachments day wise under library$")
	public void validate_user_can_select_and_deselect_and_collapse_attachments_day_wise_under_library() throws InterruptedException {
		gallerypage2.validateSelectDeselectAndCollapseAttachmentsDayWise();
	}

	@And("^validate user can select and deselect and collapse  attachments month wise under library$")
	public void validate_user_can_select_and_deselect_and_collpse_attachments_monyh_wise_under_library() throws InterruptedException {
		gallerypage2.validateSelectDeselectAndCollapseAttachmentsMonthWise();
	}

	@And("^validate user can select and deselect attachments year wise under library$")
	public void validate_user_can_select_and_deselect_attachments_year_wise_under_library() throws InterruptedException {
		gallerypage2.validateSelectDeselectAttachmentsYearWise();
	}

	@And("^validate search and suggestion as per question$")
	public void validate_search_and_suggestion_as_per_question() throws InterruptedException {
		gallerypage2.validateSearchAndSuggestionAsPerQuestion();
	}

	@And("^validate search and suggestion as per category$")
	public void validate_search_and_suggestion_as_per_category() throws InterruptedException {
		gallerypage2.validateSearchAndSuggestionAsPerCategory();
	}
}