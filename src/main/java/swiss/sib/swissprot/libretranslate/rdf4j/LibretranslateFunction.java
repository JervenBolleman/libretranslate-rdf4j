package swiss.sib.swissprot.libretranslate.rdf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.query.algebra.evaluation.ValueExprEvaluationException;
import org.eclipse.rdf4j.query.algebra.evaluation.function.Function;

import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

public class LibretranslateFunction implements Function {

	public static final String NAMESPACE = "http://example.org/libretranslate/";
	static {
		Map<String, Language> langMap2 = new HashMap<>();
		for (Language lang : Language.values()) {
			langMap2.put(lang.getCode(), lang);
		}
		langMap = Collections.unmodifiableMap(langMap2);
	}
	private static final Map<String, Language> langMap;

	public String getURI() {
		return NAMESPACE + "translate";
	}

	public Value evaluate(ValueFactory vf, Value... args) throws ValueExprEvaluationException {
		// our palindrome function expects only a single argument, so throw an error
		// if there's more than one
		if (args.length != 2) {
			throw new ValueExprEvaluationException(
					"Translation function requires" + "exactly 2 argument, got " + args.length);
		}
		Value arg = args[0];
		Value translateTo = args[1];
		// check if the argument is a literal, if not, we throw an error
		if (arg instanceof Literal && translateTo instanceof Literal) {
			Literal l = ((Literal) arg);
			Literal translateToL = ((Literal) translateTo);
			if (l.getLanguage().isPresent()) {
				String lang = l.getLanguage().get();
				Language from = langMap.get(lang);
				Language to = langMap.get(translateToL.stringValue());
				if (from != null && to != null) {
					String translate = Translator.translate(from, to, l.stringValue());
					return vf.createLiteral(translate, to.getCode());
				}
			}
		} else {
			throw new ValueExprEvaluationException("invalid argument (literal expected): " + arg);
		}
		return null;

	}
}