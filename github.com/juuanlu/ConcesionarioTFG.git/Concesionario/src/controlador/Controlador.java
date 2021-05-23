package controlador;

import static modelo.Conexion.Conectar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import vista.AñadirExtras;
import vista.Bienvenida;
import vista.BusquedaFiltros;
import vista.VentaVehiculos;
import vista.VistaPrincipal;
import vista.CompraVehiculos;
import vista.CrearCuenta;
import vista.DatosCompra;
import vista.DatosVenta;
import vista.GestionConcesionario;

public class Controlador {

	static String marca;
	static String modelo;
	static String matricula;
	static String fecha_entrada;
	static String usuario;
	static String contrasenya;
	static String email;
	static String color;
	static int id;
	static String dni;
	static int idUsuario;

	public static Timestamp fecha_entrada_hoy = new Timestamp(Calendar.getInstance().getTimeInMillis());
	static String fecha_hoy = fecha_entrada_hoy.toString().split(" ")[0];
	static int anyo_matric;
	static int precio;
	public static int preciofinal;

	public static void CargarTablaPrincipal(Table table) throws SQLException {

		table.removeAll();
		String[] titles = { "Modelo", "Marca", "Precio"};
		Connection cn = Conectar();
		PreparedStatement ps1 = ((Connection) cn).prepareStatement("SELECT marca, modelo, "
				+ "precio, desguace, vendido FROM vehiculo");
		ResultSet rs = ps1.executeQuery();

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}

