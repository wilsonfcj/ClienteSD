package br.edu.ifsc.alunos.clientesd.resources.projetos;

import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.projeto.DashboardProjeto;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.RequestSituacao;
import br.edu.ifsc.alunos.clientesd.modelos.rest.ResponseBase;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProjetosAPI {

    @GET("/api/projetos")
    Call<ResponseBase<List<Projeto>>> consultarProjetos();

    @GET("/api/dashBoard")
    Call<ResponseBase<DashboardProjeto>> consultarInfoDashboard();

    @POST("/api/projetosSituacao")
    Call<ResponseBase<List<Projeto>>> consultarProjetos(@Body RequestSituacao aSituacao);
}
