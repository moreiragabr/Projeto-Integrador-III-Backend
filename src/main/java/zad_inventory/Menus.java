package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.*;
import zad_inventory.repository.*;
import zad_inventory.service.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static zad_inventory.Main.loopLogin;

public class Menus {
    private static final Scanner scanner = new Scanner(System.in);
    private static UsuarioService usuarioService;
    private static ProdutoService produtoService;
    private static SituacaoService situacaoService;
    private static CategoriaRepository categoriaRepo;
    private static UsuarioEntity usuarioLogado;

    // Constantes para formatação
    private static final String BORDER = "╔══════════════════════════════════╗";
    private static final String BORDER_MID = "╠══════════════════════════════════╣";
    private static final String BORDER_BOTTOM = "╚══════════════════════════════════╝";
    private static final String HEADER = "║           ZAD INVENTORY          ║";

    public static void menuInicial(UsuarioEntity usuario) {
        usuarioLogado = usuario;
        inicializarServicos();

        while (true) {
            clearScreen();
            System.out.println(BORDER);
            System.out.println(HEADER);
            System.out.println(BORDER_MID);
            System.out.println("║ 1. Menu de Produto               ║");
            System.out.println("║ 2. Menu de Categoria             ║");
            System.out.println("║ 3. Menu de Situação              ║");
            System.out.println("║ 4. Menu de Usuários              ║");
            System.out.println("║ 9. Logout                        ║");
            System.out.println("║ 0. Sair                          ║");
            System.out.println(BORDER_BOTTOM);

            int opcao = lerInteiro("..::: Digite sua opção: ");

            switch (opcao) {
                case 1 -> menuProdutos();
                case 2 -> menuCategorias();
                case 3 -> {
                    if (verificarPermissaoGerente()) {
                        menuSituacao();
                    }
                }
                case 4 -> {
                    if (verificarPermissaoGerente()) {
                        menuUsuarios();
                    }
                }
                case 9 -> {
                    System.out.println("\n🔓 Logout efetuado.\n");
                    loopLogin(null);
                    return;
                }
                case 0 -> {
                    System.out.println("👋 Encerrando sistema...");
                    fecharServicos();
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // ========== MÉTODOS AUXILIARES ==========
    private static void inicializarServicos() {
        EntityManager em = DBConnection.getEntityManager();
        usuarioService = new UsuarioService(new UsuarioRepository(em));
        produtoService = new ProdutoService();
        situacaoService = new SituacaoService(new SituacaoRepository(em));
        categoriaRepo = new CategoriaRepository(em);
    }

    private static void fecharServicos() {
        DBConnection.close();
    }

    private static boolean verificarPermissaoGerente() {
        if (usuarioLogado.getTipoUsuario() != UsuarioEntity.TipoUsuario.GERENTE) {
            System.out.println("❌ Acesso negado. Permissão de gerente requerida.");
            aguardarEnter();
            return false;
        }
        return true;
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.nextLine();
            }
        }
    }

    private static void aguardarEnter() {
        System.out.println("\n..::: Pressione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // ========== MENU DE PRODUTOS ==========
    public static void menuProdutos() {
        while (true) {
            clearScreen();
            System.out.println(BORDER);
            System.out.println("║         MENU DE PRODUTOS         ║");
            System.out.println(BORDER_MID);
            System.out.println("║ 1. Criar produto                 ║");
            System.out.println("║ 2. Listar produtos               ║");
            System.out.println("║ 3. Editar produto                ║");
            System.out.println("║ 4. Excluir produto               ║");
            System.out.println("║ 5. Buscar por nome               ║");
            System.out.println("║ 6. Buscar por categoria          ║");
            System.out.println("║ 7. Gerenciar estoque             ║");
            System.out.println("║ 0. Voltar                        ║");
            System.out.println(BORDER_BOTTOM);

            int opcao = lerInteiro("..::: Digite sua opção: ");
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1 -> criarProduto();
                case 2 -> listarProdutos();
                case 3 -> editarProduto();
                case 4 -> excluirProduto();
                case 5 -> buscarProdutoPorNome();
                case 6 -> buscarProdutoPorCategoria();
                case 7 -> gerenciarEstoque();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
            aguardarEnter();
        }
    }

    private static void criarProduto() {
        System.out.println("\n=== NOVO PRODUTO ===");
        ProdutoEntity novo = new ProdutoEntity();

        System.out.print("Nome: ");
        novo.setNomeProduto(scanner.nextLine());

        System.out.print("Cor: ");
        novo.setCor(scanner.nextLine());

        System.out.print("Tamanho: ");
        novo.setTamanho(scanner.nextLine());

        System.out.print("Quantidade: ");
        novo.setQuantidade(scanner.nextInt());

        System.out.print("ID da Categoria: ");
        novo.setCategoriaId(scanner.nextInt());

        ProdutoEntity salvo = produtoService.salvarProduto(novo);
        System.out.println("✅ Produto criado: ID " + salvo.getId());
    }

    private static void listarProdutos() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║        LISTA DE PRODUTOS         ║");
        System.out.println(BORDER_MID);

        try {
            List<ProdutoEntity> produtos = produtoService.listarTodos();

            if (produtos.isEmpty()) {
                System.out.println("║ Nenhum produto cadastrado         ║");
            } else {
                for (ProdutoEntity p : produtos) {
                    System.out.printf("║ ID: %-4d | %-20s  ║\n", p.getId(), p.getNomeProduto());
                    System.out.printf("║ Qtd: %-3d | Categoria: %-10d ║\n", p.getQuantidade(), p.getCategoriaId());
                    System.out.printf("║ Cor: %-5s | Tamanho: %-10s ║\n", p.getCor(), p.getTamanho());
                    System.out.println(BORDER_MID);
                }
            }
        } catch (Exception e) {
            System.out.println("║ Erro ao listar produtos: " + e.getMessage() + " ║");
        }

        System.out.println(BORDER_BOTTOM);
        aguardarEnter();
    }

    private static void editarProduto() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║        EDITAR PRODUTO         ║");
        System.out.println(BORDER_MID);

        try {
            listarProdutos();
            System.out.print("║ ID do produto a editar: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            ProdutoEntity produto = produtoService.buscarPorId(id);
            if (produto == null) {
                System.out.println(BORDER_BOTTOM);
                System.out.println("❌ Produto não encontrado!");
                aguardarEnter();
                return;
            }

            System.out.println(BORDER_MID);
            System.out.printf("║ Nome atual: %-30s ║\n", produto.getNomeProduto());
            System.out.print("║ Novo nome (enter para manter): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isEmpty()) produto.setNomeProduto(novoNome);

            System.out.printf("║ Quantidade atual: %-30d ║\n", produto.getQuantidade());
            System.out.print("║ Nova quantidade: ");
            String qtdStr = scanner.nextLine();
            if (!qtdStr.isEmpty()) produto.setQuantidade(Integer.parseInt(qtdStr));

            System.out.printf("║ Categoria atual: %-30d ║\n", produto.getCategoriaId());
            System.out.print("║ Nova categoria (enter para manter): ");
            String catStr = scanner.nextLine();
            if (!catStr.isEmpty()) produto.setCategoriaId(Integer.parseInt(catStr));

            produtoService.salvarProduto(produto);
            System.out.println(BORDER_BOTTOM);
            System.out.println("✅ Produto atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println(BORDER_BOTTOM);
            System.out.println("❌ Erro ao editar: " + e.getMessage());
        }

        aguardarEnter();
    }

    private static void excluirProduto() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║       EXCLUIR PRODUTO        ║");
        System.out.println(BORDER_MID);

        try {
            listarProdutos();
            System.out.print("║ ID do produto a excluir: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            if (confirmarAcao("Tem certeza que deseja excluir este produto?")) {
                produtoService.deletarProduto(id);
                System.out.println(BORDER_BOTTOM);
                System.out.println("✅ Produto excluído com sucesso!");
            } else {
                System.out.println(BORDER_BOTTOM);
                System.out.println("⚠️ Exclusão cancelada!");
            }
        } catch (Exception e) {
            System.out.println(BORDER_BOTTOM);
            System.out.println("❌ Erro ao excluir: " + e.getMessage());
        }

        aguardarEnter();
    }

    private static void buscarProdutoPorNome() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║     BUSCAR PRODUTO POR NOME   ║");
        System.out.println(BORDER_MID);

        try {
            System.out.print("║ Digite o nome (ou parte): ");
            String nome = scanner.nextLine();

            List<ProdutoEntity> produtos = produtoService.buscarPorNome(nome);

            if (produtos.isEmpty()) {
                System.out.println("║ Nenhum produto encontrado         ║");
            } else {
                System.out.println(BORDER_MID);
                for (ProdutoEntity p : produtos) {
                    System.out.printf("║ ID: %-4d | %-20s ║\n", p.getId(), p.getNomeProduto());
                    System.out.printf("║ Qtd: %-3d | Categoria: %-10d ║\n", p.getQuantidade(), p.getCategoriaId());
                    System.out.println(BORDER_MID);
                }
            }
        } catch (Exception e) {
            System.out.println("║ Erro na busca: " + e.getMessage() + " ║");
        }

        System.out.println(BORDER_BOTTOM);
        aguardarEnter();
    }

    private static void buscarProdutoPorCategoria() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║  BUSCAR PRODUTO POR CATEGORIA ║");
        System.out.println(BORDER_MID);

        try {
            System.out.print("║ ID da categoria: ");
            int categoriaId = scanner.nextInt();
            scanner.nextLine();

            List<ProdutoEntity> produtos = produtoService.buscarPorCategoria(categoriaId);

            if (produtos.isEmpty()) {
                System.out.println("║ Nenhum produto nesta categoria    ║");
            } else {
                System.out.println(BORDER_MID);
                for (ProdutoEntity p : produtos) {
                    System.out.printf("║ ID: %-4d | %-20s ║\n", p.getId(), p.getNomeProduto());
                    System.out.printf("║ Qtd: %-3d | Cor: %-10s ║\n", p.getQuantidade(), p.getCor());
                    System.out.println(BORDER_MID);
                }
            }
        } catch (Exception e) {
            System.out.println("║ Erro na busca: " + e.getMessage() + " ║");
        }

        System.out.println(BORDER_BOTTOM);
        aguardarEnter();
    }

    private static void gerenciarEstoque() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║       GERENCIAR ESTOQUE       ║");
        System.out.println(BORDER_MID);
        System.out.println("║ 1 - Adicionar estoque         ║");
        System.out.println("║ 2 - Remover estoque           ║");
        System.out.println("║ 0 - Voltar                    ║");
        System.out.println(BORDER_BOTTOM);

        try {
            System.out.print("..::: Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) return;

            System.out.print("ID do produto: ");
            Long id = scanner.nextLong();
            System.out.print("Quantidade: ");
            int qtd = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                produtoService.adicionarEstoque(id, qtd);
                System.out.println("✅ Estoque adicionado!");
            } else if (opcao == 2) {
                produtoService.removerEstoque(id, qtd);
                System.out.println("✅ Estoque removido!");
            } else {
                System.out.println("❌ Opção inválida!");
            }
        } catch (Exception e) {
            System.out.println("❌ Erro: " + e.getMessage());
        }

        aguardarEnter();
    }


    // ... (implementar outros métodos de produtos seguindo o mesmo padrão)

    // ========== MENU DE CATEGORIAS ==========
    public static void menuCategorias() {
        while (true) {
            clearScreen();
            System.out.println(BORDER);
            System.out.println("║       MENU DE CATEGORIAS         ║");
            System.out.println(BORDER_MID);
            System.out.println("║ 1. Criar categoria               ║");
            System.out.println("║ 2. Listar categorias             ║");
            System.out.println("║ 3. Editar categoria              ║");
            System.out.println("║ 4. Excluir categoria             ║");
            System.out.println("║ 0. Voltar                        ║");
            System.out.println(BORDER_BOTTOM);

            int opcao = lerInteiro("..::: Digite sua opção: ");
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarCategoria();
                case 2 -> listarCategorias();
                case 3 -> editarCategoria();
                case 4 -> excluirCategoria();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
            aguardarEnter();
        }
    }

    private static void criarCategoria() {
        System.out.println("\n=== NOVA CATEGORIA ===");
        CategoriaEntity nova = new CategoriaEntity();

        System.out.print("Nome: ");
        nova.setNome(scanner.nextLine());

        System.out.print("Descrição: ");
        nova.setDescricao(scanner.nextLine());

        categoriaRepo.salvar(nova);
        System.out.println("✅ Categoria criada com sucesso!");
    }

    // ========== MENU DE CATEGORIAS - MÉTODOS COMPLETOS ==========

    private static void listarCategorias() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║      LISTA DE CATEGORIAS      ║");
        System.out.println(BORDER_MID);

        try {
            List<CategoriaEntity> categorias = categoriaRepo.buscarTodos();

            if (categorias.isEmpty()) {
                System.out.println("║ Nenhuma categoria cadastrada       ║");
            } else {
                for (CategoriaEntity cat : categorias) {
                    System.out.printf("║ ID: %-3d | %-20s ║\n",
                            cat.getId(), cat.getNome());
                    System.out.printf("║ Descrição: %-40s ║\n",
                            cat.getDescricao());
                    System.out.println(BORDER_MID);
                }
            }
        } catch (Exception e) {
            System.out.println("║ Erro ao listar categorias: " +
                    e.getMessage() + " ║");
        }

        System.out.println(BORDER_BOTTOM);
        aguardarEnter();
    }

