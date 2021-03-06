#P2P системи

###Споделяне на данни и ресурси в много голям мащаб
###Поддръжка на разпределени услуги и приложения
###Създаване на приложения, използващи ресурси в периферията на интернет
###Преодоляване на ограниченията на клиент-сървър системите

##Недостатъци
  * Непостоянство на ресурсите
  * Непредвидимост на процесите и компютрите в системата
  * Липса на гарантиран достъп до определени ресурси

##Преодоляване на недостатъците
  * Репликация на ресурсите

###Характеристики
  * Гарантирана доставка за заявки при ограничен брой мрежови преходи
  * Ресурсите се идентифицират с глобален уникален идентификатор (GUID)
  
###Функционални изисквания към P2P платформите
  * Опростяване на конструирането на услуги, които са реализирани върху множество хостове в мрежова среда
  * Възможност клиентите да откриват и комуникират с всеки ресурс, достъпен за услугата
  * Възможност за добавяне на нови и премахване на съществуващи ресурси
  * Възможност за дoбавяне на нови и премахване на съществуващи хостове
  * Осигуряване на опростен програмен интерфейс, който е независим от типа на разпределените ресурси

###Нефункционални изисквания към P2P платформите
  * Глобална скалируемост
  * Балансирано натоварване
  * Оптимизация на локалните взаимодействия между съседните възли
  * Динамична наличност на хостовете
  * Сигурност на данните в среда с хетерогенна надеждност
  * Анонимност, възможност за отказ и устойчивост на цензура


###Местоположение на обектите
  * Информацията за разпределението на обектите се разделя и разпределя в мрежата
  * Всеки възел поддържа информация за местоположението на обектите в част от пространството, както и информация за топологията на пространството като цяло
  * За осигуряване на висока надеждност се извършва репликация
 
###Маршрутизиращ слой: Pastry
  * Генериране на GUID
   *  Хеш функция, използваща част или цялото състояние на обекта
  *  Слой за разпределено локализиране на обекти и рутиране (DOLR) в Tapestry
  *  Съхраняване на обект при DOLR модел
  *  128-битов GUID
  *  При N възела маршрутизирането на съобщение до обект с определен GUID изисква O(log N) стъпки
 
###Проверка за активни възли
    Изпращане на съобщения през определен интервал от време до
    съседните възли за удостоверяване на активност (heartbeat
    съобщения)

###Предотвратяване на проблеми
    Използване на механизъм за доставка at-least-once

###Структурирани P2P системи
    Наличие на глобална политика за управление на топологията на
    мрежата, добавянето и търсенето на обекти
    Хеш таблици и кръгови структури при Pastry и Tapestry
  
###Неструктурирани P2P системи
    Липса на единен контрол върху топологията и добавянето на
    обектите
    Маршрутизиращият слой се създава динамично
    Установяване на контакт с множество от съседи при добавяне на
    възел
    Създадената мрежа от възли е децентрализирана,
    самоорганизираща се и гъвкава по отношение на повреди
    Подходът доминира в интернет при споделяне на файлове
     Gnutella, FreeNet, BitTorrent

