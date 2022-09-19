package com.kwic.BusinessLogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kwic {

    private String phrase;
    private String[] phraseSplited;
    private String phraseLowerCase;
    private List<String> stopWords;
    private ArrayList<String> phraseWithoutEmptyWords = new ArrayList<String>();

    // Almacena las combinaciones del listado de palabras
    private ArrayList<ArrayList<String>> phraseCombinationArray = new ArrayList<ArrayList<String>>();
    // Almacena las combinaciones de frases completas
    private ArrayList<String> phraseCombinations = new ArrayList<String>();

    public Kwic(String phrase, List<String> stopWords) {
        this.phrase = phrase;
        this.stopWords = stopWords;
    }

    public void process(){
        this.phraseLowerCase = converToLowerCase(phrase);
        this.phraseSplited = phraseLowerCase.split(" ");
        this.removeStopWords();
        this.combinePhrase();
        this.appendPhraseWords();
        Collections.sort(this.phraseCombinations);
        
       
    }

    public void removeStopWords() {
        for (String phraseWord : this.phraseSplited) {
            // Bandera para saber si la frase fue encontrada
            boolean phraseFound = false;
            // Compara cada palabra de la frase completa con el listado de palabras vacias
            for (String word : this.stopWords) {
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
                this.phraseWithoutEmptyWords.add(phraseWord);
            }
        }
    }

    static String converToLowerCase(String text) {
        return text.toLowerCase();
    }

    public void combinePhrase() {
        // Creamos una copia del array original para modificarla
        ArrayList<String> modificablePhraseWords = (ArrayList<String>) this.phraseWithoutEmptyWords.clone();
        for (int i = 0; i < this.phraseWithoutEmptyWords.size(); i++) {
            // Antes de realizar el primer intercambio, guardamos la frase en el array de
            // combinaciones
            if (i == 0) {
                phraseCombinationArray.add(modificablePhraseWords);
            }
            // Intercambiamos el orden del primer elemento en el array
            modificablePhraseWords.add(modificablePhraseWords.get(0));
            modificablePhraseWords.remove(0);
            // Comparamos si el array modificado es igual al array con las
            // palabrasOriginales
            if (phraseWithoutEmptyWords.equals(modificablePhraseWords)) {
                // System.out.println("Frases identicas");
                // Si durante la comparacion se encuentran coincidencias, el ciclo se rompe
                break;
            } else {
                // Inserta la combinacion al array de combinaciones
                phraseCombinationArray.add((ArrayList<String>) modificablePhraseWords.clone());
            }
        }
    }

    public void appendPhraseWords() {
        for (ArrayList<String> phraseArray : phraseCombinationArray) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String phraseWord : phraseArray) {
                stringBuilder.append(phraseWord + " ");
                //System.out.print(phraseWord + " ");
            }
            phraseCombinations.add(stringBuilder.toString());
            //System.out.println(" ");
        }
    }


    public void printOrderedPhraseCombinations() {
       
        for (String phraseCombination : this.phraseCombinations) {
          System.out.println(phraseCombination);
        }
      }

}
