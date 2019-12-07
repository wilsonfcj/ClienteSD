package br.edu.ifsc.alunos.clientesd.ui.estacionamento;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import br.edu.ifsc.alunos.clientesd.ui.estacionamento.adapter.AdapterVagas;
import br.edu.ifsc.alunos.clientesd.utilidades.BaseActivty;

public class VagasActivity extends BaseActivty {

    private RecyclerView mRecyclerView;
    private AdapterVagas mAdapterVagas;
    private List<Vaga> mVagas = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vagas);
    }

    @Override
    public void mapComponents() {
        super.mapComponents();
        mRecyclerView = findViewById(R.id.recycler_view);
        setTitleToolbar("Vagas Disponíveis");
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mAdapterVagas = new AdapterVagas(VagasActivity.this, mVagas);
        mRecyclerView.setAdapter(mAdapterVagas);

        if(getIntent().getExtras().containsKey("vagas")) {
            ArrayList<Vaga> teste = (ArrayList<Vaga>) getIntent().getExtras().getParcelableArrayList("vagas");
        }
    }

    @Override
    public void mapActionComponents() {
        super.mapActionComponents();
    }
}
