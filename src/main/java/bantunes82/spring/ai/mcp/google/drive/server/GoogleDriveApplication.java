package bantunes82.spring.ai.mcp.google.drive.server;

import bantunes82.spring.ai.mcp.google.drive.server.config.GoogleDriveRuntimeHints;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication
@ImportRuntimeHints(GoogleDriveRuntimeHints.class)
public class GoogleDriveApplication {


	public static void main(String[] args) {
		SpringApplication.run(GoogleDriveApplication.class, args);
	}

}

