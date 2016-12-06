package vista;
import controlador.App;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterGraf extends JFrame{
	static final int ancho = 400;
	static final int largo = 300;
	App aplicacion;
	//paneles separadores
	JPanel sec1;
	//objetos
	JLabel titulo;
	JButton crearCliente;
	JTextArea texto;
	JScrollPane scrollTexto;
	public InterGraf(App apl){
		aplicacion = apl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ancho, largo);
		setLayout(null);
		declaracion();
		add(sec1);
		setVisible(true);
	}
	public void mensaje(String msj){
		texto.append(msj+"\n");
	}
	private void declaracion(){
		titulo = new JLabel("Parqueadero caja.");
		crearCliente = new JButton("Crear cliente");
		texto = new JTextArea(15,34);
		texto.setEnabled(false);
		texto.setDisabledTextColor(Color.black);
		scrollTexto = new JScrollPane(texto);
		sec1 = new JPanel();
		//eventos
		crearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.crearAbonado();
			}
		});
		//configuracion panel
		sec1.setBounds(0, 0, ancho, largo);
		sec1.setLayout(new FlowLayout(FlowLayout.LEADING));
		//ingreso de objetos del panel
		sec1.add(titulo);
		sec1.add(crearCliente);
		sec1.add(scrollTexto);
	}
}
