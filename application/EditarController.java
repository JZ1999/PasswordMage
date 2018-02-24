package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditarController implements Initializable{

	@FXML
	private Button listoBTN;

	ToggleGroup group = new ToggleGroup();
	@FXML
	private ToggleButton etiquetaBTN;
	@FXML
	private ToggleButton contrasenaBTN;

	@FXML
	private TextField contentTF;

	private String[] content;

	@FXML
	private void Listo(ActionEvent e) {
		if(etiquetaBTN.isSelected())
			Control.UpdateInfo(contentTF.getText(), true, Control.getID(content[0]));
		else if(contrasenaBTN.isSelected())
			Control.UpdateInfo(contentTF.getText(), false, Control.getID(content[0]));
		else if(contentTF.getText().length() <= 1) {
			JOptionPane.showMessageDialog(null, "El campo de texto debe tener el tamaño mayor que 1");
			return;
		}else {
			JOptionPane.showMessageDialog(null, "Debes seleccionar una de las dos opciones");
			return;
		}
		String msg = (etiquetaBTN.isSelected()) ? "Has cambiado la etiqueta exitosamente" : "Has cambiado la contraseña exitosamente";
		JOptionPane.showMessageDialog(null, msg);
		Stage ventana = (Stage) etiquetaBTN.getScene().getWindow();
		ventana.close();
	}

	@FXML
	private void Cambiar(ActionEvent e) {
		if(etiquetaBTN.isSelected())
			contentTF.setText(content[0]);
		else if(contrasenaBTN.isSelected())
			contentTF.setText(content[1]);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		content = Control.getInfoEdit();


		etiquetaBTN.setToggleGroup(group);
		contrasenaBTN.setToggleGroup(group);

		etiquetaBTN.setSelected(true);
	}
}
