Conceptual prototype for using a custom [RDF4j](https://rdf4j.eclipse.org/) function and [libretranslate](https://github.com/LibreTranslate/LibreTranslate) on demand to "auto" translate language tagged literals.


```sparql
PREFIX translate : <http://example.org/libretranslate>
SELECT ?litJp ?lit
WHERE {
    VALUES (?lit) {( "goedemorgen"@nl )}
    BIND(translate:translate(?lit, "ja") AS ?litJp)
}
```

This should implement caching and be configured to use a non demo translation endpoint.

