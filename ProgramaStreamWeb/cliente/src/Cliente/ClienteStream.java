package Cliente;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

public class ClienteStream {

	private final static String server_url = "http://192.168.43.134:8000/";
	
	//"ls -af /home/edwin/eclipse-workspace/Quickstart && gradle run";


	public static void reproducir() throws IOException {

		String[] datos = {"vlc", "-vvv", "rtsp://192.168.43.134:8554/JQ'"};
		Process ejecucion = Runtime.getRuntime().exec(datos);
		String linea;
		BufferedReader salida = new BufferedReader(new InputStreamReader(ejecucion.getInputStream()));
		while((linea = salida.readLine()) != null) {
			System.out.println(linea);
		}
		salida.close();
	}

	public static void enviar(String nombre) {
		try {
			XmlRpcClient server=new XmlRpcClient(server_url);
			Vector parametros = new Vector();
			parametros.addElement(new String(nombre) );

			String resultado = (String) server.execute("ejecutar.buscarPelicula", parametros);

			System.out.println("Resultado de la  Resta: "+resultado);
			JOptionPane.showMessageDialog(null, resultado);


		} catch (XmlRpcException exception) {
			System.err.println("JavaClient: XML-RPC Fault #" + Integer.toString(exception.code) + ": " + exception.toString());
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception.toString());
		}
	}

	public static String rellenar(int envio) {
		try {		
			XmlRpcClient server=new XmlRpcClient(server_url);
			Vector parametros = new Vector();

			parametros.addElement(new Integer(0) );
			String resultado = (String) server.execute("ejecutar.enviarPeliculas", parametros);


			return resultado;

		} catch (XmlRpcException exception) {
			System.err.println("JavaClient: XML-RPC Fault #" + Integer.toString(exception.code) + ": " + exception.toString());
			return null;
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception.toString());
			return null;
		}
	}

	public static int enviarVideo(String video) {

		try {		
			XmlRpcClient server=new XmlRpcClient(server_url);
			Vector parametros = new Vector();

			parametros.addElement(new String (video) );
			int resultado = (Integer) server.execute("ejecutar.ejecutarVLC", parametros);

			System.out.println(resultado);
			if(resultado == 0) {
				System.out.println("Si se ejecuta");
				
			}

			return resultado;

		} catch (XmlRpcException exception) {
			System.err.println("JavaClient: XML-RPC Fault #" + Integer.toString(exception.code) + ": " + exception.toString());
			return 0;
		} catch (Exception exception) {
			System.err.println("JavaClient: " + exception.toString());
			return 0;
		}
	}
	
	public static String descargarVideo(String nombrePelicula) {
		
		String resultado="";
		try {
			XmlRpcClient server=new XmlRpcClient(server_url);
			Vector parametros = new Vector();

			parametros.addElement(new String (nombrePelicula));
			resultado = (String) server.execute("ejecutar.obtenerIdVideo", parametros);
			System.out.print(resultado);
			FileWriter fichero = new FileWriter("/home/edwin/eclipse-workspace/Compu/ids/idVideo.txt");
			PrintWriter pw = new PrintWriter(fichero);
			System.out.println("Escribiendo en el archivo.txt");
			pw.println(resultado);
			System.out.print("Se escribio en fichero: "+fichero);
			System.out.print(resultado);
			fichero.close();
		
			
		}catch(Exception e) {
			
		}
		return resultado;
		
	}
	
	public static void ejecutarDescarga() throws IOException {
		

		String[] datos = {"gradle", "run"};
		Process ejecucion = Runtime.getRuntime().exec(datos);
		String linea;
		BufferedReader salida = new BufferedReader(new InputStreamReader(ejecucion.getInputStream()));
		while((linea = salida.readLine()) != null) {
			System.out.println(linea);
		}
		salida.close();
		System.out.print("terminadooooooo");
	}
	

}