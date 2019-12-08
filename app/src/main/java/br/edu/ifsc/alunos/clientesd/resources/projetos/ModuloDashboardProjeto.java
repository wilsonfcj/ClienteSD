package br.edu.ifsc.alunos.clientesd.resources.projetos;

import android.os.AsyncTask;

import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.projeto.DashboardProjeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.ResponseBase;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.generics.ListenerService;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModuloDashboardProjeto {

    private ListenerDashboard listenerDashboard;

    public ModuloDashboardProjeto(ListenerDashboard listenerDashboard) {
        this.listenerDashboard = listenerDashboard;
    }

    public void consultar() {
       new  AsynTaskDashBoard().execute();
    }

    private class AsynTaskDashBoard extends AsyncTask<Void, String , DashboardProjeto> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected DashboardProjeto doInBackground(Void... strings) {
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_PROJETOS);
            ProjetosAPI lApi = lRetrofit.create(ProjetosAPI.class);

            try {
                Call<ResponseBase<DashboardProjeto>> lObjectCall = lApi.consultarInfoDashboard();
                Response<ResponseBase<DashboardProjeto>> lExecute = lObjectCall.execute();
                if (lExecute.body().isSucesso()) {
                    return lExecute.body().getData();
                }
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(DashboardProjeto aDashboardInfos) {
            super.onPostExecute(aDashboardInfos);
            if(aDashboardInfos != null) {
                listenerDashboard.success(aDashboardInfos);
            } else {
                listenerDashboard.error("Não foi possível carregar as informações");
            }
        }
    }

    public interface ListenerDashboard extends ListenerService<DashboardProjeto> {
        @Override
        void success(DashboardProjeto objeto);

        @Override
        void error(String msmErro);
    }
}
