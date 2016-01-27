###Failure handling techniques

* Detecting failures
* Masking failures
* Tolerating failures
* Recovery from failures
* Redundancy




###Concurrency
* any object that represents a shared resource in a distributed
system must be responsible for ensuring that it operates
correctly in a concurrent environment.
* For an object to be safe in a concurrent environment, its
operations must be synchronized in such a way that its data
remains consistent. 


###Transparency
* Access transparency enables local and remote resources to be accessed using identical operations.
* Location transparency enables resources to be accessed without knowledge of their physical or network location (for example, which	building or IP address).
* Concurrency transparency enables several processes to operate concurrently using shared resources without interference between them

* Replication transparency enables multiple instances of resources to be used to increase reliability and performance without knowledge of the 	replicas by users or application programmers

	Failure transparency enables the concealment of faults, allowing users and
	application programs to complete their tasks despite the failure of
	hardware or software components.

	Mobility transparency allows the movement of resources and clients
	within a system without affecting the operation of users or programs.

	Performance transparency allows the system to be reconfigured to
	improve performance as loads vary.

	Scaling transparency allows the system and applications to expand in scale
	without change to the system structure or the application algorithms.



###Quality of service
	The main nonfunctional properties of systems that affect the
	quality of the Service
	reliability,
	security
	performance


###Adaptability 
	- changing system configurations and resource
	availability

###Time-critical data 
	– streams of data that are required to be
	processed or transferred from one process to another at a
	fixed rate


###Distributed System 
	is a collection of
	independent computers that appears to its
	users as a single coherent system


###Physical models 
	are the most explicit way in which to describe a
	system; they capture the hardware composition of a system in
	terms of the computers (and other devices, such as mobile phones)
	and their interconnecting networks.

###Architectural models
	describe a system in terms of the
	computational and communication tasks performed by its
	computational elements; the computational elements being
	individual computers or aggregates of them supported by
	appropriate network interconnections.

###Middleware
	layer of software whose purpose is to mask
	heterogeneity and to provide a convenient programming
	model to application programmers

###The Presentation Logic
	which is concerned with handling user
	interaction and updating the view of the application as
	presented to the user;

###The Application Logic
	which is concerned with the detailed
	application-specific processing associated with the application
	(also referred to as the business logic, although the concept is
	not limited only to business applications);

###The Data Logic
	which is concerned with the persistent storage
	of the application, typically in a database management
	system


###Limitations of middleware
	The right underlying middleware behaviour is a function of
	the requirements of a given application or set of applications
	and the associated environmental context, such as the state
	and style of the underlying network


###Latency
	The delay between the start of a message’s transmission from
	one process and the beginning of its receipt by another is
	referred to as latency. The latency includes:


###Synchronous distributed systems
	one in which the following bounds
	are defined:
	 The time to execute each step of a process has known lower and upper
	bounds.
	 Each message transmitted over a channel is received within a known
	bounded time.

###Asynchronous distributed systems
	Many distributed systems, such
	as the Internet, are very useful without being able to qualify as
	synchronous systems. Therefore we need an alternative model. An
	asynchronous distributed system is one in which there are no
	bounds on:
	 Process execution speeds – for example, one process step may take only a
	picosecond and another a century; all that can be said is that each step
	may take an arbitrarily long time.
	 Message transmission delays – for example, one message from process A
	to process B may be delivered in negligible time and another may take
	several years. In other words, a message may be received after an
	arbitrarily long time.
	 Clock drift rates – again, the drift rate of a clock is arbitrary.



##Failure model

###Omission failures
	cases when a process or communication
	channel fails to perform actions that it is supposed to do

###Process omission failures
	crash and fail-stop

###Communication omission failures


###Synchronous and asynchronous communication
	Synchronous
		sending and receiving processes synchronize at every message
		both send and receive are blocking operations
	
###Message destinations
	(Internet address, local port)
	Client programs refer to services by name and use a name server or binder
	to translate their names into server locations at runtime.
	
###Reliability
	Validity - point-to-point message service can be described as reliable if
	messages are guaranteed to be delivered despite a ‘reasonable’ number
	of packets being dropped or lost
	Integrity – messages must arrive uncorrupted and without duplication

###CORBA’s common data representation,
	external representation for the structured and primitive types that can
	be passed as the arguments and results of remote method invocations
	in CORBA. It can be used by a variety of programming languages.

###Java’s object serialization,
	the flattening and external data representation of any single object or
	tree of objects that may need to be transmitted in a message or stored
	on a disk. It is for use only by Java.
	
