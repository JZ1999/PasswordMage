package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Editar {
	public boolean Display() {
		try {
			Stage window = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("Editar.fxml"));
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
