"Movie Plus"

<img src="Screenshot_2020-05-06-02-13-54-923_com.example.android.popularmovies.jpg" width = "300"> <img src="Screenshot_2020-05-06-02-14-13-845_com.example.android.popularmovies.jpg" width = "300">

<img src="Screenshot_2020-05-06-02-14-28-196_com.example.android.popularmovies.jpg" width = "300"> <img src="Screenshot_2020-05-06-02-14-34-793_com.example.android.popularmovies.jpg" width = "300">

<img src="Screenshot_2020-05-06-02-14-39-725_com.example.android.popularmovies.jpg" width = "300"> <img src="97313233_901712653634466_1972747632321232896_n.jpg" width = "300">

<img src="Screenshot_2020-05-06-02-14-49-093_com.example.android.popularmovies.jpg" width = "300"> 

A Movie browsing android application that allows the user to browse popular and top-rated movies, see movies details, casts, trailers, reviews, posters, adding them to your favorite list and can run offline, it is built using themoviedb API.

it uses:

1-MVVM with Android Architecture Components:

-Room Persistence Library: an abstraction layer over SQLite to allow for more robust database access, and allowing the app to run offline.

-LiveData: an observable data holder class that is lifecycle aware.

-ViewModel: A class that store and manage UI-related data in a lifecycle conscious way, and allows data to survive configuration changes such as screen rotations.

-Data Binding Library: allows you to bind UI components in your layouts to data sources in the app.

-Paging library: for pagination and endless scrolling of movies, and using Boundary Callback for better user exprience.

-Navigation component: to handle fragments navigation.

2-Retrofit: to connect to themoviedb and fetch data in an asynchronous way.

3-Gson - for serialization/deserialization Java Objects into JSON and back.

4-Fragments: a modular section of an activity to to build a multi-pane UI for tablets.

5-RecyclerView, GridView and Adapters: to view movie posters in a scrollable list.

6-ConstraintLayout: to build the UI for the movie detail fragment
