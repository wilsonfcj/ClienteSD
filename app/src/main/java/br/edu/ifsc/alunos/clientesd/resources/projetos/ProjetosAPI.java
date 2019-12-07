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


//    class AsynTaskConsultarProjetos extends AsyncTask< String, String , String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_PROJETOS);
//            ProjetosAPI lApi = lRetrofit.create(ProjetosAPI.class);
//
//            try {
//                Call<ResponseBase<List<Projeto>>> lObjectCall = lApi.consultarProjetos();
//                Response<ResponseBase<List<Projeto>>> lExecute = lObjectCall.execute();
//                if (lExecute.body().isSucesso()) {
//                    Toast.makeText(MainActivity.this, "Carregou", Toast.LENGTH_LONG).show();
//                }
//            } catch (Exception aE) {
//                aE.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//    }
//
//
//    class AsynTaskConsultarProjetosFiltro extends AsyncTask<RequestSituacao, String , List<Projeto>> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected List<Projeto> doInBackground(RequestSituacao... strings) {
//            RequestSituacao lRequestSituacao = strings[0];
//            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_PROJETOS);
//            ProjetosAPI lApi = lRetrofit.create(ProjetosAPI.class);
//
//            try {
//                Call<ResponseBase<List<Projeto>>> lObjectCall = lApi.consultarProjetos(lRequestSituacao);
//                Response<ResponseBase<List<Projeto>>> lExecute = lObjectCall.execute();
//                if (lExecute.body().isSucesso()) {
//                    return lExecute.body().getData();
//                }
//            } catch (Exception aE) {
//                aE.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<Projeto> projetos) {
//            super.onPostExecute(projetos);
//            if(projetos != null) {
//                Toast.makeText(MainActivity.this, "Nome Cooordenador: " + projetos.get(0).getCoordenador(), Toast.LENGTH_LONG).show();
//            }
//        }
//    }


}
