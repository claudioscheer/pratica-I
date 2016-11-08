/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.FlxcxEspecificacoesDAO;
import dao.FlxcxOperacoesDAO;
import java.util.List;
import model.FlxcxEspecificacoes;
import model.FlxcxOperacoes;

/**
 *
 * @author Diego
 */
public class InicializacaoDadosFluxoCaixa {

    private final FlxcxEspecificacoesDAO especificacaoDAO = new FlxcxEspecificacoesDAO();
    private final FlxcxOperacoesDAO operacoesDAO = new FlxcxOperacoesDAO();
    private FlxcxEspecificacoes esp;

    public void execute() {
        new Thread(esp1).start();
        new Thread(esp2).start();
        new Thread(esp3).start();
        new Thread(esp4).start();
    }

    private Runnable esp1 = new Runnable() {

        public void run() {
            esp = InserirEspecificacao(1, "RECEITA BRUTA DE VENDAS E SERVIÇOS");
            InserirOperacao("Vendas de mercadorias e produtos", esp);
            InserirOperacao("Vendas de serviços", esp);

            esp = InserirEspecificacao(2, "DEDUÇÕES, ABATIMENTOS E IMPOSTOS (D,A,I)");
            InserirOperacao("( - ) Vendas anuladas", esp);
            InserirOperacao("( - ) Descontos incondicionais concedidos", esp);
            InserirOperacao("( - ) Abatimentos concedidos sobre vendas", esp);
            InserirOperacao("( - ) ICMS sobre vendas", esp);
            InserirOperacao("( - ) PIS sobre a receita bruta", esp);
            InserirOperacao("( - ) Cofins sobre a receita bruta", esp);
            InserirOperacao("( - ) ISS sobre o faturamento (prestadora de serviços)", esp);
            InserirOperacao("( - ) Ajuste de clientes ao valor presente", esp);
        }
    };

    private Runnable esp2 = new Runnable() {

        public void run() {
            esp = InserirEspecificacao(3, "CUSTOS OPERACIONAIS");
            InserirOperacao("Custo da mercadoria vendida (CMV)", esp);
            InserirOperacao("Custo dos serviços prestados (CSP)", esp);
            InserirOperacao("Custo do Produto Vendido (CPV)", esp);

            esp = InserirEspecificacao(4, "OUTRAS RECEITAS OPERACIONAIS (ORO)");
            InserirOperacao("Dividendos de investimentos avaliados a custo", esp);
            InserirOperacao("Receitas de equivalência patrimonial", esp);
            InserirOperacao("Receitas financeiras", esp);
            InserirOperacao("Receitas de doações recebidas", esp);
            InserirOperacao("Receitas de aluguéis (para a ESAF, Aluguéis Ativos)", esp);
            InserirOperacao("Receitas de juros (para a ESAF, Juros Ativos)", esp);
        }
    };

    private Runnable esp3 = new Runnable() {

        public void run() {

            esp = InserirEspecificacao(5, "DESPESAS OPERACIONAIS (DO)");
            InserirOperacao("Salários", esp);
            InserirOperacao("Gratificações", esp);
            InserirOperacao("INSS", esp);
            InserirOperacao("FGTS", esp);
            InserirOperacao("Despesas de aluguéis", esp);
            InserirOperacao("Energia elétrica", esp);
            InserirOperacao("Água e esgoto", esp);
            InserirOperacao("Prêmios de seguros", esp);
            InserirOperacao("Honorários de diretoria", esp);
            InserirOperacao("Viagens e representações", esp);
            InserirOperacao("Material de limpeza e de expediente consumido", esp);
            InserirOperacao("PIS sobre outras receitas", esp);
            InserirOperacao("PIS sobre a folha de pagamento", esp);
            InserirOperacao("Cofins sobre outras receitas", esp);
            InserirOperacao("Despesas com provisão para perdas em estoques", esp);
            InserirOperacao("Despesas com provisão para riscos e contingências", esp);
            InserirOperacao("Despesas com provisão para créditos de liquidação duvidosa", esp);
            InserirOperacao("Juros pagos/incorridos (passivos)", esp);
            InserirOperacao("Descontos financeiros concedidos", esp);
            InserirOperacao("Despesas de equivalência patrimonial", esp);

        }
    };

    private Runnable esp4 = new Runnable() {

        public void run() {

            esp = InserirEspecificacao(6, "OUTRAS RECEITAS (OU RECEITAS DA ATIVIDADE DESCONTINUADA)");
            InserirOperacao("Ganhos na alienação de ativos imobilizados", esp);
            InserirOperacao("Receitas na reversão de provisão para perdas em investimentos", esp);

            esp = InserirEspecificacao(7, "OUTRAS DESPESAS (OU DESPESAS DA ATIVIDADE DESCONTINUADA)");
            InserirOperacao("Perdas na alienação de investimentos", esp);
            InserirOperacao("Despesas com provisão para perdas permanentes em investimentos", esp);
            InserirOperacao("Perdas na alienação ou baixa de investimentos permanentes", esp);
            InserirOperacao("Perdas na alienação ou baixa de ativos imobilizados", esp);

            esp = InserirEspecificacao(8, "PARTICIPAÇÕES DE TERCEIROS NOS LUCROS (PTLS)");
            InserirOperacao("Debenturistas", esp);
            InserirOperacao("Empregados", esp);
            InserirOperacao("Administradores", esp);
            InserirOperacao("Partes beneficiárias", esp);
            InserirOperacao("Instituições ou fundos de assistência ou previdência de empregados", esp);

        }
    };

    public FlxcxEspecificacoes InserirEspecificacao(int espCodigo, String espDescricao) {

        FlxcxEspecificacoes especificacao = new FlxcxEspecificacoes();

        especificacao.setEspCodigo(espCodigo);
        especificacao.setEspDescricao(espDescricao);

        this.especificacaoDAO.Inserir(especificacao);

        return especificacao;
    }

    public void InserirOperacao(String descricao, FlxcxEspecificacoes especificacao) {

        FlxcxOperacoes operacoes = new FlxcxOperacoes();
        operacoes.setOpDescricao(descricao);
        operacoes.setFlxcxEspecificacoes(especificacao);

        this.operacoesDAO.Inserir(operacoes);

    }

}
