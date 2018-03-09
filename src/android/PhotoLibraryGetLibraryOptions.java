package com.yarris.cordova.consol.watermarking;

public class PhotoLibraryGetLibraryOptions {

  public final int itemsInChunk;
  public final double chunkTimeSec;
  public final boolean includeAlbumData;

  public PhotoLibraryGetLibraryOptions(int itemsInChunk, double chunkTimeSec, boolean includeAlbumData) {
    this.itemsInChunk = itemsInChunk;
    this.chunkTimeSec = chunkTimeSec;
    this.includeAlbumData = includeAlbumData;
  }

}
