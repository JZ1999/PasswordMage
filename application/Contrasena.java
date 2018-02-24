
package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Contrasena {
	private Stage window;

		//Se llama cuando le dan en crear contraseña
		public boolean Display() {
			try {
				window = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("Crear.fxml"));
				Scene scene = new Scene(root);


				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

				window.setScene(scene);
				window.setMinWidth(350);
				window.initModality(Modality.APPLICATION_MODAL);
				window.showAndWait();



			} catch(Exception e) {
				e.printStackTrace();
			}
			return true;

		}
}