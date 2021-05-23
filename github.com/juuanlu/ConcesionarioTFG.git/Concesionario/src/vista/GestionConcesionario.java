package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import static modelo.Conexion.Conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class GestionConcesionario {

	protected Shell shlGestinDelConcesionario;
	private Table table;
	Connection cn = Conectar();
	private Text textAnyadirModelo;
	private Text textAnyadirPrecio;
	
	public static String marcaAnyadir;
	public static String modeloAnyadir;
	public static String colorAnyadir;
	public static String precioAnyadir;
	public static String matriculaAnyadir;
	public static String anyo_matricAnyadir;
	
	public static String idEliminar;
	Controlador c = new Controlador();
	private Label lblPrecio;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GestionConcesionario window = new GestionConcesionario();
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
		shlGestinDelConcesionario.open();
		shlGestinDelConcesionario.layout();
		while (!shlGestinDelConcesionario.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlGestinDelConcesionario = new Shell();
		shlGestinDelConcesionario.setSize(1082, 533);
		shlGestinDelConcesionario.setText("Gesti\u00F3n del concesionario");
		
		table = new Table(shlGestinDelConcesionario, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(231, 69, 604, 403);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		try {
			c.TablaGestion(table);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Label lblTitulo = new Label(shlGestinDelConcesionario, SWT.NONE);
		lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 29, SWT.BOLD | SWT.ITALIC));
		lblTitulo.setAlignment(SWT.CENTER);
		lblTitulo.setBounds(10, 10, 1046, 52);
		lblTitulo.setText("GESTI\u00D3N DEL CONCESIONARIO");
		
		Label lblAnyadirVehiculos = new Label(shlGestinDelConcesionario, SWT.BORDER);
		lblAnyadirVehiculos.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD | SWT.ITALIC));
		lblAnyadirVehiculos.setAlignment(SWT.CENTER);
		lblAnyadirVehiculos.setBounds(10, 69, 215, 27);
		lblAnyadirVehiculos.setText("A\u00F1adir veh\u00EDculos");
		
		Label lblEliminarVehiculos = new Label(shlGestinDelConcesionario, SWT.BORDER);
		lblEliminarVehiculos.setText("Eliminar veh\u00EDculos");
		lblEliminarVehiculos.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD | SWT.ITALIC));
		lblEliminarVehiculos.setAlignment(SWT.CENTER);
		lblEliminarVehiculos.setBounds(841, 69, 215, 27);
		
		Label lblMarca = new Label(shlGestinDelConcesionario, SWT.NONE);
		lblMarca.setAlignment(SWT.CENTER);
		lblMarca.setBounds(26, 150, 180, 15);
		lblMarca.setText("Marca");
		
		Label lblModelo = new Label(shlGestinDelConcesionario, SWT.NONE);
		lblModelo.setAlignment(SWT.CENTER);
		lblModelo.setBounds(26, 200, 180, 15);
		lblModelo.setText("Modelo");
		
		Combo comboAnyadirColor = new Combo(shlGestinDelConcesionario, SWT.NONE);
		comboAnyadirColor.setBounds(26, 271, 180, 23);
		comboAnyadirColor.add("");
		comboAnyadirColor.add("Blanco");
		comboAnyadirColor.add("Negro");
		comboAnyadirColor.add("Rojo");
		comboAnyadirColor.add("Gris");
		comboAnyadirColor.add("Naranja");
		comboAnyadirColor.add("Azul");
		comboAnyadirColor.add("Amarillo");
		
		
		Label lblColor = new Label(shlGestinDelConcesionario, SWT.NONE);
		lblColor.setText("Color");
		lblColor.setAlignment(SWT.CENTER);
		lblColor.setBounds(26, 250, 180, 15);
		
		lblPrecio = new Label(shlGestinDelConcesionario, SWT.NONE);
		lblPrecio.setText("Precio");
		lblPrecio.setAlignment(SWT.CENTER);
		lblPrecio.setBounds(26, 300, 180, 15);
		
		textAnyadirModelo = new Text(shlGestinDelConcesionario, SWT.BORDER);
		textAnyadirModelo.setBounds(26, 223, 180, 21);
		
		textAnyadirPrecio = new Text(shlGestinDelConcesionario, SWT.BORDER);
		textAnyadirPrecio.setBounds(26, 321, 180, 21);
		
		Label lblID = new Label(shlGestinDelConcesionario, SWT.NONE);
		lblID.setText("ID");
		lblID.setAlignment(SWT.CENTER);
		lblID.setBounds(861, 220, 180, 15);
		
		
		Combo comboAnyadirMarca = new Combo(shlGestinDelConcesionario, SWT.NONE);
		comboAnyadirMarca.setBounds(26, 171, 180, 23);
		comboAnyadirMarca.add("");
		comboAnyadirMarca.add("BMW");
		comboAnyadirMarca.add("Mercedes");
		comboAnyadirMarca.add("Seat");
		comboAnyadirMarca.add("Tesla");
		comboAnyadirMarca.add("Peugeot");
		comboAnyadirMarca.add("Ford");
		comboAnyadirMarca.add("Audi");
		
		Button btnAnyadir = new Button(shlGestinDelConcesionario, SWT.NONE);
		btnAnyadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				modeloAnyadir = textAnyadirModelo.getText();
				precioAnyadir = textAnyadirPrecio.getText();
				
				if (comboAnyadirMarca.getSelectionIndex() == -1 || comboAnyadirColor.getSelectionIndex() == -1 || modeloAnyadir.isEmpty() || precioAnyadir.isEmpty()) {

					JOptionPane.showMessageDialog(null, "Tiene que rellenar todos los campos para realizar la inserción.");

				} else {

					marcaAnyadir = comboAnyadirMarca.getItem(comboAnyadirMarca.getSelectionIndex());
					colorAnyadir = comboAnyadirColor.getItem(comboAnyadirColor.getSelectionIndex());
					

					try {
						c.AnyadirVehiculo();
						JOptionPane.showMessageDialog(null, "¡Vehiculo añadido con éxito!");
						c.TablaGestion(table);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "¡Ha habido un erroe!");
						e1.printStackTrace();
					}
				}
			}
		});
		btnAnyadir.setBounds(26, 359, 180, 27);
		btnAnyadir.setText("A\u00F1adir");
		
		Combo comboID = new Combo(shlGestinDelConcesionario, SWT.NONE);
		comboID.setBounds(861, 241, 180, 23);
		
		Button btnEliminar = new Button(shlGestinDelConcesionario, SWT.NONE);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				if (comboID.getSelectionIndex() == -1) {

					JOptionPane.showMessageDialog(null, "Tiene que seleccionar un ID para realizar la eliminación.");

				} else {
				
				idEliminar = comboID.getItem(comboID.getSelectionIndex());
				
				try {
					c.EliminarVehiculo();
					JOptionPane.showMessageDialog(null, "¡Vehiculo eliminado con éxito!");
					c.TablaGestion(table);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		btnEliminar.setText("Eliminar");
		btnEliminar.setBounds(861, 288, 180, 27);
		
			
			Button btnVolver = new Button(shlGestinDelConcesionario, SWT.NONE);
			btnVolver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					
					shlGestinDelConcesionario.close();
					VistaPrincipal vp = new VistaPrincipal();
					vp.open();
					
				}
			});
			btnVolver.setBounds(981, 459, 75, 25);
			btnVolver.setText("Volver");
			
			try {
				Connection cn = Conectar();
				PreparedStatement ps = ((Connection) cn).prepareStatement("SELECT id, vendido FROM vehiculo");
				ResultSet rs = ps.executeQuery();
				comboID.add("");
				
			while(rs.next()) {

				comboID.add(rs.getString("id"));
				
			}
			
		} catch (SQLException e) {
		}
		

	}
}
