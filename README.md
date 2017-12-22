# Playground app

## What's that?

Side project for playing around with different Android libraries, approaches and patterns.

## Libraries

## Architecture
* Model-View-Presenter
    * [Base MVP classes](https://github.com/jahudzik/playground-app/tree/develop/app/src/main/java/com/jahu/playground/mvp)
    * [Example feature package](https://github.com/jahudzik/playground-app/tree/develop/app/src/main/java/com/jahu/playground/features/chooseuser)

### Dependency Injection
* Dagger [(docs)](https://google.github.io/dagger/)
    * [BRANCH: di/dagger](https://github.com/jahudzik/playground-app/commits/di/dagger)
    * [Basic configuration with a single AppComponent providing Context](https://github.com/jahudzik/playground-app/commit/e1778cc03347b71b97c6e47f7faf220aea50c0ab)
    * [Moved Retrofit initialization to the Dagger's DataModule and injecting it](https://github.com/jahudzik/playground-app/commit/e3364d1d20a1ece870fbd360fe3f757cb899ceda)
    * [Added Dagger module providing all the UseCase classes and their dependencies](https://github.com/jahudzik/playground-app/commit/7e014689f0c425ea6eac937bbbc135c0b2f773f2)
    * [Example of a separate subcomponent definition for the single feature](https://github.com/jahudzik/playground-app/commit/f7e85d428fc437b784aa2c3809176f4dc331c0fc)
    
### Network

* Retrofit [(docs)](http://square.github.io/retrofit/)
    * [Setup](https://github.com/jahudzik/playground-app/commit/9fab27130c0beda4ae7f6fbc639b718fc965a602)

### Data persistence
* Sugar ORM [(docs)](http://satyan.github.io/sugar/)
    * [BRANCH: db/sugar_orm](https://github.com/jahudzik/playground-app/commits/db/sugar_orm)
    * [Basic configuration](https://github.com/jahudzik/playground-app/commit/41f8b59aa05779a4e1c26c3a384792d7bb43da0d)
    * [Marking data classes as SugarRecords](https://github.com/jahudzik/playground-app/commit/fe2f6db4296c8cbf7def121123bb9aaf8736da2c)
    * [Repository implementation](https://github.com/jahudzik/playground-app/commit/a6db1884e0c1016f2644a40e6ef97fff848bd613)

### Static code analysis

* Detekt [(docs)](https://github.com/arturbosch/detekt)
    * [Configuration](https://github.com/jahudzik/playground-app/commit/04ba665d3e46667f92e50296ee62e456410f011c)
* Lint [(docs)](https://developer.android.com/studio/write/lint.html)
    * [Configuration](https://github.com/jahudzik/playground-app/commit/c677e2cc06d0bc2ceb5b311bb04cea5e257c8299)

### Logging and analysis

* StrictMode [(docs)](https://developer.android.com/reference/android/os/StrictMode.html)
    * [Setup](https://github.com/jahudzik/playground-app/commit/53d79cc6e12ea6a1bbcf65022915b1416e11bf68)
* Timber [(docs)](https://github.com/JakeWharton/timber)
    * [Setup](https://github.com/jahudzik/playground-app/commit/1235934fc8dc6d5218af83c120405c1cc8dafb42)
* Stetho [(docs)](http://facebook.github.io/stetho/)
    * [Setup](https://github.com/jahudzik/playground-app/commit/aa166640670e80b8937cef9512c7ed25e197e130)
* LeakCanary [(docs)](https://github.com/square/leakcanary)
     * [Setup](https://github.com/jahudzik/playground-app/commit/85480900bc857023627a91a305e5a14a3dc1bf12)
     * [Watching for fragment leaks](https://github.com/jahudzik/playground-app/commit/38d7d45242d15cfad3855996886794753907f0ef)

## UI Stuff

* FloatingActionButton [(docs)](https://developer.android.com/reference/android/support/design/widget/FloatingActionButton.html)
    * [Setup](https://github.com/jahudzik/playground-app/commit/1135142571efdc2edb672124894f877557bc5ac6)
* BottomNavigationView [(docs)](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)
    * [Setup](https://github.com/jahudzik/playground-app/commit/484c22b1d450b53170622aa58d34664564ee627e)

## Security

* Tapjacking [(reference)](https://blog.devknox.io/tapjacking-android-prevent/)
    * [Preventing it by setting filterTouchesWhenObscured attribute](https://github.com/jahudzik/playground-app/commit/cd5bfb43369044311bb222357f400c45875e9e22)
* AllowBackup [(reference)](https://blog.devknox.io/what-is-android-allowbackup-how-it-affects-your-app/)
    * [Disabling it](https://github.com/jahudzik/playground-app/commit/8a4fb57c910981c325d42e8a24231e70abec1ca2)

### Testing 

* JUnit 4 [(docs)](http://junit.org/junit4)
    * [Setup](https://github.com/jahudzik/playground-app/commit/2ae79209f13a80ccb9ad02b0a8e71e0339d83cc3)
* Mockito Kotlin [(docs)](https://github.com/nhaarman/mockito-kotlin)
    * [Setup](https://github.com/jahudzik/playground-app/commit/2ae79209f13a80ccb9ad02b0a8e71e0339d83cc3)
    * Argument Matchers ([reference](https://github.com/nhaarman/mockito-kotlin/wiki/Mocking-and-verifying#argument-matchers))
        * [Verifying invocation arguments with the Argument Matchers](https://github.com/jahudzik/playground-app/commit/84ac249a362450233ad686be94697e95335940de#diff-658aa0166cdde11c0d58825acdbb55e4)
    * InOrder ([reference](https://github.com/nhaarman/mockito-kotlin/wiki/Mocking-and-verifying#inorder))
        * [Testing invocations order with Mockito.inOrder()](https://github.com/jahudzik/playground-app/commit/b76d8d22a47665540242ae0d63a601173ddf81ed)
        * [Using Mockito.inOrder() with higher-order function parameter](https://github.com/jahudzik/playground-app/commit/7a2c55efbd485d233fc171f8c3aca87278ecbe91)
    * Testing asynchronous callbacks with Mockito.thenAnswer()
        * [Example 1](https://github.com/jahudzik/playground-app/commit/a6f1b28ce48273f228319a6ce7ae048f5ed1158a)
        * [Example 2](https://github.com/jahudzik/playground-app/commit/026e3ee0e87ede7d90138389929d28b7bfb4fb18)
* Espresso [(docs)](https://developer.android.com/training/testing/espresso/index.html)
    * [BRANCH: test/espresso](https://github.com/jahudzik/playground-app/commits/test/espresso)
    * [Helper class with common operations](https://github.com/jahudzik/playground-app/blob/test/espresso/app/src/androidTest/java/com/jahu/playground/BaseTest.kt)
    * [Setup](https://github.com/jahudzik/playground-app/commit/027034a40113fafffe1d04caa7368b40f02d355f)
    * [Simple test example](https://github.com/jahudzik/playground-app/commit/ffc5a3a9747066a13857edd902fab1bbcc70c57e)
    * [Operations on RecyclerView items](https://github.com/jahudzik/playground-app/commit/588ff5a17335e84fd8216cf9f6aa86f1820b2ace)

### Continuous integration
 
* Travis [(docs)](https://travis-ci.org/)
    * [Configuration (1)](https://github.com/jahudzik/playground-app/commit/6427b738ee8ab3c379be6d0e96b3f6f332906c11)
    * [Configuration (2)](https://github.com/jahudzik/playground-app/commit/30016905e0f613d652efe96119d3cda54cd904ec)
    * [Configuration (3)](https://github.com/jahudzik/playground-app/commit/ec40fb9959ca6bdc02da10b0d524a4334e257335)
    * [Configuration (4)](https://github.com/jahudzik/playground-app/commit/c418f26c6f480ef4a302a6b8a82ba3ccfd72d150)

## Kotlin stuff

* Use objects to define singletons [(docs)](https://kotlinlang.org/docs/reference/object-declarations.html#object-declarations)
    * [Example](https://github.com/jahudzik/playground-app/commit/c37c31936115a0837badbbc2a8e20e62a9b8c2ea)
* Use package-level functions for static stuff [(docs)](https://kotlinlang.org/docs/reference/classes.html#companion-objects)
    * [Getting rid of companion objects](https://github.com/jahudzik/playground-app/commit/afcc74664ce97a8984b7ae867fdc211f39451144)
* Extension functions [(docs)](https://kotlinlang.org/docs/reference/extensions.html)
    * [Example](https://github.com/jahudzik/playground-app/commit/afa7155085c08844e5618282d1326283c24cc398)

## IntelliJ stuff

* Share settings in VCS [(reference)](https://tips.seebrock3r.me/share-settings-with-the-team-a-year-later-e28c24fc07aa#.lr4d1itga)
    * [gitignore configuration](https://github.com/jahudzik/playground-app/commit/31c69d569e3d8d198fb29896faac446859d5b44d#diff-a084b794bc0759e7a6b77810e01874f2)
* [Allowing underscores in methods names (unit tests)](https://github.com/jahudzik/playground-app/commit/a0dba3aeac1aee3c599d8e0ae6aba9699cb9a866)
