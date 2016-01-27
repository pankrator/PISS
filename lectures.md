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


