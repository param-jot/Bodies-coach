package digimantra.com.bodiestv.utilities;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by shivam on 20/8/16.
 */
public class GeoLocationUtils {


    public static Location getGeoLocation(LatLng latLng, Context context){
        Geocoder geocoder;
        List<Address> addresses = new ArrayList<>();
        geocoder = new Geocoder(context, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        if(addresses!=null && addresses.size()>0){
            Address address = addresses.get(0);
            return new Location(address.getCountryName(),address.getAdminArea(),address.getLocality(),address.getAddressLine(0));

        }
        else{
            return null;
        }

    }





    public static class Location{
        private final String addressLine;
        private final String country;
        public String state;
        public String city;

        public Location(String countryName,String state, String city,String addressLine) {
            this.country = countryName;
            this.state = state;
            this.city = city;
            this.addressLine =addressLine;
        }

        public String getAddressLine() {
            return addressLine;
        }

        public String getState() {
            return state;
        }

        public String getCity() {
            return city;
        }
    }
}
