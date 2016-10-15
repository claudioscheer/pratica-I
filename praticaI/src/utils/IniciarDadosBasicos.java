package utils;

import dao.AtivoImobilizadoDAO;
import dao.CategoriaDAO;
import dao.HistoricoDepreciacaoDAO;
import dao.MarcaDAO;
import java.util.Calendar;
import java.util.Date;
import model.AtivoImobilizado;
import model.Categoria;
import model.HistoricoDepreciacao;
import model.Marca;

public class IniciarDadosBasicos {

    public static void main(String[] args) {

//        CategoriaDAO categoriaDAO = new CategoriaDAO();
//        for (int i = 1; i <= 3; i++) {
//            Categoria categoria = new Categoria();
//            categoria.setCodigo(i);
//            categoria.setDescricao("categoria " + i);
//            categoriaDAO.insert(categoria);
//        }
//
//        MarcaDAO marcaDAO = new MarcaDAO();
//        for (int i = 1; i <= 3; i++) {
//            Marca marca = new Marca();
//            marca.setCodigo(i);
//            marca.setDescricao("marca " + i);
//            marcaDAO.insert(marca);
//        }
        HistoricoDepreciacaoDAO historicoDepreciacaoDAO = new HistoricoDepreciacaoDAO();
        Calendar data = Calendar.getInstance();
        AtivoImobilizado ativoImobilizado = new AtivoImobilizadoDAO().get(4);
        for (int i = 1; i <= 4; i++) {
            HistoricoDepreciacao historicoDepreciacao = new HistoricoDepreciacao();
            historicoDepreciacao.setAno(data.get(Calendar.YEAR));
            historicoDepreciacao.setMes(data.get(Calendar.MONTH));
            historicoDepreciacao.setDia(data.getTime());
            historicoDepreciacao.setAtivoImobilizado(ativoImobilizado);
            historicoDepreciacao.setValor(300.87);
            historicoDepreciacaoDAO.insert(historicoDepreciacao);
        }
        
        System.exit(0);

    }

}
