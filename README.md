OBJ2100 Eksamen 2025 Gruppe 15

For å sette opp og kjøre prosjektet gjør du følgende:

1. Klon eller last ned prosjektet til din maskin.
2. Åpne prosjektmappen i din favoritt-IDE (for eksempel IntelliJ IDEA eller Eclipse).
3. Maven vil automatisk hente og installere alle nødvendige avhengigheter når prosjektet åpnes (bl.a. JavaFX og MySQL Connector/J).
4. Gå til filen `src/main/resources/db.properties` og fyll inn dine egne MySQL-detaljer:
   db.url=jdbc:mysql://localhost:3306/
   db.name=sakssystem
   db.username=ditt_brukernavn
   db.password=ditt_passord
Husk å lagre filen etter at du har lagt inn informasjonen.
5. Start din lokale MySQL-server om den ikke allerede kjører.
6. Kjør `SakServer`-klassen som ligger i `server/network`-mappen først.
7. Når serveren kjører, kjører du `Main`-klassen som ligger i `client/view`-mappen for å starte klienten.
8. Systemet er nå klart for bruk. Logg inn, opprett saker, og test funksjonaliteten.

Hvis du får problemer:
- Sjekk at du har riktige databaseinnstillinger i `db.properties`.
- Sjekk at din MySQL-server faktisk kjører.