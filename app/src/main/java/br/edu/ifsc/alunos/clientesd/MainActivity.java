package br.edu.ifsc.alunos.clientesd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.modelos.cardapio.Cardapio;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.DashboardParking;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.DashboardProjeto;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.modelos.rest.RequestSituacao;
import br.edu.ifsc.alunos.clientesd.modelos.rest.ResponseBase;
import br.edu.ifsc.alunos.clientesd.props.WebServiceProps;
import br.edu.ifsc.alunos.clientesd.resources.cantina.CantinaAPI;
import br.edu.ifsc.alunos.clientesd.resources.estacionamento.EstacionamentoAPI;
import br.edu.ifsc.alunos.clientesd.resources.generics.RetrofitImpl;
import br.edu.ifsc.alunos.clientesd.resources.projetos.ProjetosAPI;
import br.edu.ifsc.alunos.clientesd.ui.projetos.ProjetosActivity;
import br.edu.ifsc.alunos.clientesd.utilidades.BaseActivty;
import br.edu.ifsc.alunos.clientesd.utilidades.ConnectionUtil;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivty {

//    Projetos
    private RelativeLayout relativeLayoutProjetoI;
    private RelativeLayout relativeLayoutProjetoII;
    private TextView textViewProjetoI;
    private SwipeRefreshLayout swipeRefreshLayout;

//    Estacionamento
    private RelativeLayout relativeLayoutEstacionamentoI;
    private RelativeLayout relativeLayoutEstacionamentoII;

    private TextView textViewEstacionamentoI;
    private TextView textViewEstacionamentoII;

    private int contResponse = 0;

    private ArrayList<Vaga> vagas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleToolbar("Dashboard");
        swipeRefreshLayout.setRefreshing(true);
        consultWS();
    }

    @Override
    public void mapComponents() {
        super.mapComponents();
        mapComponentsProjeto();
        mapComponentsEstacionamento();
    }

    @Override
    public void mapActionComponents() {
        super.mapActionComponents();

        relativeLayoutProjetoI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProjetosActivity.class));
            }
        });

        relativeLayoutProjetoII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Em breve!", Snackbar.LENGTH_LONG).show();
            }
        });

        relativeLayoutEstacionamentoI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lIntent = new Intent(MainActivity.this, ProjetosActivity.class);
                Bundle lBundle = new Bundle();
                lBundle.putParcelableArrayList("vagas", (ArrayList<? extends Parcelable>) vagas);
                lIntent.putExtras(lBundle);
                startActivity(lIntent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                consultWS();
            }
        });


    }

    private void consultWS() {
        if(ConnectionUtil.isNetworkAvailable(MainActivity.this)) {
            new AsynTaskDashBoard().execute();
            new AsynTaskDashBoardEntacionamento().execute();
        }
    }

    public void mapComponentsProjeto() {
        relativeLayoutProjetoI = findViewById(R.id.dashboard_projetoI);
        relativeLayoutProjetoII = findViewById(R.id.dashboard_projetoII);

        textViewProjetoI = findViewById(R.id.tv_projetoI);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
    }

    public void mapComponentsEstacionamento() {
        relativeLayoutEstacionamentoI = findViewById(R.id.dashboard_estacionamentoI);
        relativeLayoutEstacionamentoII = findViewById(R.id.dashboard_estacionamentoII);

        textViewEstacionamentoI = findViewById(R.id.tv_estacionamentoI);
        textViewEstacionamentoII = findViewById(R.id.tv_estacionamentoII);
    }

    class AsynTaskDashBoard extends AsyncTask<RequestSituacao, String , DashboardProjeto> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected DashboardProjeto doInBackground(RequestSituacao... strings) {
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
            addResponse();
            if(aDashboardInfos != null) {
                displayInfoProjeto(aDashboardInfos);
            }
            hideProgess();
        }
    }

    private DashboardParking criarDashboarEstacionamento(ArrayList<Vaga> totalVagas) {
        vagas = totalVagas;
        Integer livre = 0;
        for(Vaga lVaga : totalVagas) {
            if(!lVaga.situacao)
                livre++;
        }
        DashboardParking lDashboardParking = new DashboardParking(totalVagas.size(), livre);
        return lDashboardParking;
    }

    class AsynTaskDashBoardEntacionamento extends AsyncTask<RequestSituacao, String , DashboardParking> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected DashboardParking doInBackground(RequestSituacao... strings) {
            Retrofit lRetrofit = new RetrofitImpl().buildRetrofit(WebServiceProps.URL.API_ESTACIONAMENTO);
            EstacionamentoAPI lApi = lRetrofit.create(EstacionamentoAPI.class);
            try {
                Call<ArrayList<Vaga>> lObjectCall = lApi.consultarVagas();
                Response<ArrayList<Vaga>> lExecute = lObjectCall.execute();
                return criarDashboarEstacionamento(lExecute.body());
            } catch (Exception aE) {
                aE.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(DashboardParking aDashboardInfos) {
            super.onPostExecute(aDashboardInfos);
            addResponse();
            if(aDashboardInfos != null) {
                displayInfoEstacionamento(aDashboardInfos);
            }
            hideProgess();
        }
    }

    private void hideProgess() {
        if(contResponse == 2) {
            swipeRefreshLayout.setRefreshing(false);
            contResponse = 0;
        }
    }

    private void addResponse() {
        contResponse++;
    }

    public void displayInfoProjeto(DashboardProjeto aDashboardInfos) {
        textViewProjetoI.setText("Total: " + aDashboardInfos.getTotalProjetos());
    }

    public void displayInfoEstacionamento(DashboardParking aDashboardParking) {
        textViewEstacionamentoI.setText("Total: " + aDashboardParking.getTotalParkingSpaces());
        textViewEstacionamentoII.setText("Total: " + aDashboardParking.getFreeParkingSpace());
    }

}
