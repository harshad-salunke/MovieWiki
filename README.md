# MovieWiki

### 	Reflexion AI Assignment



### NAME OF THE APP
MovieWiki <br/>
App Demo Video - https://drive.google.com/file/d/1sI6bUqbgL6avP4PzkinMjTBZhRQ3NzJL/view <br/>


### DESCRIPTION

MovieWiki contains information about different movies so that user can get the imformation about movie.
form MovieWiki user can watch the trailer of all movies


### How to use API Data to build app 
### Poster Image
"Movie Poster": "https://picsum.photos/1280/720"
Movie poster image url is same url for all movies ,but for every refresh of url is change the image
so to solve this problem i use need to refresh the image url in Glide using signature method
```
 Glide.with(context)
            .load(movie.Movie_Poster)
            .apply(RequestOptions.fitCenterTransform()
                .placeholder(R.drawable.place_holder)
                .signature(ObjectKey(System.currentTimeMillis())))
            .into(holder.movie_poster)
```

### Youtube Trailer Video
Every movies contian the "YouTube Trailer": "rlZ5MG-E2Ls" this is the youtube Trailer code of movie so i replace this youtube url
```
https://www.youtube.com/watch?v=YouTube Trailer
```
and start new intent
```
 var intent:Intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+movie_Data.YouTube_Trailer));
            startActivity(intent)
```

### Dependencies Used
```
    // retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

// GSON
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// coroutine
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
    
// ViewModel
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.5.1'
    
//room
    implementation "androidx.room:room-runtime:2.2.4"
    kapt "androidx.room:room-compiler:2.2.4"
    implementation "androidx.room:room-ktx:2.2.5"

//    glide
    implementation 'com.github.bumptech.glide:glide:4.4.0'
    kapt 'com.github.bumptech.glide:compiler:4.4.0'

    //expandable text
    implementation 'com.ms-square:expandableTextView:0.1.4'
    
//circleimage
    implementation 'de.hdodenhof:circleimageview:3.1.0'
```


### ARCHITECTURE
 - MVVM Architecture

 
 ### Technologies Used
Kotlin	<br/>
XML	<br/>
Retrofit	<br/>
http://task.auditflo.in/2.json API <br/>

## App Ui
### App UI When  Internet Connectivity

![Untitled design (6)](https://user-images.githubusercontent.com/87861834/219965889-2c50780e-29b6-4ff4-a5ea-8a8c12de3a0e.png)


### App UI When No Internet Connectivity

![Untitled design (5)](https://user-images.githubusercontent.com/87861834/219966012-8e313f3e-b0d7-4046-bb04-cb1d88925033.png)




