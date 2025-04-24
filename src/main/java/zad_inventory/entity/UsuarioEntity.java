package zad_inventory.entity;

import javax.persistence.*;
import java.util.List;
import zad_inventory.enums.TipoUsuario;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    // Campo para armazenar o total (não persistido no banco)
    @Transient
    private Long totalProdutos;

    // Relação One-to-Many com Produto
    @OneToMany(mappedBy = "usuario")
    private List<ProdutoEntity> produtos;

    // Construtores
    public UsuarioEntity() {}

    public UsuarioEntity(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    // Métodos de negócio
    public boolean isGerente() {
        return tipoUsuario == TipoUsuario.GERENTE;
    }

    // Calcula o total dinamicamente (se necessário)
    public Long calcularTotalProdutos() {
        return produtos != null ? (long) produtos.size() : 0L;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public List<ProdutoEntity> getProdutos() { return produtos; }
    public void setProdutos(List<ProdutoEntity> produtos) { this.produtos = produtos; }

    public Long getTotalProdutos() {
        return totalProdutos != null ? totalProdutos : calcularTotalProdutos();
    }

    public void setTotalProdutos(Long totalProdutos) {
        this.totalProdutos = totalProdutos;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Nome: " + nome +
                ", Email: " + email +
                ", Tipo: " + tipoUsuario +
                ", Total de Produtos: " + getTotalProdutos();
    }
}