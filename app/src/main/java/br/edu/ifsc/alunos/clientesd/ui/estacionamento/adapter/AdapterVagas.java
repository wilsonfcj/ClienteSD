package br.edu.ifsc.alunos.clientesd.ui.estacionamento.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsc.alunos.clientesd.R;
import br.edu.ifsc.alunos.clientesd.modelos.estacionamento.Vaga;
import br.edu.ifsc.alunos.clientesd.modelos.projeto.Projeto;
import br.edu.ifsc.alunos.clientesd.props.Situacao;
import br.edu.ifsc.alunos.clientesd.utilidades.DateUtil;

public class AdapterVagas extends RecyclerView.Adapter<AdapterVagas.ViewHolderProjetos> {

    private List<Vaga> mListProjetos;
    private Context mContext;
    private int lastPosition = -1;

    @interface SituacaoVaga {
        int OCUPADA = 0;
        int LIVRE = 1;
    }

    public AdapterVagas(Context aContext, List<Vaga> aListProjetos) {
        this.mContext = aContext;
        this.mListProjetos = aListProjetos;
    }

    @Override
    public int getItemViewType(int position) {
        return mListProjetos.get(position).getSituacao() ? SituacaoVaga.LIVRE : SituacaoVaga.OCUPADA;
    }

    @NonNull
    @Override
    public ViewHolderProjetos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vaga, parent,
                false);
        if(viewType == SituacaoVaga.LIVRE) {
            lView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_vaga_livre, parent,      false);
        }
        ViewHolderProjetos lViewHolderProjetos = new ViewHolderProjetos(lView);
        return lViewHolderProjetos;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProjetos holder, int position) {
        Vaga lVaga = mListProjetos.get(position);
        holder.tvVaga.setText(String.valueOf(lVaga.getNumero() + 1));
        if(lVaga.getSituacao()) {
            showInfoStatus(holder, "Livre", R.color.md_green_400, R.drawable.ic_free_slot, R.color.colorAccent);
        } else {
            showInfoStatus(holder, "Ocupada", R.color.md_red_400, R.drawable.ic_busy_slot, R.color.md_white_1000);
        }
    }

    private void showInfoStatus(ViewHolderProjetos holder, String text, int color, int img, int colorText) {
        holder.tvStatus.setText(text);
        holder.imageView.setImageResource(img);
        holder.linearLayoutStatus.setBackgroundColor(mContext.getResources().getColor(color));
        holder.tvVaga.setTextColor(mContext.getResources().getColor(colorText));
    }

    @Override
    public int getItemCount() {
        return mListProjetos.size();
    }

    public void addItens(List<Vaga> aList) {
        mListProjetos.clear();
        mListProjetos.addAll(aList);
        notifyDataSetChanged();
    }

    public static class ViewHolderProjetos extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public LinearLayout linearLayoutStatus;
        public TextView tvStatus;
        public TextView tvVaga;

        public ViewHolderProjetos(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvVaga = itemView.findViewById(R.id.tv_numero);
            linearLayoutStatus = itemView.findViewById(R.id.container_status);
        }
    }
}
