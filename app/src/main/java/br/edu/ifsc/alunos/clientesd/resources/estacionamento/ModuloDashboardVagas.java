package br.edu.ifsc.alunos.clientesd.resources.estacionamento;

import android.os.AsyncTask;

import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.DashboardParking;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.DashboardProjeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.RequestSituacao;
import br.edu.ifsc.alunos.clientesd.modelos.rest.ResponseBase;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.generics.ListenerService;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import br.edu.ifsc.alunos.clientesd.resources.projetos.ProjetosAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModuloDashboardVagas {

    private ListenerDashboard listenerDashboard;

    public ModuloDashboardVagas(ListenerDashboard listenerDashboard) {
        this.listenerDashboard = listenerDashboard;
    }

    public void consultar() {
       new  AsynTaskDashboardEntacionamento().execute();
    }

    private class AsynTaskDashboardEntacionamento extends AsyncTask<Void, String , DashboardParking> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected DashboardParking doInBackground(Void... strings) {
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_ESTACIONAMENTO);
            EstacionamentoAPI lApi = lRetrofit.create(EstacionamentoAPI.class);
            try {
                Call<DashboardParking> lObjectCall = lApi.consultarInfoDashboard();
                Response<DashboardParking> lExecute = lObjectCall.execute();
                return lExecute.body();
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(DashboardParking aDashboardInfos) {
            super.onPostExecute(aDashboardInfos);
            if(aDashboardInfos != null) {
                listenerDashboard.success(aDashboardInfos);
            } else {
                listenerDashboard.error("Não foi possível carregar as informações");
            }
        }
    }


    public interface ListenerDashboard extends ListenerService<DashboardParking> {
        @Override
        void success(DashboardParking objeto);

        @Override
        void error(String msmErro);
    }
}
