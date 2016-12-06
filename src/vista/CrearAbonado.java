package vista;
import controlador.App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class CrearAbonado extends JFrame{
	static final int ancho = 300;
	static final int largo = 300;
	App aplicacion;
	//paneles separadores
	JPanel sec1;
	JPanel sec2;
	//objetos
	JLabel titulo;
	JLabel nombrelab;
	JLabel direccionLab;
	JLabel telefonoLab;
	JTextField nombre;
	JTextField telefono;
	JTextField direccion;
	JButton guardar;

	public CrearAbonado(App apl){
		aplicacion = apl;
		setSize(ancho, largo);
		setLayout(null);
		declaracion();
		add(sec1);
		add(sec2);
		setVisible(true);
	}
	private void declaracion(){
		titulo = new JLabel("Crear cliente: ");
		nombrelab = new JLabel("Nombre: ");
		direccionLab = new JLabel("Direccion: ");
		telefonoLab = new JLabel("Telefono: ");
		nombre = new JTextField(10);
		telefono = new JTextField(10);
		direccion = new JTextField(10);
		guardar = new JButton("Guardar");
		sec1 = new JPanel();
		sec2 = new JPanel();
		//configuracion panel
		sec1.setBounds(0, 0, ancho, 20);
		sec1.setLayout(new FlowLayout(FlowLayout.LEADING));
		sec2.setBounds(0, 20, 200, largo-20);
		sec2.setLayout(new FlowLayout(FlowLayout.LEADING));
		//eventos
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom_text, direc_text, tel_text;
				nom_text = nombre.getText();
				direc_text = direccion.getText();
				tel_text = telefono.getText();
				if(!(nom_text.length() == 0 || 
					tel_text.length() == 0 ||
					direc_text.length() == 0)){
					aplicacion.guardarAbonado(nom_text,direc_text,tel_text);
					nombre.setText("");
					direccion.setText("");
					telefono.setText("");
				}else{
					JOptionPane.showMessageDialog(aplicacion.abonado, "ERROR: capo vacio");
				}
			}
		});
		//ingreso de objetos del panel
		sec1.add(titulo);
		sec2.add(nombrelab);
		sec2.add(nombre);
		sec2.add(direccionLab);
		sec2.add(direccion);
		sec2.add(telefonoLab);
		sec2.add(telefono);
		sec2.add(guardar);
	}
}
