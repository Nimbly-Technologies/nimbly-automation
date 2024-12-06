package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.SchedulesPage2;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utlis.UtlisManager;

public class SchedulesPage_Nimbly2_StepDefinition {
	public UtlisManager utlisManager; // Making utlisManager as global
	public SchedulesPage2 schedulespage2;

	public SchedulesPage_Nimbly2_StepDefinition(UtlisManager utlisManager) throws Exception {
		this.utlisManager = utlisManager;
		this.schedulespage2 = utlisManager.appPageobjectManager.getSchedulesPage2();
	}

	@When("^search for the (.*)$")
	public void serach_for_the_schedule(String scheduleName) throws InterruptedException {
		schedulespage2.searchForSchedule(scheduleName);

	}

	@And("^validate schedule card details for (.*) schedule (.*)$")
	public void validate_schedule_card_details(String scheduleType, String attachmentType) throws InterruptedException {
		schedulespage2.validateScheduleCardDetails(scheduleType,attachmentType);
	}

	@And("^verify check in pop up details for (.*) schedule (.*)$")
	public void validate_checkin_popup(String scheduleType, String attachmentType) throws InterruptedException {
		schedulespage2.validateCheckinPopupDetails(scheduleType, attachmentType);
	}

	@Then("^start the auditing process for (.*) schedule (.*) attachments$")
	public void start_auditing(String scheduleType, String attachmentType) throws InterruptedException {
		schedulespage2.auditingProcess(scheduleType,attachmentType);
	}

	@And("^validate report review page for (.*) schedule (.*) attachments$")
	public void validate_report_review_page(String scheduleType, String attachmentType) throws InterruptedException {
		schedulespage2.validateReviewReport(scheduleType,attachmentType);

	}

	@And("^submit the report in online mode$")
	public void submit_the_report_online_mode() throws InterruptedException {
		schedulespage2.submitReport();
	}

	@And("^validate report submitted page for (.*) schedule$")
	public void report_submitted_Page(String scheduleType) throws InterruptedException {
		schedulespage2.reportSubmittedPage(scheduleType);
	}

	@When("^switch to offline mode$")
	public void switch_to_offline_mode() throws InterruptedException {
		schedulespage2.switchToOfflineMode();
	}

	@And("^checkout the report in offline mode$")
	public void checkout_the_report_in_offline_mode() throws InterruptedException {
		schedulespage2.checkoutReport();
	}

	@And("^validate offline schedule status after checkout$")
	public void validate_offline_schedule_status_after_checkout() throws InterruptedException {
		schedulespage2.validateOfflineScheduleStatus();
	}

	@And("^switch to online mode$")
	public void switch_to_online_mode() throws InterruptedException {
		schedulespage2.switchToOnlineMode();
	}

	@Then("^validate schedule status after switching to online mode$")
	public void validate_schedule_status_online_mode() throws InterruptedException {
		schedulespage2.validateOfflineScheduleStatus();
	}

	@And("^validate modify and sync with server on checkin pop up$")
	public void validate_modify_and_sync_with_server() throws InterruptedException {
		schedulespage2.validateModifyAndSyncWithServer();
	}

	@And("^validate review report for (.*) schedule (.*) attachments after switching to online mode$")
	public void validate_review_report_after_switching_to_online_mode(String scheduleType, String attachmentType) throws InterruptedException {
		schedulespage2.validateReviewReportPageAfterSwitchingToOnlineMode(scheduleType,attachmentType);
	}

	@And("^sync your data with server$")
	public void sync_your_data_with_server() throws InterruptedException {
		schedulespage2.syncDataWithServer();
	}
	
	@And("^start the audit process to validate save as draft in (.*) mode$")
	public void strat_audit_process_to_validate_save_as_draft(String mode) throws InterruptedException {
		schedulespage2.saveAsDraft(mode);
	}
	
	@Then("^validate attachments after save as a draft$")
	public void validate_attachments_after_save_as_draft() throws InterruptedException {
		schedulespage2.validateAttachments();
	}
	
	@And("^start audit proccess and submit the report$")
	public void start_audit_and_submit_report() throws InterruptedException {
		schedulespage2.startAuditAndSubmitReport();
	}
	
	@And("^validate checkin radius$")
	public void validate_checkin_radius() throws InterruptedException {
		schedulespage2.validateCheckinRadius();
	}
	@And("^start audit process and answer only mandatory questions$")
	public void answer_only_mandatory_questions() throws InterruptedException {
		schedulespage2.answerMandatoryQuestions();
	}
	@And("^validate review repoprt page for use deductions$")
	public void review_report_page_for_use_deductions() throws InterruptedException {
		schedulespage2.validateReviewReportUseDeductions();
	}
	@And("^validate site and questionnaire names along with signature and selfie error messagaes$")
	public void validate_site_and_questionnaire() throws InterruptedException {
		schedulespage2.validateSiteAndQuestionnaireOnReviewPage();
	}
	@And("^start audit process for conditional questions$")
	public void start_audit_process_for_conditional_questions() throws InterruptedException {
		schedulespage2.auditProcessForConditionalQuestions();
	}

	@And("^validate review page for conditional questions$")
	public void validate_review_page_for_conditional_questions() {
		schedulespage2.validateReviewPageForConditionalQuestions();
	}

	@And("^validate report submitted page for conditional questions$")
	public void validate_report_submitted_page_for_conditional_question() throws InterruptedException {
		schedulespage2.validateReportSubmittedPageForConditionalQuestions();
	}
	
	@And("^verify schedule progress after answering few questions$")
	public void verify_schedule_progress_after_answering_few_questions() throws InterruptedException {
		schedulespage2.verifyScheduleProgress();
	}
	
	@And("^verify green yellow and red flags count under preview of questionnaire$")
	public void verify_green_yellow_red_flags_count_under_preview_of_questionnaire() throws InterruptedException {
		schedulespage2.verifyFlagsCountOfQuestionnairePreview();
	}
	@And("^validate choosing different camera resolutions$")
	public void validate_choosing_different_camera_resolutions() throws InterruptedException {
		schedulespage2.validateCameraResolution();
	}
	@And("^validate user able to delete attachments$")
	public void validate_user_able_to_delete_attachments() throws InterruptedException {
		schedulespage2.verifyAttachmentsDeletion();
	}
	@And("^checkin a schedule$")
	public void checkin_a_schedule() throws InterruptedException {
		schedulespage2.checkinSchedule();
	}
	@And("^verify user can select any piority and schedule progress$")
	public void verify_user_can_select_any_priority_and_schedule_progress() throws InterruptedException {
		schedulespage2.verifyScheduleProgressAndSelectPriority();
	}
	@And("^validate image annotation$")
	public void validate_image_annotation() {
		schedulespage2.validateImageAnnotation();
	}
	@And("^navigates back to schedule page$")
	public void navigates_back_to_schedule_page() throws InterruptedException {
		schedulespage2.navigateBackToSchedulePage();
	}
	@And("^start auditing and validate maximum green flag threshold and negative scoring$")
	public void start_auditing_and_validate_maximum_green_flag_threshold_and_negative_scoring() throws InterruptedException {
		schedulespage2.validateGreenFlagThresholdAndNegativeScoring();
	}
}