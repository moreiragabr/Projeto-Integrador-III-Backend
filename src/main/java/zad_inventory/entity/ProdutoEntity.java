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

    @ManyToOne
    @JoinColumn(name = "fk_categoria_id", insertable = false, updatable = false)
    private CategoriaEntity categoria;

    @Column(name = "fk_categoria_id")
    private Long categoriaId;  // Alterado de int para Long

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id", insertable = false, updatable = false)
    private UsuarioEntity usuario;

    @Column(name = "fk_usuario_id")
    private Long usuarioId;

    @Column(name = "cor")
    private String cor;

    @Column(name = "tamanho")
    private String tamanho;

    public ProdutoEntity() {}

    // Construtor atualizado (categoriaId como Long)
    public ProdutoEntity(Long id, String nomeProduto, int quantidade, Long categoriaId,
                         Long usuarioId, String cor, String tamanho) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.categoriaId = categoriaId;
        this.usuarioId = usuarioId;
        this.cor = cor;
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "ProdutoEntity{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", quantidade=" + quantidade +
                ", categoriaId=" + categoriaId +
                ", nomeCategoria=" + getNomeCategoria() +
                ", descricaoCategoria=" + getDescricaoCategoria() +
                ", usuarioId=" + usuarioId +
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

    public Long getCategoriaId() { return categoriaId; }  // Retorno alterado para Long
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }  // Parâmetro alterado para Long

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public CategoriaEntity getCategoria() { return categoria; }
    public void setCategoria(CategoriaEntity categoria) { this.categoria = categoria; }

    public UsuarioEntity getUsuario() { return usuario; }
    public void setUsuario(UsuarioEntity usuario) { this.usuario = usuario; }

    // Métodos para acessar dados da categoria
    public String getNomeCategoria() {
        return categoria != null ? categoria.getNome() : null;
    }

    public String getDescricaoCategoria() {
        return categoria != null ? categoria.getDescricao() : null;
    }
}