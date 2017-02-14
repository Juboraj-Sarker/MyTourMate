package juborajsarker.mytourmate.nearby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import juborajsarker.mytourmate.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NearbyActivity extends AppCompatActivity {
    ListView placesLV;
    NearbyPlacesApi nearbyPlacesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);

        placesLV = (ListView) findViewById(R.id.placesLV);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nearbyPlacesApi=retrofit.create(NearbyPlacesApi.class);
        getAllData();
    }


    private void getAllData() {



        String dynamicUrl="json?location=23.8103,90.4125&radius=1000&types=food|cafe=AIzaSyDcVH6xq2HwTvO1yB6-JIqp5UePP_ZiiO4";
        Call<NearByPlaces> nearByPlacesCall=nearbyPlacesApi.getAllNearByPlaces(dynamicUrl);
        nearByPlacesCall.enqueue(new Callback<NearByPlaces>() {
            @Override
            public void onResponse(Call<NearByPlaces> call, Response<NearByPlaces> response) {
                NearByPlaces nearByPlaces=response.body();
                List<Result> results=nearByPlaces.getResults();
                ArrayList<String>nameList=new ArrayList<String>();
                for(Result result:results){
                    nameList.add(result.getName());
                }
                ArrayAdapter<String>nameAdapter=new ArrayAdapter<String>(NearbyActivity.this,android.R.layout.simple_list_item_1,nameList);
                placesLV.setAdapter(nameAdapter);
            }

            @Override
            public void onFailure(Call<NearByPlaces> call, Throwable t) {

            }
        });
    }


    public void nearByCafe(View view) {

        String dynamicUrl="json?location=23.8103,90.4125&radius=1000&types=food|cafe&key=AIzaSyDcVH6xq2HwTvO1yB6-JIqp5UePP_ZiiO4";
            Call<NearByPlaces> nearByPlacesCall=nearbyPlacesApi.getAllNearByPlaces(dynamicUrl);
            nearByPlacesCall.enqueue(new Callback<NearByPlaces>() {
                @Override
                public void onResponse(Call<NearByPlaces> call, Response<NearByPlaces> response) {
                    NearByPlaces nearByPlaces=response.body();
                    List<Result> results=nearByPlaces.getResults();
                    ArrayList<String>nameList=new ArrayList<String>();
                    for(Result result:results){
                        nameList.add(result.getName());
                    }
                    ArrayAdapter<String>nameAdapter=new ArrayAdapter<String>(NearbyActivity.this,android.R.layout.simple_list_item_1,nameList);
                    placesLV.setAdapter(nameAdapter);
                }

                @Override
                public void onFailure(Call<NearByPlaces> call, Throwable t) {

                }
            });

    }

    public void nearBy_ATM(View view) {

        String dynamicUrl="json?location=23.8103,90.4125&radius=1000&types=atm&key=AIzaSyDcVH6xq2HwTvO1yB6-JIqp5UePP_ZiiO4";
        Call<NearByPlaces> nearByPlacesCall=nearbyPlacesApi.getAllNearByPlaces(dynamicUrl);
        nearByPlacesCall.enqueue(new Callback<NearByPlaces>() {
            @Override
            public void onResponse(Call<NearByPlaces> call, Response<NearByPlaces> response) {
                NearByPlaces nearByPlaces=response.body();
                List<Result> results=nearByPlaces.getResults();
                ArrayList<String>nameList=new ArrayList<String>();
                for(Result result:results){
                    nameList.add(result.getName());
                }
                ArrayAdapter<String>nameAdapter=new ArrayAdapter<String>(NearbyActivity.this,android.R.layout.simple_list_item_1,nameList);
                placesLV.setAdapter(nameAdapter);
            }

            @Override
            public void onFailure(Call<NearByPlaces> call, Throwable t) {

            }
        });


    }

    public void nearByHospital(View view) {


        String dynamicUrl="json?location=23.8103,90.4125&radius=1000&types=hospital&key=AIzaSyDcVH6xq2HwTvO1yB6-JIqp5UePP_ZiiO4";
        Call<NearByPlaces> nearByPlacesCall=nearbyPlacesApi.getAllNearByPlaces(dynamicUrl);
        nearByPlacesCall.enqueue(new Callback<NearByPlaces>() {
            @Override
            public void onResponse(Call<NearByPlaces> call, Response<NearByPlaces> response) {
                NearByPlaces nearByPlaces=response.body();
                List<Result> results=nearByPlaces.getResults();
                ArrayList<String>nameList=new ArrayList<String>();
                for(Result result:results){
                    nameList.add(result.getName());
                }
                ArrayAdapter<String>nameAdapter=new ArrayAdapter<String>(NearbyActivity.this,android.R.layout.simple_list_item_1,nameList);
                placesLV.setAdapter(nameAdapter);
            }

            @Override
            public void onFailure(Call<NearByPlaces> call, Throwable t) {

            }
        });


    }
}
