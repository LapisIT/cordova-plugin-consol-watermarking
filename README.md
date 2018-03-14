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