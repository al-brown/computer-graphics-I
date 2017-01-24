# Computergrafik I

## Musterprojekt für die Abgabe der Übungsaufgaben

Dieses Projekt ist Teil der Unterrichtsmaterialien für das 
Modul *[Computergrafik I](https://tramberend.beuth-hochschule.de/course/winter-2016/cg1/)* 
im *[Bachelor Medieninformatik](https://studiengang.beuth-hochschule.de/bmi/)* im Winter 2016. 
Es gibt die Projektstruktur für die Abgabe der Übungsaufgaben verbindlich vor.

## Alle Lösungen

Hier steht eine Liste aller abgegebenen Lösungen.

- [Aufgabe 1:  Rote Scheibe auf Blau](doc/a01.md)
- [Aufgabe 2:  Viele Scheiben](doc/a02.md)
- [Aufgabe 3:  Gestreifter Himmel](doc/a03.md)
- [Aufgabe 4:  Unbeleuchteter Planet](doc/a04.md)
- [Aufgabe 5:  Beleuchteter Planet](doc/a05.md)
- [Aufgabe 6:  Bewegliche Kamera](doc/a06.md)
- [Aufgabe 10: Licht und Schatten](doc/a10.md)
 
- [Bonusaufgabe 1 - Zylinder und Kegel](doc/b01.md)
- [Bonusaufgabe 2 - Torus](doc/b02.md)
- [Bonusaufgabe 3 - Parallelisierung](doc/b03.md)


## Struktur

Das Repository enthält über den Lauf des Semesters alle Lösungsabgaben für eine Person.
Jede Lösung einer Aufgabe besteht aus mindestens zwei Komponenten:

1.  Eine **Dokumentation** der Lösung

    Die Erläuterungen befindet sich in einer Datei im Verzeichnis `doc`, und werden im 
    [Markdown Format](https://daringfireball.net/projects/markdown/) verfasst. Jede Doku 
    enthält als erstes ein aussagekräftiges Bild, das mit der Lösung erzeugt wurde. Danach werden
    die wichtigsten Details des Lösungsansatzes, unter Nennung aller externen Quellen, erläutert. 
    
    Die Datei wird nach der *Id* der Aufgabenstellung benannt. Für die erste Aufgabe lautet der
    Dateiname z.B. [`a01.md`](doc/a01.md).
     
2.  Ein Java-Package mit der **Implementierung** der Lösung
   
    Im Verzeichnis `src` befindet sich für jede Lösung ein Java-Package mit einer 
    ausführbaren `Main` Klasse. Die Ausführung des Programms erzeugt das in der 
    Dokumentation dargestellte Bild. Der Name des Package setzt sich aus dem Namen der Person,
    der Matrikelnummer und der *Id* der Aufgabe zusammen. Das Package für die erste Aufgabe
    lautet für den Dozenten beispielsweise `tramberend7215.a01`.

    Im Package `tramberend7215` können sich natürlich weitere Packages befinden, die von 
    mehreren Lösungen gemeinsam verwendet werden.

Die termingerechte Abgabe einer Lösung wird durch das Setzen eines *Tags* auf den entsprechenden
*Commit* des Repositories dokumentiert. Der Name des *Tags* leitet sich aus der *Id* der Aufgabe ab, und
 lautet für die erste Aufgabe z.B. `abgabe-a01`.
 