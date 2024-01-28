package adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.PropertyReader;

public class MainAdapter {

    Gson gson;
    String token;
    String baseApiUrl;

    public MainAdapter() {
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        token = System.getProperty("token", PropertyReader.getProperty("qaseio.token"));
        baseApiUrl = PropertyReader.getProperty("qaseio.base.apiUrl");
    }

}
