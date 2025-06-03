package bantunes82.mcp.server.google.driver.services;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GoogleDriverService {

    private Drive service;

    private Logger logger = LoggerFactory.getLogger(GoogleDriverService.class);

    public GoogleDriverService(Drive service) {
        this.service = service;
    }

    /**
     * Upload new file.
     *
     * @return Inserted file metadata if successful, {@code null} otherwise.
     * @throws IOException if service account credentials file not found.
     */
    public String uploadBasic(String folderName, String fileName, String fileContentType, java.io.File filePath) throws IOException {
        String folderId = getFolderIdByName(folderName); // Retrieve folder ID by name

        // Upload the file on drive.
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
        if (folderId != null) {
            fileMetadata.setParents(List.of(folderId)); // Set the folder ID
        }

        // Specify media type and file-path for file.
        FileContent mediaContent = new FileContent(fileContentType, filePath);
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

    private String getFolderIdByName(String folderName) throws IOException {
        try {
            var result = service.files().list()
                    .setQ("name='" + folderName + "' and mimeType='application/vnd.google-apps.folder'")
                    .setFields("files(id, name)")
                    .execute();
            var files = result.getFiles();
            if (files != null && !files.isEmpty()) {
                return files.get(0).getId(); // Return the first matching folder ID
            } else {
                logger.error("Folder with name '{}' not found.", folderName);
                return null;
            }
        } catch (GoogleJsonResponseException e) {
            logger.error("Unable to retrieve folder ID: {}", e.getDetails());
            throw e;
        }
    }


}

