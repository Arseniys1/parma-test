[Животные](index.md)
## Получение списка животных

Пример:
**GET** `/getAnimals`

Результат: `200 OK` 
~~~
{
    "ok": true,
    "response": [
        {
            "id": 1,
            "subspecies": {
                "id": 1,
                "name": "Вид"
            },
            "wardens": [
                {
                    "id": 1,
                    "name": "Иван",
                    "married": true,
                    "salary": 100.98,
                    "familyName": "Иванов",
                    "birthDay": 932839200000
                }
            ],
            "name": "Вася",
            "birthDay": 932839200000
        }
    ]
}
~~~

## Создание животного

Пример:
**POST** `/createAnimal`

`name` - обязательное, строка, мин 2, макс 100 - `Имя животного`

`birth_day` - не обязательное, строка, дата в формате dd.MM.yyyy - `День рождения животного`

`subspecies_id` - обязательное, целое число - `Id вида животного`

`warden_ids` - обязательное, массив, целое число - `Id смотрителей животного` Пример warden_ids=1&warden_ids=2

Результат: `200 OK`
~~~
{
    "ok": true,
    "response": {
        "id": 1,
        "subspecies": {
            "id": 1,
            "name": "Вид"
        },
        "wardens": [
            {
                "id": 1,
                "name": "Иван",
                "married": true,
                "salary": 100.98,
                "familyName": "Иванов",
                "birthDay": 932839200000
            }
        ],
        "name": "Вася",
        "birthDay": 932839200000
    }
}
~~~

## Редактирование животного

Пример:
**PUT** `/editAnimal`

`id` - обязательное, целое число - `Id животного`

`name` - обязательное, строка, мин 2, макс 100 - `Имя животного`

`birth_day` - не обязательное, строка, дата в формате dd.MM.yyyy - `День рождения животного`

`subspecies_id` - обязательное, целое число - `Id вида животного`

`warden_ids` - обязательное, массив, целое число - `Id смотрителей животного` Пример warden_ids=1&warden_ids=2

Результат: `200 OK`
~~~
{
    "ok": true,
    "response": {
        "id": 1,
        "subspecies": {
            "id": 1,
            "name": "Вид"
        },
        "wardens": [
            {
                "id": 1,
                "name": "Иван",
                "married": true,
                "salary": 100.98,
                "familyName": "Иванов",
                "birthDay": 932839200000
            }
        ],
        "name": "Вася",
        "birthDay": 932839200000
    }
}
~~~

## Удаление животного

Пример:
**DELETE** `/deleteAnimal`

`id` - обязательное, целое число - `Id животного`

Результат: `200 OK`
~~~
{
    "ok": true,
    "response": "OK"
}
~~~