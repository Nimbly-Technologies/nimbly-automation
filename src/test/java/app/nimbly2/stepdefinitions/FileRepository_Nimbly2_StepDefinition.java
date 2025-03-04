package app.nimbly2.stepdefinitions;

import app.nimbly2.page_objects.FileRepositoryPage2;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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
	@And("^validate files and folders under list and grid view$")
	public void validate_files_and_folders_under_list_and_grid_view() {
		filerepositorypage2.validateFilesAndFoldersUnderListAndGridView();
	}
	@And("^validate user can be able to download file and folder$")
	public void validate_user_can_be_able_to_download_file_and_folder() throws InterruptedException {
		filerepositorypage2.downloadFileAndFolder();
	}
	@And("^verify user can share file and folder$")
	public void verify_usr_can_share_file_and_folder() throws InterruptedException {
		filerepositorypage2.shareFileAndFolder();
	}
	@And("^verify user can create new folder and upload new file$")
	public void verify_user_can_create_new_folder_qand_upload_new_file() throws InterruptedException {
		filerepositorypage2.createNewFolderAndUploadNewFile();
	}
	@And("^switch to shared with me tab$")
	public void switch_to_shared_with_me_tab() throws InterruptedException {
		filerepositorypage2.switchToSharedWithMe();
	}
	@And("^share file and folder in list and grid view under shared with me$")
	public void share_file_and_folder_in_list_view_under_shared_with_me() throws InterruptedException {
		filerepositorypage2.shareFileAndFolderInListAndGridViewUnderSharedWithMe();
	}
	@And("^share and download recently uploaded file$")
	public void share_and_download_recently_uploaded_file() throws InterruptedException {
		filerepositorypage2.shareAndDownloadRecentlyUploadedFile();
	}
	@And("^rename file and folder names$")
	public void rename_file_and_folder_names() throws InterruptedException {
		filerepositorypage2.renameFileAndFolder();
	}
	@And("^move files and folders to trash$")
	public void move_files_and_folders_to_trash() throws InterruptedException {
		filerepositorypage2.moveFilesAndFoldersToTrash();
	}
	@And("^create new file and folder$")
	public void create_new_file_and_folder() throws InterruptedException {
		filerepositorypage2.createNewFolderAndFile();
	}
	@And("^validate the file type filter functionality for files (.*) (.*) (.*)$")
	public void validate_the_file_type_filter_functionality(String videoFile, String docFile, String imageFile) throws InterruptedException {
		filerepositorypage2.validateFileTypeFilter(videoFile,docFile,imageFile);
	}
	@And("^validate the file type filter functionality for folders (.*) (.*) (.*)$")
	public void validate_the_file_type_filter_functionality_for_folders(String videoFile, String docFile, String imageFile) throws InterruptedException {
		filerepositorypage2.validateFileTypeFilterForFolder(videoFile,docFile,imageFile);
	}
	@And("^verify user can share folder$")
	public void verify_user_can_share_folder() throws InterruptedException {
		filerepositorypage2.shareFolderUnderSharedWithMe();
	}
	@And("^verify sort functionality under shared with me$")
	public void verify_sort_functionality_under_shared_with_me() throws InterruptedException {
		filerepositorypage2.verifySortFunctionalityUnderSharedWithMe();
	}
	@And("^rename the file in recent tab$")
	public void rename_the_file_in_recent_tab() throws InterruptedException {
		filerepositorypage2.renameFileInRecentTab();
	}
	@And("^move file to trash in recent tab$")
	public void move_file_to_trash_in_recent_tab() throws InterruptedException {
		filerepositorypage2.moveFileToTrashInRecentTab();
	}
	@And("^navigates to starred tab$")
	public void navigates_to_starred_tab() throws InterruptedException {
		filerepositorypage2.navigateToStarredTab();
	}
	@And("^add file and folder to starred$")
	public void add_file_and_folder_to_starred() throws InterruptedException {
		filerepositorypage2.addFileAndFolderToStarred();
	}
	@And("^restore files and folders from trash$")
	public void restore_files_and_folders_from_trash() throws InterruptedException {
		filerepositorypage2.restoreFileAndFolderFromTrash();
	}
	@And("^create new folder$")
	public void create_new_folder() throws InterruptedException {
		filerepositorypage2.createNewFolder();
	}
	@And("^move folder to trash$")
	public void move_folder_to_trash() throws InterruptedException {
		filerepositorypage2.moveFolderToTrash();
	}
	@Then("^delete a folder permanently from trash$")
	public void delete_a_folder_permanently_from_trash() throws InterruptedException {
		filerepositorypage2.deleteFolderPermanentlyFromTrash();
	}
	@And("^navigates to trash$")
	public void navigates_to_trash() throws InterruptedException {
		filerepositorypage2.navigatesToTrash();
	}
	@And("^verify the search functionality$")
	public void verify_the_serach_functionality() throws InterruptedException {
		filerepositorypage2.verifySearchFunctionality();
	}
	@And("^verify that files and folders can be shared with multiple users simultaneously$")
	public void verify_that_files_and_folders_shared_with_multiple_users() throws InterruptedException {
		filerepositorypage2.shareFilesAndFoldersToMultipleUsers();
	}
	@And("^Verify that files and folders cannot be shared outside the organization$")
	public void verify_that_files_and_folders_cannot_be_shared_outside_the_organization() throws InterruptedException {
		filerepositorypage2.verifyFilesAndFoldersCannotBeSharedOutsideOrganization();
	}
	@And("^open a folder to upload files with the same name and add subfolders$")
	public void open_a_folder_to_upload_files_with_the_same_name_and_add_subfolders() throws InterruptedException {
		filerepositorypage2.openFolderToUploadMoreFiles();
	}

}
