package application;

import java.util.Random;

public class Tips {


	static String[] Tips = {

			"Nunca utilizar solo n�meros",
			"No uses solo letras ni solo palabras",
			"Optar siempre por combinaciones alfanum�ricas",
			"Intercalar signos de teclado",
			"Las mejores son las claves aleatorias",
			"No utilizar la misma contrase�a para todo",
			"Tener una contrase�a con minimo 12 caracteres",
			"No utilizar palabras que tenga alguna relacion con si mismo"

	};

	static public String RandTip(){
		Random random = new Random();
		int tamDeTips = Tips.length;//Menos 1 por comienzo de indice 0
		int randNumber = random.nextInt(tamDeTips);

		String randTip = Tips[randNumber];

		String formatted = String.format("Tip 0%d: %s", randNumber+1, randTip);

		return formatted;
	}
}
