package com.kwic;

import java.io.IOException;
import java.util.Scanner;

import com.kwic.BusinessLogic.Kwic;
import com.kwic.Data.File;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe una frase:");
        String phrase = sc.nextLine();
        System.out.println("Frase inicial: " + phrase);
        File file = new File();
        Kwic kwic = new Kwic(phrase, file.getStopWords());
        kwic.process();
        System.out.println("Combinaciones: ");
        kwic.printOrderedPhraseCombinations();
        sc.close();
    }

}
