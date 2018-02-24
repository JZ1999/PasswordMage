package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.JOptionPane;

public class Controller {
	//Create password section==========================================================================
	@FXML
	private Button ListoBTN;
	@FXML
	private Label title;
	@FXML
	private TextField pwd;
	@FXML
	private TextField pwdConfirm;
	@FXML
	private TextField userName;

	private Parent root;

	public void Verify(ActionEvent e) {
		if(PasswordsMatch() && CheckFields()) {

			Success();
		}else {
			//Alert.Display("Error: Las contraseñas no coinciden.");
			if(!pwd.getText().equals(pwdConfirm.getText())) {
				String msg = Control.errors.getError(1);
				JOptionPane.showMessageDialog(null, msg);
			}

		}
	}

	private boolean PasswordsMatch() {
		String password1 = pwd.getText();
		String password2 = pwdConfirm.getText();
		return password1.equals(password2);
	}

	private boolean CheckFields() {
		boolean Null = (
				userName.getText().isEmpty() && pwd.getText().isEmpty() && pwd.getText().isEmpty());


		if(Null) {
			JOptionPane.showMessageDialog(null, Control.errors.getError(6));
			return false;
		}
		boolean wrongLength = !(userName.getText().length() > 2);

		if(wrongLength) {
			JOptionPane.showMessageDialog(null, Control.errors.getError(7));
			return false;
		}

		wrongLength = !(pwd.getText().length() >= 9);
		if(wrongLength) {
			JOptionPane.showMessageDialog(null, Control.errors.getError(8));
			return false;
		}
		return true;




	}
	//===========================================================================================================


	//Se llama al crear una contrasena exitosamente
	public void Success(){

		try {
			//Secure.createKey();
			Control.guardarUsuario(userName.getText(), pwd.getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		Scene thisScene = ListoBTN.getScene();
		Window aux = thisScene.getWindow();
		Stage window = (Stage)aux;

		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene menu = new Scene(root);

		menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(menu);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}

