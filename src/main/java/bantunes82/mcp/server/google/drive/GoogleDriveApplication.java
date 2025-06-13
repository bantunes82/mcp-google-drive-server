package bantunes82.mcp.server.google.drive;

import bantunes82.mcp.server.google.drive.services.GoogleDriveService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GoogleDriveApplication implements CommandLineRunner {

	private GoogleDriveService googleDriveService;

	public GoogleDriveApplication(GoogleDriveService googleDriveService) {
		this.googleDriveService = googleDriveService;
	}

	@Override
	public void run(String... args) throws Exception {

		/*var folderName = "Viagens"; // Folder's name.
		var fileName = "test.doc"; // File's name.
		var fileContent = "This is a test file content for Google Drive upload."; // File's content.

		googleDriveService.uploadMicrosoftWordFile(folderName, fileName, fileContent);*/
	}

	public static void main(String[] args) {
		SpringApplication.run(GoogleDriveApplication.class, args);
	}

}