###XML (Extensible Markup Language),
	defines a textual format for representing structured data. It was
	originally intended for documents containing textual self-describing
	structured data – for example documents accessible on the Web – but
	it is now also used to represent the data sent in messages exchanged
	by clients and servers in web services.
	
###remote object reference 
	is an identifier for a remote object
	that is valid throughout a distributed system

###Multicast communication
	Multicast operation sends a single message from one process
	to each of the members of a group of processes, usually in
	such a way that the membership of the group is transparent
	to the sender
	
###Network virtualization
	Network virtualization is concerned with the construction of
	many different virtual networks over an existing network such
	as the Internet. Each virtual network can be designed to
	support a particular distributed application.
	
	Each virtual network has its own particular addressing
	scheme, protocols and routing algorithms, but redefined to
	meet the needs of particular application classes.

###Overlay networks
	An overlay network is a virtual network consisting of nodes
	and virtual links, which sits on top of an underlying network
	(such as an IP network) and offers something that is not
	otherwise provided
	
	The disadvantages are that overlays introduce an extra level
	of indirection (and hence may incur a performance penalty)
	and they add to the complexity of network services when
	compared, for example, to the relatively simple architecture
	of TCP/IP networks
	
###Marshalling 
	is the process of taking a collection of data items and
	assembling them into a form suitable for transmission in a message
	
###Unmarshalling
	is the process of disassembling them on arrival to
	produce an equivalent collection of data items at the destination

###External data representation
	an agreed standard for the
	representation of data structures and primitive values
	
##Протокол заявка-отговор

###doOperation
	Предназначение: Извикване на отдалечена операция
	Аргументи: референция към отдалечен сървър, операция и набор параметри за
	извикване на операцията
	
###getRequest
	Предназначение: Използва се от сървърния процес за получаване на заявка
	
###sendReply
	Предназначение: изпращане на отговор от сървъра към клиента

###R протокол
	Изполава се, когато клиентът не очаква получаване на отговор
	Реализира се върху UDP дейтаграми

###RR протокол
	Отговорът от сървъра се тълкува като съобщение за потвърждение
	от клиента
	Следващата заявка от клиента се тълкува като съобщение за
	потвърждение от сървъра

###RRA протокол
	Използва съобщение за потвърждение
	Съдържа идентификатор на заявката requestId от съобщението отговор
	Потвърждава всички отговори с по-малък идентификатор requestId
	Потвърждението не блокира клиента

###HTTP
	Поддържа фиксирано множество от методи (GET, PUT, POST OPTIONS и т.н.)
	Осигурява договаряне на съдържанието
	Клиентската заявка може да съдържа данни за начина на представяне на данните от
	сървъра
	Поддържа автентикация
	Достъп до ресурс с потребителско име и парола

###GET
	Заявка за ресурс с определен URL адрес, при който информацията за заявката се
	съдържа в URL адреса
	Отговорът съдържа данни или резултат от изпълнение на програма
	
###HEAD
	Заявката е идентична на GET, при което се връща информация за данните, а не
	самите данни

###POST
	Заявка за ресурс с определен URL адрес, при който информацията се поставя в
	тялото ѝ

###PUT
	Заявка за съхраняване на данни като нов или съществуващ ресурс със специфичен
	URL адрес като идентификатор

###DELETE
	Заявка за изтриване на ресурс с определен URL адрес

###OPTIONS
	Заявка за информация, свързана с комукационните възможности на даден URL
	адрес (списък от HTTP методи, които могат да се приложат върху URL адреса)

###TRACE
	Връщане на заявката от сървъра обратно към клиента с диагностични цели
	
###Интерфейс
	Скрива реализацията на даден модул, предоставяйки информация
	за процедурите и променливите, които са достъпни чрез него

###Интерфейс при разпределени системи
	Спецификация на процедури, предлагани от даден сървър, и
	типовете на техните аргументи
	Пример: файлов сървър

###Преимущества при използването на интерфейси
	Липса на необходимост от познаване на програмната реализация
	Липса на необходимост от познаване на използваните програмен
	език и платформа
	Възможност за промяна на реализацията при запазване на
	интерфейса

###Interface Definition Language (IDL)
	Проектиран с цел извикване на процедура, реализирана на един език, от друг
	език
	Заложената в езика концепция се прилага и при други технологии
	Sun XDR, CORBA IDL, WSDL

##Семантика на RPC извикването

###Maybe
	Отдалечената процедура се изпълнява еднократно или въобще не се
	изпълнява
	Опастност от изгубване на съобщения за заявки или отговори
	Опастност от повреди в сървъра

