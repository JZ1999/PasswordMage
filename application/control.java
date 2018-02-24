package application;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Button;

/*
 * La clase control es la clase que debe correr
 * para comenzar el programa. Controla todo el sistema uniendo todas las clases.
 */

class Control {

    static Scanner input = new Scanner(System.in);
    private static String pwd;//password
    private static String name;
    private static SQLiteJDBC SQLite;//Se hace un objeto de sqlite para pedir
    								//, borrar, ver, etc.
    private static GUI gui;//Un objeto para activar y controlar el GUI

    //Para poder conseguir las etiquetas de la base de datosy usarlas en la ListView del GUI
    private static ArrayList<String> etiquetas;

    //Manejar los mensajes de errores
    public static Errors errors;

    public static boolean tieneCuenta;

    private static String[] infoToEdit = new String[2];


    public static void main(String args[]){


        SQLite = new SQLiteJDBC();
        tieneCuenta = SQLite.revisarCuenta();
        if(tieneCuenta)
        	etiquetas = SQLite.Get();

        errors = new Errors();

        //Revisando si el programa se llam� con la bandera que indicar� que esta en modo TUI
        if(args != null && args.length > 0) {
	        boolean accion = Base();
	        while(accion){
	            //Agarrar la respuesta del usuario
	            String res = getInput_Output(OpcionesDeInput.DeseaGuardar);
	            if(res.toLowerCase().equals("si")){
	                //Guardar a DB
	                System.out.println("La etiqueta de la contraseña: ");
	                System.out.print(">");
	                name = input.next();

	                boolean insertion = SQLite.Insert(name, pwd);
	                String printStatement = insertion ? "Guardado Exitosamente" : "Error01: No se pudo guardar";
	                System.out.println(printStatement);
	                break;
	            }else if(res.toLowerCase().equals("no")){
	                Base();
	            }
	        }
        }else {
        	//Creacion e inicializacion del GUI
        	gui = new GUI();


        	GUI.main(args);
        }

    }

    //Funciones Para comunicarse con Control, MenuController y SQLiteJDBC=========================================
    public static void setEtiquetaAndPWD(String str1, String str2) {
    	infoToEdit[0] = str1;//La etiqueta
    	infoToEdit[1] = str2;//La pwd

    }

    public static String[] getInfoEdit() {

		return infoToEdit;
    }

    public static boolean UpdateInfo(String info, boolean name,String id) {
    	if(name)
    		return SQLite.UpdateName(info, id);
    	return SQLite.UpdatePWD(info, id);

    }

    public static String getID(String name) {
    	return SQLite.GetID(name);
    }

    public static void MakeAccount(Button buttonToGetScene) {

    	try {
			gui.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}

    	SQLite = new SQLiteJDBC(1);
    	gui = new GUI();
    	gui.Display(buttonToGetScene);
    }

    public static String getContraGUI(String etiqueta) {
    	return SQLite.Get(etiqueta);
    }

    public static boolean delContra(String etiqueta) {
    	return SQLite.Delete(etiqueta);
    }

    public static boolean agregarContra(String etiqueta, String pwd) {
    	return SQLite.Insert(etiqueta, pwd);
    }

    @SuppressWarnings("unchecked")
	public static ArrayList<String> getEtiquetas() {

    	etiquetas = SQLite.Get();

    	if(etiquetas == null)
    		return new ArrayList<String>();
    	return (ArrayList<String>) etiquetas.clone();
    }

    public static boolean guardarUsuario(String nombre, String contra) {

    	return SQLite.guardaUsuario(nombre, contra);
    }

    public static boolean iniciarSesion(String nombre, String contra) {
    	return SQLite.iniciarUsuario(nombre, contra);
    }



    //==========================================================================================================

    //Este enum solo se usa en modo TUI
    static enum OpcionesDeInput{
        PedirTam,DeseaGuardar;
    }


    //Base es para poder crear y pedir setear la contraseña
    //Ocupamos que sea reusable
    static boolean Base(){
        System.out.println("\t\tBienvenido");
        System.out.println("\t\nComandos:\n\t\tC: Conseguir Contraseña\n\t\tS: Setear Contraseña\n");
        //Al darle G se dara la contraseña segun la etiqueta
        //Al darle S se setearaF una nueva contraseña a la base de datos
        while(true){
            System.out.print(">");
            String commando = input.next();
            commando = commando.toUpperCase();
            if(commando.equals("C")){
                System.out.println("De la etiqueta de la contraseña: ");
                System.out.print(">");
                String etiqueta = input.next();
                String Contrasena = SQLite.Get(etiqueta);
                System.out.println(Contrasena);
                return false;

            }else if(commando.equals("S")){
                int len = Integer.parseInt(getInput_Output(OpcionesDeInput.PedirTam));

                createPW PasswordObject = new createPW(len);

                PasswordObject.setpwd();//setear la contrasena
                pwd = PasswordObject.getpwd();
                return true;
            }else{
                System.out.println("Error03: Commando Equivocado");
            }
        }
    }


    private static String getInput_Output(OpcionesDeInput opcion){
        if(opcion == OpcionesDeInput.PedirTam){
            System.out.println("El tamaño de la contraseña: ");
            System.out.print(">");
            int len = input.nextInt();
            if(len < 12)
                len = 12;
            return Integer.toString(len);
        }

        if(opcion == OpcionesDeInput.DeseaGuardar){
            System.out.printf("Contraseña Generada: %s\n\n",pwd);
            System.out.println("¿Deseas guardar esta contraseña?");
            System.out.print(">");
            String respuesta = input.next();
            if(respuesta.equals("si")){
                return "si";
            }else{
                return "no";
            }
        }
        return "Error";
    }
}