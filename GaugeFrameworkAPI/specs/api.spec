Bilyoner Case Study
=====================
table: data.csv
//değişkenler data.scv dosyasındadır.

//1. hHps://dummy.restapiexample.com/api/v1/employees servisine istek aNlır.
//2. Servisin https status 200 döndüğü kontrol edilir.
//3. 24 adet kaydın geldiği kontrol edilir.
//4. employee_salary değeri 313500 olan kaydın employee_name değerinin "Haley Kennedy" olduğu kontrol edilir.

//Yukarıda belirtilen steplerin 2 farklı şekilde otomasyon senaryosunu hazırladım.
//İstenilen 4 stepi Scenario-1 de tek step şeklinde, Scenario-2 ise 3 step şeklinde hazırladım.

Scenario-1
-------------------
tags: Q2
* <url> servisine istek atılır. Statusun <status>, kaydın <count> olduğu ve değeri <price> olan kaydın <who> olduğu doğrulanır.

Scenario-2
-------------------
tags: Q2
* <url> servisine istek atılır. Servisin https status <status> döndüğü kontrol edilir.
* <count> adet kaydın geldiği kontrol edilir.
* employee_salary değeri <price> olan kaydın employee_name değerinin <who> olduğu kontrol edilir.