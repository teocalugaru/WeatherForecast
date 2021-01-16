# Weather Forecast
## Descriere aplicatie
Aceasta aplicatie realizeaza obtinerea si afisarea unor date meteorologice.Utilizatorul are posibilitatea de a-si alege un stat si un oras din acel stat, iar programul ii va
arata cateva valori meteorologice in timp real(temperatura, viteza vantului, presiune etc).
## Implementare
Pentru implementare am folosit 2 clase care descriu statul si orasul(State si City), iar pentru a controla aplicatia am folosit clasele WeatherController(pentru interfata grafica)
si Server(aceasta citeste informatiile din fisierul de configurare si face request-uri la serverul meteorologic openweather->[Visit website](https://openweathermap.org/api)
Fisierul de configurare a fost folosit pentru a introduce statele si orasele la care utilizatorul are acces.
Va exista si un fisier de log unde vor fi scrise informatii de fiecare data cand utilizatorul alege un alt oras sau un alt stat.
