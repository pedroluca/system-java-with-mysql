package SistemaProdutosMySQL;

public class ProdutoPerecivel extends Produto {
  private String dataVencimento;

  public ProdutoPerecivel(int codigo, String nome, double preco, int quantidade, String dataVencimento) {
    super(codigo, nome, preco, quantidade);
    this.dataVencimento = dataVencimento;
  }

  public String getDataVencimento() {
    return dataVencimento;
  }

  @Override
  public String toString() {
    return super.toString() + ", Data de vencimento: " + dataVencimento;
  }
}
