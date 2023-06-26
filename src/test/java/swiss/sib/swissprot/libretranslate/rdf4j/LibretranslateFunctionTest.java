package swiss.sib.swissprot.libretranslate.rdf4j;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.junit.jupiter.api.Test;

public class LibretranslateFunctionTest {
	private static final ValueFactory vf = SimpleValueFactory.getInstance();

	@Test
	public void englishToDutch() {
		LibretranslateFunction ltf = new LibretranslateFunction();
		Literal en = vf.createLiteral("goodmorning", "en");
		Literal nl = vf.createLiteral("nl");
		Value evaluate = ltf.evaluate(vf, en, nl);
		assertNotNull(evaluate);
		assertTrue(evaluate instanceof Literal);
		Literal l = (Literal) evaluate;
		assertTrue(l.getLanguage().isPresent());
		assertEquals(l.getLanguage().get(), "nl");
		assertEquals(l.stringValue(), "goedemorgen");
	}
	
	@Test
	public void englishToJapanese() {
		LibretranslateFunction ltf = new LibretranslateFunction();
		Literal en = vf.createLiteral("goodmorning", "en");
		Literal ja = vf.createLiteral("ja");
		Value evaluate = ltf.evaluate(vf, en, ja);
		assertNotNull(evaluate);
		assertTrue(evaluate instanceof Literal);
		Literal l = (Literal) evaluate;
		assertTrue(l.getLanguage().isPresent());
		assertEquals("ja", l.getLanguage().get());
//		assertEquals(l.stringValue(), "おはようございます");
		assertEquals(l.stringValue(), "グッドモーニング");
	}
}