###At-least-once
	Клиентът получава резултат, при което знае, че процедурата е изпълнена
	поне веднъж, или получава информация за липса на резултат
	Липса на опастност от изгубване на съобщения
	Опастност от повреда на сървъра
	Опастност от случайни повреди

###At-most-once
	Клиентът получава резултат, при което знае, че процедурата е изпълнена
	само веднъж, или получава информация за липса на резултат, при което
	процедурата е изпълнена веднъж или изобщо не е изпълнена
	Липса на опастност от изгубване на съобщения

###Прилики между RMI и RPC
	Поддръжка на програмиране с интерфейси
	Базиране на протокол заявка-отговор
	Поддръжка на идентично ниво на прозрачност

###Разлики между RMI и RPC
	Възможност за използване на обектно-ориентираната парадигма
	при RMI
	Възможност за предаване на параметри като референции към
	обекти
	
##RMI

### Прокси
	Осигурява прозрачност на извикването при клиента
	Препраща заявката за извикване на метод към отдалечения обект
	Реализира методите в интерфейса на отдалечения обект

###Диспечер
	Получава заявките от комуникационния модул
	Използва идентификатора на заявената операция operationID, за да
	избере подходящ метод в скелетона, към който да препрати заявката

###Скелетон
	Реализира методите в отдалечения интерфейс на отдалечения обект
	
###Динамично извикване
	Същност на динамичното извикване:
	Свързването с отдалечения обект не е по време на компилация
	Осигурява достъп на клиента до общото представяне на отдалеченото
	извикване, определено от операцията doOperation

	Динамични скелетони
	Сървърът хоства обекти, чиито интерфейси не са известни по време на
	компилиране
	Реализации
	Създаване на динамични скелетони при CORBA

###Сървърната програма
	Съдържа класове на диспечери и скелетони

###Клиентска програма
	Съдържа класове на проксита
	
###“Фабрични методи”
	Използват се за създаване на сърванти, тъй като интерфейсите на
	отдалечените обекти не съдържат конструктори

####Всяко извикване на отдалечен обект води до създаване на отделна нишка за него от сървъра

###Сериализиране на обект, реализиращ Remote интерфейс
	Обектът се заменя с отдалечената му референция, съдържаща
	името на класа му

###Сериализиране на произволен обект
	Информацията за класа се анотира с местоположението му (URL),
	което позволява да бъде свален

###Сваляне на класове от една виртуална машина на друга
	Получателят не притежава клас на обект, подаден по стойност
	Получателят не притежава клас на прокси

###Преимущества
	Липса на необходимост от поддържане на едно и също множество
	от класове при всички потребители
	Прозрачност при използване на инстанции на нови класове от
	клиентските и сървърните програми


#ИНДИРЕКТНА КОМУНИКАЦИЯ

###Индиректна комуникация: определение
	Комуникация в разпределените системи посредством посредници,
	при което липсва директна връзка между изпращача и
	получателя/получателите
	
### Предимства на индиректната комуникация
	Липса на свързаност в пространството
	Изпращачът не е необходимо да идентифицира получателя и обратно
	Липса на свързаност във времето
	Изпращачът и получателят са независими във времето
	
###Недостатъци на индиректната комуникация
	Намаляване на производителността поради наличие
	допълнително комуникационно ниво

###Асинхрона комуникация
	Изпращачът изпраща съобщение и продължава да работи без да
	се блокира
	
###Липса на свързаност във времето
	Изпращачът и получателят съществуват независимо един от друг

###Групова комуникация: определение
	Изпращане на съобщение до група от получатели и доставяне на
	съобщението до всички членове на групата

###Обектна група (подход на високо ниво)
	Колекция от обекти, които конкурентно обработват едно и също
	множество от извиквания, връщайки индивидуален отговор
	Пример: Electra – CORBA базирана система, работеща в
	“прозрачен” и “непрозрачен” режим
	
###Затворена група
	само членовете на
	групата могат да изпращат съобщения
	до нея

###Отворена група
	процес извън групата може да изпраща съобщения до нея
	
###Припокриващи се групи
	процесите и обектите могат да участват в няколко групи
	
###Неприпокриващи се групи
	всеки процес принадлежи на точно определена група

###Интегритет 
	Доставянето на съобщенията е еднократно (at-most-once)

###Валидност
	Изпратените съобщения рано или късно ще бъдат доставени
	
##Характеристики на publish-subscribe системите

###Хетерогенност
	Осигурява комуникация между компоненти в разпределените
	системи, които не са проектирани да взаимодействат

