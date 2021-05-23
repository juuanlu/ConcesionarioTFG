package vista;

import static modelo.Conexion.Conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class VentaVehiculos {

	protected Shell shlVentaDeVehculos;
	Controlador c = new Controlador();

	public static String marca;
	public static String modelo;
	public static String id;
	public static Timestamp fecha_matricula = Timestamp.from(Instant.now());
	public static int precio;
	private Table table;
	Connection cn = Conectar();
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VentaVehiculos window = new VentaVehiculos();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 * @throws SQLException 
	 */
	public void open() throws SQLException {
		Display display = Display.getDefault();
		createContents();
		shlVentaDeVehculos.open();
		shlVentaDeVehculos.layout();
		while (!shlVentaDeVehculos.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws SQLException 
	 */
	protected void createContents() throws SQLException {
		shlVentaDeVehculos = new Shell();
		shlVentaDeVehculos.setSize(652, 452);
		shlVentaDeVehculos.setText("Venta de veh\u00EDculos");
		
		Label lblNewLabel = new Label(shlVentaDeVehculos, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD | SWT.ITALIC));
		lblNewLabel.setBounds(10, 11, 511, 41);
		lblNewLabel.setText("VENTA DE VEH\u00CDCULOS");
		
		Combo comboID = new Combo(shlVentaDeVehculos, SWT.NONE);
		comboID.setBounds(204, 374, 110, 23);
		
		Button btnComprar = new Button(shlVentaDeVehculos, SWT.NONE);
		btnComprar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				if (comboID.getSelectionIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Tiene que seleccionar un ID para realizar la venta.");   

				} else {

					id = String.valueOf(comboID.getItem(comboID.getSelectionIndex()));

					try {
						c.ComprobarID(comboID);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
			
		
		btnComprar.setBounds(320, 353, 115, 44);
		btnComprar.setText("Vender");
		
		table = new Table(shlVentaDeVehculos, SWT.BORDER | SWT.FULL_SELECTION);
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String string = "";
				if(table.getSelectionIndex() != -1) { 
					TableItem[] selection = table.getSelection();
					for (int i = 0; i < selection.length; i++)
						string += selection[i] + "\n";
					System.out.println(string);
				} else {
				} 

			}
		});
		table.setBounds(20, 62, 599, 272);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		Button btnRecargarLista = new Button(shlVentaDeVehculos, SWT.NONE);
		btnRecargarLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				int contador = 0;
				try {

					table.removeAll();
					Connection cn = Conectar();
					PreparedStatement ps1 = ((Connection) cn).prepareStatement("SELECT "
							+ "* FROM vehiculo");
				    ResultSet rs = ps1.executeQuery();
					
				    String[] titles = { "ID", "Marca", "Modelo", "Color", "Matricula", 
				    		"Fecha de Entrada", "Año de Matriculación", "Precio" };
				    
				    for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
						TableColumn column = new TableColumn(table, SWT.NULL);
						 column.setText(titles[loopIndex]);
					}
				    
				    while (rs.next()) {
						boolean desguace = rs.getBoolean("desguace");

						if(!desguace) {
							boolean vendido = rs.getBoolean("vendido");
							
							contador++;
							String id = rs.getString("id");
							String marca = rs.getString("marca");
							String modelo = rs.getString("modelo");
							String color = rs.getString("color");
							String matricula = rs.getString("matricula");
							String fecha_entrada = rs.getString("fecha_entrada");
							String anyo_matric =  rs.getString("anyo_matriculacion");
							String precio = rs.getString("precio");

							if(matricula == null) {
								matricula = "";
							}

							if(anyo_matric == null) {
								anyo_matric = "";
							}
							
							if(!vendido) {
								TableItem item = new TableItem(table, SWT.NULL);
								item.setText(0, id);
						        item.setText(1, marca);
						        item.setText(2, modelo);
						        item.setText(3, color);
						        item.setText(4, matricula);
						        item.setText(5, fecha_entrada);
						        item.setText(6, anyo_matric);
						        item.setText(7, precio + " €");

							}

							}
						for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
							table.getColumn(loopIndex).pack();
						}
					}

					
				
			    
			    if(contador == 0) {
					JOptionPane.showMessageDialog(null, "No existen vehículos disponibles actualmente.");
					} 
			    
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				try {
					c.UpdateComboBox(comboID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRecargarLista.setBounds(527, 30, 92, 25);
		btnRecargarLista.setText("Cargar lista");
		
		Label lblID = new Label(shlVentaDeVehculos, SWT.NONE);
		lblID.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblID.setBounds(204, 353, 110, 15);
		lblID.setText("ID");
		
		c.UpdateComboBox(comboID);
		
		Button btnVolver = new Button(shlVentaDeVehculos, SWT.NONE);
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shlVentaDeVehculos.close();
				VistaPrincipal vp = new VistaPrincipal();
				vp.open();
				
			}
		});
		btnVolver.setBounds(544, 378, 75, 25);
		btnVolver.setText("Volver");
	    
	}
}
