package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import static modelo.Conexion.Conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import controlador.Controlador;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class BusquedaFiltros {

	protected Shell shlBusquedaPorFiltros;
	private Table table;
	private Text textMarca;
	private Text textModelo;
	private Text textColor;
	
	public static String marca;
	public static String modelo;
	public static String color;
	
	Controlador c = new Controlador();
	private Button btnNewButton;
	
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BusquedaFiltros window = new BusquedaFiltros();
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
		shlBusquedaPorFiltros.open();
		shlBusquedaPorFiltros.layout();
		while (!shlBusquedaPorFiltros.isDisposed()) {
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
		shlBusquedaPorFiltros = new Shell();
		shlBusquedaPorFiltros.setSize(643, 485);
		shlBusquedaPorFiltros.setText("Busqueda por filtros");
		
		table = new Table(shlBusquedaPorFiltros, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 141, 607, 267);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		textMarca = new Text(shlBusquedaPorFiltros, SWT.BORDER);
		textMarca.setBounds(83, 22, 111, 32);
		
		Label lblMarca = new Label(shlBusquedaPorFiltros, SWT.NONE);
		lblMarca.setBounds(30, 31, 47, 15);
		lblMarca.setText("Marca");
		
		Label lblModelo = new Label(shlBusquedaPorFiltros, SWT.NONE);
		lblModelo.setText("Modelo");
		lblModelo.setBounds(223, 31, 52, 15);
		
		textModelo = new Text(shlBusquedaPorFiltros, SWT.BORDER);
		textModelo.setBounds(280, 22, 111, 32);
		
		Label lblColor = new Label(shlBusquedaPorFiltros, SWT.NONE);
		lblColor.setText("Color");
		lblColor.setBounds(432, 31, 51, 15);
		
		textColor = new Text(shlBusquedaPorFiltros, SWT.BORDER);
		textColor.setBounds(485, 22, 111, 32);
		
		Button btnFiltrar = new Button(shlBusquedaPorFiltros, SWT.NONE);
		btnFiltrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				marca = textMarca.getText();
				modelo = textModelo.getText();
				color = textColor.getText();
				
				try {
					c.BusquedaFiltro(table);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnFiltrar.setBounds(246, 82, 99, 32);
		btnFiltrar.setText("Filtrar");
		
		btnNewButton = new Button(shlBusquedaPorFiltros, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shlBusquedaPorFiltros.close();
				VistaPrincipal vp = new VistaPrincipal();
				vp.open();
				
			}
		});
		btnNewButton.setBounds(542, 414, 75, 25);
		btnNewButton.setText("Volver");
		
		
		

	}
}
