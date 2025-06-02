package bantunes82.mcp.server.google.driver;

import bantunes82.mcp.server.google.driver.services.UploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleDriverApplication implements CommandLineRunner {

	private UploadService uploadService;

	public GoogleDriverApplication(UploadService uploadService) {
		this.uploadService = uploadService;
	}

	@Override
	public void run(String... args) throws Exception {
		uploadService.uploadBasic();
	}

	public static void main(String[] args) {
		SpringApplication.run(GoogleDriverApplication.class, args);
	}

}
