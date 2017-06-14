package ebookfrenzy.com.mapdemo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mark on 5/2/2017.
 */
public interface GerritAPI {
    @GET("group?id=360630,350550,352736,358619,361058,359792,352733,359783,361291,360502,352354,361320,359173,360686,352736,360923,359796&units=metric&appid=8e45fef69845026683e14015a4c842ac")
    Call<Example> getMyJSON();

}
