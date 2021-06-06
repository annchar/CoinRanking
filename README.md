<h1 align="center">CoinRanking</h1> 

<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
[![Medium](https://img.shields.io/badge/-Medium-000000?style=for-the-badge&logo=Medium&logoColor=white)](https://annchar.medium.com/android-paging-3-library-with-offset-and-limit-parameters-mvvm-livedata-and-coroutine-part1-5f85aa4fd29a)
[![Github](https://img.shields.io/badge/-Github-000000?style=for-the-badge&logo=Github&logoColor=white)](https://github.com/annchar)

<p align="center">  
CoinRanking is a small demo application based on modern Android application tech-stacks and MVVM architecture.<br>
Fetching data from the network and render data via Paging 3
</p>
</br>

<p align="center">
<img src="/previews/screenshot.png" width="50%"/>
</p>


<img src="/previews/preview.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- Minimum SDK level 24
- [Kotlin](https://kotlinlang.org/) based.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous.
- [Koin](https://github.com/InsertKoinIO/koin) for dependency injection.
- Android Jetpack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - ViewBinding - write code that interacts with views.
  - Navigation - navigate in the app.
  - Paging 3 - render list.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Timber](https://github.com/JakeWharton/timber) - logging.
<br>

## MAD Score
<img src="/previews/summary.png"/>
<img src="/previews/jetpack.png"/>

## Architecture
CoinRanking is based on MVVM architecture and a repository pattern.
<p align="center">
  <img src="/previews/architecture.png"/>
</p>

## Open API
CoinRanking using the [Coinmarketcap API](https://coinmarketcap.com/api) for constructing RESTful API.<br>

<b>How to run application</b>
- Register [Coinmarketcap API](https://coinmarketcap.com/api)
- Open local.properties file
- Add `api_key` line also add your api key like
~~~ 
api_key = 10ce47bf-92fb-42a5-bc4c-93de83123Drf
~~~ 


## Blog
[Android Paging 3 library with Offset and Limit parameters, MVVM, LiveData, and Coroutine - Part1](https://annchar.medium.com/android-paging-3-library-with-offset-and-limit-parameters-mvvm-livedata-and-coroutine-part1-5f85aa4fd29a)

# License
```xml
Designed and developed by 2021 annchar (Chanoknad M)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
