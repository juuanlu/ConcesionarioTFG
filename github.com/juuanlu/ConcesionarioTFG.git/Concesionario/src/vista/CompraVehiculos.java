package vista;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CompraVehiculos {

	protected Shell shlCompraDeVehculos;
	private Text textMarca;
	private Text textModelo;
	private Text textMatricula;
	private Text textAnyoMatric;
	private Text textPrecio;
	private Text textColor;
	
	public static String marca;
	public static String modelo;
	public static String matricula;
	public static String color;
	public static int anyo_matric;
	public static int precio;
	
	Controlador c = new Controlador();
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CompraVehiculos window = new CompraVehiculos();
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
		shlCompraDeVehculos.open();
		shlCompraDeVehculos.layout();
		while (!shlCompraDeVehculos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCompraDeVehculos = new Shell();
		shlCompraDeVehculos.setSize(453, 410);
		shlCompraDeVehculos.setText("Compra de veh\u00EDculos");
		
		Label lblTitulo = new Label(shlCompraDeVehculos, SWT.NONE);
		lblTitulo.setAlignment(SWT.CENTER);
		lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD | SWT.ITALIC));
		lblTitulo.setBounds(10, 10, 418, 42);
		lblTitulo.setText("COMPRA DE VEH\u00CDCULOS");
		
		Label lblMarca = new Label(shlCompraDeVehculos, SWT.NONE);
		lblMarca.setBounds(53, 80, 146, 15);
		lblMarca.setText("Marca");
		
		Label lblModelo = new Label(shlCompraDeVehculos, SWT.NONE);
		lblModelo.setText("Modelo");
		lblModelo.setBounds(53, 149, 146, 15);
		
		Label lblMatricula = new Label(shlCompraDeVehculos, SWT.NONE);
		lblMatricula.setText("Matricula");
		lblMatricula.setBounds(53, 223, 146, 15);
		
		Label lblFechaMatric = new Label(shlCompraDeVehculos, SWT.NONE);
		lblFechaMatric.setText("A\u00F1o de Matriculaci\u00F3n");
		lblFechaMatric.setBounds(241, 80, 146, 15);
		
		Button btnVender = new Button(shlCompraDeVehculos, SWT.NONE);
		btnVender.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				try {
					
					marca = textMarca.getText();
					modelo = textModelo.getText();
					matricula = textMatricula.getText();
					anyo_matric = Integer.parseInt(textAnyoMatric.getText());
					precio = Integer.parseInt(textPrecio.getText());
					color = textColor.getText();
					
					c.ComprobarMatricula();
					
				} catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, "Rellena los campos correctamente para realizar su compra.");
					
				}
			}
		});
		btnVender.setBounds(145, 301, 146, 42);
		btnVender.setText("Comprar");
		
		textMarca = new Text(shlCompraDeVehculos, SWT.BORDER);
		textMarca.setBounds(53, 101, 146, 28);
		
		textModelo = new Text(shlCompraDeVehculos, SWT.BORDER);
		textModelo.setBounds(53, 170, 146, 28);
		
		textMatricula = new Text(shlCompraDeVehculos, SWT.BORDER);
		textMatricula.setBounds(53, 244, 146, 28);
		
		textAnyoMatric = new Text(shlCompraDeVehculos, SWT.BORDER);
		textAnyoMatric.setBounds(241, 101, 146, 28);
		
		textPrecio = new Text(shlCompraDeVehculos, SWT.BORDER);
		textPrecio.setBounds(241, 244, 146, 28);
		
		Label lblPrecio = new Label(shlCompraDeVehculos, SWT.NONE);
		lblPrecio.setText("Precio");
		lblPrecio.setBounds(241, 223, 146, 15);
		
		Label lblColor = new Label(shlCompraDeVehculos, SWT.NONE);
		lblColor.setText("Color");
		lblColor.setBounds(241, 149, 146, 15);
		
		textColor = new Text(shlCompraDeVehculos, SWT.BORDER);
		textColor.setBounds(241, 170, 146, 28);
		
		Button btnVolver = new Button(shlCompraDeVehculos, SWT.NONE);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shlCompraDeVehculos.close();
				VistaPrincipal vp = new VistaPrincipal();
				vp.open();
				
			}
		});
		btnVolver.setBounds(353, 336, 75, 25);
		btnVolver.setText("Volver");

	}

}
