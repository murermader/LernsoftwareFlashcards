# FlashCards Projekt

Projekt von: Kai, Silas und Rafael

<h2>Einleitung</h2>

Das Programm soll eine Lernsoftware werden, mit der man mithilfe von Flashkarten lernen kann. Die Flashkarten werden nach bestimmten Zeitintervallen abgefragt (SRS Prinzip: https://en.wikipedia.org/wiki/Spaced_repetition) um einen optimalen Lernerfolg zu erzielen.

<h2>Funktionsweise</h2>

Der User kann Flashkarten mit Vorder- und Rückseite erstellen. Bei der Abfrage wird die Vorderseite einer Karte gezeigt. Der User hat Zeit über die Antwort nachzudenken, und kann dann zwischen verschiedenen Schwirigkeitsgraden wählen. Je nach Schwierigkeitsgrad, wird der Karte eine neues Abfragedatum gesetzt. Die Karte wird erst wieder abbefragt, wenn wir uns nach dem Abfragedatum befinden. Wenn das Programm  beendet wird, werden alle Flashkarten abgespeichert, und es kann weitergelernt werden, sobald die Karten wieder zur Verfügung stehen.


<h2>Unsere Anforderungen:</h2>

<h3>MUST-HAVE:</h3>

	1. Man kann mehrere Decks mit Lernkarten erstellen.
	2. Zu einem Themengebiet werden Lernkarten bereits erstellt.
	2. Die notwendigstens Fragen werden zuerst abgefragt. (Sortierung)
	3. Es gibt für jede Karte einen Schwierigkeitsgrad, der den Wiederholungsintervall beeinflusst.
	5. Benutzeroberfläche mit JavaFX und intuitivier Bedienung.
	6. Programm kann Decks (Lernkarten) speichern und beim Starten einlesen.
	7. Flashkarten erstellen/löschen/bearbeiten/zu Deck hinzufügen.
	10. Logging, zum Nachvollziehen möglicher nicht vorhergesehner Fehler
	
<h3>NICE-TO-HAVEs:</h3>

	1. Lernhistorie
	2. Nutzerstatistik
	3. Speichern der Daten in der Cloud

----------------------------------------------------------
DHBW Loerrach 2019

© Rafael, Kai und Silas
