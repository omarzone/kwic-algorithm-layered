package com.kwic;

// Import this class to handle errors
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadFile {

  ArrayList<String> phraseWithoutEmptyWords = new ArrayList<String>();
  ArrayList<ArrayList<String>> phraseCombinationArray = new ArrayList<ArrayList<String>>();

  public ReadFile(String[] phrase) throws IOException {
    // Guarda cada linea del archivo en una lista de strings
    List<String> content = Files.readAllLines(Paths.get("src/main/java/com/kwic/spanish.txt"));

    // Compara cada palabra de la frase completa
    for (String phraseWord : phrase) {

      // Bandera para saber si la frase fue encontrada
      boolean phraseFound = false;

      // Compara cada palabra de la frase completa con el listado de palabras vacias
      for (String word : content) {
        // System.out.println(word);
        // Si la palabra de la frase es igual a la palabra del listado, cambias bandera
        // a true y cortas el ciclo.
        if (phraseWord.equals(word)) {
          // System.out.println("La palabra " + phraseWord + " se compara con " + word );
          phraseFound = true;
          break;
        }
      }
      // En dado caso que la palabra no haya sido encontrada, la agregas a un
      // arraylist el cual no contiene palabras vacias.
      if (!phraseFound) {
        phraseWithoutEmptyWords.add(phraseWord);
      }
    }

    for (String word : phraseWithoutEmptyWords) {
      System.out.println(word);
    }

    combinePhrase();

    System.out.println("----------COMBINATIONS---------");

    printPhraseCombinations();

  }

  public void combinePhrase() {

    //Creamos una copia del array original para modificarla
    ArrayList<String> modificablePhraseWords = (ArrayList<String>)phraseWithoutEmptyWords.clone();


    for (int i = 0; i < phraseWithoutEmptyWords.size(); i++) {
      //Antes de realizar el primer intercambio, guardamos la frase en el array de combinaciones
      if(i==0){
        phraseCombinationArray.add(modificablePhraseWords);
      }
      //Intercambiamos el orden del primer elemento en el array
      modificablePhraseWords.add(modificablePhraseWords.get(0));
      modificablePhraseWords.remove(0);
      //Comparamos si el array modificado es igual al array con las palabrasOriginales
      if(phraseWithoutEmptyWords.equals(modificablePhraseWords)){
        System.out.println("Frases identicas");
        //Si durante la comparacion se encuentran coincidencias, el ciclo se rompe
        break;
      }else{
        //Inserta la combinacion al array de combinaciones
        phraseCombinationArray.add((ArrayList<String>)modificablePhraseWords.clone());
      }
    }

  }


  //Realiza la impresion de la combinacion de frases
  public void printPhraseCombinations(){
    
    for(ArrayList<String> phraseArray : phraseCombinationArray  ){
      for(String phraseWord : phraseArray){
        System.out.print(phraseWord + " ");
      }
      System.out.println(" ");
    }
  }

}