###Асинхронност
	Издателите и получателите на съобщенията не са свързани

###Модел, базиран на канал
	Издателите публикуват в именувани канали
	
###Модел, базиран на тема
	Нотификациите се представят с набор от полета, едно от които представя тема
	
###Модел, базиран на съдържание
	Абонаментът се дефинира върху множество от полета на нотификационните
	съобщения
	
###Модел, базиран на тип
	Основава се на обекно-ориентираните подходи, при които обектите се
	характеризират с тип

##Опашки със съобщения: парадигма

###Стилове за получаване на съобщения
	Блокиращо получаване
		Блокирането продължава докато съответното съобщение е налично
	Неблокиращо получаване
		Статусът на опашката се проверява и се връща съобщение или индикация за липса
		на съобщение
	Нотификация
		Нотификация за събитие при наличие нa дадено съобщение в опашката
		
###Свойства на системите, базирани на опашки
	Персистентност на съобщенията
	Надеждна доставка на съобщения: съобщенията се доставят винаги и
	еднократно

##Java Message Service (JMS)

### Същност на JMS
	Спецификация на стандартизиран начин за индиректна
	комуникация между разпределени Java приложения
	Поддържа теми и опашки като алтернативни варианти за
	доставяне на съобщения
	
###Роли в JMS
	JMS клиент
		Java програма или компонент, които генерират или консумират съобщения
		(JMS производител или JMS консуматор)
	JMS доставчик
		Система, която реализира JMS спецификацията
	JMS съобщение
		Обект, който се използва за комуникация между JMS клиентите
	JMS дестинация
		Обект, поддържащ индиректна комуникация в JMS (JMS тема или JMS опашка)
		
##Разпределена споделена памет: репликация

###Машина на състоянията
	Промяната в състоянието е отговор на събитие, получено от друга
	реплика или средата
	Осигуряване на консистентност
		Репликите трябва да стартират при едно и също състояние
		Репликите трябва да обработват събитията в еднакъв ред
		Репликите трябва да реагират детерминистично на всяко събитие

###Репликационна стратегия на Xu&Liskov
	Изпълнява се върху определено множество от реплики
	Списъците притежават логически имена, определени от първия им
	елемент, спрямо който се групират в множества
	Системата се състои от т.нар. работници, извършващи изчисления над
	пространството със списъци, и набор от реплики
	
	
	
#РАЗПРЕДЕЛЕНИ ОБЕКТИ И КОМПОНЕНТИ

###Разпределени обекти
	Комуникацията се осъществява посредством отдалечено
	извикване на методи или алтернативна парадигма (разпределени
	събития)
	Преимущества на подхода
		Инкапсулация
		Даннова абстракция и скриване на реализацията
		Възможност за реализиране на динамични и разширяеми решения
		
###JavaRMI
	Използва се само при разработване на Java приложения
###CORBA
	Предлага независимо от програмния език решение (C++, Java, Python и др.)
	
##CORBA

###Брокер за заявки към обекти (Object Request Broker)
###Компоненти на CORBA (Common ORB Architecture)
	Език за дефиниране на интерфейси (IDL)
	Външно представяне на данни (CDR)

###Клиенти на CORBA обектите
	Програми, изпращащи заявки към отдалечени обекти и
	получаващи отговори

###CORBA обект
	Отдалечен обект, реализиращ IDL интерфейс
	Предоставя отдалечена референция към себе си
	
###Семантика на извикването
	Подразбираща се семантика е at-most-once
	Възможност за използване на семантика maybe с ключова дума
	oneway

### Изключения
	Дефинират се в интерфейсите и се предизвикват от методите

###ORB core
	Изпълнява роля на комуникационен модул

###Обектен адаптер (Object adapter)
	Създава отдалечени обектни референции за CORBA обекти
	Диспечеризира отдалечените извиквания към скелетона на релевантния
	сървант
	Активира и деактивира сърванти
	
###Име на обект
	Специфицира се от приложната програма или се генерира от обектния
	адаптер

##CORBA: Portable Object Adapter (POA)

###Същност на POA стандарта
	Осигурява приложенията и сървантите да използват ORBs, разработени от
	различни разработчици
	
###Skeleton
	Клас, който се генерира от IDL компилатора, като се използва
	програмния език на сървъра

###Proxy/stub
	Прокси клас (при обектно-ориентираните езици) или множество от
	стъб процедури (при процедурните езици), които се генерират от IDL
	компилатора, като се използва програмния език на клиента

