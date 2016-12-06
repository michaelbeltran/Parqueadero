package controlador;
import vista.InterfazEntrada;
import modelo.Conexion;
import modelo.Datos;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Entrada {
	InterfazEntrada interfaz;
	Conexion con;
	Socket s;
	OutputStream salida;
	DataOutputStream sale;
	String lisAbonados = "select id_cliente from cliente where estado = 0;",
		lisAbonadosInac = "select id_cliente from cliente where estado = 1;",
		ticketLista = "select id_ticket from ticket where estado = 1 and pago = 1;",
		ticketListaNPagos = "select id_ticket from ticket where estado = 1 and pago = 0;";
	public Entrada(){
		//mostrar interfaz
		con = new Conexion();
		interfaz = new InterfazEntrada(this, con.consultaLista(lisAbonados),
			con.consultaLista(lisAbonadosInac),
			con.consultaLista(ticketLista),
			con.consultaLista(ticketListaNPagos));
		try{
			s = new Socket("localhost", 1996);
			salida = s.getOutputStream();
			sale = new DataOutputStream(salida);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public void expedirTicket(){
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String fecha = dateFormat.format(date);
			con.consultaDML("insert into ticket (estado,fecha,fecha_salida,pago) values(1,\""+fecha+"\", \"0000-00-00 00:00:00\",0);");
			String id = con.consultaLista("select MAX(id_ticket) from ticket;")[0];
			sale.writeUTF(Datos.ticket+" numero: "+id+" fecha: "+fecha);
			interfaz.actualizarTicket(con.consultaLista(ticketLista));
			interfaz.actualizarTicketPago(con.consultaLista(ticketListaNPagos));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void pagarTicket(String id){
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String fecha = dateFormat.format(date);
			con.consultaDML("update ticket set pago = 1, fecha_salida = \""+fecha+"\" where id_ticket = "+id);
			sale.writeUTF(Datos.ticket+" pagado numero: "+id+" fecha salida: "+fecha);
			interfaz.actualizarTicket(con.consultaLista(ticketLista));
			interfaz.actualizarTicketPago(con.consultaLista(ticketListaNPagos));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void verificarTicket(String id){
		try {
			con.consultaDML("update ticket set estado = 0 where id_ticket = "+id);
			sale.writeUTF(Datos.ticket+" numero: "+id);
			interfaz.actualizarTicket(con.consultaLista(ticketLista));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void pasarTarjeta(String numTarjeta){
		try {
			con.consultaDML("update cliente set estado = 1 where id_cliente = "+numTarjeta);
			sale.writeUTF(Datos.tarjeta+": "+numTarjeta);
			interfaz.actualizarTargetaEntrada(con.consultaLista(lisAbonados));
			interfaz.actualizarTargetaSalida(con.consultaLista(lisAbonadosInac));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void pasarTarjetaSalida(String numTarjeta){
		try {
			con.consultaDML("update cliente set estado = 0 where id_cliente = "+numTarjeta);
			sale.writeUTF(Datos.tarjeta+": "+numTarjeta);
			interfaz.actualizarTargetaEntrada(con.consultaLista(lisAbonados));
			interfaz.actualizarTargetaSalida(con.consultaLista(lisAbonadosInac));
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args){
		Entrada aplicacion = new Entrada();
	}
}
