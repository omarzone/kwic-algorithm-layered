package com.kwic;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {   
        
        String phrase = "La casa AzUL es muy GRANDE";
        System.out.println("Frase inicial: " + phrase);

        System.out.println("---------Convertir a minisculas---------");
        System.out.println(converToLowerCase(phrase));

        String phraseLowerCase = converToLowerCase(phrase);

        String[] phraseSplited = phraseLowerCase.split(" ");

        System.out.print("-------Eliminar palabras-vacias---------");
        
        System.out.println("");
        ReadFile file = new ReadFile(phraseSplited);
        
    }


    static String converToLowerCase(String text){
        return text.toLowerCase();
    }
}
