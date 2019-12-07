package br.edu.ifsc.alunos.clientesd.ui.projetos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.props.Situacao;
import br.edu.ifsc.alunos.clientesd.utilidades.DateUtil;

public class AdapterProjetos extends RecyclerView.Adapter<AdapterProjetos.ViewHolderProjetos> {

    private List<Projeto> mListProjetos;
    private Context mContext;
    private int lastPosition = -1;

    public AdapterProjetos(Context aContext, List<Projeto> aListProjetos) {
        this.mContext = aContext;
        this.mListProjetos = aListProjetos;
    }

    @NonNull
    @Override
    public ViewHolderProjetos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_projeto_pesquisa, parent,
                false);
        ViewHolderProjetos lViewHolderProjetos = new ViewHolderProjetos(lView);
        return lViewHolderProjetos;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProjetos holder, int position) {
        Projeto lProjeto = mListProjetos.get(position);
        holder.tvCoordenador.setText(lProjeto.getCoordenador());
        holder.tvCodigo.setText(lProjeto.getCodigo());

        holder.tvDescricao.setText(lProjeto.getTitulo().length() > 144 ?
                getDescriptionFormated(lProjeto.getTitulo()) + "..." : lProjeto.getTitulo());

        if(lProjeto.getEmailCoordenador().contains("|")) {
            String[] emails = lProjeto.getEmailCoordenador().split(" | ");
            holder.tvEmail.setText(emails[0]);
        } else {
            holder.tvEmail.setText(lProjeto.getEmailCoordenador());
        }
        holder.tvData.setText(DateUtil.convertData(lProjeto.getDataCadastro(), "dd/MM/yyyy"));
        Situacao lSituacao = Situacao.getSituacao(lProjeto.getSituacao());
        holder.tvSituacao.setText(lSituacao.situacao);
        holder.tvSituacao.setTextColor(mContext.getResources().getColor(lSituacao.color));

        if(position >lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext,
                    R.anim.up_from_bottom);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    private String getDescriptionFormated(String oldString) {
        return oldString.substring(0, 141);
    }

    @Override
    public int getItemCount() {
        return mListProjetos.size();
    }

    public void addItens(List<Projeto> aList) {
        mListProjetos.clear();
        mListProjetos.addAll(aList);
        notifyDataSetChanged();
    }

    public static class ViewHolderProjetos extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView tvCoordenador;
        public TextView tvCodigo;
        public TextView tvDescricao;
        public TextView tvEmail;
        public TextView tvData;
        public TextView tvSituacao;

        public ViewHolderProjetos(@NonNull View itemView) {
            super(itemView);
            tvCoordenador = itemView.findViewById(R.id.tv_coordenador);
            tvCodigo = itemView.findViewById(R.id.tv_codigo);
            tvDescricao = itemView.findViewById(R.id.tv_descricao);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvData = itemView.findViewById(R.id.tv_data);
            tvSituacao = itemView.findViewById(R.id.tv_situacao);
        }
    }
}
