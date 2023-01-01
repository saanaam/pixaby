<h3>The Pixabay mobile app includes the following features:</h3>
For simplicity, I've chosen the Pixaby free API: https://pixabay.com/api/docs/

* Fast search and a user-friendly interface
* Show Image detail 


<H2>Stack:</H2>

* Kotlin
* Compose for building native UI
* coroutines flow to handle an asynchronous stream of data
* Coroutines to manage long-running tasks
* Clean architecture + MVVM
* Multi module (feautred-layerd-base)
* Retrofit + Gson for REST api communication
* Room to implement offline first strategy
* Paging3 for hadling pagination in app
* Navigation component for in-app navigation
* Hilt for dependency injection  
* Junit


<h2>Architecture</h2>

the project architechture is MVVM clean Architecture, each feature is separate module for each feature and separate layers named presentation, domain, data for each feature module. I implemented all clean architecture concepts.

The following diagram shows the structure of each feature module with 3 layers:
Presentation
Domain
Data

<h5>Communication between layers</h5>
UI calls method from ViewModel.
ViewModel executes Use case.
Use case combines data from Repositories (IOC).
Each Repository returns data from a Data Source (Cached or Remote) (IOC).
Information flows back to the UI where we display the list of images.
