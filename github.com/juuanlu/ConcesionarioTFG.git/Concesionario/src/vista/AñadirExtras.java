package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import controlador.Controlador;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AñadirExtras {

	protected Shell shlAadirExtras;
	Controlador c = new Controlador();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AñadirExtras window = new AñadirExtras();
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
		shlAadirExtras.open();
		shlAadirExtras.layout();
		while (!shlAadirExtras.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAadirExtras = new Shell();
		shlAadirExtras.setSize(326, 247);
		shlAadirExtras.setText("A\u00F1adir Extras");
		
		Label lblTitulo = new Label(shlAadirExtras, SWT.NONE);
		lblTitulo.setAlignment(SWT.CENTER);
		lblTitulo.setFont(SWTResourceManager.getFont("Segoe UI", 25, SWT.BOLD));
		lblTitulo.setBounds(10, 10, 285, 52);
		lblTitulo.setText("A\u00D1ADIR EXTRAS");
		
		Button btnExtraDisplay = new Button(shlAadirExtras, SWT.CHECK);
		btnExtraDisplay.setBounds(60, 72, 124, 27);
		btnExtraDisplay.setText("Display 9.7 Pulgadas");
		
		Button btnExtraPackDeportivo = new Button(shlAadirExtras, SWT.CHECK);
		btnExtraPackDeportivo.setBounds(60, 105, 124, 27);
		btnExtraPackDeportivo.setText("Pack Deportivo");
		
		Label lblPrecioExtraDisplay = new Label(shlAadirExtras, SWT.NONE);
		lblPrecioExtraDisplay.setText("800");
		lblPrecioExtraDisplay.setBounds(203, 79, 18, 15);
		
		Label lblPack€ = new Label(shlAadirExtras, SWT.NONE);
		lblPack€.setText("\u20AC");
		lblPack€.setBounds(227, 79, 18, 15);
		
		Label lblPrecioExtraPackDeportivo = new Label(shlAadirExtras, SWT.NONE);
		lblPrecioExtraPackDeportivo.setText("1250");
		lblPrecioExtraPackDeportivo.setBounds(203, 112, 24, 15);
		
		Label lblDisplay€ = new Label(shlAadirExtras, SWT.NONE);
		lblDisplay€.setText("\u20AC");
		lblDisplay€.setBounds(233, 112, 18, 15);
		
		Button btnAceptar = new Button(shlAadirExtras, SWT.NONE);
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {

				int preciofinal = Controlador.preciofinal;
				boolean extradisplay = btnExtraDisplay.getSelection();
				boolean extrapackdeportivo = btnExtraPackDeportivo.getSelection();
				int preciodisplay = Integer.parseInt(lblPrecioExtraDisplay.getText());
				int preciopackdeportivo = Integer.parseInt(lblPrecioExtraPackDeportivo.getText());

				int confirmacion = JOptionPane.showConfirmDialog(null,  "¿Está seguro de realizar la compra?", 
						"Confirmación de la compra", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {

					if(extradisplay == true && extrapackdeportivo == false) {

						JOptionPane.showMessageDialog(null, "El precio asciende " + preciodisplay + "€. "
								+ "El precio total es "
								+ "de " + (preciofinal + preciodisplay) + "€");

						DatosVenta dv = new DatosVenta();
						dv.open();
					}

					else if (extrapackdeportivo == true && extradisplay == false) {

						JOptionPane.showMessageDialog(null, "El precio asciende " + preciopackdeportivo + "€. "
								+ "El precio total es "
								+ "de " + (preciofinal + preciopackdeportivo) + "€");
						DatosVenta dv = new DatosVenta();
						dv.open();

					} else if (extradisplay == true && extrapackdeportivo == true) {

						JOptionPane.showMessageDialog(null, "El precio asciende " + (preciopackdeportivo + 
								preciodisplay) + "€. El precio total es de " + (preciofinal + preciopackdeportivo 
										+ preciodisplay) + "€");

						DatosVenta dv = new DatosVenta();
						dv.open();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Se ha cancelado la compra");
				}
			}
		});
		btnAceptar.setBounds(116, 153, 75, 32);
		btnAceptar.setText("Aceptar");

	}
}
