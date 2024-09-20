//used to store all locators of Auditing questions 
//and required methods with their functionality as names will be declared 
//which can be used or usderstood easily in stepdefinition files

package app.app_page_objects.SchedulePage_PageObjects;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

@SuppressWarnings("deprecation")
public class AuditingWorkflow {

	public AppiumDriver driver;
	public Properties prop;

	public AuditingWorkflow(AppiumDriver driver, Properties prop) {
		this.driver = driver;
		this.prop = prop;

	}

//Single audit locators of Auditing questions
	By next_question_locator = AppiumBy.className("android.widget.Button");
	By review_report_button_locator = AppiumBy.className("android.widget.Button");
	By checkout_button_locator = AppiumBy.className("android.widget.Button");
	By checkout_popup_yes_button_locator = AppiumBy.id("android:id/button2");
	By got_it_button_locator = AppiumBy.className("android.widget.Button");

	By question_1_answer_locator = AppiumBy.xpath("//android.widget.TextView[@text='Yes']");
	By question_2_answer_locator = AppiumBy.xpath("//android.widget.TextView[@text='Yes']");
	By question_3_answer_priority_popup_locator = AppiumBy.xpath("//android.widget.TextView[@text='Low']");
	By question_3_answer_priority_popup_save_locator = AppiumBy.className("android.widget.Button");
	By question_3_answer_edit_comment_locator = AppiumBy.className("android.widget.EditText");
	By question_4_answer_edit_comment_locator = AppiumBy.className("android.widget.EditText");
	By question_5_answer_edit_text_locator = AppiumBy.className("android.widget.EditText");
	By question_6_answer_locator = AppiumBy.xpath("(//android.widget.TextView)[11]");
	By question_7_answer_locator = AppiumBy.xpath("(//android.widget.TextView)[10]");
	By question_8_answer_locator = AppiumBy.xpath("(//android.widget.TextView)[10]");
	By question_9_answer_locator = AppiumBy.xpath("//android.widget.EditText[@text='Type your answer here']");
	By tab_on_slider = AppiumBy.xpath("//android.widget.SeekBar[@index='4']");

//team audit locators
	By finish_section_button_locator = AppiumBy.className("android.widget.Button");
	By upload_report_button_locator = AppiumBy.className("android.widget.Button");
	By upload_report_popup_proceed_button_locator = AppiumBy.xpath("(//android.widget.TextView)[3]");
	By finalize_report_button_locator = AppiumBy.className("android.widget.Button");
	By finalize_report_popup_proceed_button_locator = AppiumBy.xpath("(//android.widget.TextView)[3]");

	By teamaudit_section1_locator = AppiumBy.xpath("(//android.widget.TextView)[10]");
	By teamaudit_section2_locator = AppiumBy.xpath("(//android.widget.TextView)[14]");

//single audit with attachments
	By Add_photo_button = AppiumBy.xpath("(//android.widget.ImageView)[1]");
	By take_photo_from_camera = AppiumBy.xpath("//android.widget.TextView[@text='From Camera']");
	By camera_resolution_selection = AppiumBy.xpath("//android.widget.TextView[@text='Small (640x480)']");
	By click_on_ok_button = AppiumBy.xpath("//android.widget.TextView[@text='Ok']");
	By click_on_capture_photo_button = AppiumBy.xpath("(//android.view.ViewGroup)[20]");
	By click_on_use_photo_button = AppiumBy.xpath("//android.widget.TextView[@text='Use Photo']");
	By click_on_add_attachment_button = AppiumBy
			.xpath("//android.widget.TextView[@text='Add pdf, xlsx, or doc files']");
	By add_attachment = AppiumBy.xpath("(//android.widget.LinearLayout)[13]");
	By scroll_down = AppiumBy
			.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Add Photo\"));");
	By take_selfie_signature = AppiumBy.xpath("//android.widget.TextView[@text='Take a selfie signature']");
	By click_on_capture = AppiumBy.xpath("(//android.view.ViewGroup)[19]");
	By tab_digital_signature = AppiumBy.xpath("//android.widget.TextView[@text='TAP TO SIGN']");
	By add_signature = AppiumBy.xpath("(//android.view.View)");
	By save_signature = AppiumBy.xpath("//android.widget.TextView[@text='Save']");
	By enter_user_name = AppiumBy.xpath("//android.widget.EditText[@text='Enter your name']");
	By enter_user_position = AppiumBy.xpath("//android.widget.EditText[@text='Enter your position']");

	public void click_on_next_question() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkout_button_locator));
		driver.findElement(next_question_locator).click();
	}

	public void click_on_review_report() {
		driver.findElement(review_report_button_locator).click();
	}

	public void click_on_checkout() throws InterruptedException {
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(checkout_button_locator).click();
	}

	public void click_yes_on_checkout_popup() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(checkout_popup_yes_button_locator).click();
	}

	public void click_got_it() throws InterruptedException {
		Thread.sleep(7000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(got_it_button_locator).click();
		Thread.sleep(5000);
	}

	public void click_on_question_1_answer() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(question_1_answer_locator).click();
	}

	public void click_on_question_2_answer() throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(question_2_answer_locator).click();
	}

	@SuppressWarnings({ "rawtypes" })
	public void slide_on_question_3_answer_with_priority() throws InterruptedException {
		Thread.sleep(3000);
//	TouchAction tapaction = new TouchAction(driver);
//	tapaction.tap(PointOption.point(708, 812)).perform();
		Thread.sleep(3000);
		driver.findElement(tab_on_slider).click();
		driver.findElement(question_3_answer_priority_popup_locator).click();
		Thread.sleep(3000);
		driver.findElement(question_3_answer_priority_popup_save_locator).click();
		driver.findElement(question_3_answer_edit_comment_locator).sendKeys("Priority Set to Low");
	}

	public void click_on_question_4_answer() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(question_4_answer_edit_comment_locator).sendKeys("question 4 answer");
	}

	public void click_on_question_5_answer() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(question_5_answer_edit_text_locator).sendKeys("123");
	}

	public void click_on_question_6_answer() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(question_6_answer_locator).click();
	}

	public void click_on_question_7_answer() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(question_7_answer_locator).click();
	}

	public void click_on_question_8_answer() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(question_8_answer_locator).click();
	}

	public void click_on_question_9_answer() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(question_9_answer_locator).sendKeys("1");
		Thread.sleep(2000);
	}

