package br.edu.ifsc.alunos.clientesd.props;

public @interface WebServiceProps {
    @interface URL {
        String API_PROJETOS = "https://projetos-pesquisa-api.herokuapp.com";
        String API_CANTINA = "http://cardapio-ifsc.herokuapp.com";
        String API_ESTACIONAMENTO = "http://ifsc-parking-ws.herokuapp.com";
    }
}
