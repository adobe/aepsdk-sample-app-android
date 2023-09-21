# AEP SDK Sample App for Android

# Notice of deprecation

Each [respective extension repository](https://developer.adobe.com/client-sdks/documentation/current-sdk-versions/#android) now has its own test app. Please refer to those repositories for their test apps.

## About this Project

This repository contains the Android sample app for the latest versions of the Android Mobile SDKs. 

> **Note**
> The sample app with the previous Android SDKs is archived in [sdk-1.x](https://github.com/adobe/aepsdk-sample-app-android/tree/sdk-1.x) branch.


## Prerequisites

- Android Studio 3.+ with an Android emulator running 7.0+
- JDK 8.+
- Gradle 4.4+

## Installation

1. Open Android Studio and select `Open an existing project`  from the main screen or click `File -> Open...` .
2. Import the Sample-App/settings.gradle file into Android Studio.
3. Set your `ENVIRONMENT_FILE_ID` in MainApp.java.
4. Run Android `app` on the emulator or on real device.

## Documentation
### Edge Extensions Prerequisites
The app needs to be configured with the following Edge extensions in the Data Collection UI before it can be used: 
- [Edge](https://developer.adobe.com/client-sdks/documentation/edge-network)
- [Edge Identity](https://developer.adobe.com/client-sdks/documentation/identity-for-edge-network)
- [Consent](https://developer.adobe.com/client-sdks/documentation/consent-for-edge-network)
- [Messaging](https://developer.adobe.com/client-sdks/documentation/iam)

### Lifecycle for Edge Network 
Follow the [documentation](https://developer.adobe.com/client-sdks/documentation/lifecycle-for-edge-network) to forward Lifecycle extension metrics to the Adobe Experience Platform.

### Messaging
Follow the [documentation](Documentation/README.md) for using the messaging features.

### Advertising Identifier
Follow the [documentation](Documentation/README.md#advertising-identifier) for enabling advertising identifier features.

## Contributing

Contributions are welcomed! Read the [Contributing Guide](./.github/CONTRIBUTING.md) for more information.

## Licensing

This project is licensed under the MIT License. See [LICENSE](LICENSE) for more information.

