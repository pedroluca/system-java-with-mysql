package SistemaProdutosMySQL;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemaGerenciamentoProdutos {
  public static ConexaoMySql database = new ConexaoMySql();
  public static Scanner scan = new Scanner(System.in);
  public static ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

  public static void main(String[] args) {
    loadArrayList();
  }

  public static void systemMenu() {
    while(true) {
      System.out.println();
      System.out.println("## Sistema de Gerenciamento de Produtos ##");
      System.out.println("1- Inserir produto perecível");
      System.out.println("2- Inserir produto não perecível");
      System.out.println("3- Consultar produtos");
      System.out.println("4- Adicionar estoque");
      System.out.println("5- Sair");
      System.out.println("Escolha uma opção: ");

      int escolha = scan.nextInt();
      scan.nextLine();

      switch (escolha) {
        case 1:
          addNewProduct(listaProdutos, true);
          break;
        case 2:
          addNewProduct(listaProdutos, false);
          break;
        case 3:
          checkProducts(listaProdutos);
          break;
        case 4:
          updateProductQuantity(listaProdutos);
          break;
        case 5:
          closeSystem();
          break;
        default:
          System.out.println("Opção inválida!");
          break;
      }
    }
  }

  public static String addNewProduct(ArrayList<Produto> listaProdutos, boolean isPerecivel) {
    String sql;
    System.out.println();
    System.out.println("## Adicionar produto ##");
    int codigoProduto, validCode = 1;
    do {
      if (validCode != 1) System.out.println("O código informado é inválido ou já está em uso por outro produto!");
      System.out.println("Código: ");
      codigoProduto = scan.nextInt();
      scan.nextLine();
      validCode = validateCode(listaProdutos, codigoProduto);
    } while (validCode != 1);
    System.out.println("Nome: ");
    String nomeProduto = scan.nextLine();
    System.out.println("Preço: R$");
    double precoProduto = scan.nextDouble();
    scan.nextLine();
    System.out.println("Quantidade: ");
    int quantidadeProduto = scan.nextInt();
    scan.nextLine();
    if (isPerecivel) {
      System.out.println("Data de vencimento: ");
      String dataProduto = scan.nextLine();
      ProdutoPerecivel novoProduto = new ProdutoPerecivel(codigoProduto, nomeProduto, precoProduto, quantidadeProduto, dataProduto);
      listaProdutos.add(novoProduto);
      sql = "INSERT INTO Produto (codigo, nome, preco, quantidade, data_vencimento, isPerecivel) VALUES (" + codigoProduto + ", '" + nomeProduto + "', " + precoProduto + ", " + quantidadeProduto + ", '" + dataProduto + "', 1)";
    } else {
      System.out.println("Material: ");
      String materialProduto = scan.nextLine();
      ProdutoNaoPerecivel novoProduto = new ProdutoNaoPerecivel(codigoProduto, nomeProduto, precoProduto, quantidadeProduto, materialProduto);
      listaProdutos.add(novoProduto);
      sql = "INSERT INTO Produto (codigo, nome, preco, quantidade, material, isPerecivel) VALUES (" + codigoProduto + ", '" + nomeProduto + "', " + precoProduto + ", " + quantidadeProduto + ", '" + materialProduto + "', 0)";
    }
    try {
      database.openDatabase();
      database.executeQuery(sql);
      database.closeDatabase();
      return "Produto adicionado com sucesso!";
    } catch (Exception error) {
      error.printStackTrace();
    }
    return "\nProduto não pôde ser adicionado!";
  }

  public static void checkProducts(ArrayList<Produto> listaProdutos) {
    System.out.println();
    System.out.println("## Consultar produtos ##");
    for (Produto produto : listaProdutos) {
      System.out.println(produto);
    }
    System.out.println();
    systemMenu();
  }

  public static String updateProductQuantity(ArrayList<Produto> listaProdutos) {
    System.out.println();
    System.out.println("## Adicionar estoque ##");
    System.out.println("Digite o código do produto: ");
    int codigoEstoque = scan.nextInt();
    scan.nextLine();
    System.out.println("Digite a quantidade a ser adicionada: ");
    int quantidadeAdd = scan.nextInt();
    scan.nextLine();

    for (Produto produto : listaProdutos) {
      if (produto.getCodigo() == codigoEstoque) {
        produto.adicionarEstoque(quantidadeAdd);
        return "Quantidade atualizada com sucesso!";
      }
    }
    return "Houve um erro ao atualizar a quantidade!";
  }

  public static int validateCode (ArrayList<Produto> listaProdutos, int codigoProduto) {
    for (Produto produto : listaProdutos) {
      if (produto.getCodigo() == codigoProduto) return 0;
    }
    return 1;
  }

  public static void loadArrayList() {
    database.openDatabase();
    String sql = "SELECT * FROM Produto";
    listaProdutos.clear();
    listaProdutos.addAll(database.executeSelectQuery(sql));
    database.closeDatabase();
    systemMenu();
  }

  public static void closeSystem() {
    System.out.println("Sistema encerrado!");
    scan.close();
    System.exit(0);
  }
}
