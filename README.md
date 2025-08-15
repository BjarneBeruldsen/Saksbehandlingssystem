<!-- PROJECT LOGO --> 
<br />
<div align="center">
  <a href="#">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">OBJ2100 Eksamen 2025 – Gruppe 15</h3>

  <p align="center">
    Saksbehandlingssystem – JavaFX & MySQL
    <br />
    <a href="#about-the-project"><strong>Utforsk dokumentasjonen »</strong></a>
    <br />
    <br />
    <a href="#usage">Se bruk</a>
    &middot;
    <a href="#contact">Kontakt</a>
    &middot;
    <a href="#roadmap">Veikart</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Innholdsfortegnelse</summary>
  <ol>
    <li>
      <a href="#about-the-project">Om prosjektet</a>
      <ul>
        <li><a href="#built-with">Bygget med</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Komme i gang</a>
      <ul>
        <li><a href="#prerequisites">Forutsetninger</a></li>
        <li><a href="#installation">Installasjon</a></li>
      </ul>
    </li>
    <li><a href="#usage">Bruk</a></li>
    <li><a href="#roadmap">Veikart</a></li>
    <li><a href="#contributing">Bidrag</a></li>
    <li><a href="#license">Lisens</a></li>
    <li><a href="#contact">Kontakt</a></li>
    <li><a href="#acknowledgments">Takk til</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](#)

Dette prosjektet er et forenklet sakshåndteringssystem utviklet som eksamensoppgave i **OBJ2100 – Objektorientert Programmering 2** våren 2025.  
Systemet støtter rapportering, tildeling og oppfølging av saker, med et rollebasert JavaFX-grensesnitt og en flertrådserver som kommuniserer med en MySQL-database.

**Hovedtrekk:**
* Rollebasert GUI for Ledere, Testere og Utviklere
* MySQL-database med oppslagstabeller og referanseintegritet
* Klient–server-arkitektur med sockets og TCP
* Maven for bygg og avhengighetsstyring

<p align="right">(<a href="#readme-top">til toppen</a>)</p>

### Built With

* [![JavaFX](https://img.shields.io/badge/JavaFX-blue.svg?style=for-the-badge)](https://openjfx.io/)
* [![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
* [![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

<p align="right">(<a href="#readme-top">til toppen</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
- **Java 17** eller nyere
- **Maven**
- **MySQL Server** (kjørende lokalt)

### Installation

1. Klon eller last ned prosjektet
   ```sh
   git clone https://github.com/ditt_brukernavn/sakssystem.git
   ```
2. Åpne prosjektet i din favoritt-IDE (f.eks. IntelliJ IDEA eller Eclipse)
3. Maven vil automatisk hente og installere alle nødvendige avhengigheter
4. Rediger `src/main/resources/db.properties` med dine MySQL-detaljer:
   ```properties
   db.url=jdbc:mysql://localhost:3306/
   db.name=sakssystem
   db.username=ditt_brukernavn
   db.password=ditt_passord
   ```
5. Start MySQL-serveren
6. Kjør `SakServer`-klassen (server/network)
7. Kjør `Main`-klassen (client/view)

Systemet er nå klart til bruk

<p align="right">(<a href="#readme-top">til toppen</a>)</p>

<!-- USAGE -->
## Usage
Når systemet er startet:
- Logg inn med ønsket brukerprofil
- Opprett saker (tester/leder)
- Tildel saker (leder)
- Oppdater status (alle roller)
- Søk etter saker

<p align="right">(<a href="#readme-top">til toppen</a>)</p>

<!-- ROADMAP -->
## Roadmap
- Rollebasert innlogging
- Multi-threaded server
- Dynamisk henting av databaseverdier
- Refaktorering av tabellvisninger for å unngå kode-duplisering

<p align="right">(<a href="#readme-top">til toppen</a>)</p>


<!-- ACKNOWLEDGMENTS -->
## Acknowledgments
- JavaFX Documentation
- MySQL Documentation
- Maven Documentation
- Socket Programming in Java

<p align="right">(<a href="#readme-top">til toppen</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
