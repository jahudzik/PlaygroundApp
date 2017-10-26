# Playground app

## What's that?

Side project for playing around with different Android libraries, approaches and patterns.

## Libraries

### Network

* [Retrofit](http://square.github.io/retrofit/)
    * [Setup](https://github.com/jahudzik/playground-app/commit/9fab27130c0beda4ae7f6fbc639b718fc965a602)

## Architecture
* Model-View-Presenter
    * [BasePresenter](https://github.com/jahudzik/playground-app/commit/9e19f351c60843ef017eccf85fb436ce2714d0ec)
    * [BaseActivity](https://github.com/jahudzik/playground-app/commit/460df953704898aee4d42b41b178330ee6a022ea)
    * [BaseFragment](https://github.com/jahudzik/playground-app/commit/5a6b8bcc42b6107f9034182baa1f8891a9f38922)

### Testing 

* [JUnit 4](http://junit.org/junit4)
    * [Setup](https://github.com/jahudzik/playground-app/commit/2ae79209f13a80ccb9ad02b0a8e71e0339d83cc3)
* [Mockito Kotlin](https://github.com/nhaarman/mockito-kotlin)
    * [Setup](https://github.com/jahudzik/playground-app/commit/2ae79209f13a80ccb9ad02b0a8e71e0339d83cc3)
    * Testing asynchronous callbacks with Mockito.doAnswer()
        * [Example 1](https://github.com/jahudzik/playground-app/commit/a6f1b28ce48273f228319a6ce7ae048f5ed1158a)
        * [Example 2](https://github.com/jahudzik/playground-app/commit/94114633db2432b2ad2e4b8db1a919b8a585474a#diff-3ffc222822565a8f64d7a4f732a977e5)
    * [Testing invocations order with Mockito.inOrder()](https://github.com/jahudzik/playground-app/commit/b76d8d22a47665540242ae0d63a601173ddf81ed)        

### Continuous integration
 
* [Travis](https://travis-ci.org/)
    * [Configuration (1)](https://github.com/jahudzik/playground-app/commit/6427b738ee8ab3c379be6d0e96b3f6f332906c11)
    * [Configuration (2)](https://github.com/jahudzik/playground-app/commit/30016905e0f613d652efe96119d3cda54cd904ec)
    * [Configuration (3)](https://github.com/jahudzik/playground-app/commit/ec40fb9959ca6bdc02da10b0d524a4334e257335)
    * [Configuration (4)](https://github.com/jahudzik/playground-app/commit/c418f26c6f480ef4a302a6b8a82ba3ccfd72d150)

### Static code analysis

* [Detekt](https://github.com/arturbosch/detekt)
    * [Configuration](https://github.com/jahudzik/playground-app/commit/04ba665d3e46667f92e50296ee62e456410f011c)
* [Lint](https://developer.android.com/studio/write/lint.html)
    * [Configuration](https://github.com/jahudzik/playground-app/commit/c677e2cc06d0bc2ceb5b311bb04cea5e257c8299)

### Logging and analysis

* [StrictMode](https://developer.android.com/reference/android/os/StrictMode.html)
    * [Setup](https://github.com/jahudzik/playground-app/commit/53d79cc6e12ea6a1bbcf65022915b1416e11bf68)
* [Timber](https://github.com/JakeWharton/timber)
    * [Setup](https://github.com/jahudzik/playground-app/commit/1235934fc8dc6d5218af83c120405c1cc8dafb42)
* [Stetho](http://facebook.github.io/stetho/)
    * [Setup](https://github.com/jahudzik/playground-app/commit/aa166640670e80b8937cef9512c7ed25e197e130)
* [LeakCanary](https://github.com/square/leakcanary)
     * [Setup](https://github.com/jahudzik/playground-app/commit/85480900bc857023627a91a305e5a14a3dc1bf12)

## UI Stuff

* [FloatingActionButton](https://developer.android.com/reference/android/support/design/widget/FloatingActionButton.html)
    * [Setup](https://github.com/jahudzik/playground-app/commit/1135142571efdc2edb672124894f877557bc5ac6)
* [BottomNavigationView](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView.html)
    * [Setup](https://github.com/jahudzik/playground-app/commit/484c22b1d450b53170622aa58d34664564ee627e)

## Security

* [Tapjacking](https://blog.devknox.io/tapjacking-android-prevent/)
    * [Preventing it by setting filterTouchesWhenObscured attribute](https://github.com/jahudzik/playground-app/commit/cd5bfb43369044311bb222357f400c45875e9e22)
* [AllowBackup](https://blog.devknox.io/what-is-android-allowbackup-how-it-affects-your-app/)
    * [Disabling it](https://github.com/jahudzik/playground-app/commit/8a4fb57c910981c325d42e8a24231e70abec1ca2)

## Kotlin stuff

* [Use objects to define singletons](https://kotlinlang.org/docs/reference/object-declarations.html#object-declarations)
    * [Example](https://github.com/jahudzik/playground-app/commit/c37c31936115a0837badbbc2a8e20e62a9b8c2ea)
* [Use package-level functions for static stuff](https://kotlinlang.org/docs/reference/classes.html#companion-objects)
    * [Getting rid of companion objects](https://github.com/jahudzik/playground-app/commit/afcc74664ce97a8984b7ae867fdc211f39451144)
* [Extension functions](https://kotlinlang.org/docs/reference/extensions.html)
    * [Example](https://github.com/jahudzik/playground-app/commit/afa7155085c08844e5618282d1326283c24cc398)

## IntelliJ stuff

* [Share settings in VCS](https://tips.seebrock3r.me/share-settings-with-the-team-a-year-later-e28c24fc07aa#.lr4d1itga)
    * [gitignore configuration](https://github.com/jahudzik/playground-app/commit/31c69d569e3d8d198fb29896faac446859d5b44d#diff-a084b794bc0759e7a6b77810e01874f2)
* [Allowing underscores in methods names (unit tests)](https://github.com/jahudzik/playground-app/commit/a0dba3aeac1aee3c599d8e0ae6aba9699cb9a866)
