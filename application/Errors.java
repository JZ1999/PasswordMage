package application;

import java.util.Hashtable;

public class Errors {

	private static Hashtable<Integer, String> Errors = new Hashtable<Integer, String>();


	public Errors(){

		Errors.put(1, "Las contrase�as no coinciden");
		Errors.put(2, "La contrase�a no se pudo guardar");
		Errors.put(3, "La contrase�a o la etiqueta estan vacias");

		//la 4 y 5 van juntas solo se usan para cosas diferentes
		Errors.put(4, "No se pudo eliminar la etiqueta");
		Errors.put(5, "No se ha podido borrar");

		Errors.put(6, "Dejaste algunas de las cajas de texto en vacio");
		Errors.put(7, "El tama�o del nombre de Usuario debe ser mayor a 1");
		Errors.put(8, "El tama�o de la contrase�a debe de ser mayor a 8");
		Errors.put(9, "Nombre de usuario o contrase�a equivocada");
		Errors.put(10, "Debes de marcar minimo una caja con un check");

	}

	//Retornar un error formateado
	public String getError(int numeroDeError) {
		String error = Errors.get(numeroDeError);
		String msg = String.format("Error0%d: %s", numeroDeError, error);
		return msg;
	}

}
