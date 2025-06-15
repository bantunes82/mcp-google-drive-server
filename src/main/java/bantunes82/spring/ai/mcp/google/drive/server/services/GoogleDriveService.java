package bantunes82.spring.ai.mcp.google.drive.server.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class GoogleDriveService {

    private Drive service;

    private Logger logger = LoggerFactory.getLogger(GoogleDriveService.class);

    public GoogleDriveService(Drive service) {
        this.service = service;
    }

    /**
     * Upload new file.
     *
     * @return Inserted file metadata if successful, {@code null} otherwise.
     * @throws IOException if service account credentials file not found.
     */
    @Tool(description = "Uploads a file content in Microsoft Word format to Google Drive in a specified folder.")
    public String uploadMicrosoftWordFile(@ToolParam(description = "Folder name to be uploaded in Google Drive", required = false) String folderName,
                              @ToolParam(description = "File name to be uploaded in Google Drive") String fileName,
                              @ToolParam(description = "File Content to be uploaded in Google Drive") String fileContent) throws IOException {
        Optional<String> folderId = getFolderIdByName(folderName); // Retrieve folder ID by name

        var fileMetadata = new File();
        fileMetadata.setName(fileName);

        folderId.ifPresent(id-> fileMetadata.setParents(List.of(id)));

        var mediaContent = new ByteArrayContent("application/msword", fileContent.getBytes(StandardCharsets.UTF_8));
        try {
            File file = service.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();
            logger.info("File ID: {}", file.getId());
            return file.getId();
        } catch (GoogleJsonResponseException e) {
            // TODO(developer) - handle error appropriately
            logger.error("Unable to upload file: {}", e.getDetails());
            throw e;
        }
    }

    private Optional<String> getFolderIdByName(String folderName) throws IOException {
        try {
            var result = service.files().list()
                    .setQ("name='" + folderName + "' and mimeType='application/vnd.google-apps.folder'")
                    .setFields("files(id, name)")
                    .execute();
            var files = result.getFiles();
            if (files != null && !files.isEmpty()) {
                return Optional.ofNullable(files.get(0).getId()); // Return the first matching folder ID
            } else {
                logger.error("Folder with name '{}' not found.", folderName);
                return Optional.empty();
            }
        } catch (GoogleJsonResponseException e) {
            logger.error("Unable to retrieve folder ID: {}", e.getDetails());
            throw e;
        }
    }


}

