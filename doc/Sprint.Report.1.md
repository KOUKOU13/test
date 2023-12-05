# Sprint Report 1.1

In diesem Sprint wurden die bis zum [Meilenstein 1](meilenstein-url) erledigten User Stories abgearbeitet.

## Beschreibung der wichtigsten implementierten Features

- Fahrten können angelegt werden. Diese Funktionalität ist jedoch noch sehr limitiert mit fixierter Passangierzahl und fixierten Addressen.
- Fahrten können nach Start/Ende gefiltert eingesehen werden.
- Backend: Addressen, Nutzer, Fahrten.

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
