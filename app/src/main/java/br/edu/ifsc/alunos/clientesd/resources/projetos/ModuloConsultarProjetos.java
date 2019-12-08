package br.edu.ifsc.alunos.clientesd.resources.projetos;

import android.os.AsyncTask;

import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.projeto.DashboardProjeto;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.RequestSituacao;
import br.edu.ifsc.alunos.clientesd.modelos.rest.ResponseBase;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.generics.ListenerService;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModuloConsultarProjetos {

    private ListenerDashboard listenerDashboard;

    public ModuloConsultarProjetos(ListenerDashboard listenerDashboard) {
        this.listenerDashboard = listenerDashboard;
    }

    public void consultar(RequestSituacao requestSituacao) {
       new  AsynTaskConsultarProjetos().execute(requestSituacao);
    }

    private class AsynTaskConsultarProjetos extends AsyncTask<RequestSituacao, String , List<Projeto>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Projeto> doInBackground(RequestSituacao... requests) {
            RequestSituacao requestSituacao = requests[0];
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_PROJETOS);
            ProjetosAPI lApi = lRetrofit.create(ProjetosAPI.class);

            try {
                Call<ResponseBase<List<Projeto>>> lObjectCall = lApi.consultarProjetos(requestSituacao);
                Response<ResponseBase<List<Projeto>>> lExecute = lObjectCall.execute();
                if (lExecute.body().isSucesso()) {
                    return lExecute.body().getData();
                }
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Projeto> projetos) {
            super.onPostExecute(projetos);
            if(projetos != null) {
                listenerDashboard.success(projetos);
            } else {
                listenerDashboard.error("Não foi possível carregar as informações");
            }
        }
    }

    public interface ListenerDashboard extends ListenerService<List<Projeto>> {
        @Override
        void success(List<Projeto> objeto);

        @Override
        void error(String msmErro);
    }
}
