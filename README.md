# JDBC PostgreSQL 16 avec Docker – Application Java Standalone

## Étudiante
Nom : Mira El Dalati  
Matricule : 230878

---

## Objectif du projet
Ce projet a pour objectif de mettre en œuvre JDBC afin de permettre à une application Java standalone
de se connecter à une base de données PostgreSQL 16 exécutée dans un container Docker.
L’application permet d’effectuer des opérations CRUD (Create, Read, Update, Delete) sur une table
`students`.

---

## Outils et versions utilisés
- Java : 1.8.0_371
- IntelliJ IDEA
- Maven
- Docker : 29.1.3
- PostgreSQL : 16
- Système : Windows

---

## Lancement de PostgreSQL avec Docker

```bash
docker run --name pg16 -e POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=jdbc_demo -p 5432:5432 -d postgres:16
docker ps
