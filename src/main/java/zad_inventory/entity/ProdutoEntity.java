package zad_inventory.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nome;
    
    @Column(length = 500)
    private String descricao;
    
    @Column(length = 50)
    private String tamanho;
    
    @Column(length = 50)
    private String cor;
    
    @Column(length = 50)
    private String formato;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;
    
    @Column(name = "quantidade_estoque", nullable = false)
    private Integer estoque;
    
    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    // Construtores
    public Produto() {
        this.dataCriacao = LocalDateTime.now();
    }
    
    public Produto(String nome, String descricao, String tamanho, String cor, 
                  String formato, BigDecimal preco, Integer estoque) {
        this();
        this.nome = nome;
        this.descricao = descricao;
        this.tamanho = tamanho;
        this.cor = cor;
        this.formato = formato;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
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
        this.estoque = estoque;
    }
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }
    
    // Callbacks JPA
    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }
    
    @Override
    public String toString() {
        return "Produto{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", descricao='" + descricao + '\'' +
               ", tamanho='" + tamanho + '\'' +
               ", cor='" + cor + '\'' +
               ", formato='" + formato + '\'' +
               ", preco=" + preco +
               ", estoque=" + estoque +
               ", dataCriacao=" + dataCriacao +
               ", dataAtualizacao=" + dataAtualizacao +
               '}';
    }
}
