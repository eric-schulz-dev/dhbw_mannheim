package semester4.Aufgabe3;

public class Koinzidenzindex {

    public static void main (String[]args){

        String text = "The first rule of Fight Club is: you do not talk about Fight Club";

        findLetterSpread(text);

        //TESt

    }

    public static void findLetterSpread(String s){
        s = s.replaceAll("\\s", "");
        System.out.println(s);


    }
}
