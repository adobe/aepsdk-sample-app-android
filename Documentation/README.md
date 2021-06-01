# Messaging

## Configuration
Before using messaging feature in the sample app you need to follow the below steps to configure it:

1. Configure Firebase project for the aep sample app using the firebase [documentation](https://firebase.google.com/docs/cloud-messaging/android/client)
    1. Use `com.adobe.marketing.mobile.sampleapp` as the package name while creating the firebase android app. 
    1. Follow the Firebase [documentation](https://firebase.google.com/docs/cloud-messaging/android/client#add_a_firebase_configuration_file) to download and overwrite the `google-services.json` configuration file in the `Sample-App/app` folder.
1. Setup [Journey Optimizer](https://aep-sdks.gitbook.io/docs/beta/adobe-journey-optimizer)
1. Update the `LAUNCH_ENVIRONMENT_FILE_ID` constant value in `Sample-App/app/src/java/com/adobe/marketing/mobile/sampleapp/MainApp.Java` file with the config app id of the property. 
For more information on how to get the config app id from launch check this [document](https://experienceleague.adobe.com/docs/launch/using/publish/environments/environments.html?lang=en#mobile-configuration) 
