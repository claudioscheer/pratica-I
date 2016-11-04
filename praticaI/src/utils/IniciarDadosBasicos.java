package utils;

import dao.CarCapContasDAO;
import dao.CategoriaDAO;
import dao.FlxcxTributacaoDAO;
import dao.MarcaDAO;
import dao.ProdutoDAO;
import dao.TipoOperacaoDAO;
import enumeraveis.StatusConta;
import enumeraveis.TipoConta;
import java.util.Calendar;
import java.util.Date;
import model.CarCapContas;
import model.CarEstTipoOperacao;
import model.CarPessoa;
import model.EstCategoria;
import model.EstMarca;
import model.EstProduto;
import model.FlxcxTributacao;

public class IniciarDadosBasicos {

    public static void main(String[] args) {

        // FluxoDeCaixa();
//        Patrimonio();
        alimentarContas();
        System.exit(0);
    }

    public static void alimentarContas() {

        CarCapContas novo = new CarCapContas();

        TipoOperacaoDAO op = new TipoOperacaoDAO();
//        PatNotaFiscal nfe = new PatNotaFiscal(6, 200, new Date(), "3242342342342342342342342", new Date());

        CarEstTipoOperacao tpop = new CarEstTipoOperacao();

        //tpop.setCarCapContases(null);
        tpop.setTpOpId(1);
        tpop.setTpOpTipo(1);
        tpop.setTpOpNome("Teste");
        tpop.setTpOpDescricao("Teste");

        CarPessoa nova = new CarPessoa(1, "Diego");

        for (int i = 21; i <= 40; i++) {

            novo.setContaId(i);
            novo.setCapContaStatus(StatusConta.Fechada);
            novo.setCarCapParcelas(null);
            novo.setCarPessoa(nova);
            novo.setCarEstTipoOperacao(tpop);
            novo.setContaValorTotal(435.56 * i);
            novo.setContaValorPago(435.56 * i);
            novo.setContaDataEmissao(new Date());
            novo.setContaTipo(TipoConta.Saida);
//        novo.setPatNotaFiscal(nfe);

            CarCapContasDAO contaDAO = new CarCapContasDAO();

            contaDAO.insert(novo);

        }

    }

    public static void Patrimonio() {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        for (int i = 1; i <= 3; i++) {
            EstCategoria categoria = new EstCategoria();
            categoria.setCategoriaId(i);
            categoria.setCategoriaDescricao("categoria " + i);
            categoriaDAO.Inserir(categoria);
        }

        MarcaDAO marcaDAO = new MarcaDAO();
        for (int i = 1; i <= 3; i++) {
            EstMarca marca = new EstMarca();
            marca.setMarcaId(i);
            marca.setMarcaDescricao("marca " + i);
            marcaDAO.Inserir(marca);
        }

        ProdutoDAO produtoDAO = new ProdutoDAO();
        for (int i = 1; i <= 4; i++) {
            EstProduto produto = new EstProduto();
            produto.setProdutoId(i);
            produto.setProdutoDescricao("produto " + i);
            produtoDAO.insert(produto);
        }
    }

    public static void FluxoDeCaixa() {
//    
        Calendar data = Calendar.getInstance();
//        FlxcxLivroCaixaDAO livroCaixaDao = new FlxcxLivroCaixaDAO();
        FlxcxTributacaoDAO trib = new FlxcxTributacaoDAO();
//
//        FlxcxOperacoesDAO operacaoDAO = new FlxcxOperacoesDAO();
//
//        FlxcxOperacoes ope = new FlxcxOperacoes();
//        
//        CarCapContas conta = new CarCapContas(1,2.0,new Date(),2,2.0,TipoConta.Entrada);
//                      
//        ope.setOpCodigo(1);
//        ope.setOpDescricao("TesteOperacao");
//        ope.getCarCapContass().add(conta);
//        
//        operacaoDAO.Inserir(ope);

//        FlxcxOperacoes op = operacaoDAO.Buscar(1);
//                
        for (int i = 1; i <= 10; i++) {

            FlxcxTributacao tr = new FlxcxTributacao();

            // tr.setTribCodigo(i);
            tr.setTribDescricao("Tributacao: " + i);

            trib.Inserir(tr);

        }

//        for (model.FlxcxTributacao i :trib.ListarTodas()){
//        
//            System.out.println(i.getTribCodigo());
//            System.out.println(i.getTribDescricao());
//        
//        }
//        FlxcxTributacao trib = tributacaoDAO.Buscar(1);
//        System.out.println(trib.getTribDescricao());
    }

}