    private static void editarCategoria() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║      EDITAR CATEGORIA       ║");
        System.out.println(BORDER_MID);

        try {
            // Primeiro lista as categorias para facilitar seleção
            listarCategorias();

            System.out.print("║ ID da categoria a editar: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpar buffer

            CategoriaEntity categoria = categoriaRepo.buscarPorId(id);
            if (categoria == null) {
                System.out.println(BORDER_BOTTOM);
                System.out.println("❌ Categoria não encontrada!");
                aguardarEnter();
                return;
            }

            System.out.println(BORDER_MID);
            System.out.printf("║ Nome atual: %-40s ║\n", categoria.getNome());
            System.out.print("║ Novo nome (enter para manter): ");
            String novoNome = scanner.nextLine();
            if (!novoNome.isBlank()) {
                categoria.setNome(novoNome);
            }

            System.out.printf("║ Descrição atual: %-40s ║\n", categoria.getDescricao());
            System.out.print("║ Nova descrição (enter para manter): ");
            String novaDesc = scanner.nextLine();
            if (!novaDesc.isBlank()) {
                categoria.setDescricao(novaDesc);
            }

            categoriaRepo.atualizar(categoria);
            System.out.println(BORDER_BOTTOM);
            System.out.println("✅ Categoria atualizada com sucesso!");
        } catch (Exception e) {
            System.out.println(BORDER_BOTTOM);
            System.out.println("❌ Erro ao editar: " + e.getMessage());
        }

        aguardarEnter();
    }

