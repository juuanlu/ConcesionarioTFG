
package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.sql.SQLException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import controlador.Controlador;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;

public class Bienvenida {

	public static Shell shell;
	private Text textUsuario;
	public static String usuario;
	public static String contrasenya;
	 Display display = Display.getDefault();
	Image image = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/concesionariosjuanluBienvenida.png");
	private Text textContrasenya;
	Controlador c = new Controlador();
	

	public Bienvenida() {
		// TODO Auto-generated constructor stub
	}

	public Bienvenida(Shell shell2) {
		Bienvenida.shell = shell2;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Bienvenida window = new Bienvenida();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(494, 464);
		shell.setText("Concesionarios Juanlu");
		
		Label lblImagen = new Label(shell, SWT.BORDER | SWT.SHADOW_IN | SWT.CENTER);
		lblImagen.setAlignment(SWT.CENTER);
		lblImagen.setBounds(10, 10, 456, 290);
		lblImagen.setImage(image); 
		
		Button btnEntrar = new Button(shell, SWT.CENTER);
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				usuario = textUsuario.getText();
				contrasenya = textContrasenya.getText();
				
				try {
					c.IniciarSesion(shell, display);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		
		
		btnEntrar.setBounds(78, 373, 157, 38);
		btnEntrar.setText("Entrar");
		
		textUsuario = new Text(shell, SWT.BORDER);
		textUsuario.setBounds(69, 323, 146, 28);
		
		Label lblUsuario = new Label(shell, SWT.NONE);
		lblUsuario.setBounds(21, 331, 43, 15);
		lblUsuario.setText("Usuario:");
		
		Label lblContrasea = new Label(shell, SWT.NONE);
		lblContrasea.setText("Contrase\u00F1a:");
		lblContrasea.setBounds(234, 331, 63, 15);
		
		textContrasenya = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textContrasenya.setBounds(304, 323, 146, 28);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				InicioAdmin ia = new InicioAdmin();
				ia.open();
				
			}
		});
		btnNewButton.setBounds(241, 373, 157, 38);
		btnNewButton.setText("Crear cuenta");

	}
	
	public void CerrarShell() {
		shell.close();
		
	}
	
}
