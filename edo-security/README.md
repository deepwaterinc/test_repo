Для запуска keycloak через docker введите данную команду в терминале:
docker run -p 8181:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev

Далее пройдя по адресу http://localhost:8181/ выбираем Administration Console и будет окно для ввода логина и пароля для keycloak.
![img.png](images for readme/img.png)

Введите username=admin, password=admin.

Вы войдете в master realm.

Выбираем создать realm.
![img_1.png](images for readme/img_1.png)
Вводим имя edo-project-realm.
![img_2.png](images for readme/img_2.png)
Дальше выбираем вкладку Clients и нажимаем на кнопку Create client.
![img_3.png](images for readme/img_3.png)
В ClientID вводим edo-project-client.
![img_4.png](images for readme/img_4.png)
Настройки безопасности оставляем по умолчанию.
![img_5.png](images for readme/img_5.png)
Нажимаем save.

В поле Valid redirect URIs ставим заглушку "*" и сохраняем
![img_1.png](images for readme/img_111.png)
Во вкладке Clients выбираем наш клиент и далее в Roles нажимаем кнопку Create role.
![img_6.png](images for readme/img_6.png)
Далее вводим название роли admin и сохраняем.
![img_7.png](images for readme/img_7.png)
Переходим во вкладку Realm roles и нажимаем на кнопку Create role.
![img_8.png](images for readme/img_8.png)
Вводим название роли api_admin и сохраняем.
![img_9.png](images for readme/img_9.png)
В этой же вкладке справа сверху выбираем Action -> Add associated roles.
![img_10.png](images for readme/img_10.png)
Далее выбираем вкладку Filter by clients.
![img_11.png](images for readme/img_11.png)
Ставим галочку на edo-project-client admin и нажимаем Assign.
![img_12.png](images for readme/img_12.png)
Далее переходим на вкладку Users и выбираем Create new user.
![img_13.png](images for readme/img_13.png)
Вводим имя admin и сохраняем.
![img_14.png](images for readme/img_14.png)
Тут же переходим во вкладку Credentials и нажимаем кнопку Set password.
![img_15.png](images for readme/img_15.png)
Вводим два раза пароль, деактивируем Temporary и нажимаем сохранить.
![img_16.png](images for readme/img_16.png)
Тут же переходим на вкладку Role mapping и нажимаем кнопку Assign role.
![img_18.png](images for readme/img_18.png)
Далее переходим во вкладку Filter by clients и выбираем edo-project-client admin и нажимаем Assign.
Все готово для работы с keycloak.

При первом переходе на endpoint появится окно с вводом логина и пароля. 
Там необходимо будет ввести admin и пароль, который вы придумали при создании юзера.


**Работа через Postman**

Сначала необходимо получить токен через post-запрос 
по адресу http://localhost:8181/realms/edo-project-realm/protocol/openid-connect/token

В теле запроса вводим:
  
KEY      VALUE

grant_type -> password

client_id -> edo-project-client

username -> admin

password -> ваш_пароль
![img.png](images for readme/img2.png)
Получаем токен, копируем его.
И уже при get-запросе на endpoint выбираем тип авторизации Bearer Token и в поле Token
вводим полученный ранее токен.
![img.png](images for readme/img33.png)
