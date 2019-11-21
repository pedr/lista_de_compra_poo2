/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listadecompra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author pedr
 */
public class ListaDeCompra {

    
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Produto> produtos = new ArrayList<>();
    public static ArrayList<HistoricoCompra> historicosCompras = new ArrayList<>();
    public static ArrayList<ListaCompra> listasCompras = new ArrayList<>();
    public static ArrayList<Supermercado> supermercados = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for (Mes mes : Mes.values()) {
            ArrayList<ItemCompra> itensCompras = new ArrayList<ItemCompra>();
            listasCompras.add(new ListaCompra(mes, itensCompras));
        }
        
        produtos = ProdutoDAO.getAll();
        supermercados = SupermercadoDAO.getAll();
        historicosCompras = HistoricoCompraDAO.getAll();
        ListaCompraDAO.createIfDoesntExist();
        listasCompras = ListaCompraDAO.getAll();
        
        while (true) {
            
            System.out.println("");
            System.out.println("0 - Cadastrar produto");
            System.out.println("1 - Inserir Compra");
            System.out.println("2 - Listar as parada");
            System.out.println("3 - Cadastrar supermercado");
            System.out.println("4 - Listar tabela");
            System.out.println("9 - Sair");
            System.out.println("");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch(option) {
                case 0:
                    cadastrarProduto();
                    break;
                case 1:
                    inserirCompra();
                    break;
                case 2:
                    list();
                    break;
                case 3:
                    cadastrarSupermercado();
                    break;
                case 4:
                    listarTabela();
                    break;
                case 9: 
                    return;
            }
        }
    }
    
    public static void inserirCompra() {
        // mercado
        if (produtos.size() < 1 || supermercados.size() < 1) {
            System.out.println("ERRO: Necessita de um produto e um supermercado cadastrado");
            return;
        }
        
        System.out.println("Escolha um supermercado por nome ou id");
        listarArray(supermercados);
        String nomeSM = scanner.nextLine();
        Supermercado supermercado = getSupermercadoFromList(nomeSM);
        if (supermercado == null) {
            System.out.println("ERRO: Esse supermercado não existe");
            return;
        }
        
        // mes
        System.out.println("Escolha o mês da compra: (1:jan, 2:fev, etc");
        int valueMes = scanner.nextInt();
        scanner.nextLine();
        Mes mes = Mes.valueOf(valueMes);
        if (mes == null) {
            System.out.println("ERRO: Mês invalido");
            return;
        }
        // listasCompras começa do 0
        ListaCompra listacompra = ListaCompraDAO.get(mes);
        
        if (listacompra == null) {
            System.out.println("ERRO: não foi possível achar a lista desse mês");
            return;
        }
        
        // produto
        System.out.println("Escolha um produto por nome ou id:");
        listarArray(produtos);
        String nomeProduto = scanner.nextLine();
        Produto produto = getProdutoFromList(nomeProduto);
        if (produto == null) {
            System.out.println("ERRO: Esse produto não existe");
            return;
        }
        
        // qntEfetiva
        System.out.println("Quanto foi efetivamente comprado: ");
        Double efetivamenteComprado = scanner.nextDouble();
        
        ItemCompra itemCompra = new ItemCompra(produto, efetivamenteComprado);
        ItemCompraDAO.create(itemCompra);
        ListaCompraDAO.addOneItemToLista(listacompra, itemCompra);
        listacompra = ListaCompraDAO.get(mes);
        
        // valor pago
        System.out.println("Quanto foi pago pelo produto: ");
        Double valorPago = scanner.nextDouble();
        HistoricoCompra hc = new HistoricoCompra(mes, produto, valorPago, supermercado);
        HistoricoCompraDAO.create(hc);
        historicosCompras = HistoricoCompraDAO.getAll();
    }
    
    public static void listarTabela() {
        System.out.println("Escolha um mês (1-jan, 2-fev, etc");
        int valueMes = scanner.nextInt();
        scanner.nextLine();
        Mes mes = Mes.valueOf(valueMes);
        if (mes == null) {
            System.out.println("ERRO: Mês não existe");
            return;
        }
        printarListaCompra(mes);
    }
    
    public static void printarListaCompra(Mes mes) {
        ListaCompra lc = ListaCompraDAO.get(mes);
        System.out.println("No mês de " + mes + " foram comprados os seguintes itens:");
        System.out.println("Tamanho da lista de itens: " + lc.getItensCompra().size());
        for (ItemCompra ic : lc.getItensCompra()) {
            System.out.println("\t" + ic.toStringToListaCompra());
        }
        System.out.println("Gasto total estimado: " + lc.getTotalEstimado());
    }

    public static void list() {
        System.out.println("Escolha o que quer listar: ");
        System.out.println("0 - produtos");
        System.out.println("1 - supermercados");
        System.out.println("2 - histórico");
        System.out.println("3 - lista de compras (por mês)");
        
        int opcao = scanner.nextInt();
        switch(opcao) {
            case 0:
                listarArray(produtos);
                break;
            case 1:
                listarArray(supermercados);
                break;
            case 2:
                System.out.println("\t\tProduto\t Mês\t Valor Compra\t Supermercado");
                listarArray(historicosCompras);
                break;
            case 3:
                listarArray(listasCompras);
                break;
        }
    }
    
    public static void listarArray(ArrayList arrList) {
        for (Object object : arrList) {
            IsTable isTable = (IsTable)object;
            System.out.println("\tid:" + isTable.getId() + " - " + object.toString());
        }
        System.out.println("");
    }
    
    public static void cadastrarProduto() {
        System.out.println("Insira os dados do produto: ");
        System.out.println("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.println("Unidade de compra:");
        for (UnidadeCompra uCompra : UnidadeCompra.values()) {
            System.out.println(uCompra.valor + " - " + uCompra);
        }
        int unidade = scanner.nextInt();
        
        // encontra enum pelo int
        UnidadeCompra unidadeCompra = UnidadeCompra.valueOf(unidade);
        
        if (unidadeCompra == null) {
            System.out.println("ERRO: Unidade de compra inválida");
            return;
        }
        
        System.out.println("Quantidade prevista por mês (qnt efetiva de compra muda de mês para mês): ");
        Double qntPrevista = scanner.nextDouble();
        
        Produto novoProduto = new Produto(nome, descricao, unidadeCompra, qntPrevista);
        novoProduto = ProdutoDAO.create(novoProduto);
        
        if (novoProduto == null) {        
            System.out.println("ERRO: Produto não foi cadastrado");
            return;
        }
        
        produtos = ProdutoDAO.getAll();
        System.out.println("Novo produto inserido");
        return;
    }
    
    public static void cadastrarSupermercado() {
        System.out.println("Nome do supermercado: ");
        String nome = scanner.nextLine();
        Supermercado supermercado = new Supermercado(nome);

        supermercado = SupermercadoDAO.create(supermercado);        
        if (supermercado == null) {
            System.out.println("ERRO: não foi possível salvar o supermercado no banco de dados");
            return;
        }
        
        supermercados = SupermercadoDAO.getAll();
        System.out.println("Supermercado cadastrado");
    }
    
    public static Produto getProdutoFromList(String input) {
        int id = -1;
        try {
            id = Integer.parseInt(input.trim());
            for (Produto produto : produtos) {
                if (produto.getId() == id) {
                    return produto;
                }    
            }
            return null;
        } catch (Exception e) {
            for (Produto produto : produtos) {
                if (produto.getNome().equals(input)) {
                    return produto;
                }
            }
        }
        return null;
    }
    
    public static Supermercado getSupermercadoFromList(String input) {
        int id = -1;
        try {
            id = Integer.parseInt(input.trim());
            for (Supermercado mercado : supermercados) {
                if (mercado.getId() == id) {
                    return mercado;
                }
            }
            return null;
        } catch (Exception e) {
            for (Supermercado mercado : supermercados) {
                if (mercado.getNome().equals(input)) {
                    return mercado;
                }
            }
        }
        return null;
    }
}
