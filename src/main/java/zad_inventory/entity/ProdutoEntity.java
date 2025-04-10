package zad_inventory.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;
    
        @Column(name = "nome_produto", nullable = false, length = 255)
    private String nomeProduto;
    
    @ManyToOne
    @JoinColumn(name = "fk_id_categoria", nullable = false)
    private int idCategoria;
    
    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;
    
    @Column(name = "tamanho", length = 50)
    private String tamanho;
    
    @Column(name = "cor", nullable = false, length = 50)
    private String cor;
    
    @Column(name = "formato", nullable = false, length = 50)
    private String formato;
    
    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(name = "estoque")
    private Integer estoque = 0;

    // Construtores
    public Produto() {
    }

    public Produto(String nomeProduto, String descricao, String tamanho, 
                  String cor, String formato, BigDecimal preco, Integer estoque) {
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.cor = cor;
        this.formato = formato;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Getters e Setters
    public Long getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo");
        }
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
               "idProduto=" + idProduto +
               ", nomeProduto='" + nomeProduto + '\'' +
               ", descricao='" + descricao + '\'' +
               ", tamanho='" + tamanho + '\'' +
               ", cor='" + cor + '\'' +
               ", formato='" + formato + '\'' +
               ", preco=" + preco +
               ", estoque=" + estoque +
               '}';
    }
}
