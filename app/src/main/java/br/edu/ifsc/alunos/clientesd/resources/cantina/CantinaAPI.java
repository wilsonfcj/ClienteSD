package br.edu.ifsc.alunos.clientesd.resources.cantina;

import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.cardapio.Cardapio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CantinaAPI {

    @GET("/api/cardapios")
    Call<List<Cardapio>> consultarCardapio();

    @GET("/api/cardapio/dia/{dia}")      // Exemplo query @GET("/api/cardapio/dia")
    Call<Cardapio> consultarCardapio(@Path("dia") Integer id);   // Exemplo query (@Query("dia") Integer id);
}
