package application;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import application.createPW.Chars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class CrearController {

	@FXML
	private TextField newContra;
	@FXML
	private TextField newEtiqueta;
	@FXML
	private Button guardarBTN;
	@FXML
	private Button generarBTN;
	@FXML
	private CheckBox AlfabetoCHK;
	@FXML
	private CheckBox NumerosCHK;
	@FXML
	private CheckBox ArrobaCHK;
	@FXML
	private CheckBox BarrabajaCHK;
	@FXML
	private Slider tamanoSL;

	private String pwd;//Guarda la password
	private String etiqueta;



	@FXML//Genera una contrasena y la coloca en el Label
	private void Generar(ActionEvent e) {

		ArrayList<Chars> dict = crearDiccionario();
		if(dict.size() == 0) {
			String msg = Control.errors.getError(10);
			JOptionPane.showMessageDialog(null, msg);
			return;
		}

		double len = tamanoSL.getValue();
		//Crear y conseguir contrasena
		createPW PasswordObject = new createPW((int)len, dict);
		PasswordObject.setpwd();
		pwd = PasswordObject.getpwd();
		//Crear y conseguir contrasena

		newContra.setText(pwd);
	}

	@FXML//Guarda la contrasena a la base de datos
	private void Guardar() throws HeadlessException {
		etiqueta = newEtiqueta.getText();
		pwd = newContra.getText();

		//Revisa si los dos no estan vacios
		Check noVacios = (String a, String b) -> {return (!(a.isEmpty()) && !(b.isEmpty()));};


		String msg;
		if(noVacios.check(pwd, etiqueta)) {

			try {
				if(Control.agregarContra(etiqueta, pwd)) {
					msg = "Contraseña Guardada Exitosamente";
					JOptionPane.showMessageDialog(null, msg);
					Stage ventana = (Stage) newEtiqueta.getScene().getWindow();
					ventana.close();
				}else {
					//msg = "Error: Contraseña no se pudo guardar";
					msg = Control.errors.getError(2);
					JOptionPane.showMessageDialog(null, msg);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			//msg = "Error: La contraseña o la etiqueta estan vacias";
			msg = Control.errors.getError(3);
			JOptionPane.showMessageDialog(null, msg);
		}
	}

	private ArrayList<Chars> crearDiccionario(){
		ArrayList<Chars> out = new ArrayList<Chars>();
		if(AlfabetoCHK.isSelected())
			out.add(Chars.L);
		if(NumerosCHK.isSelected())
			out.add(Chars.N);
		if(ArrobaCHK.isSelected())
			out.add(Chars.A);
		if(BarrabajaCHK.isSelected())
			out.add(Chars.U);

		return out;
	}

}


