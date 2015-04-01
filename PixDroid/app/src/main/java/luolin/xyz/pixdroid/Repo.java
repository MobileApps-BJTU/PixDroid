package luolin.xyz.pixdroid;

import java.util.ArrayList;

/**
 * Created by yx on 2015/4/2.
 */
public class Repo {

    private static Repo repo = null;

    public ArrayList<String> LIKE ;
    public ArrayList<String> ARCHIVE ;
    public ArrayList<String> TRASH ;

    private Repo(){
        LIKE = new ArrayList<String>();
        ARCHIVE = new ArrayList<String>();
        TRASH = new ArrayList<String>();
    }

    public static Repo getInstance(){
        if(repo == null){
            repo = new Repo();
        }
        return repo;
    }


}
