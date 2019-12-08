package br.edu.ifsc.alunos.clientesd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import br.edu.ifsc.alunos.clientesd.modelos.cardapio.Cardapio;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.DashboardParking;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.DashboardProjeto;
import br.edu.ifsc.alunos.clientesd.resources.cantina.CantinaAPI;
import br.edu.ifsc.alunos.clientesd.resources.cantina.ModuloConsultarPrato;
import br.edu.ifsc.alunos.clientesd.resources.estacionamento.ModuloDashboardVagas;
import br.edu.ifsc.alunos.clientesd.resources.projetos.ModuloDashboardProjeto;
import br.edu.ifsc.alunos.clientesd.ui.estacionamento.VagasActivity;
import br.edu.ifsc.alunos.clientesd.ui.projetos.ProjetosActivity;
import br.edu.ifsc.alunos.clientesd.utilidades.BaseActivty;
import br.edu.ifsc.alunos.clientesd.utilidades.ConnectionUtil;
import br.edu.ifsc.alunos.clientesd.utilidades.DateUtil;

public class MainActivity extends BaseActivty {

//    Projetos
    private RelativeLayout relativeLayoutProjetoI;
    private RelativeLayout relativeLayoutProjetoII;
    private TextView textViewProjetoI;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ModuloDashboardProjeto moduloDashboardProjeto;

//    Estacionamento
    private RelativeLayout relativeLayoutEstacionamentoI;
    private RelativeLayout relativeLayoutEstacionamentoII;
    private ModuloDashboardVagas moduloDashboardEstacionamento;
    private TextView textViewEstacionamentoI;
    private TextView textViewEstacionamentoII;

//    Cantina
    private TextView textCantinaII;
    private TextView textCantinaI;
    ModuloConsultarPrato moduloConsultarPrato;


    private int contResponse = 0;

    private ArrayList<Vaga> vagas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleToolbar("Dashboard");
    }

    @Override
    public void mapComponents() {
        super.mapComponents();
        mapComponentsProjeto();
        mapComponentsCantina();
        mapComponentsEstacionamento();
    }

    @Override
    protected void onResume() {
        super.onResume();
        consultWS();
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
                startActivity(new Intent(MainActivity.this, VagasActivity.class));
            }
        });

        relativeLayoutEstacionamentoII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("livres", true);
                Intent intent = new Intent(MainActivity.this, VagasActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                consultWS();
            }
        });
    }

    @Override
    public void createRestListener() {
        moduloDashboardProjeto = new ModuloDashboardProjeto(new ModuloDashboardProjeto.ListenerDashboard() {
            @Override
            public void success(DashboardProjeto objeto) {
                addResponse();
                displayInfoProjeto(objeto);
                hideProgess();
            }

            @Override
            public void error(String msmErro) {
                addResponse();
                hideProgess();
            }
        });

        moduloConsultarPrato = new ModuloConsultarPrato(new ModuloConsultarPrato.ListenerCardapio() {
            @Override
            public void success(Cardapio objeto) {
                addResponse();
                displayInfoCantina(objeto);
                hideProgess();
            }

            @Override
            public void error(String msmErro) {
                addResponse();
                textCantinaI.setText("Total: " + 0);
                hideProgess();
            }
        });

        moduloDashboardEstacionamento = new ModuloDashboardVagas(new ModuloDashboardVagas.ListenerDashboard() {
            @Override
            public void success(DashboardParking objeto) {
                addResponse();
                displayInfoEstacionamento(objeto);
                hideProgess();
            }

            @Override
            public void error(String msmErro) {
                addResponse();
                hideProgess();
            }
        });
    }

    private void consultWS() {
        if(ConnectionUtil.isNetworkAvailable(MainActivity.this)) {
            swipeRefreshLayout.setRefreshing(true);
            moduloDashboardProjeto.consultar();
            moduloConsultarPrato.consultar(DateUtil.getCurrentDay());
            moduloDashboardEstacionamento.consultar();
        }
    }

    private void mapComponentsProjeto() {
        relativeLayoutProjetoI = findViewById(R.id.dashboard_projetoI);
        relativeLayoutProjetoII = findViewById(R.id.dashboard_projetoII);

        textViewProjetoI = findViewById(R.id.tv_projetoI);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
    }

    private void mapComponentsEstacionamento() {
        relativeLayoutEstacionamentoI = findViewById(R.id.dashboard_estacionamentoI);
        relativeLayoutEstacionamentoII = findViewById(R.id.dashboard_estacionamentoII);

        textViewEstacionamentoI = findViewById(R.id.tv_estacionamentoI);
        textViewEstacionamentoII = findViewById(R.id.tv_estacionamentoII);
    }

    private void mapComponentsCantina() {
        textCantinaII = findViewById(R.id.tv_cantinaII);
        textCantinaI = findViewById(R.id.tv_cantinaI);
        textCantinaII.setText(DateUtil.getDate("dd/MM/yyyy"));
    }

    private void hideProgess() {
        if(contResponse == 3) {
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

    public void displayInfoCantina(Cardapio cardapio) {
        int cont = 0;
        if(cardapio.getPrimeiroPrato() != null || !cardapio.getPrimeiroPrato().isEmpty()) {
            cont++;
        }
        if(cardapio.getSegundoPrato() != null || !cardapio.getSegundoPrato().isEmpty()) {
            cont++;
        }
        if(cardapio.getTerceiroPrato() != null || !cardapio.getTerceiroPrato().isEmpty()) {
            cont++;
        }
        if(cardapio.getQuartoPrato() != null || !cardapio.getQuartoPrato().isEmpty()) {
            cont++;
        }
        if(cardapio.getQuintoPrato() != null || !cardapio.getQuintoPrato().isEmpty()) {
            cont++;
        }
        if(cardapio.getSextoPrato() != null || !cardapio.getSextoPrato().isEmpty()) {
            cont++;
        }
        textCantinaI.setText("Total: " + cont);
    }

    public void displayInfoEstacionamento(DashboardParking aDashboardParking) {
        textViewEstacionamentoI.setText("Total: " + aDashboardParking.getTotalParkingSpaces());
        textViewEstacionamentoII.setText("Total: " + aDashboardParking.getFreeParkingSpace());
    }
}
