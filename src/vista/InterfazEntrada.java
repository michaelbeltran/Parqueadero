package vista;
import controlador.Entrada;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InterfazEntrada extends JFrame{
	static final int ancho = 500;
	static final int largo = 200;
	String abonadosLista[],
	abonadosListaSalida[],
	ticketLista[],
	ticketPagos[];
	Entrada aplicacion;
	//paneles separadores
	JPanel sec1;
	JPanel sec2;
	JPanel sec3;
	JPanel sec4;
	JPanel sec5;
	JPanel sup1;
	JPanel sup2;
	//objetos
	JLabel titulo;
	JButton expTicket;
	JLabel titulo2;
	JComboBox tarjeta;
	JLabel titulo3;
	JComboBox IngresarTicket;
	JLabel titulo4;
	JComboBox Ingresartarjeta;
	JLabel entrada;
	JLabel salida;
	JLabel pago;
	JComboBox ticketPago;
	public InterfazEntrada(Entrada apl, String []en, String []sa, String []tic, String []pag){
		aplicacion = apl;
		setAbonados(en);
		setAbonadosSalida(sa);
		setTickets(tic);
		setTicketsPagos(pag);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ancho, largo);
		setLayout(null);
		declaracion();
		add(sup1);
		add(sup2);
		add(sec1);
		add(sec2);
		add(sec3);
		add(sec4);
		add(sec5);
		setVisible(true);
	}
	public void setTicketsPagos(String []a){
		if( a.length != 0)
			ticketPagos = a;
		else{
			ticketPagos = new String[1];
			ticketPagos[0] = "Vacio";
		}
		
	}
	public void setAbonados(String []a){
		if( a.length != 0)
			abonadosLista = a;
		else{
			abonadosLista = new String[1];
			abonadosLista[0] = "Vacio";
		}
		
	}
	public void setAbonadosSalida(String []a){
		if( a.length != 0)
			abonadosListaSalida = a;
		else{
			abonadosListaSalida = new String[1];
			abonadosListaSalida[0] = "Vacio";
		}
		
	}
	public void setTickets(String []a){
		if( a.length != 0)
			ticketLista = a;
		else{
			ticketLista = new String[1];
			ticketLista[0] = "Vacio";
		}
		
	}

	public void actualizarTargetaEntrada(String a[]){
		setAbonados(a);
		tarjeta.setModel(new DefaultComboBoxModel((Object[]) abonadosLista));
	}
	public void actualizarTargetaSalida(String a[]){
		setAbonadosSalida(a);
		Ingresartarjeta.setModel(new DefaultComboBoxModel((Object[]) abonadosListaSalida));
	}
	public void actualizarTicket(String a[]){
		setTickets(a);
		IngresarTicket.setModel(new DefaultComboBoxModel((Object[]) ticketLista));
	}
	public void actualizarTicketPago(String a[]){
		setTicketsPagos(a);
		ticketPago.setModel(new DefaultComboBoxModel((Object[]) ticketPagos));
	}

	private void declaracion(){
		titulo = new JLabel("Ticket");
		expTicket = new JButton("Expedir ticket");
		titulo2 = new JLabel("Tarjeta");
		tarjeta = new JComboBox();
		tarjeta.setModel(new DefaultComboBoxModel((Object[]) abonadosLista));
		titulo3 = new JLabel("Ticket NO:");
		IngresarTicket = new JComboBox();
		IngresarTicket.setModel(new DefaultComboBoxModel((Object[]) ticketLista));
		titulo4 = new JLabel("Tarjeta NO:");
		Ingresartarjeta = new JComboBox();
		Ingresartarjeta.setModel(new DefaultComboBoxModel((Object[]) abonadosListaSalida));
		entrada = new JLabel("Entrada");
		salida = new JLabel("Salida");
		pago = new JLabel("Pagar tickets");
		ticketPago = new JComboBox();
		ticketPago.setModel(new DefaultComboBoxModel((Object[]) ticketPagos));
		
		sec1 = new JPanel();
		sec2 = new JPanel();
		sec3 = new JPanel();
		sec4 = new JPanel();
		sec5 = new JPanel();
		sup1 = new JPanel();
		sup2 = new JPanel();
		//configuracion panel
		sup1.setBounds(0, 0, 200, 25);
		sup1.setLayout(new FlowLayout(FlowLayout.LEADING));
		sup2.setBounds(200, 0, 300, 25);
		sup2.setLayout(new FlowLayout(FlowLayout.LEADING));

		sec1.setBounds(0, 25, 200, 50);
		sec1.setLayout(new FlowLayout(FlowLayout.LEADING));
		sec2.setBounds(0, 75, 200, 50);
		sec2.setLayout(new FlowLayout(FlowLayout.LEADING));
		sec3.setBounds(200, 25, 300, 50);
		sec3.setLayout(new FlowLayout(FlowLayout.LEADING));
		sec4.setBounds(200, 75, 300, 50);
		sec4.setLayout(new FlowLayout(FlowLayout.LEADING));
		sec5.setBounds(100, 140, 200, 50);
		sec5.setLayout(new FlowLayout(FlowLayout.LEADING));
		//eventos
		expTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.expedirTicket();
			}
		});
		tarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.pasarTarjeta((String)tarjeta.getSelectedItem());
			}
		});
		IngresarTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.verificarTicket((String)IngresarTicket.getSelectedItem());
			}
		});
		Ingresartarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.pasarTarjetaSalida((String)Ingresartarjeta.getSelectedItem());
			}
		});
		ticketPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aplicacion.pagarTicket((String)ticketPago.getSelectedItem());
			}
		});
		//ingreso de objetos del panel
		sup1.add(entrada);
		sec1.add(titulo);
		sec1.add(expTicket);
		sec2.add(titulo2);
		sec2.add(tarjeta);
		sup2.add(salida);
		sec3.add(titulo3);
		sec3.add(IngresarTicket);
		sec4.add(titulo4);
		sec4.add(Ingresartarjeta);
		sec5.add(pago);
		sec5.add(ticketPago);
	}
}
