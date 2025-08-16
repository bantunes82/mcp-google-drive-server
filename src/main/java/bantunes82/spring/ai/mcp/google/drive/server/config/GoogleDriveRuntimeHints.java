package bantunes82.spring.ai.mcp.google.drive.server.config;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;

public class GoogleDriveRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        // Register hints for Google Drive API classes
        hints.reflection().registerType(File.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INVOKE_PUBLIC_METHODS,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.PUBLIC_FIELDS);

        hints.reflection().registerType(FileList.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INVOKE_PUBLIC_METHODS,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.PUBLIC_FIELDS);

        hints.reflection().registerType(GenericJson.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INVOKE_PUBLIC_METHODS,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.PUBLIC_FIELDS);

        // Register hints for Data class
        hints.reflection().registerType(Data.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.DECLARED_FIELDS);
        hints.serialization().registerType(TypeReference.of(Data.class));

        // Register hints for GoogleJsonError
        hints.reflection().registerType(GoogleJsonError.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.DECLARED_FIELDS);
        hints.serialization().registerType(TypeReference.of(GoogleJsonError.class));

        // Register hints for GoogleJsonError.ErrorInfo
        hints.reflection().registerType(GoogleJsonError.ErrorInfo.class,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.DECLARED_FIELDS);
        hints.serialization().registerType(TypeReference.of(GoogleJsonError.ErrorInfo.class));
    }
}