# ATM Test

### Создание банковской карты.

В данном методе должен приниматься транспортный объект со следующими полями

- Имя пользователя (Валидация - не пустое поле) 
- Номер карты (Валидация - Количество символов должно быть равно - 16)
- Дата рождения пользователя (Валидация - не пустое поле)
- Пол пользователя (Валидация - не пустое поле)
- Адрес пользователя (Валидация - не пустое поле) 

`http://localhost:8081/api/cards`

Request Method - POST. Body:
```json
{
	  "name": "name",
	  "numberOfCard": "1234567890123453",
	  "birthDate": "birthDate",
	  "sex": "sex",
	  "address": "address"
}
```

### Аутентификация карты. 

В данном методе должен приниматься транспортный объект   со следующими полями 

- Номер карты (Валидация - количество символов == 16)
- Пароль пользователя (Без хеширования в чистом виде)(Валидация - не пустое поле)

`http://localhost:8081/api/authCard`

Request Method - POST. Body:
```json
{
	  "numberOfCard": "1234567890123456",
	  "password": "123456"
}
```
###Перевод средств с карты на карту.
 
 В данном методе должен приниматься транспортный объект   со следующими полями 
- Номер карты отправителя (Валидация - количество символов == 16)
- Номер карты получателя (Валидация - количество символов == 16)
- Сумма для перевод (Не пустое поле, тип данных (любой удобный с плавающей точкой))
- Пароль отправителя(Валидация - не пустое поле)

`http://localhost:8081/api/transfer`

Request Method - POST. Body:
```json
{
	  "numberOfRecipientsCard": "1000200030004000",
	  "amount": 2000.0,
	  "sender":	
		    {
			      "numberOfCard": "1234567890123456",
			      "password": "123456"
		    }
	
}
```
Response:

```json
{
    "id": 1,
    "name": "card1",
    "numberOfCard": "1234567890123456",
    "birthDate": "123",
    "sex": "male",
    "address": "address",
    "balance": 6000,
    "user": {
        "id": 1,
        "card": null,
        "numberOfCard": null,
        "password": "123456"
    }
}
```

###Получение списка всех карт (Сериализация JSON).

Данные должны быть отсортированы по номерам карт (по возрастанию)

`http://localhost:8081/api/cards`

Request Method - GET.
Response:

```json
[
    {
        "id": 2,
        "name": "card2",
        "numberOfCard": "1000200030004000",
        "birthDate": "123",
        "sex": "male",
        "address": "address",
        "balance": 6000,
        "user": {
            "id": 2,
            "card": null,
            "numberOfCard": null,
            "password": "123456"
        }
    },
    {
        "id": 1,
        "name": "card1",
        "numberOfCard": "1234567890123456",
        "birthDate": "123",
        "sex": "male",
        "address": "address",
        "balance": 12000,
        "user": {
            "id": 1,
            "card": null,
            "numberOfCard": null,
            "password": "123456"
        }
    },
    {
        "id": 4,
        "name": "card4",
        "numberOfCard": "6593002408792501",
        "birthDate": "123",
        "sex": "male",
        "address": "address",
        "balance": 6000,
        "user": {
            "id": 4,
            "card": null,
            "numberOfCard": null,
            "password": "123456"
        }
    },
    {
        "id": 3,
        "name": "card3",
        "numberOfCard": "9289432905108365",
        "birthDate": "123",
        "sex": "male",
        "address": "address",
        "balance": 6000,
        "user": {
            "id": 3,
            "card": null,
            "numberOfCard": null,
            "password": "123456"
        }
    }
]
```
