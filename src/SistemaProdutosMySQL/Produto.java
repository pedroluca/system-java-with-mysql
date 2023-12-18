package SistemaProdutosMySQL;

public interface Produto {
  // private int codigo;
  // private String nome;
  // private double preco;
  // private int quantidade;

  // public Produto(int codigo, String nome, double preco, int quantidade) {
  //   this.codigo = codigo;
  //   this.nome = nome;
  //   this.preco = preco;
  //   this.quantidade = quantidade;
  // }

  public int getCodigo();

  public String getNome();

  public double getPreco();

  public int getQuantidade();

  public void adicionarEstoque(int quantidade);

  @Override
  public String toString();

  /*
  public int getCodigo() {
    return codigo;
  }

  public String getNome() {
    return nome;
  }

  public double getPreco() {
    return preco;
  }

  public int getQuantidade() {
    return quantidade;
  }

  public void adicionarEstoque(int quantidade) {
    this.quantidade += quantidade;
    System.out.println("Quantidade em estoque atualizada para: " + this.quantidade);
  }

  @Override
  public String toString() {
    return "Código: " + codigo + ", Nome: " + nome + ", Preço: R$" + preco + ", Quantidade: " + quantidade;
  } */
}
