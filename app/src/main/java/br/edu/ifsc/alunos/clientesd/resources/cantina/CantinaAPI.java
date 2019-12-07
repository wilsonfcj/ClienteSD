package br.edu.ifsc.alunos.clientesd.resources.cantina;

import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.cardapio.Cardapio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CantinaAPI {

    @GET("/api/cardapios")
    Call<List<Cardapio>> consultarCardapio();

    @GET("/api/cardapio")
    Call<List<Cardapio>> consultarCardapio(@Query("dia") Integer id);


    //    class AsynTaskCardapio extends AsyncTask<Void, String , List<Cardapio>> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected List<Cardapio> doInBackground(Void... voids) {
//            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_CANTINA);
//            CantinaAPI lApi = lRetrofit.create(CantinaAPI.class);
//
//            try {
//                Call<List<Cardapio>> lObjectCall = lApi.consultarCardapio();
//                Response<List<Cardapio>> lExecute = lObjectCall.execute();
//                if (!lExecute.body().isEmpty()) {
//                    return lExecute.body();
//                }
//            } catch (Exception aE) {
//                aE.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<Cardapio> aDashboardInfos) {
//            super.onPostExecute(aDashboardInfos);
//            if(aDashboardInfos != null) {
//                Toast.makeText(MainActivity.this, "Carregou", Toast.LENGTH_LONG).show();
//            }
//        }
//    }


//    class AsynTaskCardapio extends AsyncTask<Void, String , List<Cardapio>> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected List<Cardapio> doInBackground(Void... voids) {
//            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_CANTINA);
//            CantinaAPI lApi = lRetrofit.create(CantinaAPI.class);
//
//            try {
//                Call<List<Cardapio>> lObjectCall = lApi.consultarCardapio();
//                Response<List<Cardapio>> lExecute = lObjectCall.execute();
//                if (!lExecute.body().isEmpty()) {
//                    return lExecute.body();
//                }
//            } catch (Exception aE) {
//                aE.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<Cardapio> aDashboardInfos) {
//            super.onPostExecute(aDashboardInfos);
//            if(aDashboardInfos != null) {
//                Toast.makeText(MainActivity.this, "Carregou", Toast.LENGTH_LONG).show();
//            }
//        }
//    }
}
