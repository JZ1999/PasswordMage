package application;

import java.util.Random;
import java.util.ArrayList;

class createPW{
	/*
	Este enum son las combinaciones diferentes para crear
	una contrasena, i.e LN hace una contrasena usando el
	alfabeto de USA y los numeros del 0-9
	*/
    static public enum Chars{
        //L = US alphabet
        //N = 0-9
        //U = underscores _
        //A = @
        L,N,U,A;
    }

    //From 12-20
    private int len = 12;//Default 12

    private ArrayList<Chars> dict = new ArrayList<Chars>();//Contiene los Chars

    private String pwd = "";

    private Random randNum;//Da el atributo de aleatorio a la contrasena

    private ArrayList<Character> Dictionary;//Contiene los caracteres que usara


    //Diferentes constructores dependiendo de los Chars(enum) que quiera usar
    createPW(){

        this.Dictionary = CreateDictionaries();
    }
    createPW(int len){

        this.len = len;
        this.Dictionary = CreateDictionaries();
    }
    createPW(int len, ArrayList<Chars> dict){

        this.len = len;
        this.dict = dict;
        this.Dictionary = CreateDictionaries();
    }

    String getpwd(){return pwd;}

    void setpwd(){
        int aux = len;
        while(aux-- != 0){
            randNum = new Random();

            pwd+=Dictionary.get(randNum.nextInt(Dictionary.size()));
        }
    }

    //Basado en los Chars selecionados en el objeto
    //Se crea el diccionario
    ArrayList<Character> CreateDictionaries(){

        int counterLower = 65;//Guarda los valores asccii de las letras mayusculas
        int counterUpper = 97;//Guarda las mayusculas
        int counterNums = 48;//Guarda los numeros
        int underscore = 95;//Guarda la barra baja
        int at = 64;//guarda el arroba
        ArrayList<Character> DictToReturn = new ArrayList<Character>();
        for(Chars value : dict){
            switch(value){
                case L:
                    //Este while mete todas las mayusculas
                    while(true){
                        if(counterLower  == 91){break;}//90 es Z

                        DictToReturn.add((char) counterLower );
                        counterLower++;

                    }
                    //Este es para las minusculas
                    while(true){
                        if(counterUpper  == 123){break;}//122 es z

                        DictToReturn.add((char) counterUpper );
                        counterUpper++;
                    }
                    break;
                case N:
                    //Meter todos los numeros
                    while(true){
                        if(counterNums == 58){break;}

                        DictToReturn.add((char) counterNums);
                        counterNums++;
                    }
                    break;
                case U:
                    DictToReturn.add((char) underscore);
                    break;
                case A:
                    DictToReturn.add((char) at);
                    break;
            }
        }


        return DictToReturn;

    }

}
