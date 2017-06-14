package ebookfrenzy.com.mapdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TextWatcher {
    private RecyclerView recView;
    private DerpAdapter adapter;

    private AutoCompleteTextView edit;
    private static String[] items = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recView = (RecyclerView) findViewById(R.id.rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

        edit = (AutoCompleteTextView) findViewById(R.id.edit);
        edit.addTextChangedListener(this);
        callRetrofitReviews retrofitMovies = callRetrofitReviews.getInstance(getApplicationContext());

        retrofitMovies.retrofitCall(new NetworkResponse<java.util.ArrayList<ebookfrenzy.com.mapdemo.List>>() {
            public static final String TAG = "Ss";

            @Override
            public void onSucess(java.util.ArrayList<ebookfrenzy.com.mapdemo.List> result) {
                items = new String[result.size()];
                for (int i = 0; i < result.size(); i++)
                    items[i] = result.get(i).getName();

                edit.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.itemss,
                        items));
                edit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle bundle = new Bundle();
                        String tit = (String) parent.getItemAtPosition(position);;
                        bundle.putString("key",tit);
                        Intent in = new Intent(MainActivity.this,MapDemoActivity.class);
                        in.putExtras(bundle);
                        startActivity(in);


                    }
                });

                adapter = new DerpAdapter(DerpData.getListData(result), getApplication());
                recView.setAdapter(adapter);


            }

            @Override
            public void onFailure() {
                Toast.makeText(getApplication().getApplicationContext(), "Not_success", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}

