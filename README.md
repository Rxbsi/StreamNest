# 📺 StreamNest

## ❗DISCLAIMER
Da ich nicht mehr am Projekt weiter Arbeite fehlen einige Features.
Um sich das Projekt trotzdem anschauen zu können hier eine kurze Anleitung:

1. Projekt downloaden/kopieren
2. In der docker-compose.yml seine smtp daten eintragen
3. In cmd:
   1. docker compose build
   2. docker compose up -d
4. localhost:3000 aufrufen und mit username: admin passwort: admin anmelden

Dann kann man alle bestehenden Features testen.
Bestehende Features:
- Admin Tool mit hinzufügen von Videos über aws/minio
- User Hinzufügen/entfernen/bearbeiten
- Email Verifikation des Users
- Halbwegs schönes frontend (Projekt war zum frontend lernen da)
- Token verification der anmeldung
- ... Kann sein das ich was vergessen habe, habe länger nicht am Projekt gearbeitet


## ✍ Projektbeschreibung
StreamNest ist ein Projekt für mich selber um in der Arbeit mit JWT, AWS S3, RabbitMQ, Spring Boot und React vertrauter zu werden.
Ziel ist nicht das Projekt zu veröffentlichen, sondern während der Arbeit an diesem Projekt mehr Kompetenz mit angegebenen TechStack zu erlangen.

Für weitere Fragen gerne ein Issue öffnen oder eine Mail schreiben :) \
Gerne auch für Feedback o.ä.!


## 🔧 Features
- JWT Login
- Admin Tool zur Verwaltung von Usern und Upload von Videos
- Email Password Verification
- Watchlist zum merken von Filmen/Videos
- Suche von Videos über Searchbar


## 🎥 Showcase
**COMING SOON**


## 🛠️ TechStack
- **👨‍💻 Java**: Hauptprogrammiersprache
- **🎨 JavaScript**: Hauptprogrammiersprache Frontend
- **📊 Spring Boot**: Framework für die REST-API
- **🌐 React.js**: Frontend Framework
- **📹 Video.js**: Zum rendern und schauen der Videos
- **🌈 Bootstrap, SASS**: CSS Framework
- **☁️ AWS, S3 Buckets**: Speicherung der Videos
- **🐇 RabbitMQ**: Asynchrone verarbeitung bestimmter Tasks, bspw Email verschicken
- **🎫 YouTrack**: Ticket Verwaltung für bessere übersicht
- **🐘 PostgreSQL**: Für die restliche Datenspeicherung (User Verwaltung usw)


## ⚙️ Setup-Anleitung

1. **Projekt klonen**:
   - Klone das Repository auf deinen lokalen Rechner.

2. **SMTP Login anpassen**:
   - Gehe in den Root-Ordner des Projekts und öffne die Datei `docker-compose.yml`.
   - Passe die SMTP-Logins an deine eigenen Anmeldedaten an.
   - Beachte, dass der Account **keine 2FA aktiviert** haben darf, da sonst entweder ein spezielles Passwort benötigt wird oder die 2FA den Login blockiert.

3. **Docker-Setup starten**:
   - Führe `docker-compose up` im Root-Ordner aus, um die notwendigen Container zu starten.


## 🔮 Ideen für die Zukunft
- **🐍 Python**: Für die Implementation von Vorschlagsalgorithmen mit TensorFlow
- ¯\\_(ツ)_/¯

## ⚠ Disclaimer
Dieses Projekt ist nur für Bildungszwecke gedacht und darf nicht für illegale oder kommerzielle Zwecke verwendet werden.
Ich übernehme **keine** Verantwortung für eine missbräuchliche Nutzung dieses Projekts.