    private static void excluirCategoria() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║      EXCLUIR CATEGORIA       ║");
        System.out.println(BORDER_MID);

        try {
            // Lista categorias para referência
            listarCategorias();

            System.out.print("║ ID da categoria a excluir: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpar buffer

            // Confirmação simples
            if (confirmarAcao("Tem certeza que deseja excluir esta categoria?")) {
                CategoriaEntity categoria = categoriaRepo.buscarPorId(id);
                if (categoria != null) {
                    categoriaRepo.remover(categoria);
                    System.out.println(BORDER_BOTTOM);
                    System.out.println("✅ Categoria excluída com sucesso!");
                } else {
                    System.out.println(BORDER_BOTTOM);
                    System.out.println("❌ Categoria não encontrada!");
                }
            } else {
                System.out.println(BORDER_BOTTOM);
                System.out.println("⚠️ Exclusão cancelada!");
            }
        } catch (Exception e) {
            System.out.println(BORDER_BOTTOM);
            System.out.println("❌ Erro ao excluir: " + e.getMessage());
        }

        aguardarEnter();
    }

    // ========== MENU DE SITUAÇÃO ==========
    public static void menuSituacao() {
        while (true) {
            clearScreen();
            System.out.println(BORDER);
            System.out.println("║       MENU DE SITUAÇÃO           ║");
            System.out.println(BORDER_MID);
            System.out.println("║ 1. Criar situação                ║");
            System.out.println("║ 2. Editar situação               ║");
            System.out.println("║ 3. Listar situações              ║");
            System.out.println("║ 4. Excluir situação              ║");
            System.out.println("║ 0. Voltar                        ║");
            System.out.println(BORDER_BOTTOM);

            int opcao = lerInteiro("..::: Digite sua opção: ");
            scanner.nextLine();

            switch (opcao) {
                case 1 -> criarSituacao();
                case 2 -> editarSituacao();
                case 3 -> listarSituacoes();
                case 4 -> excluirSituacao();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
            aguardarEnter();
        }
    }

    private static void criarSituacao() {
        System.out.println("\n=== NOVA SITUAÇÃO ===");
        SituacaoEntity nova = new SituacaoEntity();

        System.out.print("Nome da situação: ");
        nova.setSituacaoNome(scanner.nextLine());

        System.out.print("ID do produto: ");
        ProdutoEntity produto = produtoService.buscarPorId(scanner.nextLong());
        nova.setProdutoId(Math.toIntExact(produto.getId()));
        nova.setProdutoNome(produto.getNomeProduto());

        System.out.print("Quantidade: ");
        nova.setQuantidade(scanner.nextInt());

        nova.setUsuarioId(Math.toIntExact(usuarioLogado.getId()));
        nova.setUsuarioNome(usuarioLogado.getNome());
        nova.setData(LocalDateTime.now());

        situacaoService.criarSituacao(nova);
        System.out.println("✅ Situação registrada com sucesso!");
    }

    private static void editarSituacao() {
        System.out.println("\n=== EDITAR SITUAÇÃO ===");
        System.out.print("ID da situação: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        SituacaoEntity situacao = situacaoService.buscarSituacaoPorId(id);
        if (situacao == null) {
            System.out.println("Situação não encontrada!");
            return;
        }

        System.out.print("Novo nome (" + situacao.getSituacaoNome() + "): ");
        situacao.setSituacaoNome(scanner.nextLine());

        // Atualizar outros campos conforme necessário

        situacaoService.atualizarSituacao(situacao);
        System.out.println("✅ Situação atualizada!");
    }

    private static void listarSituacoes() {
        List<SituacaoEntity> situacoes = situacaoService.listarTodasSituacoes();
        System.out.println("\n=== LISTA DE SITUAÇÕES ===");
        for (SituacaoEntity s : situacoes) {
            System.out.printf(
                    "ID: %d | Situação: %s | Produto: %s | Usuário: %s | Qtd: %d | Data: %s\n",
                    s.getId(), s.getSituacaoNome(), s.getProdutoNome(),
                    s.getUsuarioNome(), s.getQuantidade(), s.getData()
            );
        }
    }

    private static void excluirSituacao() {
        System.out.println("\n=== EXCLUIR SITUAÇÃO ===");
        System.out.print("ID da situação: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        if (confirmarAcao("Tem certeza que deseja excluir esta situação?")) {
            situacaoService.removerSituacao(id);
            System.out.println("✅ Situação excluída!");
        }
    }


    // ========== MENU DE USUÁRIOS ==========
    public static void menuUsuarios() {
        while (true) {
            clearScreen();
            System.out.println(BORDER);
            System.out.println("║        MENU DE USUÁRIOS          ║");
            System.out.println(BORDER_MID);
            System.out.println("║ 1. Listar usuários               ║");
            System.out.println("║ 2. Registrar usuário             ║");
            System.out.println("║ 3. Excluir usuário               ║");
            System.out.println("║ 4. Editar nome                   ║");
            System.out.println("║ 0. Voltar                        ║");
            System.out.println(BORDER_BOTTOM);

            int opcao = lerInteiro("..::: Digite sua opção: ");
            scanner.nextLine();

            switch (opcao) {
                case 1 -> listarUsuarios();
                case 2 -> registrarUsuario();
                case 3 -> excluirUsuario();
                case 4 -> editarNomeUsuario();
                case 0 -> { return; }
                default -> System.out.println("Opção inválida.");
            }
            aguardarEnter();
        }
    }

    private static void listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.listarUsuarios();
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        usuarios.forEach(u -> System.out.printf(
                "ID: %d | Nome: %s | Email: %s | Tipo: %s\n",
                u.getId(), u.getNome(), u.getEmail(), u.getTipoUsuario()
        ));
    }
    // ========== MENU DE USUÁRIOS - MÉTODOS COMPLETOS ==========

    private static void registrarUsuario() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║      CADASTRAR NOVO USUÁRIO     ║");
        System.out.println(BORDER_MID);

        UsuarioEntity novoUsuario = new UsuarioEntity();

        try {
            System.out.print("║ Nome completo: ");
            novoUsuario.setNome(scanner.nextLine());

            System.out.print("║ Email: ");
            novoUsuario.setEmail(scanner.nextLine());

            System.out.print("║ Senha: ");
            novoUsuario.setSenha(scanner.nextLine());

            System.out.print("║ Tipo (GERENTE/FUNCIONARIO): ");
            novoUsuario.setTipoUsuario(UsuarioEntity.TipoUsuario.valueOf(
                    scanner.nextLine().toUpperCase()));

            usuarioService.registrarUsuario(novoUsuario, usuarioLogado);

            System.out.println(BORDER_BOTTOM);
            System.out.println("✅ Usuário registrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Tipo de usuário inválido! Use GERENTE ou FUNCIONARIO");
        } catch (Exception e) {
            System.out.println("❌ Erro ao registrar: " + e.getMessage());
        }
    }

