package zad_inventory.entity;

import javax.persistence.*;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "quantidade")
    private int quantidade;

    /*
    @Column(name = "fk_categoria_id")
    private int categoriaId; //public classe variavel
    */

    @Column(name = "cor")
    private String cor;

    @Column(name = "tamanho")
    private String tamanho;

    public ProdutoEntity() {}

    public ProdutoEntity(Long id, String nomeProduto, int quantidade, int categoriaId, String cor, String tamanho) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        //this.categoriaId = categoriaId;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                //", categoriaId=" + categoriaId +
                ", cor='" + cor + '\'' +
                ", tamanho='" + tamanho + '\'' +
                '}';
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    //public int getCategoriaId() { return categoriaId; }
    //public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
}
