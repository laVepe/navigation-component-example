# navigation-component-example
<p>Simple Android app showcasing the usage of the navigation architecture component. Build in Kotlin with use of LiveData, ViewModel and Navigation.</p>

App consists of two activities and multiple fragments. First one is LoginActivity which has only one fragment. Second one is MainActivity which uses navigation drawer for populating fragments. 
<br><br>Navigation between screens is built using Destinations and Actions in Navigation graph. App has two navigation graphs (one for each activity). 
<br><br>To send data between actvities/fragments <b>Safe args</b> Gradle plugin is used.
<br><br>Navigation component also makes it easier to react to click events on items in Recycler view. There is no need to pass listener instance to adapter if you're only interested in navigating to another screen.
<br><br>App also shows handling <b>deep links</b> with navigation component. Click on an icon in right corner of toolbar creates a notification. After clicking on the notification, user is taken to DeepLinkFragment.

<br><b>FreeContent</b> flow is a showcase of <b>conditional navigation</b>. User can see <b>PremiumContent</b> fragment only after he/she has "bought" the premium version. So user has to go through <b>BuyPremium</b> flow at first.

<br><b>Resources used:</b><br>
• navigation Codelab: https://codelabs.developers.google.com/codelabs/android-navigation/#0

• official documentation: https://developer.android.com/topic/libraries/architecture/navigation/navigation-implementing

• safe args: https://developer.android.com/topic/libraries/architecture/navigation/navigation-implementing#Safe-args
