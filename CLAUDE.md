# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

A single-page Play Framework app that reports mathematical facts about an integer (currently just primality; the spec in `docs/Spec.md` calls out other attributes as future features). Java 21, Play 3.0.x, sbt, jQuery + Knockout for the UI. No database, no auth.

## Commands

```bash
sbt compile           # build
sbt test              # run all tests
sbt "testOnly services.MathFactsServiceTest"           # single test class
sbt "testOnly services.MathFactsServiceTest -- -n smallPrimes"  # single JUnit 5 method
sbt run               # start dev server on http://localhost:9000
sbt clean
```

`sbt run` is long-running — start it in the background or in a separate terminal.

## Architecture

The naming generalizes from "prime" to **MathFacts** so new attributes can be added without churning class names. Three Java layers:

- `app/services/MathFactsService.java` — pure logic. `isPrime(long)` today; future fact methods land here.
- `app/models/MathFacts.java` — DTO returned to the client. Adding a new fact means adding a field here and populating it in `MathFactsService.factsFor(...)` — no controller or route changes needed.
- `app/controllers/HomeController.java` — `GET /` renders the page; `POST /api/check` accepts `{ "n": <int> }` and returns the `MathFacts` DTO as JSON via `play.libs.Json`. Service is `@Inject`ed (Guice, via the `guice` dep in `build.sbt`).

Frontend (`app/views/index.scala.html` + `public/javascripts/app.js`) is a Knockout viewmodel that posts to `/api/check` with jQuery and renders the response. jQuery and Knockout are loaded from CDN (`code.jquery.com`, `cdnjs.cloudflare.com`) — the browser fetches them, not the sandbox.

Routes are declared in `conf/routes`; Play generates a `controllers.routes` reverse-router from this at compile time, which the template uses for `@routes.Assets.versioned(...)`.

## Adding a new math fact

1. Add a method to `MathFactsService` (e.g. `boolean isPerfectSquare(long n)`).
2. Add a field to `MathFacts` and set it in `factsFor(...)`.
3. Add a binding in `index.scala.html` to display it; the Knockout viewmodel already exposes the whole response object as `result()`.
4. Add tests to `test/services/MathFactsServiceTest.java` (JUnit 5).

No route or controller changes are needed for new facts — that's the point of the DTO shape.

## Sandbox notes

This repo is typically worked on inside a Docker sandbox (see the parent `../CLAUDE.md`). sbt and Maven Central traffic go through `gateway.docker.internal:3128`; if `sbt` can't resolve dependencies, the host needs `sbx policy allow network repo1.maven.org,repo.scala-sbt.org,repo.typesafe.com`.