###Implementation repository
	Съхранява съответствие между имената на обектните адаптери и
	местоположението на файловете, съхраняващи реализациите на
	обектите
	Регистрацията в хранилището става при инсталиране на сървърната
	програма
	При активиране на обектната реализацията в хранилището се добавя
	информация за име на хост и номер на порт
	
###Interface repository
	Осигурява информация на клиентите и сървърите за регистрираните IDL
	интерфейси
	Имена на методи, имена и типове на аргументи и изключения
	Съхранява съответствие между типовите идентификатори (repositoryID) и
	интерфейсите
	Типовите индентификатори се генерират от IDL компилатора
	Използва се при динамично свързване с CORBA обекти
	Отдалечената референция включва информация за типовия идентификатор

###Dynamic invocation interface
	Осигурява на клиентите динамично да извикват методи на CORBA обекти
	Използва се, когато създаването на прокси не е подходящо
	Клиентът получава информация за методите от хранилището за интерфейси
	
###Dynamic skeleton
	Използва се, когато даден интерфейс на CORBA обект не е известен преди
	компилирането на сървъра

###Наследен софтуер
	Възможност за предоставяне на функционалност чрез интерфейс на CORBA
	обект

##Формат на отдалечена референция към обект (Interoperable Object Reference)

###Типове референции
	Временни
	Постоянни

##CORBA: услуги (1-2)

###Naming service
	Осигурява откриване на CORBA обект по име

###Trading service
	Осигурява откриване на CORBA обект по атрибут

###Event service
	Осигурява изпращане на нотификации от обекти до абонати с
	помощта на CORBA RMI

###Notification service
	Осигурява допълнителни възможности при нотифициране
	(дефиниране на филтри, надеждност и подреждане в канала за
	събития)

###Security service
	Осигурява механизми за сигурност като автентикация, контрол на
	достъпа, защитена комуникация и др.
	
###Transaction service
	Осгурява създаване на плоски и вложени транзакции	
	
###Concurrency control service
	Управлява конкурентния достъп до обектите, използвайки
	заключвания

###Persistent state service
	Осигурява персистентно хранилище за CORBA обекти, от което се
	възстановява състоянието им

###Lifecycle service
	Дефинира конвенции за създаване, изтриване, копиране и
	преместване на CORBA обекти

###Контейнер
	Осигурява управляема хостваща среда
	за компонентите от страна на сървъра
	
	Гарантира разделяне на приложната
	логика на компонента от функциите на
	разпределената среда
	
	Скрива директния достъп до
	компонента и предприема подходящи
	действия, за да осигури поддръжка на
	разпределеното приложение

##Enterprise Java Beans (EJB)

###Същност на EJB
	Спецификация на сървърна компонентна архитектура и базов
	елемент на Java EE, множество от спецификации за клиент-сървър
	програмиране
	Поддържа разработване на приложения, при които голям брой
	клиенти комуникират с набор от услуги, реализирани като
	компоненти или конфигурация от компоненти

###Компоненти на EJB (beans)
	Реализират приложна (бизнес) логика
	Отделяне на бизнес логиката от съхраняване на състоянието

###Управление в EJB
	Контейнерът поддържа ключови разпределени услуги като
	транзакции, сигурност и др.
	Container-managed EJB
	Извикванията към разпределените услуги са от контейнера
	Bean-managed EJB
	Разработчикът управлява извикването на операциите на разпределените услуги

###Bean provider
	Разработва приложната логика в компоненти

###Application assembler
	Асемблира компонентите в приложни конфигурации

###Service provider
	Специалист в областта на базовите разпределени системни услуги,
	който осигурява определено ниво на тяхната поддръжка

###Persistent provider
	Специалист, управляващ асоциирането на персистентни данни с
	прилежащите бази от данни 

###Container provider
	Конфигурира контейнерите с необходимото ниво на системна
	поддръжка за транзакции, сигурност и др.
	
###Типове компоненти в EJB 3.0
	Session bean
	Message-driven bean

###EJB транзакции
	Осигуряват, че всички обекти, управлявани от даден сървър ще се
	запазят в консистентно състояние при конкурентен достъп или срив
	на сървъра
	Гарантират атомарно изпълнение на последователност от операции

##Същност на програмния модел Fractal

	Компонентно базиран модел за програмиране с интерфейси
	Позволява разделяне на интерфейса от реализацията
	Поддържа експлицитно представяне на софтуерната архитектура
	без да допуска имплицитни зависимости
	Осигурява реализация на приложения, които са конфигурируеми
	и реконфигурируеми в режим на изпълнение по отношение на
	текущата оперативна среда и изисквания
	
