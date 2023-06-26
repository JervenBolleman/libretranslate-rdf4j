Conceptual prototype for using a custom RDF4j function and [libretranlate]() on demand to "auto" translate language tagged literals.


```sparql
PREFIX translate : <http://example.org/libretranslate>
SELECT ?litJp ?lit
WHERE {
    VALUES (?lit) {( "goedemorgen"@nl )}
    BIND(translate:translate(?lit, "ja") AS ?litJp)
}
```



