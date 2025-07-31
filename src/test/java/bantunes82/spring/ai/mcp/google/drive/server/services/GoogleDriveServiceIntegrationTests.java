package bantunes82.spring.ai.mcp.google.drive.server.services;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class GoogleDriveServiceIntegrationTests {

    @Autowired
    private GoogleDriveService googleDriveService;

    @MockitoBean
    private Drive drive;

    @Test
    void uploadMicrosoftWordFile() throws IOException {
        Drive.Files files = Mockito.mock(Drive.Files.class);
        Drive.Files.Create create = Mockito.mock(Drive.Files.Create.class);
        Drive.Files.List list = Mockito.mock(Drive.Files.List.class);

        File fileToReturn = new File();
        fileToReturn.setId("12345");

        File folder = new File();
        folder.setId("folder123");

        FileList fileList = new FileList();
        fileList.setFiles(List.of(folder));

        when(drive.files()).thenReturn(files);

        when(files.list()).thenReturn(list);
        when(list.setQ("name='test-folder' and mimeType='application/vnd.google-apps.folder'")).thenReturn(list);
        when(list.setFields("files(id, name)")).thenReturn(list);
        when(list.execute()).thenReturn(fileList);

        when(files.create(any(File.class), any(ByteArrayContent.class))).thenReturn(create);
        when(create.setFields("id")).thenReturn(create);
        when(create.execute()).thenReturn(fileToReturn);

        String url = googleDriveService.uploadMicrosoftWordFile("test-folder", "test-file.doc", "some content");

        assertEquals("https://docs.google.com/document/d/12345/view", url);
    }
}
