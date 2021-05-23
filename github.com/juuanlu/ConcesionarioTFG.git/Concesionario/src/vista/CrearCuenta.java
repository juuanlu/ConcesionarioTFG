package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CrearCuenta {

	protected Shell shlCreacinDeCuentas;
	private Text textEmail;
	private Text textUsuario;
	private Text textContrasenya;
	public static String usuario;
	public static String contrasenya;
	public static String email;
	Controlador c = new Controlador();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CrearCuenta window = new CrearCuenta();
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
		shlCreacinDeCuentas.open();
		shlCreacinDeCuentas.layout();
		while (!shlCreacinDeCuentas.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCreacinDeCuentas = new Shell();
		shlCreacinDeCuentas.setSize(362, 388);
		shlCreacinDeCuentas.setText("Creaci\u00F3n de cuentas");
		
		Label lblNewLabel = new Label(shlCreacinDeCuentas, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewLabel.setBounds(10, 23, 327, 45);
		lblNewLabel.setText("CREACI\u00D3N DE CUENTAS");
		
		Label lblEmail = new Label(shlCreacinDeCuentas, SWT.NONE);
		lblEmail.setText("e-Mail:");
		lblEmail.setBounds(43, 86, 54, 15);
		
		textEmail = new Text(shlCreacinDeCuentas, SWT.BORDER);
		textEmail.setBounds(119, 81, 182, 31);
		
		Label lblUsuario = new Label(shlCreacinDeCuentas, SWT.NONE);
		lblUsuario.setText("Usuario:");
		lblUsuario.setBounds(43, 146, 68, 15);
		
		textUsuario = new Text(shlCreacinDeCuentas, SWT.BORDER);
		textUsuario.setBounds(119, 139, 182, 31);
		
		Button btnCrearCuenta = new Button(shlCreacinDeCuentas, SWT.CENTER);
		btnCrearCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				email = textEmail.getText();
				usuario = textUsuario.getText();
				contrasenya = textContrasenya.getText();
				
				if (email.isEmpty() || usuario.isEmpty() || contrasenya.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe rellenar los campos correctamente.");
					
				} else {
					
					if (textUsuario.getText().length() > 10) {
						JOptionPane.showMessageDialog(null, "El usuario debe tener máximo 10 carácteres.");
					} else if (textContrasenya.getText().length() > 15) {
						JOptionPane.showMessageDialog(null, "La contraseña debe tener máximo 15 carácteres.");
					} else {

						try {
							c.NuevaCuenta();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
				
			}
		});
		btnCrearCuenta.setText("Crear cuenta");
		btnCrearCuenta.setBounds(77, 287, 188, 31);
		
		Label lblContrasenya = new Label(shlCreacinDeCuentas, SWT.NONE);
		lblContrasenya.setText("Contrase\u00F1a:");
		lblContrasenya.setBounds(43, 214, 68, 15);
		
		textContrasenya = new Text(shlCreacinDeCuentas, SWT.BORDER | SWT.PASSWORD);
		textContrasenya.setBounds(119, 207, 182, 31);
		
		Label lblNewLabel_1 = new Label(shlCreacinDeCuentas, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.ITALIC));
		lblNewLabel_1.setBounds(119, 176, 182, 15);
		lblNewLabel_1.setText("M\u00E1ximo 10 car\u00E1cteres.");
		
		Label lblNewLabel_1_1 = new Label(shlCreacinDeCuentas, SWT.NONE);
		lblNewLabel_1_1.setText("M\u00E1ximo 15 car\u00E1cteres.");
		lblNewLabel_1_1.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.ITALIC));
		lblNewLabel_1_1.setBounds(119, 244, 182, 15);

	}
}
