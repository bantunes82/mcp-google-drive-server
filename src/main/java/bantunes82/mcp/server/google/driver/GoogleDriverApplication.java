package bantunes82.mcp.server.google.driver;

import bantunes82.mcp.server.google.driver.services.GoogleDriverService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleDriverApplication implements CommandLineRunner {

	private GoogleDriverService googleDriverService;

	public GoogleDriverApplication(GoogleDriverService googleDriverService) {
		this.googleDriverService = googleDriverService;
	}

	@Override
	public void run(String... args) throws Exception {

		var folderName = "Viagens"; // Folder's name.
		var fileName = "photo.png"; // File's name.
		var fileContentType = "image/png"; // File's content type.
		var filePath = new java.io.File("files/photo.png"); // File's content.

		googleDriverService.uploadBasic(folderName, fileName, fileContentType, filePath);
	}

	public static void main(String[] args) {
		SpringApplication.run(GoogleDriverApplication.class, args);
	}

}
