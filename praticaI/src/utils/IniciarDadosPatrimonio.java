package utils;

import dao.EstCategoriaDAO;
import dao.EstMarcaDAO;
import dao.EstProdutoDAO;
import dao.PatTipoBaixaDAO;
import model.EstCategoria;
import model.EstMarca;
import model.EstProduto;
import model.PatTipoBaixa;

/**
 *
 * @author Larissa Guder <lariguder10@gmail.com>
 */
public class IniciarDadosPatrimonio {

    public static void main(String[] args) {
        addCategoria();
        addMarca();
        addTipoBaixa();
    }
    
    
    public static void addCategoria() {
        EstCategoriaDAO categoriaDAO = new EstCategoriaDAO();

        EstCategoria categoria = new EstCategoria();

        categoria.setCategoriaDescricao("Móveis");
        categoriaDAO.Inserir(categoria);

        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Informática");
        categoriaDAO.Inserir(categoria);

        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Automóveis");
        categoriaDAO.Inserir(categoria);

        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Maquinário");
        categoriaDAO.Inserir(categoria);

        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Ferramentas");
        categoriaDAO.Inserir(categoria);

        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Imóveis");
        categoriaDAO.Inserir(categoria);

        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Eletronicos");
        categoriaDAO.Inserir(categoria);

    }

    public static void addMarca() {
        EstMarcaDAO marcaDAO = new EstMarcaDAO();

        EstMarca marca = new EstMarca();
        marca.setMarcaDescricao("Jaeli");
        marcaDAO.Inserir(marca);

        marca = new EstMarca();
        marca.setMarcaDescricao("Dell");
        marcaDAO.Inserir(marca);

        marca = new EstMarca();
        marca.setMarcaDescricao("John Deere");
        marcaDAO.Inserir(marca);

        marca = new EstMarca();
        marca.setMarcaDescricao("Tramontina");
        marcaDAO.Inserir(marca);

        marca = new EstMarca();
        marca.setMarcaDescricao("Samsung");
        marcaDAO.Inserir(marca);

        marca = new EstMarca();
        marca.setMarcaDescricao("Honda");
        marcaDAO.Inserir(marca);

        marca = new EstMarca();
        marca.setMarcaDescricao("Volkswagen");
        marcaDAO.Inserir(marca);

    }

    public static void addTipoBaixa() {
        PatTipoBaixaDAO tipoBaixaDAO = new PatTipoBaixaDAO();

        PatTipoBaixa tipoBaixa = new PatTipoBaixa();
        tipoBaixa.setTipoBaixaDescricao("Furto");
        tipoBaixaDAO.inserir(tipoBaixa);

        tipoBaixa = new PatTipoBaixa();
        tipoBaixa.setTipoBaixaDescricao("Fogo");
        tipoBaixaDAO.inserir(tipoBaixa);

        tipoBaixa = new PatTipoBaixa();
        tipoBaixa.setTipoBaixaDescricao("Inutilizado");
        tipoBaixaDAO.inserir(tipoBaixa);

        tipoBaixa = new PatTipoBaixa();
        tipoBaixa.setTipoBaixaDescricao("Perda");
        tipoBaixaDAO.inserir(tipoBaixa);

        tipoBaixa = new PatTipoBaixa();
        tipoBaixa.setTipoBaixaDescricao("Venda");
        tipoBaixaDAO.inserir(tipoBaixa);

        tipoBaixa = new PatTipoBaixa();
        tipoBaixa.setTipoBaixaDescricao("Mercado Negro");
        tipoBaixaDAO.inserir(tipoBaixa);
    }
    
    public static void addPessoa(){
        
    }
    
    public static void addProduto(){
        EstProdutoDAO produtoDAO = new EstProdutoDAO();
        EstProduto produto = new EstProduto();
        produto.setProdutoDescricao("Leite");
        produto.setProdutoReferencia("In Natura");
        
        
        
    }
    

}
