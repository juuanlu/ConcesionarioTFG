package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class DatosVenta {

	protected Shell shlDatosDelComprador;
	private Text textNombre;
	private Text textApellido;
	private Text textDNI;
	private Text textLocalidad;
	private Text textDireccion;
	private Text textCuentaBancaria;
	
	public static String nombre;
	public static String apellido;
	public static String dni;
	public static String localidad;
	public static String direccion;
	public static String cuenta_bancaria;
	public static String usuario;
	Combo comboID;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatosVenta window = new DatosVenta();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDatosDelComprador.open();
		shlDatosDelComprador.layout();
		while (!shlDatosDelComprador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlDatosDelComprador = new Shell();
		shlDatosDelComprador.setSize(486, 447);
		shlDatosDelComprador.setText("Datos del comprador");
		
		Label lblTitulo = new Label(shlDatosDelComprador, SWT.NONE);
		lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		lblTitulo.setAlignment(SWT.CENTER);
		lblTitulo.setBounds(10, 10, 450, 45);
		lblTitulo.setText("DATOS DEL COMPRADOR");
		
		textNombre = new Text(shlDatosDelComprador, SWT.BORDER);
		textNombre.setBounds(32, 119, 190, 28);
		
		Label lblNombre = new Label(shlDatosDelComprador, SWT.NONE);
		lblNombre.setBounds(32, 97, 190, 15);
		lblNombre.setText("Nombre");
		
		Label lblApellido = new Label(shlDatosDelComprador, SWT.NONE);
		lblApellido.setText("Primer apellido");
		lblApellido.setBounds(244, 97, 190, 15);
		
		textApellido = new Text(shlDatosDelComprador, SWT.BORDER);
		textApellido.setBounds(244, 119, 190, 28);
		
		Label lblDni = new Label(shlDatosDelComprador, SWT.NONE);
		lblDni.setText("DNI");
		lblDni.setBounds(32, 174, 190, 15);
		
		textDNI = new Text(shlDatosDelComprador, SWT.BORDER);
		textDNI.setBounds(32, 196, 190, 28);
		
		Label lblLocalidad = new Label(shlDatosDelComprador, SWT.NONE);
		lblLocalidad.setText("Localidad");
		lblLocalidad.setBounds(244, 174, 190, 15);
		
		textLocalidad = new Text(shlDatosDelComprador, SWT.BORDER);
		textLocalidad.setBounds(244, 196, 190, 28);
		
		Label lblDireccion = new Label(shlDatosDelComprador, SWT.NONE);
		lblDireccion.setText("Direccion");
		lblDireccion.setBounds(32, 256, 190, 15);
		
		textDireccion = new Text(shlDatosDelComprador, SWT.BORDER);
		textDireccion.setBounds(32, 278, 190, 28);
		
		Label lblCuentaBancaria = new Label(shlDatosDelComprador, SWT.NONE);
		lblCuentaBancaria.setText("Cuenta bancaria");
		lblCuentaBancaria.setBounds(244, 256, 190, 15);
		
		textCuentaBancaria = new Text(shlDatosDelComprador, SWT.BORDER);
		textCuentaBancaria.setBounds(244, 278, 190, 28);
		
		Button btnAceptar = new Button(shlDatosDelComprador, SWT.NONE);
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				try {
					nombre = textNombre.getText();
					apellido = textApellido.getText();
					dni = textDNI.getText();
					localidad = textLocalidad.getText();
					direccion = textDireccion.getText();
					cuenta_bancaria = textCuentaBancaria.getText();
					usuario = Bienvenida.usuario;

					if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || localidad.isEmpty() || direccion.isEmpty() || cuenta_bancaria.isEmpty()) {

						JOptionPane.showMessageDialog(null, "Tiene que rellenar todos los campos para realizar su compra.");

					} else {

						Controlador.InsertarDatosComprador(nombre, apellido, dni, localidad, direccion, cuenta_bancaria);
						Cerrar();
						JOptionPane.showMessageDialog(null, "¡Su coche ha sido vendido con éxito!");
					}

				} catch (Exception e2) {

					JOptionPane.showMessageDialog(null, "Rellena los campos correctamente para realizar su compra.");

				}
			}
		});
		
		btnAceptar.setBounds(176, 345, 116, 38);
		btnAceptar.setText("Aceptar");

	}

		public void Cerrar() {
			shlDatosDelComprador.close();
		}
	
}
