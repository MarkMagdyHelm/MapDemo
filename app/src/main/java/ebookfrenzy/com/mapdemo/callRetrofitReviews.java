package ebookfrenzy.com.mapdemo;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;


/**
 * Created by nayir on 5/4/2017.
 */

public class callRetrofitReviews {
    static   String s="2.5";
    static boolean callCreatedReviews = false;
    static callRetrofitReviews RetrofitReviews;
    Context con;
    ArrayList< ebookfrenzy.com.mapdemo.List>  reviews;

    private callRetrofitReviews(Context context) {
        con = context;
    }

    public static callRetrofitReviews getInstance(Context conn) {
        if (!callCreatedReviews)
            RetrofitReviews = new callRetrofitReviews(conn);
        callCreatedReviews = true;
        return RetrofitReviews;
    }

    public void retrofitCall( final NetworkResponse<ArrayList< ebookfrenzy.com.mapdemo.List>> reviewsResponse) {
        RetroClient retro = RetroClient.getInstanceRetrofit(con);
        GerritAPI api = retro.getApiService();
        Call<Example> call = api.getMyJSON();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

               // Log.e(TAG, String.valueOf(response.isSuccessful()));
               // reviews =  response.body().getList();
               //Log.e(TAG,"ssssssssssssssssssss");
              //  Log.e(TAG, reviews.get(0).getName());
                if (response.isSuccessful()) {
                  reviews = (ArrayList<List>) response.body().getList();
                   // Log.e(TAG,"ssssssssssssssssssss");

                    reviewsResponse.onSucess( reviews);
                    //Log.e(TAG,"ss");

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e("ERROR Status:", t.toString() + "");


                Log.e(TAG,"ssssssssssssssssssss");
                reviewsResponse.onFailure();
            }

        });
    }
}
