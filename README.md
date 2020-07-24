## SimpleCleanCode

A very simple sample project that uses MVVM architecture with dependency injection, livedata, repository pattern, retrofit, testing...  

## Architecture
![Logo](./images/mvvm-architecture.png) 

In this first release, the project doesn't store local database 

## Project
* `ApiService.kt` - Web Server where retrieve the JSON data.
* `DummyObject.kt` - Data model with the information to show in the table.
* `Resource.kt` - A generic sealed class that contains data and status about loading this data.
* `DummyObjectRepository.kt` - Interface for repository pattern.
* `DummyObjectRepositoryImpl.kt` - Implementation of the repository pattern
* `AppModule.kt`, `NetworkingModule.kt`, `PresenterModule.kt` - Koin classes
* `MainActivity.kt` - Main activity of the project
* `MainAdapter.kt` - Adapter for the RecyclerView
* `MainViewModel.kt` - ViewModel of the MainActivity, prepare and manage the data for a UI component
* `CleanApp.kt` - An application class to init Koin

## Unit Test
* `ApiServiceTest.kt` - Unit test for the Api with [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
* `MainViewModelTest.kt` - Unit test for the request in ViewModel with [PowerMock](https://github.com/powermock/powermock)

## Next updates
* Add Room to store local database
* UI Test

## Developed By

Antonio Corrales desarrollogit@gmail.com

## License

Copyright 2020 Antonio Corrales

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