//common method which comprises of answering all questions
	public void answer_all_the_questions() throws InterruptedException {
		Thread.sleep(3000);

		click_on_question_1_answer();
		click_on_next_question();
		click_on_question_2_answer();
		click_on_next_question();
		slide_on_question_3_answer_with_priority();
		click_on_next_question();
		click_on_question_4_answer();
		click_on_next_question();
		click_on_question_5_answer();
		click_on_next_question();
		click_on_question_6_answer();
		click_on_next_question();
		click_on_question_7_answer();
		click_on_next_question();
		click_on_question_8_answer();
		click_on_next_question();
		click_on_question_9_answer();
	}

//team audit methods
	public void select_sections_and_answer_all_the_questions() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(teamaudit_section1_locator).click();
		answer_all_the_questions();
		driver.findElement(finish_section_button_locator).click();
		Thread.sleep(3000);
		driver.findElement(teamaudit_section2_locator).click();
		answer_all_the_questions();
		driver.findElement(finish_section_button_locator).click();
	}

	public void upload_report_and_accept_the_popup() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(upload_report_button_locator).click();
		Thread.sleep(3000);
		driver.findElement(upload_report_popup_proceed_button_locator).click();
	}

	public void finalize_report_and_accept_the_popup() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(finalize_report_button_locator).click();
		Thread.sleep(3000);
		driver.findElement(finalize_report_popup_proceed_button_locator).click();
	}

	public void start_auditing_with_attachmnets() throws InterruptedException {
		answer_first_question();
		click_on_next_question();
		answer_second_question();
	}

	public void answer_first_question() throws InterruptedException {
		click_on_question_1_answer();
		add_pdf_xlsx_attachments();
		scroll_down_page();
		capture_photo();
		click_on_next_question();
	}

	public void answer_second_question() throws InterruptedException {
		scroll_down_page();
		add_pdf_xlsx_attachments();
		scroll_down_page();
		capture_photo();
		click_on_question_2_answer();
	}

	public void capture_photo() throws InterruptedException {
		Thread.sleep(6000);
		driver.findElement(Add_photo_button).click();
		Thread.sleep(3000);
		driver.findElement(take_photo_from_camera).click();
		Thread.sleep(3000);
		driver.findElement(click_on_capture_photo_button).click();
		Thread.sleep(3000);
		driver.findElement(click_on_use_photo_button).click();
	}

	public void add_pdf_xlsx_attachments() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(click_on_add_attachment_button).click();
		Thread.sleep(3000);
		driver.findElement(add_attachment).click();
	}

	public void select_camera_resolution() throws InterruptedException {
		Thread.sleep(3000);
		try {
			WebElement resolution = driver.findElement(camera_resolution_selection);
			if (resolution.isDisplayed()) {
				resolution.click();
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
		Thread.sleep(3000);
		try {
			WebElement button = driver.findElement(click_on_ok_button);
			if (button.isDisplayed()) {
				button.click();
			}
		} catch (NoSuchElementException e) {
			e.getMessage();
		}
	}

	public void scroll_down_page() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(scroll_down);
	}

	public void digitalSignature() {
		// Locate the signature canvas element
		WebElement signatureCanvas = driver.findElement(add_signature);

		// Get the location and size of the signature canvas
		int startX = signatureCanvas.getLocation().getX();
		int startY = signatureCanvas.getLocation().getY();
		int endX = startX + signatureCanvas.getSize().getWidth();
		int endY = startY + signatureCanvas.getSize().getHeight();

		// Create a pointer input for the pen (or finger) action
		PointerInput pen = new PointerInput(PointerInput.Kind.PEN, "pen");

		// Create a sequence of actions
		Sequence signatureSequence = new Sequence(pen, 0)
				.addAction(
						pen.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX + 10, startY + 10))
				.addAction(pen.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(pen.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX + 50,
						startY + 50))
				.addAction(pen.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX + 90,
						startY + 10))
				.addAction(pen.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX + 130,
						startY + 50))
				.addAction(pen.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		// Perform the sequence
		driver.perform(Arrays.asList(signatureSequence));

	}

	public void add_signature_and_selfie() throws InterruptedException {
		add_selfie();
		add_digital_signature();
	}

	public void add_selfie() throws InterruptedException {
		Thread.sleep((3000));
		driver.findElement(take_selfie_signature).click();
		;
		Thread.sleep((3000));
		driver.findElement(click_on_capture).click();
		;
		Thread.sleep((3000));
		driver.findElement(click_on_use_photo_button).click();
		;
	}

	public void add_digital_signature() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(tab_digital_signature).click();
		;
		Thread.sleep((3000));
		digitalSignature();
		Thread.sleep((3000));
		driver.findElement(save_signature).click();
		;
		Thread.sleep((3000));
		driver.findElement(enter_user_name).sendKeys("Arjun");
		;
		Thread.sleep((3000));
		driver.findElement(enter_user_position).sendKeys("account_holder");
		;
	}

}