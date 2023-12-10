# Sprint Report 1

In diesem Sprint wurden die bis zum [Meilenstein 2](https://sopra.informatik.uni-stuttgart.de/sopra/sopra-ws23/project12-emundo/-/tags/v0.2) erledigten User Stories abgearbeitet.

## Beschreibung der wichtigsten implementierten Features

- Fahrten können angelegt werden. Diese Funktionalität ist jedoch noch sehr limitiert mit fixierter Passangierzahl und fixierten Addressen.
- Fahrten können nach Start/Ende gefiltert eingesehen werden.
- Backend: Addressen, Nutzer, Fahrten.

### Nach dem Kundengespräch

- Fahrten können editiert und gelöscht werden.
- Fahrten können nun beliebige Addressen nutzen.
- Fahrten haben nun einen Preis.
- Fahrten haben eine Beschreibung - diese dient dafür dass man z.B. findet wo genau die Abholung stattfindet und wo genau das Ziel erreicht ist und zum Auffinden des Autos durch z.B. Angabe des Kennzeichens.
- Fahrten können nach Addresse gefiltert werden.
- Addressen nutzen nun Distrikt statt Straße und Hausnummer.
- Fahrten können bearbeitet werden durch die ID. Hier fehlt noch die vollständige Bearbeitung der Addresse.
- Nutzer können sich registrieren mit Namen, Passwort, Email, Beschreibung.
- Nutzer können ihr Profil bearbeiten.
- Nutzer können eine Beschreibung für ihr Auto hinzufügen, sollten diese Sich als ein Fahrer registrieren.
Der Nutzen hierfür ist es, z.B. zu beschreiben welches Auto dieser Nutzer besitzt.
- Nutzer können sich anmelden mit der Email.
- Nutzer können sich für Fahrten anmelden. Es fehlt noch der Bewerbungsprozess - demnach first-come-first-serve momentan.
- Darkmode / Lightmode wurde implementiert.
- Eine vordefinierte Email kann an andere abgeschickt werden.

## Tests/Testprotokolle/Nachweis der Testabdeckung

Bis jetzt nur manueller Test im Frontend und via Postman.
z.B. können weitere Addressen hinzugefügt werden mit folgendem zugefügt werden:
```
POST localhost:8001/api/v1/addresses/
Body: {
  "city" : "stuttgart",
  "postcode" : "70569",
  "street" : "pfaffenwaldring",
  "houseNumber" : 0
}
```
Desweiteren können ebenfalls Nutzer und Fahrten manuell hinzugefügt werden.
Testabdeckung ist nicht ausreichend und muss automatisiert werden.

### Nach dem Kundengespräch
- Es wurden automatisierte Tests hinzugefügt für User / Ride / Address.
- Die automatisierte Testabdeckung ist noch ungenügend - Insbesondere fehlen noch die Tests für die Controller und UserRides.
- Manuell wurde jede Funktion des Frontends erfolgreich getestet - Registrieren, Einloggen, Fahrten erstellen und verwalten, Fahrten beitreten.
