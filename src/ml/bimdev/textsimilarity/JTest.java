package ml.bimdev.textsimilarity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.kpfu.itis.textsimilarity.JaccardTextAnalyzer;
import ru.kpfu.itis.textsimilarity.SimpleTextProvider;
import ru.kpfu.itis.textsimilarity.TextAnalyzer;
import ru.kpfu.itis.textsimilarity.TextProvider;

public class JTest {
    TextAnalyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new JaccardTextAnalyzer();
    }

    @Test
    public void testTwoStrings() {
        TextProvider tp1 = new SimpleTextProvider("some string with 5 words");
        TextProvider tp2 = new SimpleTextProvider("5 words with some string");
        Assert.assertEquals(analyzer.analyze(tp1, tp2), 1.0d, 0.01);
    }

    @Test
    public void testHalfSimilarity() {
        TextProvider tp1 = new SimpleTextProvider("one two three four");
        TextProvider tp2 = new SimpleTextProvider("three four five six");
        Assert.assertEquals(analyzer.analyze(tp1, tp2), .33d, 0.01);
    }

    @Test
    public void testZeroSimilarity() {
        TextProvider tp1 = new SimpleTextProvider("one two three four");
        TextProvider tp2 = new SimpleTextProvider("five, six");
        Assert.assertEquals(analyzer.analyze(tp1, tp2), 0.0d, 0.01);
    }



}
