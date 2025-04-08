package zad_inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int tamanho;

    @Column(length = 50, nullable = false)
    private String cor;

    @Column(length = 50, nullable = false)
    private String formato;

    @Column(length = 255)
    private String descricao;

    @Column(nullable = false)
    private int estoque;

    public ProdutoEntity() {
        
    }

    public ProdutoEntity(int tamanho, String cor, String formato, String descricao, int estoque) {
        this.ml = ml;
        this.cor = cor;
        this.formato = formato;
        this.descricao = descricao;
        this.estoque = estoque;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setMl(int tamanho) {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "id=" + id +
                ", ml=" + ml +
                ", cor='" + cor + '\'' +
                ", formato='" + formato + '\'' +
                ", descricao='" + descricao + '\'' +
                ", estoque=" + estoque +
                '}';
    }
}
