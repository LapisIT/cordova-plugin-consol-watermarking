# cordova-plugin-consol-watermarking

# Add/remove plugin
```bash
cordova plugin add https://github.com/SpatialVision/cordova-plugin-consol-watermarking.git#develop --save

cordova plugin remove cordova-plugin-consol-watermarking
```

# Watermark 
file URI is the output of camera plugin.
 
```js
    const options: CameraOptions = {
      allowEdit: false,
      quality: 100,
      destinationType: this.camera.DestinationType.FILE_URI,
      encodingType: this.camera.EncodingType.JPEG,
      mediaType: this.camera.MediaType.PICTURE,
      correctOrientation: true,
    };

    this.camera.getPicture(options).then((fileUri) => {
      const fileUri = 'file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521004359073.jpg', 
        id = 'B1427146',
        address = '44, AARONS PASS VIC 2850',
        name = 'TEST_PROJ16',
        lat = '37.81675',
        lng = '144.95621',
        date = '2018-03-14',
        time = '16:30:54 + 11:00',
        values = [fileUri, id, address, name, lat, lng, date, time];
    
      //window object via injected window service
      this.window.nativeWindow.ConsolWatermarking.watermark(values, (res) => {
      })
    });

```
# Android impl
Big thanks to the following library:

https://github.com/vinaygaba/RubberStamp

# Android log output when successful
```
03-15 12:05:45.968 29225 29334 D ConsolWatermarking: url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg
03-15 12:05:45.968 29225 29334 D Watermarking: url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg, WatermarkLines{id='B1427146', address='44, AARONS PASS VIC 2850', name='TEST_PROJ16', lat='37.81675° S', lng='144.95621° E', date='2018-03-14', time='16:30:54 + 11:00'}
03-15 12:05:45.968 29225 29334 D Watermarking: watermark photoUrl: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg
03-15 12:05:45.968 29225 29334 D Watermarking-ReadPhoto: read file from url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg
03-15 12:05:46.278 29225 29334 D Watermarking-ReadPhoto: read: android.graphics.Bitmap@636019d dencity: 160, getWidth: 1458, getHeight: 1944, getScaledWidth(read.getDensity()): 1458, getScaledHeight(read.getDensity()): 1944
03-15 12:05:46.278 29225 29334 D Watermarker: photo: com.yarris.cordova.consol.watermarking.Photo@5eab612
03-15 12:05:46.278 29225 29334 D Watermarker: header: Rect(0, 0 - 1458, 174)
03-15 12:05:46.278 29225 29334 D Watermarker: footer: Rect(0, 1808 - 1458, 1944)
03-15 12:05:46.278 29225 29334 D WatermarkConfig: textSize: 42
03-15 12:05:46.278 29225 29334 D Watermarker: markHeaderFooter: android.graphics.Bitmap@636019d
03-15 12:06:09.788 29225 29334 D ConsolWatermarking: url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg
03-15 12:06:09.788 29225 29334 D Watermarking: url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg, WatermarkLines{id='B1427146', address='44, AARONS PASS VIC 2850', name='TEST_PROJ16', lat='37.81675° S', lng='144.95621° E', date='2018-03-14', time='16:30:54 + 11:00'}
03-15 12:06:09.788 29225 29334 D Watermarking: watermark photoUrl: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg
03-15 12:06:09.788 29225 29334 D Watermarking-ReadPhoto: read file from url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol/cache/1521075941587.jpg
03-15 12:06:10.368 29225 29334 D Watermarking-ReadPhoto: read: android.graphics.Bitmap@a761a99 dencity: 160, getWidth: 1458, getHeight: 1944, getScaledWidth(read.getDensity()): 1458, getScaledHeight(read.getDensity()): 1944
03-15 12:06:10.368 29225 29334 D Watermarker: photo: com.yarris.cordova.consol.watermarking.Photo@bba215e
03-15 12:06:10.368 29225 29334 D Watermarker: header: Rect(0, 0 - 1458, 174)
03-15 12:06:10.368 29225 29334 D Watermarker: footer: Rect(0, 1808 - 1458, 1944)
03-15 12:06:10.368 29225 29334 D WatermarkConfig: textSize: 42
03-15 12:06:10.368 29225 29334 D Watermarker: markHeaderFooter: android.graphics.Bitmap@a761a99
03-15 12:20:37.418 12270 12270 D ConsolWatermarking: Initializing ConsolWatermarking
03-15 12:21:41.908 12270 12486 D ConsolWatermarking: url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521076890723.jpg
03-15 12:21:41.908 12270 12486 D Watermarking: url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521076890723.jpg, WatermarkLines{id='B1427146', address='44, AARONS PASS VIC 2850', name='TEST_PROJ16', lat='37.81675° S', lng='144.95621° E', date='2018-03-14', time='16:30:54 + 11:00'}
03-15 12:21:41.908 12270 12486 D Watermarking: watermark photoUrl: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521076890723.jpg
03-15 12:21:41.908 12270 12486 D Watermarking-ReadPhoto: read file from url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521076890723.jpg
03-15 12:21:42.218 12270 12486 D Watermarking-ReadPhoto: read: android.graphics.Bitmap@987a308 dencity: 160, getWidth: 1458, getHeight: 1944, getScaledWidth(read.getDensity()): 1458, getScaledHeight(read.getDensity()): 1944
03-15 12:21:42.218 12270 12486 D Watermarker: photo: com.yarris.cordova.consol.watermarking.Photo@29d0aa1
03-15 12:21:42.218 12270 12486 D Watermarker: header: Rect(0, 0 - 1458, 174)
03-15 12:21:42.218 12270 12486 D Watermarker: footer: Rect(0, 1808 - 1458, 1944)
03-15 12:21:42.218 12270 12486 D WatermarkConfig: textSize: 42
03-15 12:21:42.218 12270 12486 D Watermarker: markHeaderFooter: android.graphics.Bitmap@987a308
03-15 12:21:42.458 12270 12486 D Watermarking-SavePhoto: save url: file:///storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521076890723.jpg
03-15 12:21:42.458 12270 12486 D Watermarking-SavePhoto: save to file: /storage/emulated/0/Android/data/au.com.spatialvision.consol.watermarking/cache/1521076890723.jpg
03-15 12:21:42.748 12270 12486 D Watermarking: success: true
```