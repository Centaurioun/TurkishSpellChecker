package SpellChecker;

import Corpus.Sentence;
import MorphologicalAnalysis.FsmMorphologicalAnalyzer;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SimpleSpellCheckerTest {

    @org.junit.Test
    public void testSpellCheck() throws FileNotFoundException {
        FsmMorphologicalAnalyzer fsm = new FsmMorphologicalAnalyzer();
        SimpleSpellChecker simpleSpellChecker = new SimpleSpellChecker(fsm);
        Scanner input = new Scanner(new File("misspellings.txt"));
        while (input.hasNext()){
            String misspelled = input.next();
            String corrected = input.next();
            assertEquals(corrected, simpleSpellChecker.spellCheck(new Sentence(misspelled)).toString());
        }
        input.close();
    }

    @Test
    public void testSpellCheck2() {
        Sentence[] original = {
                new Sentence("yeni sezon başladı"),
                new Sentence("sırtıkara adındaki canlı , bir balıktır"),
                new Sentence("siyah ayı , ayıgiller familyasına ait bir ayı türüdür"),
                new Sentence("yeni sezon başladı gibi"),
                new Sentence("alışveriş için markete gitti"),
                new Sentence("küçük bir yalıçapkını geçti"),
                new Sentence("meslek odaları birliği yeniden toplandı"),
                new Sentence("yeni yılın sonrasında vakalarda artış oldu"),
                new Sentence("atomik saatin 10 mhz sinyali kalibrasyon hizmetlerinde referans olarak kullanılmaktadır"),
                new Sentence("rehberimiz bu bölgedeki çıngıraklı yılan varlığı hakkında konuştu"),
                new Sentence("bu son model cihaz 24 inç ekran büyüklüğünde ve 9 kg ağırlıktadır")};
        Sentence[] modified = {
                new Sentence("yenisezon başladı"),
                new Sentence("sırtı kara adındaki canlı , bir balıktır"),
                new Sentence("siyahayı , ayıgiller familyasına ait bir ayı türüdür"),
                new Sentence("yeni se zon başladı gibs"),
                new Sentence("alis veriş için markete gitit"),
                new Sentence("kucuk bri yalı çapkını gecti"),
                new Sentence("mes lek odaları birliği yenidün toplandı"),
                new Sentence("yeniyılın sonrasında vakalarda artış oldu"),
                new Sentence("atomik saatin 10mhz sinyali kalibrasyon hizmetlerinde referans olarka kullanılmaktadır"),
                new Sentence("rehperimiz buı bölgedeki çıngıraklıyılan varlıgı hakkınd konustu"),
                new Sentence("bu son model ciha 24inç ekran büyüklüğünde ve 9kg ağırlıktadır")};
        FsmMorphologicalAnalyzer fsm = new FsmMorphologicalAnalyzer();
        SimpleSpellChecker nGramSpellChecker = new SimpleSpellChecker(fsm);
        for (int i = 0; i < modified.length; i++){
            assertEquals(original[i].toString(), nGramSpellChecker.spellCheck(modified[i]).toString());
        }
    }

}