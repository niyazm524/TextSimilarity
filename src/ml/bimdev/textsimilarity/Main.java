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
        Result[] results = new Result[count];
        for (int i = 0; i < count; i++) {
            System.out.print("It's a text or a file? [T/f]  ");
            String line;
            if(!sc.next().equals("f")) {
                System.out.println("Enter your text line\n");
                do { line = sc.nextLine(); } while (line.isEmpty());
                providers[i] = new SimpleTextProvider(line);
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
        System.out.print("Now enter your search query. Is it just text or file? [T/f]  ");
        String line;
        if(!sc.next().equals("f")) {
            System.out.println("Enter your text line\n");
            do { line = sc.nextLine(); } while (line.isEmpty());
            query = new SimpleTextProvider(line);
        } else {
            System.out.print("Enter filename: ");
            query = new FileTextProvider(new File(sc.nextLine()));
        }

        for (int i = 0; i < providers.length; i++) {
            results[i] = new Result(i, providers[i], analyzer.analyze(query, providers[i]));
        }
        Arrays.sort(results);

        int i = 0;
        for(Result result : results) {
            System.out.printf("%s) %s", result.id, result.toString());
        }
    }
}
