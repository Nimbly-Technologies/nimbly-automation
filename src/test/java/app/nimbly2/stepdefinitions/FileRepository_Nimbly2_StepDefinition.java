package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.FileRepositoryPage2;
import io.cucumber.java.en.When;
import utlis.UtlisManager;

public class FileRepository_Nimbly2_StepDefinition {
	public UtlisManager utlisManager; // Making utlisManager as global
	public FileRepositoryPage2 filerepositorypage2;

	public FileRepository_Nimbly2_StepDefinition(UtlisManager utlisManager) throws Exception {
		this.utlisManager = utlisManager;
		this.filerepositorypage2 = utlisManager.appPageobjectManager.getFileRepositoryPage2();
	}
	
	@When("^navigates to file repo$")
	public void navigates_to_file_repo() throws InterruptedException {
		filerepositorypage2.navigateToFileRepo();
	}

}
