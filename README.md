## Definition
The app should allow the users to see a list of movies with the basic info. Should be able to
sort the list, perform search and see the details of every movie. In addition the user will be
able to mark movies as favorite and see all those favorite movies in separate list. Also the
user will be able to make a watch list.

The board for the project can be found on the github repository:  
[Project board](https://github.com/maiconhellmann/bluecoding-evaluation-imdb/projects/1)

#### Api documentation
API Url=https://api.themoviedb.org  
API for images=https://image.tmdb.org/t/p  
API version=3  
API key=5f18fddd90d0b4d77b1f975ab4a1355b  

More info can be found [HERE](https://developers.themoviedb.org/3/)
 
## Architeture
MVVM(Model View ViewModel) - using Repository, UseCase and ViewModel  
Modularization to make gradle build faster

This project is based on another architecture project I have developed:  [Github](https://github.com/maiconhellmann/hellmann-architecture)  
This evaluation was also an opportunity to go further and improve my concepts using modularization, navigation, use case, repository, ViewModel and DataBinding.

### Modules
* app: Handle views, navigation, events, states, etc
* data: Implements the local(cache) and remote repository
* domain: Defines the repository interface and the UseCase

### Language
Kotlin

### Stack
* Retrofit
* Koin - Dependency Injection
* RxJava - reactive programming
* Data binding
* Room - ORM
* LiveData 
* Navigation library

### Tests
TODO

## Improvements noticed and missing requirements
* Handle movie details separately. There are more info in the Movie details than the data returned by the list of movies. For now the app is fetching this info each time the detail screen is shown.
* Use Dagger instead of Koin. Koin is easier to use. Due to a short time to develop, it decided to use it
* Implement login/signin onboarding to be able to fetch watch list, favorites, etc. The Login feature was not in the scope of the evaluation.
* In a larger project I would use a module for each feature and a separated module for navigation
* This project`s architecture, in my opinion, was to much for this app but my intention was to show my knowledge regarding the stack of technologies used
* Remove API KEY from the repository
* Create a CI/CD script(real project)
* Automated tests(real project)
* Would be nice raising the code coverage of the tests(more than 70% at least)


## Download
The file can be downloaded clicking on the following link:  
[Download 1.0.0 version](https://github.com/maiconhellmann/bluecoding-evaluation-imdb/tree/master/releases/1.0.0/app-debug.apk)