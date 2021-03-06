
# react-native-new-land-scanner

## Getting started

`$ npm install react-native-new-land-scanner --save`
`$ npm install git+https://github.com/nerjok/react-native-newland_scanner.git`

### Mostly automatic installation

`$ react-native link react-native-new-land-scanner`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-new-land-scanner` and add `RNNewLandScanner.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNNewLandScanner.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNNewLandScannerPackage;` to the imports at the top of the file
  - Add `new RNNewLandScannerPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-new-land-scanner'
  	project(':react-native-new-land-scanner').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-new-land-scanner/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-new-land-scanner')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNNewLandScanner.sln` in `node_modules/react-native-new-land-scanner/windows/RNNewLandScanner.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using New.Land.Scanner.RNNewLandScanner;` to the usings at the top of the file
  - Add `new RNNewLandScannerPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNNewLandScanner from 'react-native-new-land-scanner';

// TODO: What to do with the module?
RNNewLandScanner;
```
  
