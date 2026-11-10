# Aegis — AI-ready Java Test Automation Framework (starter)

Starter repository with a modular foundation for UI, API, and JDBC database validation. Java 17, Maven, Selenium, Cucumber BDD, Rest Assured, JUnit Platform, SQL Server JDBC.

## Local system under test (nopCommerce + SQL Server)

Docker Compose uses the official nopCommerce container and SQL Server 2022 container. nopCommerce installation is completed through its setup wizard.

```bash
docker compose up -d
docker compose ps
```

Open **http://localhost:8080**. In the nopCommerce installer, use:
- Database: Microsoft SQL Server
- Server: `sqlserver`
- Database name: `nopcommerce`
- SQL login: `sa`
- Password: `Aegis_LocalDev!2026`

Select the option to create the database if it does not exist. After installation, add sample data in the installer if offered. This password is for local learning only; do not reuse it outside this disposable environment.

Stop containers: `docker compose down`
Reset local database and app data (destructive): `docker compose down -v`

## Framework configuration

`src/test/resources/config.properties` sets local web/API endpoints and JDBC settings. The JDBC utility reads environment overrides (`AEGIS_DB_URL`, `AEGIS_DB_USER`, `AEGIS_DB_PASSWORD`) first. The DB URL assumes the installer created a database named `nopcommerce`.

## Run

```bash
mvn clean test
```

Cucumber smoke UI feature is tagged `@ui`. Chrome is started by Selenium Manager; ensure Chrome is installed. This initial smoke test checks that the local site responds and has a non-empty title.

Run only API examples by implementing/configuring a reachable API endpoint and then use the corresponding test class. Do not assume nopCommerce API endpoints are available in a vanilla installation: API access depends on installing and configuring an API plugin.

## Packages

- `core`: WebDriver lifecycle and configuration
- `ui.pages`: Page Object examples
- `api`: Rest Assured request specification
- `db`: JDBC connection/query helper (parameterized queries)
- `config`: property/environment configuration
- `steps` + `features`: Cucumber BDD

## Learning roadmap

1. Boot local nopCommerce and inspect SQL Server tables after installation.
2. Expand page objects and Cucumber scenarios for registration, catalog, cart, and checkout.
3. Install/configure an API plugin; add API response assertions and API-to-DB checks.
4. Add test-data setup/cleanup, reporting, CI, and AI-assisted failure triage/test generation.

## Important scope note

This is a starter foundation, not a pre-populated exhaustive commerce regression suite. The database schema is created by nopCommerce's installation process and may vary by version/configuration. Before asserting table/column details, inspect the actual schema in your local database.
