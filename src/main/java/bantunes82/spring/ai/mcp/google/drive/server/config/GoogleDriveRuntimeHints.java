package bantunes82.spring.ai.mcp.google.drive.server.config;

import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.util.Data;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

public class GoogleDriveRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        try {
            // Register reflection and serialization hints for the entire package
            String packageName = "com.google.api.services.drive.model";
            String packagePath = packageName.replace('.', '/');
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(classLoader);
            Resource[] resources = resolver.getResources("classpath*" + packagePath + "/*.class");

            for (Resource resource : resources) {
                String className = packageName + "." + resource.getFilename().replace(".class", "");
                Class<?> clazz = Class.forName(className);
                hints.reflection().registerType(clazz,
                        MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                        MemberCategory.INVOKE_DECLARED_METHODS,
                        MemberCategory.DECLARED_FIELDS);
                hints.serialization().registerType(TypeReference.of(clazz));
            }

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

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to register runtime hints for Google Drive API", e);
        }
    }
}