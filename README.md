# Mobile-SOS-GO-iOS

IDE: Xcode Version 9.4.1

## Purpose and Responsibilities
The README.md provides needed information for the support team to build and maintain the application.  The techlead is responsible for completing this document prior to application rollout.

## App Overview
Overview of the app purpose, target audience, internal or external, features and special functionality go here.

## Build Instructions


How to use PodFile:
Navigate to Podfile and run 'pod install'
Open the ProActive.workspace created during install once the installation is complete


## Libraries Used

CocoaPods : 
The project is generated with podfile: CocoaPods 

The next libraries are installed with [cocoapods](https://cocoapods.org)

• UrbanAirship-iOS-SDK


  Push Notifications


• GoogleAnalytics


  Supervises the activity as set in your application. Thus, you see immediately what works and what does not.


• Reachability


 The Reachability use the System Configuration framework to monitor the network state of an iOS device. In particular, it demonstrates how to know when IP can be routed and when traffic will be routed through a Wireless Wide Area Network (WWAN) interface such as EDGE or 3G. Note: Reachability cannot tell your application if you can connect to a particular host, only that an interface is available that might allow a connection, and whether that interface is the WWAN. To understand when and how to use Reachability, read "Networking Overview".


•  SAMKeychain: 


 Is a simple wrapper for accessing accounts, getting passwords, setting passwords, and deleting passwords using the system Keychain on Mac OS X and iOS.


•  MBProgressHUD


MBProgressHUD is an iOS drop-in class that displays a translucent HUD with an indicator and/or labels while work is being done in a background thread. The HUD is meant as a replacement for the undocumented, private UIKit UIProgressHUD with some additional features.


## Google Analytics

In the Appdelegate.m class should be placed in  "kCFR GA_TrackingID" the key (Productive or development) of Google Analytics

This project use the instance old .For to use the new, you need uncomment the next code :
`NSError *configureError;
[[GGLContext sharedInstance] configureWithError:&configureError];`
`NSAssert(!configureError, @"Error configuring Google services: %@", configureError);`
and comment the next line of code:
`[[GAI sharedInstance] trackerWithTrackingId:@"UA-89808418-1"];`
The first code lines use the file GoogleService-Info.plist only add the key "TRACKING_ID" and the key "GOOGLE_APP_ID" in that file.
## Urban Airship
In the AirshipConfig.plist file should be placed the keys (Productive or development) of Urban Airship
## Backend Environments / Auth Details
In the Constants.m file, to change the environment, in the key named  "SIMULATION_RESPONSE " must put "1" for simulation or "0" for production

`#define SIMULATION_RESPONSE 0 // If 1 is Simulation is 0 is Production`

## Service Accounts
When use simulation (in Constants.m file put in "SIMULATION_RESPONSE" the value "1"), the app has  the username (VAZQUC20) and password (test) for to use the app. In Simulation need that username , because the app search that name, for to show information of test.
