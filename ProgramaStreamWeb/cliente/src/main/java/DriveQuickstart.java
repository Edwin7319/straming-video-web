import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.client.http.FileContent;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


public class DriveQuickstart {

	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart.
	 * If modifying these scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
	//private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS,SheetsScopes.DRIVE);
	private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

	/**
	 * Creates an authorized Credential object.
	 * @param HTTP_TRANSPORT The network HTTP Transport.
	 * @return An authorized Credential object.
	 * @throws IOException If the credentials.json file cannot be found.
	 */
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = DriveQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	public static void main(String... args) throws IOException, GeneralSecurityException {
		// Build a new authorized API client service.

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();


		/*
		///CREAR CARPETA "VIDEOS"
		File fileMetadata = new File();
		fileMetadata.setName("Videos");
		fileMetadata.setMimeType("application/vnd.google-apps.folder");

		File file = service.files().create(fileMetadata).setFields("id").execute();
		System.out.println("ID de Carpeta: " + file.getId());


		//SUBIR A CARPETA "VIDEOS"	


			FileWriter fichero = new FileWriter("ids/Los Vengadores.txt");
			PrintWriter pw = new PrintWriter(fichero);
			System.out.println("Escribiendo en el archivo.txt");

			String carpetaId = "1qsBV99XWxGoqw0ijtIQLApmMjcvFZbxO";
			File fileMetadata = new File();
			fileMetadata.setName("Los Vengadores.mp4");
			fileMetadata.setParents(Collections.singletonList(carpetaId));
			java.io.File direccionVideo = new java.io.File("videos/Los Vengadores.mp4");
			FileContent tipoContenido = new FileContent("video/mp4", direccionVideo);
			File video = service.files().create(fileMetadata, tipoContenido).setFields("id, parents").execute();

			pw.println(video.getId());
			System.out.println("ID de Carpeta: " + video.getId());

			fichero.close();
*/
		 


		//DESCARGAR VIDEO
		String cadena;
		String fileId="";
		FileReader f = new FileReader("ids/idVideo.txt");
		BufferedReader b = new BufferedReader(f);
		while((cadena = b.readLine())!=null) {
			fileId = cadena;
		}
		b.close();

		System.out.println("--- --- --- Descargando video --- --- ---");
		FileOutputStream output = null;
		try {
			//String fileId = "14dhziH-CFdNXzpJqUPSciRjxU4m7dMV5";
			java.io.File targetFile = new java.io.File("/home/edwin/Desktop/Video/Los Vengadores.mp4");
			output = new FileOutputStream(targetFile);
			service.files().get(fileId).executeMediaAndDownloadTo(output);
			System.out.printf("%s Video fue descargado en la ruta.\n", targetFile);

		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println();


	}
}
