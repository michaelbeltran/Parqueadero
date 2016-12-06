package modelo;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import java.sql.*;

public class Conexion {
	private Connection con;
	private Statement sta;
	private ResultSet rs;
	private ResultSetMetaData md;
	private String[] nomColum;
	private int columnas;

	public Conexion(){
		String baseDatosNombre = "parqueadero";
		String usuario = "root";
		String clave = "123456";
		String ruta = "jdbc:mysql://localhost:3306/"+baseDatosNombre;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			System.out.println("Exito en la conexion :)");
			con = DriverManager.getConnection(ruta, usuario, clave);
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	public String[] nombresColumnas(){
		nomColum = new String[columnas];
		try{
			for(int k=0;k<columnas;k++){
				nomColum[k] = md.getColumnName(k+1);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return nomColum;
	}
	public void consultaDML(String sent){
		try{
			sta = con.createStatement();
			sta.executeUpdate(sent);
		}catch(SQLException e){System.out.println(e);}
	}
	public String[][] consulta(String sent){
		String[][] salida={};
		int i=0, numFilas=0;
		try{
			sta = con.createStatement();
			rs = sta.executeQuery(sent);
			md = rs.getMetaData();
			columnas = md.getColumnCount();
			if(rs.last()){
				numFilas = rs.getRow();
				rs.beforeFirst();
			}
			salida = new String[numFilas][columnas];
			while(rs.next()){
				for(int j=0; j < columnas; j++)
					salida[i][j] = rs.getString(j+1);
				i++;
			}
		}catch(SQLException e){System.out.println(e);}
		return salida;
	}
	public String[] consultaLista(String sentencia){
		return convLista(consulta(sentencia));
	}
	public String[] convLista(String[][] datos){
		String[] lista = new String[datos.length];
		for(int i = 0; i < datos.length; i++)
			lista[i] = datos[i][0];
		return lista;
	}
	public Connection getConexion(){
		return con;
	}
	public void setConexion(Connection conexion){
		this.con = conexion;
	}
}
