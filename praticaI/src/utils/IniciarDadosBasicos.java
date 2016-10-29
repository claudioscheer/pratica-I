package utils;

import dao.AtivoImobilizadoDAO;
import dao.CategoriaDAO;
import dao.FlxcxOperacoesDAO;
import dao.FlxcxTributacaoDAO;
import dao.HistoricoDepreciacaoDAO;
import dao.ImpostoDAO;
import dao.MarcaDAO;
import dao.ProdutoDAO;
import enumeraveis.TipoConta;
import java.util.Calendar;
import java.util.Date;
import model.CarCapContas;
import model.FlxcxOperacoes;
import model.FlxcxTributacao;
import modelAntigo.AtivoImobilizado;
import modelAntigo.Categoria;
import modelAntigo.EstadoBem;
import modelAntigo.HistoricoDepreciacao;
import modelAntigo.Imposto;
import modelAntigo.Marca;
import modelAntigo.Produto;
import modelAntigo.UtilizacaoBem;

public class IniciarDadosBasicos {

    public static void main(String[] args) {

        FluxoDeCaixa();
//        Patrimonio();

        System.exit(0);
    }

    public static void Patrimonio() {

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        for (int i = 1; i <= 3; i++) {
            Categoria categoria = new Categoria();
            categoria.setCodigo(i);
            categoria.setDescricao("categoria " + i);
            categoriaDAO.insert(categoria);
        }

        MarcaDAO marcaDAO = new MarcaDAO();
        for (int i = 1; i <= 3; i++) {
            Marca marca = new Marca();
            marca.setCodigo(i);
            marca.setDescricao("marca " + i);
            marcaDAO.insert(marca);
        }

        AtivoImobilizadoDAO ativoImobilizadoDAO = new AtivoImobilizadoDAO();
        Marca marca = new MarcaDAO().get(1);
        Categoria categoria = new CategoriaDAO().get(1);
        for (int i = 1; i <= 4; i++) {
            AtivoImobilizado ativoImobilizado = new AtivoImobilizado();
            ativoImobilizado.setMarca(marca);
            ativoImobilizado.setCategoria(categoria);
            ativoImobilizado.setDescricao("ativo imobilizado " + i);
            ativoImobilizado.setEstadoBem(EstadoBem.usado);
            ativoImobilizado.setTaxaValorResidual(10);
            ativoImobilizado.setUtilizacao(UtilizacaoBem.diaria);
            ativoImobilizado.setValorAtual(10);
            ativoImobilizado.setValorOriginal(10);
            ativoImobilizado.setValorResidual(10);

            ativoImobilizadoDAO.insert(ativoImobilizado);
        }

        HistoricoDepreciacaoDAO historicoDepreciacaoDAO = new HistoricoDepreciacaoDAO();
        Calendar data = Calendar.getInstance();
        AtivoImobilizado ativoImobilizado = new AtivoImobilizadoDAO().get(1);
        for (int i = 1; i <= 4; i++) {
            HistoricoDepreciacao historicoDepreciacao = new HistoricoDepreciacao();
            historicoDepreciacao.setAno(data.get(Calendar.YEAR));
            historicoDepreciacao.setMes(data.get(Calendar.MONTH));
            historicoDepreciacao.setDia(data.getTime());
            historicoDepreciacao.setAtivoImobilizado(ativoImobilizado);
            historicoDepreciacao.setValor(300.87);
            historicoDepreciacaoDAO.insert(historicoDepreciacao);
        }

        ProdutoDAO produtoDAO = new ProdutoDAO();
        for (int i = 1; i <= 4; i++) {
            Produto produto = new Produto();
            produto.setCodigo(i);
            produto.setNome("produto " + i);
            produtoDAO.insert(produto);
        }

        ImpostoDAO impostoDAO = new ImpostoDAO();
        for (int i = 1; i <= 4; i++) {
            Imposto imposto = new Imposto();
            imposto.setImposto(i);
            imposto.setNome("imposto " + i);
            imposto.setAliquota(459345);
            impostoDAO.insert(imposto);
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
