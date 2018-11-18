package ml.bimdev.textsimilarity;


import ru.kpfu.itis.textsimilarity.SimpleTextProvider;
import ru.kpfu.itis.textsimilarity.TextAnalyzer;

public class Main {
    public static void main(String[] args) {
        TextAnalyzer analyzer = new CosineTextAnalyzer();
        String s1 = "Мама мыла раму, а я ничего не делал";
        String s2 = "Я совсем ничего не делал";

        double similarity = analyzer.analyze(new SimpleTextProvider(s1), new SimpleTextProvider(s2));
        System.out.println("similarity = " + Math.round(similarity*10000) / 100d + "%");
    }
}
