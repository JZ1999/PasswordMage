package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

//Esta es la ventana de iniciar sesion o crear cuenta
public class GUI extends Application {

	private Stage window;
	private boolean tieneCuenta;

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		//Revisa base de datos para ver si ya a creado una cuenta
		tieneCuenta = Control.tieneCuenta;
		if(tieneCuenta)
			iniciarSesion();
		else
			crearCuenta();

	}


	public void setRevisarCuenta(boolean bool) {
		tieneCuenta = bool;
		}


	//Para abrir ventana de crear cuenta
	private void crearCuenta() {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root);


			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.getIcons().add(new Image("logoPasswordMage.png"));
			window.setScene(scene);
			window.show();
			window.setMinWidth(350);




		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void Display(Button button) {


		try {
		Scene thisScene = button.getScene();
		Window aux = thisScene.getWindow();
		Stage window = (Stage)aux;

		Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene menu = new Scene(root);

		menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		window.setScene(menu);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Para abrir ventana de crear cuenta
	private void iniciarSesion() {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("IniciarSesion.fxml"));
			Scene scene = new Scene(root);


			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.getIcons().add(new Image("logoPasswordMage.png"));
			window.setScene(scene);
			window.show();
			window.setMinWidth(350);


		} catch(Exception e) {
			System.err.println(e);
			//e.printStackTrace();
		}
	}




	public static void main(String[] args) {
		launch(args);
	}
}

