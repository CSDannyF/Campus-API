# Campus API

Een REST API voor het beheer van campussen, lokalen, gebruikers en
reserveringen, gebouwd als schoolproject (graduaat Programmeren, UCLL).

## Technologie
- Java / Spring Boot
- Maven
- Spring Data JPA
- H2 (in-memory) en MySQL (persistent), via aparte profielen

## Architectuur
Gelaagde opbouw met controllers, services, repositories en modellen,
met centrale exception handling en integratietests.

## Functionaliteit
- **Campussen**: opvragen, toevoegen, bijwerken en verwijderen
- **Lokalen**: lokalen van een campus opvragen (met filter op minimum
  aantal zitplaatsen), opvragen op naam, en toevoegen aan een campus
- **Gebruikers**: opvragen op id, zoeken op deel van de naam, toevoegen
- **Reserveringen**: reserveringen van een gebruiker opvragen, aanmaken,
  en een lokaal koppelen aan een reservering (met eigenaar- en
  beschikbaarheidscontrole)

## Testen
Bevat integratietests, onder andere voor de CampusController.
