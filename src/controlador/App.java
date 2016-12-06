package controlador;
import vista.InterGraf;
import vista.CrearAbonado;
import modelo.Conexion;
import modelo.Datos;
import java.net.*;
import java.io.*;
public class App {
	InterGraf interfaz;
	public CrearAbonado abonado;
	Conexion con;
	public App(){
		//mostrar interfaz
		interfaz = new InterGraf(this);
		con = new Conexion();
		Lista objLista = new Lista();
		try{
			ServerSocket ss = new ServerSocket(1996);
			while(true){
				Socket clienteN = ss.accept();
				Proceso sub = new Proceso(clienteN);
				sub.start();
				objLista.anadir(sub);
				System.out.println("cliente conectado");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void main(String[] args){
		App aplicacion = new App();
	}
	public void guardarAbonado(String nombre, String direccion, String telefono){
		String sent = "insert into cliente (nombre,telefono,direccion) values(\"%n%\",%t%,\"%d%\")";
		sent = sent.replace("%n%", nombre);
		sent = sent.replace("%d%", direccion);
		sent = sent.replace("%t%", telefono);
		con.consultaDML(sent);
	}
	public void crearAbonado(){
		abonado = new CrearAbonado(this);
	}
	public class Nodo{
		Nodo sig = null;
		Proceso objPro = null;
	} // Cierra class Nodo


	public class Lista{
		Nodo cabeza = null;
		Nodo ultimo = null;
		Nodo correr = null;

		public void anadir(Proceso objProceso){
			Nodo nuevo = new Nodo();
			nuevo.objPro = objProceso;
			if(cabeza==null){
				cabeza = nuevo;
				ultimo = nuevo;
			}else{
				ultimo.sig = nuevo;
				ultimo = nuevo;
			}
		} // Cierra anadir
	} // Cierra Lista


	public class Proceso extends Thread{
		Socket ref;
		public Proceso(Socket re){
			try{
				ref = re;
			}catch(Exception e){
			}
		}
		public void run(){
			try{
				InputStream entrada = ref.getInputStream();
				DataInputStream entra = new DataInputStream(entrada);
				while(true){
					Thread.sleep(100);
					String leido = entra.readUTF();
					System.out.println(leido);
					interfaz.mensaje(leido);
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
	} // Cierra class Proceso
}
