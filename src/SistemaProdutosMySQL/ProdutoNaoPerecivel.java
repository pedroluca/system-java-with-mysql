package SistemaProdutosMySQL;

public class ProdutoNaoPerecivel extends Produto {
  private String material;

  public ProdutoNaoPerecivel(int codigo, String nome, double preco, int quantidade, String material) {
    super(codigo, nome, preco, quantidade);
    this.material = material;
  }

  public String getMaterial() {
    return material;
  }

  @Override
  public String toString() {
    return super.toString() + ", Material: " + material;
  }
}