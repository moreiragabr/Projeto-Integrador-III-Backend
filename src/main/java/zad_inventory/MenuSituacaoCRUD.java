package zad_inventory;

import zad_inventory.config.DBConnection;
import zad_inventory.entity.ProdutoEntity;
import zad_inventory.entity.SituacaoEntity;
import zad_inventory.entity.UsuarioEntity;
import zad_inventory.repository.SituacaoRepository;
import zad_inventory.repository.UsuarioRepository;
import zad_inventory.service.ProdutoService;
import zad_inventory.service.SituacaoService;
import zad_inventory.service.UsuarioService;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuSituacaoCRUD {

    private static UsuarioService usuarioService;
    private static SituacaoService situacaoService;

    public static void SituacaoTeste() {

        EntityManager em = DBConnection.getEntityManager();
        usuarioService = new UsuarioService(new UsuarioRepository(em));
        situacaoService = new SituacaoService(new SituacaoRepository(em));

        ProdutoService produtoService = new ProdutoService();


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu de teste do CRUD da classe situação\n");
            System.out.println("1 - Criar situação");
            System.out.println("2 - Alterar situação");
            System.out.println("3 - Listar situações");
            System.out.println("4 - Deletar situação");
            System.out.print("\n..:::  ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao){
                    case 1:
                        SituacaoEntity novaSituacao = new SituacaoEntity();
                        ProdutoEntity produtoSelecionado = new ProdutoEntity();
                        UsuarioEntity usuarioSelecionado = new UsuarioEntity();
                        SituacaoEntity situacaoSelecionada = new SituacaoEntity();

                        System.out.print("\n..::: Nome da situação: ");
                        novaSituacao.setSituacaoNome(scanner.nextLine());

                        System.out.print("\n..::: Id do produto: ");
                        produtoSelecionado = produtoService.buscarPorId(Long.valueOf(scanner.nextInt()));
                        novaSituacao.setProdutoId(Math.toIntExact(produtoSelecionado.getId()));
                        novaSituacao.setProdutoNome(produtoSelecionado.getNomeProduto());

                        System.out.print("\n..::: Id do usuario: ");
                        usuarioSelecionado = usuarioService.buscarPorId(Long.valueOf(scanner.nextInt()));
                        novaSituacao.setUsuarioId(Math.toIntExact(usuarioSelecionado.getId()));
                        novaSituacao.setUsuarioNome(usuarioSelecionado.getNome());

                        System.out.print("\n..::: Quantidade: ");
                        novaSituacao.setQuantidade(scanner.nextInt());
                        novaSituacao.setData(LocalDateTime.now());

                        situacaoService.criarSituacao(novaSituacao);
                        break;

                    case 2:
                        System.out.print("\n..::: Id da situação a ser modificada: ");
                        situacaoSelecionada = situacaoService.buscarSituacaoPorId(Long.valueOf(scanner.nextInt()));
                        scanner.nextLine();

                        System.out.print("\n..::: Novo nome da situação: ");
                        situacaoSelecionada.setSituacaoNome(scanner.nextLine());

                        System.out.print("\n..::: Id do produto: ");
                        produtoSelecionado = produtoService.buscarPorId(Long.valueOf(scanner.nextInt()));
                        situacaoSelecionada.setProdutoId(Math.toIntExact(produtoSelecionado.getId()));
                        situacaoSelecionada.setProdutoNome(produtoSelecionado.getNomeProduto());

                        System.out.print("\n..::: Id do usuario: ");
                        usuarioSelecionado = usuarioService.buscarPorId(Long.valueOf(scanner.nextInt()));
                        situacaoSelecionada.setUsuarioId(Math.toIntExact(usuarioSelecionado.getId()));
                        situacaoSelecionada.setUsuarioNome(usuarioSelecionado.getNome());

                        System.out.print("\n..::: Nova Quantidade: ");
                        situacaoSelecionada.setQuantidade(scanner.nextInt());
                        situacaoSelecionada.setData(LocalDateTime.now());

                        situacaoService.atualizarSituacao(situacaoSelecionada);
                        break;

                    case 3:
                        System.out.println("\n..::: Lista de todas as situações do banco de dados\n");
                        List<SituacaoEntity> situacoes = situacaoService.listarTodasSituacoes();
                        for(SituacaoEntity s : situacoes){
                            System.out.printf("Id: %d | Nome da situação: %s | Id produto: %d | Nome produto: %s | Id usuario: %d | Usuario nome: %s | Quantidade: %d\n",
                                    s.getId(), s.getSituacaoNome(), s.getProdutoId(), s.getProdutoNome(), s.getUsuarioId(), s.getUsuarioNome(), s.getQuantidade());
                        }
                        break;

                    case 4:
                        System.out.print("\n..::: Id da situação a ser deletada: ");
                        situacaoSelecionada = situacaoService.buscarSituacaoPorId(Long.valueOf(scanner.nextInt()));
                        scanner.nextLine();

                        situacaoService.removerSituacao(situacaoSelecionada.getId());
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    }
}
