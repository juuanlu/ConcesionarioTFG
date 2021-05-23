package vista;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

public class Fotos {

	protected Shell shlImagen;
	Display display = Display.getDefault();
	String imagen;
	Image imagePeugeot = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/peugeot208.jpg");
	Image imageSeat = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/seatibiza.jpg");
	Image imageMercedes = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/mercedesclasea.jpg");
	Image imageTesla = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/teslaroadster.jpg");
	Image imageBMW = new Image(display, 
		     "C:/Users/Juanlu/eclipse-workspace/Concesionario/bmwserie5.jpg");



	public Fotos() {
		
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Fotos window = new Fotos();
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
		shlImagen.open();
		shlImagen.layout();
		while (!shlImagen.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	public Fotos(String string) {
		this.imagen = string;
	}
	
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlImagen = new Shell();
		shlImagen.setText("Imagenes");
		
		Label lblImagen = new Label(shlImagen, SWT.NONE);

		if(imagen.equals("TableItem {208}")) {
			shlImagen.setSize(680, 444);
			lblImagen.setBounds(10, 10, 642, 385);
			lblImagen.setImage(imagePeugeot); 
		}
		
		if(imagen.equals("TableItem {Ibiza}")) {
			shlImagen.setSize(720, 444);
			lblImagen.setBounds(10, 10, 684, 385);
			lblImagen.setImage(imageSeat); 
		}
		
		if(imagen.equals("TableItem {Clase A}")) {
			shlImagen.setSize(720, 444);
			lblImagen.setBounds(10, 10, 684, 385);
			lblImagen.setImage(imageMercedes); 
		}
		
		if(imagen.equals("TableItem {Roadster}")) {
			shlImagen.setSize(720, 444);
			lblImagen.setBounds(10, 10, 684, 385);
			lblImagen.setImage(imageTesla); 
		}
		
		if(imagen.equals("TableItem {Serie 5}")) {
			shlImagen.setSize(720, 444);
			lblImagen.setBounds(10, 10, 684, 385);
			lblImagen.setImage(imageBMW); 
		}
	}

	
}
