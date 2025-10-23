# Koco/R

...that is: Coco/R ported to Kotlin

## Project status

So far only the generated Parser has been ported to Kotlin. The next step will be to additionally port the Scanner.

## Build

A bundled JAR-File ready to drop into a project can be created using the following gradle command:

```
./gradlew jar
```

The JAR-File will be created at `build/libs/Coco.jar`

## Bootstrapping

The project itself is based on Coco/R, so in order to generate the parser and scanner, please use the following build
target:

```
./gradlew generateParser
```

## License

This project is based on **Coco/R** (Copyright © 1990–2005 Hanspeter
Moessenboeck, University of Linz, et al.) and is licensed under the
**GNU General Public License, version 2 or later**.

Portions of this work were ported to Kotlin and adapted for Gradle
build integration by Matthias Raschhofer (2025).

As per the Coco/R license, source code *generated* by the tool is
not subject to the GPL.
