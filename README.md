# Project 2 - *Twitter*

**Twitter** is an android app that allows a user to view their Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **26** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **sign in to Twitter** using OAuth login
* [x] User can **view tweets from their home timeline**
  * [x] User is displayed the username, name, and body for each tweet
  * [x] User is displayed the [relative timestamp](https://gist.github.com/nesquena/f786232f5ef72f6e10a7) for each tweet "8m", "7h"
* [x] User can ***log out of the application** by tapping on a logout button
* [x] User can **compose and post a new tweet**
  * [x] User can click a “Compose” icon in the Action Bar on the top right
  * [x] User can then enter a new tweet and post this to Twitter
  * [x] User is taken back to home timeline with **new tweet visible** in timeline
  * [x] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
* [x] User can **see a counter that displays the total number of characters remaining for tweet** that also updates the count as the user types input on the Compose tweet page
* [x] User can **pull down to refresh tweets timeline**
* [x] User can **see embedded image media within a tweet** on list or detail view.

The following **optional** features are implemented:

* [x] User is using **"Twitter branded" colors and styles**
* [ ] User sees an **indeterminate progress indicator** when any background or network task is happening
* [x] User can **select "reply" from home timeline to respond to a tweet**
  * [x] User that wrote the original tweet is **automatically "@" replied in compose**
* [ ] User can tap a tweet to **open a detailed tweet view**
  * [ ] User can **take favorite (and unfavorite) or retweet** actions on a tweet
* [ ] User can view more tweets as they scroll with infinite pagination
* [ ] Compose tweet functionality is built using modal overlay
* [ ] User can **click a link within a tweet body** on tweet details view. The click will launch the web browser with relevant page opened.
* [x] Replace all icon drawables and other static image assets with [vector drawables](http://guides.codepath.org/android/Drawables#vector-drawables) where appropriate.
* [ ] User can view following / followers list through any profile they view.
* [ ] Use the View Binding library to reduce view boilerplate.
* [ ] On the Twitter timeline, apply scrolling effects such as [hiding/showing the toolbar](http://guides.codepath.org/android/Using-the-App-ToolBar#reacting-to-scroll) by implementing [CoordinatorLayout](http://guides.codepath.org/android/Handling-Scrolls-with-CoordinatorLayout#responding-to-scroll-events).
* [ ] User can **open the twitter app offline and see last loaded tweets**. Persisted in SQLite tweets are refreshed on every application launch. While "live data" is displayed when app can get it from Twitter API, it is also saved for use in offline mode.

The following **additional** features are implemented:

* [ ] List anything else that you can get done to improve the app functionality!

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='[http://i.imgur.com/link/to/your/gif/file.gif](https://imgur.com/a/7nhY32N)' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Kap](https://getkap.co/).

Here's a link: https://imgur.com/a/7nhY32N

## Notes

Block: Problems displaying character counter when composing a new tweet (20 min)
Solution: Had to wrap the EditText with a TextInputLayout and then use the right id’s in the RelativeLayout

Block: Adding an image to the view in a tweet (40 min)
Solution: 
I had to get the json object from the “entities” API and then get the “media” and finally the url of the media with “media_url_https” from the API
I had to create a new ImageView and set the visibility to none if there is no image in the tweet

Block: Adding the relative date and time stretch feature (20 min)
Solution: Had to go back into the code and see how I was grabbing the other data and utilizing them in order to figure it out; add a view to the xml file and then call the new function in TweetsAdapter

Block: The margin above and below some tweet images are really large (a lot of whitespace) while other images look fine (20 min)
Solution: 
I utilized the centerInside scale type https://guides.codepath.com/android/Working-with-the-ImageView which cropped the images to a good size no matter the original format
I also used layout_gravity center which centered the images after centering them inside the given area

Block: Making the profile images circular (20 min)
Solution: 
At first I tried the solutions listed in this StackOverflow page https://stackoverflow.com/questions/22105775/imageview-in-circular-through-xml but they did not work
Then I tried the rounded corners function in the Java file to change the shape of the profile images at runtime rather than in the xml

Block: Unable to get extended tweets from API (30 min)
Solution: Figured out that the extended_text attribute from the API is a paid feature so I could not use it. However, I added code to pull from "full_text" anyways. I debugged the problem by going step-by-step and viewing the JSON response object from the API and finding there was no "extended_text."

Block: When creating the reply button, it will not navigate to the reply activity. It will navigate to other pages (such as the compose activity if I alter the code slightly) but it crashes when navigating to the reply activity. (30 min)
Solution: I accidentally created two separate java and xml files so they were not properly linked. I deleted the files and recreated an “Empty Activity” and the pages were able to navigate from one to the other.

Block: Grabbing the username of the original poster to write automatically in the reply (1 hr)
Solution: I needed to put the setOnClickListener for my button in the bind function in my TweetsAdapter so that I could easily access the original tweet and all its data


## Open-source libraries used

* [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
* [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2022] [Alana Depaz]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
