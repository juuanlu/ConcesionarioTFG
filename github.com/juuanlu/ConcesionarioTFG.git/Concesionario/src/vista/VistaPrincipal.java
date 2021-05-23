package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import controlador.Controlador;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class VistaPrincipal {

	protected Shell shell;
	Display display = Display.getDefault();
	Image image = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/concesionariosjuanluPeque.png");
	Controlador c = new Controlador();
	private Table table;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			VistaPrincipal window = new VistaPrincipal();
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
		shell.setSize(664, 423);
		shell.setText("Concesionarios Juanlu");
		
		Label lblImagen = new Label(shell, SWT.BORDER | SWT.CENTER);
		lblImagen.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblImagen.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblImagen.setAlignment(SWT.CENTER);
		lblImagen.setBounds(516, 17, 105, 64);
		lblImagen.setImage(image); 
		
		Label lblTitulo = new Label(shell, SWT.BORDER);
		lblTitulo.setAlignment(SWT.CENTER);
		lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 27, SWT.BOLD | SWT.ITALIC));
		lblTitulo.setBounds(10, 22, 486, 54);
		lblTitulo.setText("CONCESIONARIOS JUANLU");
		
		Label lblListadoDeCoches = new Label(shell, SWT.NONE);
		lblListadoDeCoches.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblListadoDeCoches.setAlignment(SWT.CENTER);
		lblListadoDeCoches.setBounds(243, 118, 175, 15);
		lblListadoDeCoches.setText("B\u00FAsqueda por filtros");
		
		Button btnListadoVehiculos = new Button(shell, SWT.NONE);
		btnListadoVehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				shell.close();
				CompraVehiculos vv = new CompraVehiculos();
				vv.open();
				
				
			}
		});
		btnListadoVehiculos.setBounds(438, 139, 175, 47);
		btnListadoVehiculos.setText("Acceder");
		
		Label lblCompraVehiculos = new Label(shell, SWT.NONE);
		lblCompraVehiculos.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblCompraVehiculos.setAlignment(SWT.CENTER);
		lblCompraVehiculos.setBounds(438, 118, 175, 15);
		lblCompraVehiculos.setText("Compra de veh\u00EDculos");
		
		Button btnVentaVehiculos = new Button(shell, SWT.NONE);
		btnVentaVehiculos.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnVentaVehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shell.close();
				try {
				BusquedaFiltros bf = new BusquedaFiltros();
				bf.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnVentaVehiculos.setBounds(243, 139, 175, 47);
		btnVentaVehiculos.setText("Acceder");
		
		Button btnCompraVehiculos = new Button(shell, SWT.NONE);
		btnCompraVehiculos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shell.close();
				VentaVehiculos cv = new VentaVehiculos();
				try {
					cv.open();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnCompraVehiculos.setText("Acceder");
		btnCompraVehiculos.setBounds(438, 228, 175, 47);
		
		Label lblVentaVehiculos = new Label(shell, SWT.NONE);
		lblVentaVehiculos.setText("Venta de veh\u00EDculos");
		lblVentaVehiculos.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblVentaVehiculos.setAlignment(SWT.CENTER);
		lblVentaVehiculos.setBounds(438, 207, 175, 15);
		
		Button btnDesguace = new Button(shell, SWT.NONE);
		btnDesguace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shell.close();
				ListaDesguace ld = new ListaDesguace();
				ld.open();
				
				
			}
		});
		btnDesguace.setBounds(243, 228, 175, 47);
		btnDesguace.setText("Acceder");
		
		Label lblDesguace = new Label(shell, SWT.NONE);
		lblDesguace.setText("Veh\u00EDculos en desguace");
		lblDesguace.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDesguace.setAlignment(SWT.CENTER);
		lblDesguace.setBounds(243, 207, 175, 15);
		
		Label lblModelosDisponibles = new Label(shell, SWT.NONE);
		lblModelosDisponibles.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblModelosDisponibles.setAlignment(SWT.CENTER);
		lblModelosDisponibles.setBounds(21, 94, 205, 20);
		lblModelosDisponibles.setText("MODELOS DISPONIBLES");
		
		Label lblPulsarImagen = new Label(shell, SWT.NONE);
		lblPulsarImagen.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.ITALIC));
		lblPulsarImagen.setBounds(23, 346, 128, 15);
		lblPulsarImagen.setText("Pulsa para ver una imagen.");
		
		Button btnRecargarLista = new Button(shell, SWT.NONE);
		btnRecargarLista.setFont(SWTResourceManager.getFont("Segoe UI", 8, SWT.NORMAL));
		btnRecargarLista.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				try {
					Controlador.CargarTablaPrincipal(table);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRecargarLista.setBounds(166, 343, 60, 20);
		btnRecargarLista.setText("Recargar");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(21, 120, 205, 220);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		try {
			Controlador.CargarTablaPrincipal(table);
			
			Button btnNewButton = new Button(shell, SWT.NONE);
			btnNewButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					
					shell.close();
					InicioGestion ig = new InicioGestion();
					ig.open();
					
				}
			});
			btnNewButton.setBounds(342, 309, 175, 47);
			btnNewButton.setText("Acceder");
			
			Label lblNewLabel = new Label(shell, SWT.NONE);
			lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
			lblNewLabel.setAlignment(SWT.CENTER);
			lblNewLabel.setBounds(342, 288, 175, 15);
			lblNewLabel.setText("Gesti\u00F3n del concesionario");
			
			Button btnCerrarSesin = new Button(shell, SWT.NONE);
			btnCerrarSesin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseUp(MouseEvent e) {
					
					shell.close();
					Bienvenida b = new Bienvenida();
					b.open();
					
				}
			});
			btnCerrarSesin.setBounds(558, 349, 80, 25);
			btnCerrarSesin.setText("Cerrar sesi\u00F3n");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		table.addSelectionListener(new SelectionAdapter() { 

			public void widgetSelected(SelectionEvent e) { 

				if(table.getSelectionIndex() != -1) { 
					TableItem item = table.getItem(table.getSelectionIndex());
					
					if(item.toString().equals("TableItem {208}")) {
						Fotos f = new Fotos(item.toString());
						f.open();
					}
					if(item.toString().equals("TableItem {Ibiza}")) {
						Fotos f = new Fotos(item.toString());
						f.open();
					}
					if(item.toString().equals("TableItem {Clase A}")) {
						Fotos f = new Fotos(item.toString());
						f.open();
					}
					if(item.toString().equals("TableItem {Roadster}")) {
						Fotos f = new Fotos(item.toString());
						f.open();
					}
					if(item.toString().equals("TableItem {Serie 5}")) {
						Fotos f = new Fotos(item.toString());
						f.open();
					}
					
				} else {
				} 

			} 
		}); 
		
	}
}
