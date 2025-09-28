Prerequisites
     ->Java 17+ 
     ->Maven 3.8+

IDE (Eclipse/STS/IntelliJ) or terminal

1. Clone or Import the Project
If using STS:
File → Import → Existing Maven Projec
Select this project
If using terminal:
git clone <>
cd WeatherApiApplication

2. Build & Run
Run from IDE:
Open WeatherApiApplication.java
Right-click → Run As → Spring Boot App

Default port: 8056 

3. Available Endpoints

Current Weather
GET http://localhost:8056/weather/current?location={city}

Forecast
GET      http://localhost:8056/weather/forecast?location={city}&days={n}

Location Search
GET  http://localhost:8056/locations/search?q={query}

Health Check
GET  http://localhost:8056/health