    private static void excluirUsuario() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║        EXCLUIR USUÁRIO        ║");
        System.out.println(BORDER_MID);

        try {
            // Primeiro lista os usuários para facilitar a seleção
            listarUsuarios();

            System.out.print("║ ID do usuário a excluir: ");
            Long id = scanner.nextLong();
            scanner.nextLine(); // Limpar buffer

            if (confirmarAcao("Tem certeza que deseja excluir este usuário?")) {
                usuarioService.deletarUsuario(id, usuarioLogado);
                System.out.println(BORDER_BOTTOM);
                System.out.println("✅ Usuário excluído com sucesso!");
            }
        } catch (Exception e) {
            System.out.println(BORDER_BOTTOM);
            System.out.println("❌ Erro ao excluir: " + e.getMessage());
        }
    }

    private static void editarNomeUsuario() {
        clearScreen();
        System.out.println(BORDER);
        System.out.println("║       EDITAR NOME DE USUÁRIO      ║");
        System.out.println(BORDER_MID);

        try {
            System.out.printf("║ Nome atual: %s\n", usuarioLogado.getNome());
            System.out.print("║ Novo nome: ");
            String novoNome = scanner.nextLine();

            if (!novoNome.isBlank()) {
                usuarioLogado.setNome(novoNome);

                EntityManager em = DBConnection.getEntityManager();
                em.getTransaction().begin();
                em.merge(usuarioLogado);
                em.getTransaction().commit();
                em.close();

                System.out.println(BORDER_BOTTOM);
                System.out.println("✅ Nome atualizado com sucesso!");
            } else {
                System.out.println(BORDER_BOTTOM);
                System.out.println("⚠️ Nome não pode ser vazio!");
            }
        } catch (Exception e) {
            System.out.println(BORDER_BOTTOM);
            System.out.println("❌ Erro ao atualizar: " + e.getMessage());
        }
    }

    // Método auxiliar para confirmar ações
    private static boolean confirmarAcao(String mensagem) {
        System.out.println(BORDER_MID);
        System.out.printf("║ %s\n", mensagem);
        System.out.println("║ 1 - Sim   2 - Não");
        System.out.println(BORDER_BOTTOM);
        System.out.print("..::: Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        return opcao == 1;
    }

}
