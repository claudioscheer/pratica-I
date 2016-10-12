package utils;

import dao.CategoriaDAO;
import dao.MarcaDAO;
import model.Categoria;
import model.Marca;

public class IniciarDadosBasicos {
    
    public static void main(String[] args) {
        
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
        
    }
    
}
