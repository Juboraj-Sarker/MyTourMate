package juborajsarker.mytourmate.nearby;

/**
 * Created by jubor on 2/14/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by BITM TRAINER - 403 on 2/9/2017.
 */

public interface NearbyPlacesApi {
    @GET()
    Call<NearByPlaces>getAllNearByPlaces(@Url String changedUrl);
}
