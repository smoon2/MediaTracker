package co.miniforge.corey.mediatracker.model;

import android.util.Log;

import org.json.JSONObject;

import java.security.MessageDigest;

import co.miniforge.corey.mediatracker.media_store.Md5IdHelper;

/**
 * Created by corey on 10/20/17.
 */

public class MediaItem {
    public static int defaultId = 0;

    public String id;
    public String title;
    public String description;
    public String url;

    //We need a field that stores what kind of media item this is
    //We will do this by creating an enum that can identity the type of item.
    public MediaItemType type = MediaItemType.Generic;


    public MediaItem(JSONObject jsonObject){
        try{
            //Generate id based on the object instance (should work :D)
            this.id = jsonObject.getString("id");
            this.title = jsonObject.getString("title");
            this.description = jsonObject.getString("description");
            this.url = jsonObject.getString("url");


//            this.type = getTypeForObject((MediaItemType)jsonObject.get("type"));
        } catch (Exception e){
            Log.e("toJSONError", String.format("There was an error: %s", e.getMessage()));
        }
    }

    public MediaItem(){
        this.id = Md5IdHelper.idForObject(defaultId++);
        this.title = "defaultTitle";
        this.description = "defaultDescription";
        this.url = "defaultUrl";
    }

//    public MediaItemType getTypeForObject(MediaItemType value) {
//        switch (value){
//            case Generic:
//                break;
////            case TV:
////                return MediaItemType.TV;
////            case Movie:
////                return MediaItemType.Movie;
////            default:
////                return MediaItemType.Generic;
//        }
//
//        return MediaItemType.Generic;
//    }

    public JSONObject toJson(){
        JSONObject mediaItem = new JSONObject();

        try{
            mediaItem.put("id", this.id);
            mediaItem.put("title", this.title);
            mediaItem.put("description", this.description);
            mediaItem.put("url", this.url);

            mediaItem.put("type", this.type);
        } catch (Exception e){
            Log.e("toJSONError", String.format("There was an error: %s", e.getMessage()));
        }

        return mediaItem;
    }
}
