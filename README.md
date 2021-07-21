## Разминочное задание - https://github.com/EvgeniyLifantiy/CitiesNote/tree/main/src/main ## 

## Api requests ##

1.1 http://localhost:8080/api/cards POST добавить карту по номеру счета (в заголовках или в теле )

1.2 http://localhost:8080/cards?billId=1234123412341234 GET получить все карты по счету

1.3 http://localhost:8080/api/operations POST добавить операцию , указываются счета.(тело)

1.4 http://localhost:8080/balance?billId= GET получить баланс

2.1/2.3  http://localhost:8080/api/operations POST добавить операцию , указываются счета.(тело)

2.2 http://localhost:8080/api/operations GET просмотр. Выбрав счет можно сделать запрос на пользователя с таким счетом.


3.1 http://localhost:8080/user POST создать нового пользователя 

3.2 http://localhost:8080/bills POST создать счет (по номеру. в заголовке)

3.3 http://localhost:8080/api/cards PUT изменить статус карты по номеру счета (в заголовках или в теле)

3.4 http://localhost:8080/api/operations PUT изменить статус операции (заголовок или в теле)

## Others

### В файле Test.txt (resoureces) примеры запросов c json 

http://localhost:8080/cards?id=1234123412341234 GET получить информацию о карте по номеру

http://localhost:8080/bills GET получить все счета(по номеру в заголовке или в теле) или будет получено по авторизованному пользователю

http://localhost:8080/bills?id= GET получить счет по id

http://localhost:8080/api/operations?id=1 get получить операцию по id


