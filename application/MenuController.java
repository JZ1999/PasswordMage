package application;

import java.awt.HeadlessException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;



public class MenuController implements Initializable{
	@FXML
	private ListView<String> listV;
	@FXML
	private Button BorrarBTN;
	@FXML
	private Button EditarBTN;
	@FXML
	private Label contrasena;
	@FXML
	private Button crearBTN;
	@FXML
	private Label tipL;


	private String etiquetaSeleccionada;

	private void changeTip() {
		String tipNuevo = Tips.RandTip();
		tipL.setText(tipNuevo);
	}

	//Se llama para actualizar o crear la lista de etiquetas
	public void createList() {

		//misceláneamente cambiando el tip

		changeTip();

		listV.getItems().clear();
		System.out.println("11");
		ArrayList<String> etiquetas = Control.getEtiquetas();
		System.out.println("22");
		listV.getItems().addAll(etiquetas);

	}

	@FXML
	public void MostrarContra(MouseEvent e) {

		if(listV.getSelectionModel().getSelectedItem() != null) {
			String etiqueta = listV.getSelectionModel().getSelectedItem();

			String contra = null;
			try {
				contra = Control.getContraGUI(etiqueta);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			etiquetaSeleccionada = etiqueta;
			contrasena.setText(contra);
		}
	}

	public void Borrar(ActionEvent e) throws SQLException {

		try {
			try {
				if(Control.delContra(etiquetaSeleccionada)) {
					String mensaje = String.format("Estas seguro que deseas borrar %s",etiquetaSeleccionada);
					int confirmar = JOptionPane.showConfirmDialog(null, mensaje);
					if(confirmar == 0) {
						mensaje = String.format("Has borrado %s exitosamente",etiquetaSeleccionada);
						JOptionPane.showMessageDialog(null, mensaje);
						contrasena.setText("");
						createList();
					}
				}else {
					//String mensaje = String.format("No se a podido borrar %s",etiquetaSeleccionada);
					String error = Control.errors.getError(5);
					String mensaje = String.format("%s %s",error, etiquetaSeleccionada);
					JOptionPane.showMessageDialog(null, mensaje);

					mensaje = Control.errors.getError(4);
					throw new SQLException(mensaje);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void Editar(ActionEvent e) {
		Control.setEtiquetaAndPWD(etiquetaSeleccionada, contrasena.getText());
		Editar editar = new Editar();
		boolean cerrandoEditar = editar.Display();

		if(cerrandoEditar) {
			createList();
		}

	}

	//Abre una ventana nueva para crear una contrasena nueva
	public void Crear(ActionEvent e) {
		Contrasena ventanaNueva = new Contrasena();
		boolean cerrandoVentanaDeCrear = ventanaNueva.Display();
		if(cerrandoVentanaDeCrear) {
			createList();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Se debe llamar esta funcion al inicializarse el GUI para llenar la lista al entrar
		changeTip();
		ArrayList<String> etiquetas = Control.getEtiquetas();

		listV.getItems().addAll(etiquetas);
		//createList();
	}

	//Main menu section==========================================================================
}

