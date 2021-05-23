package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import controlador.Controlador;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class ListaDesguace {

	protected Shell shell;
	private Table table;
	Controlador c = new Controlador();
	private Button btnNewButton_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListaDesguace window = new ListaDesguace();
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
		shell.setSize(506, 438);
		shell.setText("Lista de vehículos en desguace");
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 41, 469, 302);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
			try {
				c.CargarTablaDesguace(table);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		btnNewButton.setBounds(10, 351, 469, 38);
		btnNewButton.setText("Cargar Listado");
		
		btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				
				shell.close();
				VistaPrincipal vp = new VistaPrincipal();
				vp.open();			
			}
		});
		btnNewButton_1.setBounds(404, 10, 75, 25);
		btnNewButton_1.setText("Volver");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.BOLD));
		lblNewLabel.setBounds(10, 7, 388, 31);
		lblNewLabel.setText("LISTA DE VEH\u00CDCULOS EN DESGUACE");
		

	}
}
