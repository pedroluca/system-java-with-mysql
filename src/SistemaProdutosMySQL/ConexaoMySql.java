package SistemaProdutosMySQL;

import java.sql.*;
import java.util.ArrayList;

public class ConexaoMySql {
    private static String URL = "jdbc:mysql://localhost:3307/sistema_produtos";
    private static String USER = "root";
    private static String PASSWORD = "root";

    private Connection dbconn = null;
    private Statement sqlmgr = null;

    public void openDatabase() {
        try {
            dbconn = DriverManager.getConnection(URL, USER, PASSWORD);
            sqlmgr = dbconn.createStatement();
        } catch (Exception error) {
            error.printStackTrace();
        } 
    }

    public void closeDatabase() {
        try {
            if (sqlmgr != null) {
                sqlmgr.close();
            }
            if (dbconn != null) {
                dbconn.close();
            }
        } catch (Exception error) {
            error.printStackTrace();
        } 
    }    

    public int executeQuery(String sql) {
        try {
            return sqlmgr.executeUpdate(sql); // insert/delete/update/create
        } catch (Exception error) {
            error.printStackTrace();
        } 
        return -1;
    }

    public ArrayList<Produto> executeSelectQuery(String sql) {
        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try {
            ResultSet resultSet = sqlmgr.executeQuery(sql);
            while (resultSet.next()) {
                Boolean isPerecivel = resultSet.getBoolean("isPerecivel");
                if (isPerecivel) {
                    ProdutoPerecivel produtoPerecivel = new ProdutoPerecivel(resultSet.getInt("codigo"), resultSet.getString("nome"), resultSet.getDouble("preco"), resultSet.getInt("quantidade"), resultSet.getString("data_vencimento"));
                    listaProdutos.add(produtoPerecivel);
                } else {
                    ProdutoNaoPerecivel produtoNaoPerecivel = new ProdutoNaoPerecivel(resultSet.getInt("codigo"), resultSet.getString("nome"), resultSet.getDouble("preco"), resultSet.getInt("quantidade"), resultSet.getString("material"));
                    listaProdutos.add(produtoNaoPerecivel);
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return listaProdutos;
    }

    public void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception error) {
            error.printStackTrace();
        } 
    }
}