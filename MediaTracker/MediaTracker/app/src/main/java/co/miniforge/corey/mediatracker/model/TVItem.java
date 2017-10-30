package co.miniforge.corey.mediatracker.model;

import org.json.JSONObject;

/**
 * Created by corey on 10/20/17.
 */

public class TVItem extends MediaItem {

    public int currentEpisodeWatched;
    public int totalEpisodes;


    public TVItem(JSONObject jsonObject) {
        super(jsonObject);

        try {
            currentEpisodeWatched = jsonObject.getInt("currentEpisodeWatched");
            totalEpisodes = jsonObject.getInt("totalEpisodes");

        } catch (Exception e) {

        }
    }


}
