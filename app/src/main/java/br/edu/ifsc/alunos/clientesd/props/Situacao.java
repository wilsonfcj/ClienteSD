package br.edu.ifsc.alunos.clientesd.props;

import br.edu.ifsc.alunos.clientesd.R;

public enum Situacao {
	
	DEFAULT(-1, "Situação não cadastrada", R.color.divider_color),
	CADSTRO_EM_ANDAMENTO(0,"Cadastro em andamento", R.color.md_blue_grey_100),
	SUBMETIDO(1,"Submetido", R.color.md_amber_A200),
	CADASTRADO(2,"Cadastrado", R.color.md_teal_A400),
	DISTRIBUIDO_PARA_AVALIACAO_AUTOMATICAMENTE(3,"Distribuído para avaliação (Automaticamente)", R.color.md_cyan_A200),
	AVALIAÇAO_INSUFICIENTE(4,"Avaliação insuficiente", R.color.md_cyan_700),
	DISTRIBUIDO_PARA_AVALIACAO_MANUALMENTE(5,"Distribuído para avaliação (Manualmente)", R.color.md_amber_A700),
	APROVADO(6,"Aprovado", R.color.md_amber_500),
	EM_EXECUCAO(7,"Em execução", R.color.md_green_500),
	FINALIZADO_RENOVADO(8,"Finalizado (Renovado)", R.color.md_indigo_400),
	FINALIZADO(9,"Finalizado", R.color.md_indigo_100),
	REPROVADO(10,"Reprovado", R.color.md_red_A700),
	DESATIVADO(11,"Desativado", R.color.md_brown_100),
	EXCLUIDO(12,"Excluído", R.color.md_red_300),
	FINALIZADO_COM_PENDENCIAS(13,"Finalizado com Pendências", R.color.md_indigo_200);

	Situacao(int aCodigo, String aSituacao, int aColor) {
		this.codigo = aCodigo;
		this.situacao = aSituacao;
        this.color = aColor;
	}
	
	public int codigo;
	public String situacao;
    public int color;
	
	public static Situacao getSituacao(int aCod) {
		for (Situacao lSituacao :  Situacao.values()) {
			if(lSituacao.codigo == aCod) {
				return lSituacao;
			}
		}
		return DEFAULT;
	}
	
	public static Situacao getSituacao(String aSituacao) {
		for (Situacao lSituacao :  Situacao.values()) {
			if(lSituacao.situacao.equalsIgnoreCase(aSituacao)) {
				return lSituacao;
			}
		}
		return DEFAULT;
	}
}
