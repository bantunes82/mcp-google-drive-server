package bantunes82.spring.ai.mcp.google.drive.server.services;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoogleDriveServiceTests {

    private static final String FOLDER_NAME = "Test Folder";
    private static final String FILE_NAME = "Test File";
    private static final String FILE_CONTENT = "This is a test file.";
    private static final String FOLDER_ID = "test-folder-id";
    private static final String FILE_ID = "test-file-id";

    @Mock
    private Drive driveService;

    @Mock
    private Drive.Files files;

    @Mock
    private Drive.Files.List listRequest;

    @Mock
    private Drive.Files.Create createRequest;

    private GoogleDriveService googleDriveService;

    @BeforeEach
    void setUp() throws IOException {
        when(driveService.files()).thenReturn(files);
        googleDriveService = new GoogleDriveService(driveService);
    }

    @Test
    void uploadMicrosoftWordFile_whenFolderExists_shouldUploadFileToFolder() throws IOException {
        // Arrange
        var fileList = new FileList().setFiles(List.of(new File().setId(FOLDER_ID)));
        when(files.list()).thenReturn(listRequest);
        when(listRequest.setQ("name='" + FOLDER_NAME + "' and mimeType='application/vnd.google-apps.folder'")).thenReturn(listRequest);
        when(listRequest.setFields("files(id, name)")).thenReturn(listRequest);
        when(listRequest.execute()).thenReturn(fileList);

        var uploadedFile = new File().setId(FILE_ID);
        when(files.create(any(File.class), any())).thenReturn(createRequest);
        when(createRequest.setFields("id")).thenReturn(createRequest);
        when(createRequest.execute()).thenReturn(uploadedFile);

        // Act
        var result = googleDriveService.uploadMicrosoftWordFile(FOLDER_NAME, FILE_NAME, FILE_CONTENT);

        // Assert
        assertEquals("https://docs.google.com/document/d/test-file-id/view", result);

        var fileArgumentCaptor = ArgumentCaptor.forClass(File.class);
        verify(files).create(fileArgumentCaptor.capture(), any());
        assertEquals(FILE_NAME, fileArgumentCaptor.getValue().getName());
        assertEquals(List.of(FOLDER_ID), fileArgumentCaptor.getValue().getParents());
    }

    @Test
    void uploadMicrosoftWordFile_whenFolderDoesNotExist_shouldUploadFileToRoot() throws IOException {
        // Arrange
        var fileList = new FileList().setFiles(List.of());
        when(files.list()).thenReturn(listRequest);
        when(listRequest.setQ("name='" + FOLDER_NAME + "' and mimeType='application/vnd.google-apps.folder'")).thenReturn(listRequest);
        when(listRequest.setFields("files(id, name)")).thenReturn(listRequest);
        when(listRequest.execute()).thenReturn(fileList);

        var uploadedFile = new File().setId(FILE_ID);
        when(files.create(any(File.class), any())).thenReturn(createRequest);
        when(createRequest.setFields("id")).thenReturn(createRequest);
        when(createRequest.execute()).thenReturn(uploadedFile);

        // Act
        var result = googleDriveService.uploadMicrosoftWordFile(FOLDER_NAME, FILE_NAME, FILE_CONTENT);

        // Assert
        assertEquals("https://docs.google.com/document/d/test-file-id/view", result);

        var fileArgumentCaptor = ArgumentCaptor.forClass(File.class);
        verify(files).create(fileArgumentCaptor.capture(), any());
        assertEquals(FILE_NAME, fileArgumentCaptor.getValue().getName());
        assertEquals(null, fileArgumentCaptor.getValue().getParents());
    }

    @Test
    void uploadMicrosoftWordFile_whenFolderNameIsNull_shouldUploadFileToRoot() throws IOException {
        // Arrange
        var uploadedFile = new File().setId(FILE_ID);
        when(files.create(any(File.class), any())).thenReturn(createRequest);
        when(createRequest.setFields("id")).thenReturn(createRequest);
        when(createRequest.execute()).thenReturn(uploadedFile);

        // Act
        var result = googleDriveService.uploadMicrosoftWordFile(null, FILE_NAME, FILE_CONTENT);

        // Assert
        assertEquals("https://docs.google.com/document/d/test-file-id/view", result);

        var fileArgumentCaptor = ArgumentCaptor.forClass(File.class);
        verify(files).create(fileArgumentCaptor.capture(), any());
        assertEquals(FILE_NAME, fileArgumentCaptor.getValue().getName());
        assertEquals(null, fileArgumentCaptor.getValue().getParents());
}

    @Test
    void uploadMicrosoftWordFile_whenThrowsException_shouldThrowException() throws IOException {
        // Arrange
        var fileList = new FileList().setFiles(List.of(new File().setId(FOLDER_ID)));
        when(files.list()).thenReturn(listRequest);
        when(listRequest.setQ("name='" + FOLDER_NAME + "' and mimeType='application/vnd.google-apps.folder'")).thenReturn(listRequest);
        when(listRequest.setFields("files(id, name)")).thenReturn(listRequest);
        when(listRequest.execute()).thenReturn(fileList);

        when(files.create(any(File.class), any())).thenThrow(new IOException("Test Exception"));

        // Act & Assert
        assertThrows(IOException.class, () -> googleDriveService.uploadMicrosoftWordFile(FOLDER_NAME, FILE_NAME, FILE_CONTENT));
    }
}