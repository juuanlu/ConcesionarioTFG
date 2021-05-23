package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class InicioGestion {

	protected Shell shlComprobacinDeAdministrador;
	private Text textUsuario;
	private Text textContrasenya;
	public static String usuario;
	public static String contrasenya;
	Controlador c = new Controlador();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InicioGestion window = new InicioGestion();
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
		shlComprobacinDeAdministrador.open();
		shlComprobacinDeAdministrador.layout();
		while (!shlComprobacinDeAdministrador.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlComprobacinDeAdministrador = new Shell();
		shlComprobacinDeAdministrador.setText("Comprobaci\u00F3n de administrador");
		shlComprobacinDeAdministrador.setSize(450, 288);
		
		Label lblTitulo = new Label(shlComprobacinDeAdministrador, SWT.BORDER);
		lblTitulo.setAlignment(SWT.CENTER);
		lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD | SWT.ITALIC));
		lblTitulo.setBounds(10, 23, 414, 21);
		lblTitulo.setText("Gesti\u00F3n del concesionario solo apta para administradores.");
		
		Label lblUsuario = new Label(shlComprobacinDeAdministrador, SWT.NONE);
		lblUsuario.setText("Usuario:");
		lblUsuario.setBounds(101, 79, 54, 15);
		
		textUsuario = new Text(shlComprobacinDeAdministrador, SWT.BORDER);
		textUsuario.setBounds(179, 72, 164, 29);
		
		Label lblContrasea = new Label(shlComprobacinDeAdministrador, SWT.NONE);
		lblContrasea.setText("Contrase\u00F1a:");
		lblContrasea.setBounds(100, 131, 68, 15);
		
		textContrasenya = new Text(shlComprobacinDeAdministrador, SWT.BORDER | SWT.PASSWORD);
		textContrasenya.setBounds(179, 123, 164, 29);
		
		Button btnEntrar = new Button(shlComprobacinDeAdministrador, SWT.CENTER);
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				usuario = textUsuario.getText();
				contrasenya = textContrasenya.getText();
				
				if(usuario.equals("admin") && contrasenya.equals("admin")) {
					JOptionPane.showMessageDialog(null, "Credenciales de administador correctas.");
					shlComprobacinDeAdministrador.close();
					GestionConcesionario gc = new GestionConcesionario();
					gc.open();
	            } else {
	            	JOptionPane.showMessageDialog(null,"No eres administrador");
	            	
	            }
			}
		});
		btnEntrar.setText("Acceder a la gesti\u00F3n");
		btnEntrar.setBounds(134, 182, 178, 38);
		
		Button btnVolver = new Button(shlComprobacinDeAdministrador, SWT.NONE);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shlComprobacinDeAdministrador.close();
				VistaPrincipal vp = new VistaPrincipal();
				vp.open();
			}
		});
		btnVolver.setBounds(349, 214, 75, 25);
		btnVolver.setText("Volver");

	}
}
