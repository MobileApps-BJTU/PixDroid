package luolin.xyz.pixdroid.model;

import android.content.Context;

/**
 * Created by LuoLin on 2015/3/30.
 */
public class Picture {

    //private int id;
    private String name;

    public Picture(String name) {
        this.name = name;
    }

    public int getImageResourceId(Context context) {
        try {
            return context.getResources().getIdentifier(this.name, "drawable", context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}