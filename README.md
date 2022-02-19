# TimeLogger

Írj egy olyan miniservice-t, mely a kapott log.json-t es holiday.json-t feldolgozza es outpukent előállít egy olyan json-t, amely tartalmazza userenkent es hetenkent a logolasokat.

 1. heti 32 óra rögzítéshez képest hány százalék lett rögzítve
    userenként
 2. másnap reggel (következő munkanap) 10 óráigig az előző napi   
    rögzítések hány százaléka történt meg userenként (vedd figyelembe a 
    hétvégéket és szabadságokat)

a log.json formatuma fix, a holiday.json átalakítható.


## Futtatás

Szükséges hozzá: `JAVA 11`, `Maven 3.6.3`
Futtatáskor 1. program argumentum: a logok tartalmazó json fáljneve, 2. argumentum a szabadságokat tartalmazó

## Eredmények

 1. Logolások userenként és hetenként: `user_week_logs.json`
 2. Userenként hány százalék lett rögzítve 32 órához képest: `result_1.json`
 3. Következő munkanap 10 óráig hány százalék lett rögzítve userenként: `result_2.json`
