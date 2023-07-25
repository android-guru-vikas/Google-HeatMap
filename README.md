# Google-HeatMap
This Android project is a dynamic and interactive mapping application that harnesses the power of Google Maps and the Google Maps Android Heatmap Utility. The application enables users to visualize and analyze data through heatmaps, providing valuable insights and enhancing their understanding of spatial patterns.
Refer screenshots for heatmap - 
![Heatmap](https://github.com/android-guru-vikas/Google-HeatMap/blob/main/heatmap.png)

## Setup
The easiest way to add the Google Maps Android Heatmap Utility library to your project is by adding it as a dependency to your `build.gradle`
```java
dependencies {
      implementation 'com.google.maps.android:maps-utils-ktx:3.4.0'
}
```

## Using Heatmap Library
For a basic implementation, you'll need to

1. Get a maps API KEY - follow this - https://developers.google.com/maps/documentation/android-sdk/get-api-key
2. Add a simple heatmap.
```java
        private fun addHeatMap() {
        googleMap.setMaxZoomPreference(20f)
        googleMap.setMinZoomPreference(1f)
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val patternItems = listOf(Dot(), Gap(10f), Dash(20f))
        val outerRectangleOptions = PolygonOptions()
            .add(
                LatLng(20.953999, 79.444065),
                LatLng(20.954141, 79.442888),
                LatLng(20.953436, 79.442667),
                LatLng(20.953257, 79.443890),
            )
            .zIndex(60f)
            .strokeWidth(2f)
            .strokeJointType(JointType.BEVEL)
            .strokePattern(patternItems)
            .strokeColor(0x7d800000)
            .fillColor(0x7dff0000) // Red with 50% opacity
        googleMap.addPolygon(outerRectangleOptions)
        val centerLocation = LatLng(20.953782, 79.443418)
        val cameraUpdate =
            CameraUpdateFactory.newLatLngZoom(centerLocation, 18f) // Adjust zoom level as needed
        googleMap.moveCamera(cameraUpdate)
}
```

3. Remove a heatmap
```java
    tileOverlay?.remove()
```
      
For more details, checkout this - 
https://developers.google.com/maps/documentation/android-sdk/utility/heatmap#maps_android_utils_heatmap_simple-kotlin
