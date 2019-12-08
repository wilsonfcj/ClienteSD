package br.edu.ifsc.alunos.clientesd.resources.estacionamento;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.DashboardParking;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.generics.ListenerService;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModuloConsultarVagas {

    private ListenerDashboard listenerDashboard;

    public ModuloConsultarVagas(ListenerDashboard listenerDashboard) {
        this.listenerDashboard = listenerDashboard;
    }

    public void consultar() {
       new  AsynTaskVagas().execute();
    }

    private class AsynTaskVagas extends AsyncTask<Void, String , List<Vaga>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Vaga> doInBackground(Void... strings) {
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_ESTACIONAMENTO);
            EstacionamentoAPI lApi = lRetrofit.create(EstacionamentoAPI.class);
            try {
                Call<List<Vaga>> lObjectCall = lApi.consultarVagas();
                Response<List<Vaga>> lExecute = lObjectCall.execute();
                return lExecute.body();
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Vaga> aDashboardInfos) {
            super.onPostExecute(aDashboardInfos);
            if(aDashboardInfos != null) {
                listenerDashboard.success(aDashboardInfos);
            } else {
                listenerDashboard.error("Não foi possível carregar as informações");
            }
        }
    }

    public interface ListenerDashboard extends ListenerService<List<Vaga>> {
        @Override
        void success(List<Vaga>  objeto);

        @Override
        void error(String msmErro);
    }
}
