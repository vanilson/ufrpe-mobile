package helloworld.teste.com.artigoandroidassincrono;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface CarrosService {

    @GET("/carros")
    void listCarros(Callback<CarrosContainer> callback);

    //List<Carro> listCarros(@Path("user") String user);
}
