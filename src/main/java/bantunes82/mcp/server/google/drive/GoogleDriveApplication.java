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

		var folderName = "Viagens"; // Folder's name.
		var fileName = "photo.png"; // File's name.
		var fileContentType = "image/png"; // File's content type.
		var filePath = new java.io.File("files/photo.png");
		// Read file content as byte array.
		var fileContent = java.nio.file.Files.readAllBytes(filePath.toPath());

		googleDriveService.uploadBasic(folderName, fileName, fileContentType, fileContent);
	}

	public static void main(String[] args) {
		SpringApplication.run(GoogleDriveApplication.class, args);
	}

}
