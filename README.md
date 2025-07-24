# MCP Google Drive Server

This project is a server for the Model Context Protocol (MCP) that integrates with Google Drive,
you can use it to upload Microsoft Word files to Google Drive, allowing for easy file management and sharing.
Connect to this server using the Model Context Protocol client, for instance, in Claude Desktop.

## Components

### Tools
- **upload_microsoft_world_file**:
  - Uploads a file content in Microsoft Word format to Google Drive in a specified folder.
  - Input parameters:
    - `folderName`: Folder name to be uploaded in Google Drive, if the folder doesn't exist it will be saved in the home folder. (optional)
    - `fileName`: File name to be uploaded in Google Drive.
    - `fileContent`: File Content to be uploaded in Google Drive.
  - Returns:
    - `fileId`: The ID of the uploaded file in Google Drive.

## What You Can Do
Once set up, you can ask Claude Desktop things like:
- *"What are the 10 famous touristic place of Sao Paulo, please save the result in my google drive"*
---

## Getting Started
### Prerequisites
- **Claude Desktop** ([Download here](https://claude.ai/download))
- **Generate your API Credentials**
  1. [Create a new Google Cloud project](https://console.cloud.google.com/projectcreate)
  2. [Enable the Google Drive API](https://console.cloud.google.com/apis/library/drive.googleapis.com)
  3. [Enable the Cloud Resource Manager API](https://console.cloud.google.com/apis/library/cloudresourcemanager.googleapis.com)
  4. [Set up ADC for a local development environment, inside Cloud Shell](https://cloud.google.com/docs/authentication/set-up-adc-local-dev-environment#google-idp)
     - Open Cloud Shell in your Google Cloud Console.
     - Run the following command: `cloud auth application-default login --scopes=https://www.googleapis.com/auth/cloud-platform,https://www.googleapis.com/auth/drive`
     - This will open a browser window to authenticate your Google account and grant the necessary permissions.
     - After successful authentication, the credentials will be stored in your local environment, allowing the application to access Google Cloud resources.
     - Look for the credential file that was created, it should be something like: `Credentials saved to file: [/tmp/tmp.6Rvsdsdfesx/application_default_credentials.json]`
     - Copy the contents of the credential file to a new file named `application_default_credentials.json` in the home directory of your local machine. (e.g. `~/application_default_credentials.json`)
  --
### 🎯 Choose Your Setup Method

| Method                                                               | Time | Requirements | Best For |
|----------------------------------------------------------------------|------|--------------|----------|
| **[📦 Native Binary - (not available yet)](#-native-binary-no-java)** | **min** | None! | **Most users** |
| [☕ Java Build](#-java-build-traditional)                             | 5 min | Java 24+ | Developers |
---

### ☕ Java Build (Traditional)

**For developers who want to build from source**

### Prerequisites
- **Java 24+** ([Download here](https://adoptium.net/))
- **Maven** (included via `./mvnw`)

### Steps
#### 1. Clone the repository and generate the jar file:
```bash
git clone <this-repo>
cd mcp-google-drive-server
./mvnw clean package -DskipTests
```

#### 2. Then configure Claude Desktop with:
- to configure the 'mcp-google-drive-server' to run in [SSE](https://modelcontextprotocol.io/docs/concepts/transports#server-sent-events-sse-deprecated) mode.
```json
{
  "mcpServers": {
    "mcp-google-drive-server": {
      "command": "~/.sdkman/candidates/java/24-open/bin/java",
      "args": [
        "-jar", 
        "/FULL/PATH/TO/target/mcp-google-drive-server-0.0.1-SNAPSHOT.jar"
      ],
      "env": {
        "GOOGLE_APPLICATION_CREDENTIALS": "~/application_default_credentials.json"
      }
    }
  }
}
```
- Or configure the 'mcp-google-drive-server' to run in [STDIO](https://modelcontextprotocol.io/docs/concepts/transports#standard-input%2Foutput-stdio) mode.
```json
{
  "mcpServers": {
    "mcp-google-drive-server": {
      "command": "~/.sdkman/candidates/java/24-open/bin/java",
      "args": [
        "-jar",
        "-Dspring.ai.mcp.server.stdio=true",
        "/FULL/PATH/TO/target/mcp-google-drive-server-0.0.1-SNAPSHOT.jar"
      ],
      "env": {
        "GOOGLE_APPLICATION_CREDENTIALS": "~/application_default_credentials.json"
      }
    }
  }
}
```
⚠️ **Use the full path to your generated jar file.**


#### 3. Test It Works
1. Restart Claude Desktop
2. Look for the 🔧 icon in a new conversation
3. Try: *"What are the 10 famous touristic place of Sao Paulo, please save the result in my google drive"*

✅ **Success**: You should see Claude use the `upload_microsoft_world_file` tool!


---
### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.0/maven-plugin/build-image.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.5.0/reference/web/reactive.html)
* [Model Context Protocol Server](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-server-boot-starter-docs.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