		while (rs.next()) {
			boolean desguace = rs.getBoolean("desguace");
			boolean vendido = rs.getBoolean("vendido");

			if(!desguace) {
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String precio = rs.getString("precio");

				if(!vendido) {
				TableItem item = new TableItem(table, SWT.NULL);
				item.setText(0, modelo);
				item.setText(1, marca);
				item.setText(2, precio + " €");
				}
			}

			for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
				table.getColumn(loopIndex).pack();
			}
		}
	}

	public static void InsertarDatos() throws SQLException {

		Connection cn = Conectar();
		marca = CompraVehiculos.marca;
		modelo = CompraVehiculos.modelo;
		matricula = CompraVehiculos.matricula;
		anyo_matric = CompraVehiculos.anyo_matric;
		precio = CompraVehiculos.precio;
		color = CompraVehiculos.color;
		dni = DatosCompra.dni;
		

		PreparedStatement pst = cn.prepareStatement("INSERT INTO vehiculo (marca, modelo, "
				+ "color, matricula, fecha_entrada, precio, anyo_matriculacion, vendido,"
				+ "segunda_mano, dni_cliente, id_trabajador) VALUES (?,?,?,?,?,?,?,false,true,?,?)");
		pst.setString(1, marca);
		pst.setString(2, modelo);
		pst.setString(3, color);
		pst.setString(4, matricula);
		pst.setString(5, fecha_hoy.toString());
		pst.setInt(6, precio);
		pst.setInt(7, anyo_matric);
		pst.setString(8, dni);
		pst.setInt(9, idUsuario);

		pst.executeUpdate();
		
		if(anyo_matric <= 2004) {

			JOptionPane.showMessageDialog(null, "¡Su coche ha sido enviado al desguace "
					+ "de Concesionarios Juanlu debido a su antigüedad!");
			PreparedStatement pst1 = cn.prepareStatement("UPDATE vehiculo SET desguace "
					+ "= true WHERE matricula = ?");
			pst1.setString(1, matricula);
			pst1.executeUpdate();

		} else {

			JOptionPane.showMessageDialog(null, "¡Su coche ha sido vendido a Concesionarios"
					+ " Juanlu con éxito!");
			PreparedStatement pst1 = cn.prepareStatement("UPDATE vehiculo SET desguace = "
					+ "false WHERE matricula = ?");
			pst1.setString(1, matricula);
			pst1.executeUpdate();
		}
	}

	public static void UpdateVendidoTrue() throws SQLException {

		Connection cn = Conectar();
		id = Integer.parseInt(VentaVehiculos.id);

		PreparedStatement pst = cn.prepareStatement("UPDATE vehiculo SET vendido = true WHERE id = ?");
		pst.setInt(1, id);
		pst.executeUpdate();
		
		
	}

	public void ComprobarID(Combo comboID) throws SQLException {

		id = Integer.parseInt(VentaVehiculos.id);

		Connection cn = Conectar();
		PreparedStatement pst = cn.prepareStatement("SELECT id, precio FROM vehiculo WHERE id = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		

		if(rs.next()){
			
			preciofinal = rs.getInt("precio");
			int confirmacion2 = JOptionPane.showConfirmDialog(null,  "¿Quieres añadir algún extra?", "Añadir "
					+ "extras", JOptionPane.YES_NO_OPTION);
			if (confirmacion2 == JOptionPane.YES_OPTION) {

				AñadirExtras ae = new AñadirExtras();
				ae.open();
			}

			if (confirmacion2 == JOptionPane.NO_OPTION) {

				int confirmacion = JOptionPane.showConfirmDialog(null,  "¿Está seguro de realizar la compra?", 
						"Confirmación de la compra", JOptionPane.YES_NO_OPTION);
				if (confirmacion == JOptionPane.YES_OPTION) {

					DatosVenta dv = new DatosVenta();
					dv.open();

				} else {
					JOptionPane.showMessageDialog(null, "Se ha cancelado la compra");
				}
			}

		} else {
			JOptionPane.showMessageDialog(null, "Este vehículo no está disponible en nuestro concesionario.");
		}
	}

	public void BusquedaFiltro(Table table) throws SQLException {

		String marcaFiltro = BusquedaFiltros.marca;
		String modeloFiltro = BusquedaFiltros.modelo;
		String colorFiltro = BusquedaFiltros.color;

		table.removeAll();
		Connection cn = Conectar();

		PreparedStatement ps1 = ((Connection) cn).prepareStatement("SELECT * FROM vehiculo "
				+ "WHERE marca LIKE ? "
				+ "AND modelo LIKE ? AND color LIKE ?");
		ps1.setString(1, marcaFiltro + "%");
		ps1.setString(2, modeloFiltro + "%");
		ps1.setString(3, colorFiltro + "%");
		ResultSet rs = ps1.executeQuery();

		String[] titles = { "ID", "Marca", "Modelo", "Color", "Matricula", "Fecha de Entrada",
				"Año de Matriculación", "Precio" };

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}

		while (rs.next()) {
			boolean desguace = rs.getBoolean("desguace");

			if(!desguace) {
				String id = rs.getString("id");
				String marca = rs.getString("marca");
				String modelo = rs.getString("modelo");
				String color = rs.getString("color");
				String matricula = rs.getString("matricula");
				String fecha_entrada = rs.getString("fecha_entrada");
				String anyo_matric =  rs.getString("anyo_matriculacion");
				String precio = rs.getString("precio");
				boolean vendido = rs.getBoolean("vendido");
		    
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
		}

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			table.getColumn(loopIndex).pack();
		}

	}

	public void CargarTablaDesguace(Table table) throws SQLException {
		table.removeAll();
		Connection cn = Conectar();
		PreparedStatement ps1 = ((Connection) cn).prepareStatement("SELECT marca, "
				+ "modelo, matricula, fecha_entrada, anyo_matriculacion, precio, "
				+ "desguace FROM vehiculo");
		ResultSet rs = ps1.executeQuery();

		String[] titles = { "Marca", "Modelo", "Matricula", "Fecha de Entrada", 
				"Año de Matriculación", "Precio" };

		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
			TableColumn column = new TableColumn(table, SWT.NULL);
			column.setText(titles[loopIndex]);
		}

		int contador = 0;

		while (rs.next()) {
			boolean desguace = rs.getBoolean("desguace");

			if(desguace) {

				contador++;
				String marcaDesguace = rs.getString("marca");
				String modeloDesguace = rs.getString("modelo");
				String matriculaDesguace =  rs.getString("matricula");
				String fecha_entradaDesguace =  rs.getString("fecha_entrada");
				String anyo_matricDesguace =  rs.getString("anyo_matriculacion");
				String precioDesguace =  rs.getString("precio");

				if(matriculaDesguace == null) {
					matriculaDesguace = "";
				}
				
				if(anyo_matricDesguace == null) {
					anyo_matricDesguace = "";
				}
				
				TableItem item = new TableItem(table, SWT.NULL);

				item.setText(0, marcaDesguace);
				item.setText(1, modeloDesguace);
				item.setText(2, matriculaDesguace);
				item.setText(3, fecha_entradaDesguace);
				item.setText(4, anyo_matricDesguace);
				item.setText(5, precioDesguace + " €");
				
			}

			for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
				table.getColumn(loopIndex).pack();
			}
		}

		if(contador == 0) {
			JOptionPane.showMessageDialog(null, "No existen vehículos en desguace "
					+ "actualmente.");
		}
	}

	public void ComprobarMatricula() throws SQLException {

		matricula = CompraVehiculos.matricula;

		Connection cn = Conectar();
		PreparedStatement pst = cn.prepareStatement("SELECT * FROM vehiculo WHERE matricula = ?");
		pst.setString(1, matricula);

		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			JOptionPane.showMessageDialog(null, "No pueden existir dos vehículos con matricula repetida.");

		} else {
			DatosCompra dc = new DatosCompra();
			dc.open();
		}
	}

	public void IniciarSesion(Shell shell, Display display) throws SQLException {

		Connection cn = Conectar();
		usuario = Bienvenida.usuario;
		contrasenya = Bienvenida.contrasenya;
		
		PreparedStatement pst = cn.prepareStatement("SELECT * FROM usuarios WHERE usuario = ? AND contrasenya = ?");
		pst.setString(1, usuario);
		pst.setString(2, contrasenya);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
			idUsuario = rs.getInt("id");
			JOptionPane.showMessageDialog(null, "¡Bienvenido/a a Concesionarios Juanlu, " + usuario + "!");
			shell.setVisible(false);
			VistaPrincipal vp = new VistaPrincipal();
			vp.open();
		} else {
			JOptionPane.showMessageDialog(null,"Nombre y contraseña no correctos");

		}
	}
	
	public void NuevaCuenta() throws SQLException {

		try {
		Connection cn = Conectar();
		usuario = CrearCuenta.usuario;
		contrasenya = CrearCuenta.contrasenya;
		email = CrearCuenta.email;

		PreparedStatement pst = cn.prepareStatement("INSERT INTO usuarios (usuario, contrasenya, email) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS); 
		pst.setString(1, usuario);
		pst.setString(2, contrasenya);
		pst.setString(3, email);
		pst.executeUpdate();
		ResultSet rs = pst.getGeneratedKeys();
		rs.next();
        idUsuario = rs.getInt("id");

		JOptionPane.showMessageDialog(null,"Usuario creado correctamente");
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"El usuario ya existe");
		}
	}

	public void UpdateComboBox(Combo comboID) throws SQLException {

		comboID.removeAll();

		Connection cn = Conectar();
		PreparedStatement ps = ((Connection) cn).prepareStatement("SELECT id, desguace, vendido FROM vehiculo");
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			boolean desguace = rs.getBoolean("desguace");
			boolean vendido = rs.getBoolean("vendido");

			if(!desguace) {
				if(!vendido) {
					comboID.add(rs.getString("id"));
				}
			}
		}	    
	}	
	
	public void AnyadirVehiculo() throws SQLException {

		Connection cn = Conectar();
		marca = GestionConcesionario.marcaAnyadir;
		modelo = GestionConcesionario.modeloAnyadir;
		color = GestionConcesionario.colorAnyadir;
		precio = Integer.parseInt(GestionConcesionario.precioAnyadir);
		
		PreparedStatement pst = cn.prepareStatement("INSERT INTO vehiculo (marca, modelo, "
				+ "color, fecha_entrada, precio, vendido, segunda_mano) VALUES (?,?,?,?,?,false,false) ");
		pst.setString(1, marca);
		pst.setString(2, modelo);
		pst.setString(3, color);
		pst.setString(4, fecha_hoy.toString());
		pst.setInt(5, precio);
		
		pst.executeUpdate();
		
		UpdateDesguace();
	}
	
	public void TablaGestion(Table table) throws SQLException {
	
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
		
		for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
	  	      table.getColumn(loopIndex).pack();
	  	    }
	}
	
	public void EliminarVehiculo() throws SQLException {

		Connection cn = Conectar();
		id = Integer.parseInt(GestionConcesionario.idEliminar);

		PreparedStatement pst = cn.prepareStatement("DELETE FROM vehiculo WHERE id = ?");
		pst.setInt(1, id);
		pst.executeUpdate();
	}
	
	public void UpdateDesguace() throws SQLException {

		Connection cn = Conectar();
		
		PreparedStatement pst1 = cn.prepareStatement("UPDATE vehiculo SET desguace = false");
		pst1.executeUpdate();
	}

	public static void InsertarDatosComprador(String nombre, String apellido, String dni, String localidad,
			String direccion, String cuenta_bancaria) {

		id = Integer.parseInt(VentaVehiculos.id);
		usuario = Bienvenida.usuario;
		try {

			Connection cn = Conectar();

			PreparedStatement pst = cn.prepareStatement("SELECT 'dni' FROM cliente WHERE dni = ?");
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();

		if(rs.next()) {
			
			PreparedStatement pst1;
			pst1 = cn.prepareStatement("UPDATE vehiculo SET vendido = true, dni_cliente = ?, fecha_venta = ?, id_trabajador = ? WHERE id = ?");
			pst1.setString(1, dni);
			pst1.setString(2, fecha_hoy.toString());
			pst1.setInt(3, idUsuario);
			pst1.setInt(4, id);
			pst1.executeUpdate();
			
		} else {

			PreparedStatement pst2;
			pst2 = cn.prepareStatement("INSERT INTO cliente (nombre, apellido, "
					+ "dni, localidad, direccion, cuenta_bancaria) VALUES (?,?,?,?,?,?)");

			pst2.setString(1, nombre);
			pst2.setString(2, apellido);
			pst2.setString(3, dni);
			pst2.setString(4, localidad);
			pst2.setString(5, direccion);
			pst2.setString(6, cuenta_bancaria);
			pst2.executeUpdate();

			PreparedStatement pst1;
			pst1 = cn.prepareStatement("UPDATE vehiculo SET vendido = true, dni_cliente = ?, fecha_venta = ?, id_trabajador = ? WHERE id = ?");
			pst1.setString(1, dni);
			pst1.setString(2, fecha_hoy.toString());
			pst1.setInt(3, idUsuario);
			pst1.setInt(4, id);
			pst1.executeUpdate();
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void InsertarDatosVendedor(String nombre, String apellido, String dni, String localidad,
			String direccion, String cuenta_bancaria) {
		
			Connection cn = Conectar();
		try {
			
			PreparedStatement pst = cn.prepareStatement("SELECT 'dni' FROM cliente WHERE dni = ?");
			pst.setString(1, dni);
			ResultSet rs = pst.executeQuery();

		if(rs.next()) {
			InsertarDatos();
			
		} else {
			PreparedStatement pst3;
			pst3 = cn.prepareStatement("INSERT INTO cliente (nombre, apellido, "
					+ "dni, localidad, direccion, cuenta_bancaria) VALUES (?,?,?,?,?,?)");

			pst3.setString(1, nombre);
			pst3.setString(2, apellido);
			pst3.setString(3, dni);
			pst3.setString(4, localidad);
			pst3.setString(5, direccion);
			pst3.setString(6, cuenta_bancaria);
			pst3.executeUpdate();
			InsertarDatos();
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}