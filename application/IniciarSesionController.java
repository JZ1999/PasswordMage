package application;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class IniciarSesionController {

	@FXML
	private Button BorrarBTN;
	@FXML
	private Button iniciarBTN;
	@FXML
	private TextField nombreDeUsuario;
	@FXML
	private TextField contra;

	@FXML
	private void iniciarSesion(ActionEvent e) {
		boolean siInicia = Control.iniciarSesion(nombreDeUsuario.getText(), contra.getText());
		if(siInicia) {
			Success();
		}else {
			String msg = Control.errors.getError(9);
			JOptionPane.showMessageDialog(null, msg);
		}
	}

	//Se llama al iniciar sesion exitosamente
	private void Success(){

		try {
		Scene thisScene = iniciarBTN.getScene();
		Window aux = thisScene.getWindow();
		Stage window = (Stage)aux;

		Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene menu = new Scene(root);

		menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(menu);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	@FXML
	private void BorrarCuenta() {
		int confirmar = JOptionPane.showConfirmDialog(null,"Seguro que deseas borrar esta cuenta junto con todas sus contraseñas?");

		if(confirmar == 0) {
			BorrarBaseDeDatos();
		}

	}


	private void BorrarBaseDeDatos() {
		try {
			//File db = new File("DB/passWords.sqlite");
			FileOutputStream db = new FileOutputStream("DB/passWords.sqlite");
			//db.setWritable(true);
			db.flush();
			db.close();

			/*if(db.delete()) {
				System.out.println("Si");
			}else {
				System.out.println("Ni");
			}
			System.out.println(db.exists());

			db = new File("DB/.gen");

			if(db.delete()) {
				System.out.println("Si");
			}else {
				System.out.println("Ni");
			}*/


			Control.MakeAccount(iniciarBTN);
			System.out.println("Reset");
			//PrintWriter writer = new PrintWriter("src/DB/passWords.sqlite", "UTF-8");
			//writer.close();


		}catch(Exception e) {
			System.err.print(e);
		}
	}
}
