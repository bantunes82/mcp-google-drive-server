package bantunes82.spring.ai.mcp.google.drive.server.config;

import bantunes82.spring.ai.mcp.google.drive.server.services.GoogleDriveService;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GoogleDriveConfig {

    /**
     * Provides a ToolCallbackProvider for Google Drive service.
     *
     * @param googleDriveService GoogleDriveService instance.
     * @return ToolCallbackProvider for Google Drive service.
     */
    @Bean
    public ToolCallbackProvider googleDriveTools(GoogleDriveService googleDriveService) {
        return MethodToolCallbackProvider.builder().toolObjects(googleDriveService).build();
    }

    /**
     * Provides Google credentials for accessing Google Drive API.
     *
     * @return GoogleCredentials instance with the required scopes.
     */
    @Bean
    GoogleCredentials googleCredentials() {
        try {
            return GoogleCredentials.getApplicationDefault()
                    .createScoped(List.of(DriveScopes.DRIVE_FILE));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load Google credentials", e);
        }
    }

    /**
     * Provides an HttpRequestInitializer for Google Drive API using the provided credentials.
     *
     * @param credentials GoogleCredentials instance.
     * @return HttpRequestInitializer for Google Drive API.
     */
    @Bean
    HttpRequestInitializer httpRequestInitializer(GoogleCredentials credentials) {
        return new HttpCredentialsAdapter(credentials);
    }

    /**
     * Provides a Drive service instance for interacting with Google Drive API.
     *
     * @param requestInitializer HttpRequestInitializer for Google Drive API.
     * @return Drive service instance.
     */
    @Bean
    Drive driveService(HttpRequestInitializer requestInitializer) {
        return new Drive.Builder(new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                requestInitializer)
                .setApplicationName("Google Drive MCP Server")
                .build();
    }
}
