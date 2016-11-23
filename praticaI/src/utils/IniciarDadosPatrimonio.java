package utils;

import dao.CarPessoaDAO;
import dao.EstCategoriaDAO;
import dao.EstMarcaDAO;
import dao.EstProdutoDAO;
import dao.PatDepreciacaoDAO;
import dao.PatTipoBaixaDAO;
import model.CarPessoa;
import model.EstCategoria;
import model.EstMarca;
import model.EstProduto;
import model.PatDepreciacao;
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
        addPessoa();
    }
    
    
    public static void addCategoria() {
        EstCategoriaDAO categoriaDAO = new EstCategoriaDAO();
        PatDepreciacaoDAO depreciacaoDAO = new PatDepreciacaoDAO();
        
        PatDepreciacao depreciacao = new PatDepreciacao();
        EstCategoria categoria = new EstCategoria();
        
        //ok
        categoria.setCategoriaDescricao("Móveis");
        categoriaDAO.Inserir(categoria); 
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(10.00);
        depreciacao.setDepreciacaoVidaUtil(10);
        depreciacaoDAO.inserir(depreciacao);
        
        //ok
        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Informática");
        categoriaDAO.Inserir(categoria);
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(20.00);
        depreciacao.setDepreciacaoVidaUtil(5);
        depreciacaoDAO.inserir(depreciacao);
        
        // ok
        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Automóveis");
        categoriaDAO.Inserir(categoria);
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(20.00);
        depreciacao.setDepreciacaoVidaUtil(5);
        depreciacaoDAO.inserir(depreciacao);
        
        // ok
        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Maquinário");
        categoriaDAO.Inserir(categoria);
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(25.00);
        depreciacao.setDepreciacaoVidaUtil(4);
        depreciacaoDAO.inserir(depreciacao);
        
        // ok
        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Ferramentas");
        categoriaDAO.Inserir(categoria);
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(20.00);
        depreciacao.setDepreciacaoVidaUtil(5);
        depreciacaoDAO.inserir(depreciacao);
        
        // ok
        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Imóveis");
        categoriaDAO.Inserir(categoria);
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(10.00);
        depreciacao.setDepreciacaoVidaUtil(10);
        depreciacaoDAO.inserir(depreciacao);
        
        //ok
        categoria = new EstCategoria();
        categoria.setCategoriaDescricao("Eletronicos");
        categoriaDAO.Inserir(categoria);
        depreciacao.setEstCategoria(categoria);
        depreciacao.setDepreciacaoTaxaAnual(20.00);
        depreciacao.setDepreciacaoVidaUtil(5);
        depreciacaoDAO.inserir(depreciacao);
        
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
        CarPessoaDAO pessoaDAO = new CarPessoaDAO();
        CarPessoa pessoa = new CarPessoa();
        
        pessoa.setPessoaTipo(2);
        pessoa.setPessoaNome("Larissa Daiane Caneppele Guder");
        pessoa.setPessoaCpfCnpj("02914266030");
        pessoa.setPessoaEmail("lariguder@hotmail.com");
        pessoa.setPessoaFone("92187669");
        pessoaDAO.insert(pessoa);
        
        pessoa = new CarPessoa();
        pessoa.setPessoaTipo(2);
        pessoa.setPessoaNome("Claudio Roberto Scheer Jr");
        pessoa.setPessoaCpfCnpj("03218400429");
        pessoa.setPessoaEmail("claudioscheer16@gmail.com");
        pessoa.setPessoaFone("99676593");
        pessoaDAO.insert(pessoa);

                pessoa = new CarPessoa();
        pessoa.setPessoaTipo(2);
        pessoa.setPessoaNome("Elenara Hein");
        pessoa.setPessoaCpfCnpj("09312944560");
        pessoa.setPessoaEmail("elenarahein@gmail.com");
        pessoa.setPessoaFone("99647351");
        pessoaDAO.insert(pessoa);

        
        pessoa = new CarPessoa();
        pessoa.setPessoaTipo(3);
        pessoa.setPessoaNome("Pitiço on fire Gorros");
        pessoa.setPessoaCpfCnpj("93812839401938");
        pessoa.setPessoaEmail("pitiçoonfire@gorros.com");
        pessoa.setPessoaFone("35251232");
        pessoaDAO.insert(pessoa);
    }
    
    public static void addProduto(){
        EstProdutoDAO produtoDAO = new EstProdutoDAO();
        EstProduto produto = new EstProduto();
        produto.setProdutoDescricao("Leite");
        produto.setProdutoReferencia("In Natura");
        
        
        
        
    }
    

}
