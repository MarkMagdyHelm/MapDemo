package ebookfrenzy.com.mapdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 5/4/2017.
 */

public class DerpData {




    public static List<ebookfrenzy.com.mapdemo.List> getListData(List<ebookfrenzy.com.mapdemo.List> l){
        List<ebookfrenzy.com.mapdemo.List> data = new ArrayList<>();



            for (int i = 0; i < l.size() ; i++) {
                ebookfrenzy.com.mapdemo.List   item = new ebookfrenzy.com.mapdemo.List();
                //  item.setImageResid(icons[i]);
                item.setCoord(l.get(i).getCoord());
                item.setName(l.get(i).getName());
                item.setMain(l.get(i).getMain());
                item.setWind(l.get(i).getWind());
                data.add(item);
            }


        return data;
    }
    }

