package ml.bimdev.textsimilarity;


import ru.kpfu.itis.textsimilarity.*;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("It's a text similarity program. Enter how many texts you have: ");
        int count = sc.nextInt();
        TextProvider[] providers = new TextProvider[count];
        HashMap<Integer, Double> results = new HashMap<>();
        for (int i = 0; i < count; i++) {
            System.out.println("It's a text or a file? [T/f]  ");
            if(!sc.next().equals("f")) {
                System.out.println("Enter your text line\n");
                providers[i] = new SimpleTextProvider(sc.nextLine());
            } else {
                System.out.print("Enter filename: ");
                providers[i] = new FileTextProvider(new File(sc.nextLine()));
            }
        }
        System.out.println("Okay. Now select preferred method (Jaccard or Cosine) [J/c]: ");
        TextAnalyzer analyzer;
        if(!sc.next().equals("c"))
            analyzer = new JaccardTextAnalyzer();
        else
            analyzer = new CosineTextAnalyzer();

        TextProvider query;
        System.out.println("Now enter your search query. Is it just text or file? [T/f]  ");
        if(!sc.next().equals("f")) {
            System.out.println("Enter your text line\n");
            query = new SimpleTextProvider(sc.nextLine());
        } else {
            System.out.print("Enter filename: ");
            query = new FileTextProvider(new File(sc.nextLine()));
        }

        for (int i = 0; i < providers.length; i++) {
            results.put(i, analyzer.analyze(query, providers[i]));
        }

        // todo: sort results by value and sout
    }
}
