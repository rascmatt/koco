javac -d . src/Coco/Trace.java src/Coco/Scanner.java src/Coco/Tab.java src/Coco/DFA.java src/Coco/ParserGen.java src/Coco/Parser.java src/Coco/Coco.java
jar cfm Coco.jar src/Coco/Coco.manifest Coco/*.class
rm Coco/*.class
rm -r Coco