package br.edu.ifsc.alunos.clientesd.resources.estacionamento;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.DashboardParking;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EstacionamentoAPI {

    @GET("/parking/slots")
    Call<ArrayList<Vaga>> consultarVagas();

    @GET("/parking/occupy")
    Call<Integer> vagasOcupadas();

    @POST("/parking/freeSlot")
    Call<Boolean> verificarVaga(@Query("dia") Integer id);

    @GET("/parking/dashboard")
    Call<DashboardParking> consultarInfoDashboard();

}
