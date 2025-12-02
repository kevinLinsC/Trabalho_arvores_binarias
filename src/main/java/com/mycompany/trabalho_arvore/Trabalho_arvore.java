/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalho_arvore;

import java.util.ArrayList;
import java.util.List;

import java.io.DataInputStream; // Usado para receber dados do usuário.
import java.io.IOException;

/**
 *
 * @author kevin
 */
public class Trabalho_arvore {
    
    // COLOCAR PARA NÃO EXECUTAR NADA SE NÃO TIVER UMA RAIZ.

    public static void main(String[] args) throws IOException {
        // Criando a arvore binaria com as funcionalidades de inserir, exclusão e busca.
        ArvorePessoas arvore = new ArvorePessoas();
        boolean rodando = true; // Usado para continuar ou parar o código.
        int opcao; // Opção do usuário.;
        
        //arvore.inserir(new NoPessoa("55555555555", "Ana", "Silva", "10/02/1990", 1.70, "Solteira", 0));
        //arvore.inserir(new NoPessoa("88888888888", "Bruno", "Souza", "09/01/1985", 1.80, "Casado", 2));
        //arvore.inserir(new NoPessoa("11111111111", "Carlos", "Lima", "05/07/2000", 1.75, "Solteiro", 0));
        //arvore.inserir(new NoPessoa("22222222222", "Carlos", "Lima", "05/07/2000", 1.75, "Solteiro", 0));
        //arvore.inserir(new NoPessoa("33333333333", "Carlos", "Lima", "05/07/2000", 1.75, "Solteiro", 0));
        
        
        while (rodando) {
            Linha();
            System.out.println("SISTEMA DE GERENCIAMENTO DE ARVORE BINARIA");
            Linha();
            
            // Opções para o usuário escolher.
            System.out.println("1 - Criar e Inserir nova pessoa (Novo No)");
            System.out.println("2 - Remover elemento");
            System.out.println("3 - Buscas");
            System.out.println("4 - Executar percursos (Em ordem, pos-ordem e pre-ordem)");
            System.out.println("5 - Calcular altura da arvore");
            System.out.println("6 - Calcular profundidade da arvore");
            System.out.println("7 - Sair");
            
            // Escolha do usuário.
            opcao = PegaNum("-> ", 1, 7, "Escolha um opcao valida.");
            
            // Se a arvore não possuir raiz, não deixa o usuário fazer remoção ou buscas.
            if (opcao != 1 && opcao != 6 && arvore.isVazia()) {
                Linha();
                System.out.println("ARVORE NAO POSSUI RAIZ. ESCOLHA A OPCAO '1'.");
                continue;
            }
            
            switch (opcao) {
                case 1 -> { // Inserir
                    NoPessoa NovoNo = criarNovoNoPessoa();
                    // Se a pessoa for diferente de null, insere na arvore.
                    if (NovoNo != null) {
                        // Se for cadadastrado uma nova pessoa, informa que a pessao foi inserida com sucesso.
                        if (arvore.inserir(NovoNo)) {
                            Linha();
                            System.out.println("PESSOA INSERIDA COM SUCESSO.");
                        }
                    }
                }
                
                case 2 -> { // Remoção
                    arvore.visualizarArvore(); // Visualização completa da arvore.
               
                    String cpfRemover = PegaCpf("Digite o CPF a ser removido: ");
                    arvore.remover(cpfRemover); // Removendo com base no cpf.
                    Linha();
                    System.out.println("REMOCAO EXECUTADA");
                }
                
                case 3 -> { // Buscas.
                    int opcaoBusca; // Opção do usuário para buscas.
                    boolean buscando = true; // Usado para continuar ou parar o código.
                    
                    while(buscando) {
                        arvore.visualizarArvore();
                        System.out.println("ESCOLHA UM TIPO DE BUSCA");
                        Linha();
                        opcaoBusca = PegaNum("1 - Busca simples por CPF\n2 - Mostrar caminho da Raiz ate um NO\n3 - Mostrar caminho entre dois Nos\n4 - Encontrar ancestral comum\n5 - Voltar\n-> ", 1, 5, "Digite uma opcao valida.");
                        Linha();
                        switch (opcaoBusca) {
                            case 1 -> { // Busca simples.
                                String cpfBusca = PegaCpf("Digite um cpf para ser buscado: ");
                                NoPessoa encontrado = arvore.buscarSimples(cpfBusca);
                                Linha();
                                if (encontrado != null) {
                                    System.out.println("Pessoa encontrada: " + encontrado);
                                } else {
                                    System.out.println("Pessoa com CPF " + cpfBusca + " nao encontrada.");
                                }
                            }
                            
                            case 2 -> { // Caminho raiz até nó.
                                String cpfAlvo = PegaCpf("Digite o CPF do no alvo: ");
                                
                                List<String> caminho = arvore.encontrarCaminho(cpfAlvo);
                                Linha();
                                
                                if (!caminho.isEmpty()) {
                                    System.out.println("Caminho da raiz até o no: " + cpfAlvo + ": ");
                                    System.out.println(caminho);
                                } else {
                                    System.out.println("CPF nao encontrado.");
                                }
                            }
                            
                            case 3 -> { // Caminho entre dois nós.
                                String cpf1 = PegaCpf("Digite o primeiro CPF: ");
                                String cpf2 = PegaCpf("Digite o segundo CPF: ");
                                
                                List<String> caminho = arvore.caminhoEntreDoisNos(cpf1, cpf2);
                                Linha();
                                
                                if (!caminho.isEmpty()) {
                                    System.out.println("Caminho entre os nos: " + cpf1 + ", " + cpf2 + ": ");
                                    System.out.println(caminho);
                                } else {
                                    System.out.println("Um ou ambos CPFs nao encontrados.");
                                }
                            }
                            
                            case 4 -> { // Ancestral comum.
                                String cpf1 = PegaCpf("Digite o CPF do primeiro no: ");
                                String cpf2 = PegaCpf("Digite o CPF do segundo no: ");
                                
                                NoPessoa ancestral = arvore.encontrarAncestral(cpf1, cpf2);
                                Linha();
                                
                                if (ancestral != null) {
                                    System.out.println("ANCESTRAL COMUM MAIS PROXIMO ENCONTRADO");
                                    Linha();
                                    System.out.println("Nos: " + cpf1 + " e " + cpf2);
                                    System.out.println("Ancestral: " + ancestral.getCpf() + " (" + ancestral.getNomeCompleto() + ")");
                                    
                                } else {
                                    System.out.println("Um ou ambos CPFs nao encontrados.");
                                }
                            }
                            
                            case 5 -> {
                                buscando = false;
                                System.out.println("SAINDO DA BUSCA...");
                            }
                        }
                    }
                }
                
                case 4 -> { // Percorre a arvore com o percurso escolhido.
                    System.out.println("Qual o percurso?");
                    Linha();
                    arvore.percorrer(PegaNum("1 - Em ordem\n2 - Pós-ordem\n3 - Pré-ordem\n-> ", 1, 3, "Digite uma opcao valida."));
                }
                
                case 5 -> { // Altura da arvore.
                    int altura = arvore.calcularAltura();
                    Linha();
                    if (altura != -1) {
                        System.out.println("A altura da arvore e: " + arvore.calcularAltura());
                    } else {
                        System.out.println("A arvore esta vazia.");
                    }
                    
                }
                
                case 6 -> { // Profundidade da arvore.
                    arvore.visualizarArvore();
                    String cpfAlvo = PegaCpf("Digite o CPF para medir a profundidade: ");
                    int profundidade = arvore.calcularProfundidade(cpfAlvo);
                    Linha();
                    if (profundidade >= 0) {
                        System.out.println("Profundidade do '" + cpfAlvo + "': " + profundidade);
                    } else {
                        System.out.println("CPF nao encontrado.");
                    }
                }
                
                case 7 -> { // Encerra o sistema.
                    rodando = false;
                    Linha();
                    System.out.println("SAINDO DO SISTEMA...");
                    Linha();
                }
                
                default -> {
                    System.out.println("Opcao invalida.");
                }
            }
        }
    }
    
    
    private static NoPessoa criarNovoNoPessoa() throws IOException {
        // Iniciando o DataInputStream. Usado para fazer perguntas.
        DataInputStream dado;
        dado = new DataInputStream(System.in);
        
        // Variaveis que irão ser retornadas em NoPessoa.
        String cpf;
        String nome;
        String sobrenome;
        String dataNascimento;
        double altura;
        String estadoCivil;
        int filhos;
        
        Linha();
        System.out.println("INSERCAO DE NOVO NO");
        Linha();
        
        // Pegando as informações da pessoa.
        cpf = PegaCpf("Digite o CPF: ");  
        
        System.out.print("Digite o nome: ");
        nome = dado.readLine();
        
        System.out.print("Digite o sobrenome: ");
        sobrenome = dado.readLine();
        
        System.out.print("Digite a data de nascimento: ");
        dataNascimento = dado.readLine();
        
        System.out.print("Digite o estado civil: ");
        estadoCivil = dado.readLine();
        
        altura = PegaFloat("Digite a altura: ", 0, 3, "Digite uma altura correta");
        
        filhos = PegaNum("Digite quantos filhos: ", 0, 20, "Digite uma quantidade de filhos válido.");
        
        // Retorna um NoPessoa para ser inserido na arvore.
        return new NoPessoa(cpf, nome, sobrenome, dataNascimento, altura, estadoCivil, filhos);
    }

    
    // Pessoa que será inserido na Arvore.
    public static class NoPessoa {
       // Usado como chave para ordenar na arvore.
       private String cpf;
       
       // Dados da pessoa.
       private String nome;
       private String sobrenome;
       private String dataNascimento;
       private double altura;
       private String estadoCivil;
       private int filhos;
       
       // Esquerda e direita para ser inserido na arvore.
       NoPessoa esquerda;
       NoPessoa direita;
       
       
       // Construtor com os dados da pessoa.
       public NoPessoa (String cpf, String nome, String sobrenome, String dataNascimento, double altura, String estadoCivil, int filhos) {
           this.cpf = cpf;
           this.nome = nome;
           this.sobrenome = sobrenome;
           this.dataNascimento = dataNascimento;
           this.altura = altura;
           this.estadoCivil = estadoCivil;
           this.filhos = filhos;
           this.esquerda = null;
           this.direita = null;
       }
       
       
       // Metodo para retornar o cpf. Serve para consulta.
       public String getCpf() {
           return cpf;
       }
       
       
       public String getNomeCompleto() {
           return (nome + " " + sobrenome);
       }
       
       
       // Setter usado para a remoção na arvore.
       public void setDados(NoPessoa dadosSucessor) {
            this.cpf = dadosSucessor.cpf;
            this.nome = dadosSucessor.nome;
            this.sobrenome = dadosSucessor.sobrenome;
            this.dataNascimento = dadosSucessor.dataNascimento;
            this.altura = dadosSucessor.altura;
            this.estadoCivil = dadosSucessor.estadoCivil;
            this.filhos = dadosSucessor.filhos;
       }
       
        
       @Override
       public String toString() {
           return String.format("| CPF: %s | Nome: %s %s | Altura: %.2fm | Estado Civil: %s | Filhos: %d", cpf, nome, sobrenome, altura, estadoCivil, filhos);
       }
    }
    
    
    // Funcionalidades da arvore binaria.
    public static class ArvorePessoas {
        // Primeira pessoa inserida, servindo como root.
        private NoPessoa raiz;
        
        
        // Retorna se estiver vazio.
        public boolean isVazia() {
            return this.raiz == null;
        }
        
        
        // Iniciando a raiz como nula.
        public ArvorePessoas() {
            this.raiz = null;
        }
        
        
        // Metodo para inserir a pessoa na arvore.
        public boolean inserir(NoPessoa novaPessoa) {
            String cpfNovo = novaPessoa.getCpf();
            
            // Se o cpf já estiver cadastrado na arvore informa uma mensagem de erro.
            if (buscarSimples(cpfNovo) != null) {
                Linha();
                System.out.println("CPF JA EXISTE NA ARVORE");
                return false;
            }
            // Passa a raiz para ser feita a comparação com novaPessoa. Fazendo com que seja inserido na ordem correta.
            this.raiz = inserirRecursivo(this.raiz, novaPessoa);
            return true;
        }
        
        
        private NoPessoa inserirRecursivo(NoPessoa noAtual, NoPessoa novaPessoa) {
            // Se não existir um noAtual, quer dizer que é a root.
            // Retorna imediatamente a novaPessoa como root.
            if (noAtual == null) {
                return novaPessoa;
            }
            
            // Faz a comparação para decidir se vai direita ou esquerda.
            int comparacao = novaPessoa.getCpf().compareTo(noAtual.getCpf());
            
            
            if (comparacao < 0) { // Se comparacao for menor que zero, quer dizer que o cpf é menor que o cpf da raiz de comparação.
                noAtual.esquerda = inserirRecursivo(noAtual.esquerda, novaPessoa); // A pessoa é inserida a esquerda.
            } else if (comparacao > 0) { // Se for maior que zero, quer dizer que o cpf é maior que o cpf da raiz de comparação.
                noAtual.direita = inserirRecursivo(noAtual.direita, novaPessoa); // A pessoa é inserida a direita.
            }
            
            return noAtual;
        }
        
        
        // Remover no.
        public void remover(String cpfAlvo) {
            this.raiz = removerRecursivo(this.raiz, cpfAlvo);
        }
        
        
        private NoPessoa removerRecursivo(NoPessoa noAtual, String cpfAlvo) {
            // Caso a arvore esteja vazia.
            if (noAtual == null) {
                System.out.println("Elemento nao encontrado.");
                return null;
            }
            
            // Procura o No a ser removido.
            int comparacao = cpfAlvo.compareTo(noAtual.getCpf());
            
            // Navegação.
            if (comparacao < 0) {
                // Se o alvo for menor, continua a esquerda.
                noAtual.esquerda = removerRecursivo(noAtual.esquerda, cpfAlvo);
            } else if (comparacao > 0) {
                // Se o alvo for maior, continua a direita.
                noAtual.direita = removerRecursivo(noAtual.direita, cpfAlvo);
            } else { // Nó foi encontrado.
                // Se não tiver nada na esquerda retorna a direita (0 ou 1 filho).
                if (noAtual.esquerda == null) {
                    return noAtual.direita;
                }
                // Se não tiver nada na direita retorna a esquerda (1 filho).
                if (noAtual.direita == null) {
                    return noAtual.esquerda;
                }
                
                // Se o no tiver dois filhos.
                // Busca o sucessor a direita.
                NoPessoa sucessor = encontrarMenor(noAtual.direita);
                
                // Substitui os dados do noAtual pelo sucessor.
                noAtual.setDados(sucessor);
                
                // Remove a subarvore da direita.
                noAtual.direita = removerRecursivo(noAtual.direita, sucessor.getCpf());
            }
            
            return noAtual;
        }
        
        
        // Metodo auxiliar para encontrar o No de menor valor em uma subarvore.
        private NoPessoa encontrarMenor(NoPessoa noAtual) {
            // Verifica se o no da esquerda é nulo.
            // Se for é o no mais a esquerda, que é o menor.
            if (noAtual.esquerda == null) {
                return noAtual; // Retorna o no atual.
            } 
            // Se ainda houver no a esquerda continua a busca.
            else {
                // Chama a função novamente, passando o no a esquerda.
                return encontrarMenor(noAtual.esquerda);
            }
        }
        
        
        // Visualizar arvore binaria completa.
        public void visualizarArvore() {
            LinhaFrufru();
            System.out.println("VISUALIZACAO COMPLETA DA ARVORE");
            LinhaFrufru();
           
            // Chama a função para visualizar a arvore completa, começando pela raiz e nivel 0.
            visualizarRecursivo(this.raiz, 0);
            Linha();
        }
        
        
        private void visualizarRecursivo(NoPessoa noAtual, int nivel) {
            // Se não tiver nada na arvore, retorna null.
            if (noAtual == null) {
                return;
            }
            
            // Começa escrevendo de cima (direita).
            visualizarRecursivo(noAtual.direita, nivel + 1);
            
            // Cria a indentação para adicionar espaços antes do no.
            // Simulando os níveis/profundidade.
            StringBuilder indentacao = new StringBuilder();
            for (int i = 0; i < nivel; i++) {
                indentacao.append("     ");
            }
            
            // Se o nivel for igual a zero, quer dizer que está no root.
            // Se não é um filho.
            String prefixo = (nivel == 0) ? "ROOT: " : "|-- ";
            
            // Escreve o noAtual, juntando a indentação, o prefixo e o nome da pessoa.
            System.out.println(indentacao.toString() + prefixo + noAtual.getCpf() + " (" + noAtual.getNomeCompleto() + ")");
            
            // Escreve a parte de baixo (esquerda).
            visualizarRecursivo(noAtual.esquerda, nivel + 1);
        }
        
        
        public void percorrer(int tipo) {
            // Switch para escolher o percurso.
            Linha();
            switch (tipo) {
                case 1 -> {
                    // Pré-Ordem: raiz -> esquerda -> direita
                    System.out.println("PERCURSO PRE-ORDEM");
                    Linha();
                    percorrerPreOrdem(this.raiz);
                }
                case 2 -> {
                    // Em Ordem: esquerda -> raiz -> direita
                    System.out.println("PERCURSO EM ORDEM");
                    Linha();
                    percorrerEmOrdem(this.raiz);
                }
                case 3 -> {
                    // Pós-Ordem: esquerda -> direita -> raiz
                    System.out.println("PERCURSO POS-ORDEM");
                    Linha();
                    percorrerPosOrdem(this.raiz);
                }
                default ->
                    System.out.println("Opcao invalida.");
            }
        }
        
        
        private void percorrerPreOrdem(NoPessoa noAtual) {
            // Se o noAtual for diferente de nulo, quer dizer que existe alguma pessoa cadastrada.
            // Percorre a arvore em pre-ordem
            if (noAtual != null) {
                System.out.println(noAtual);
                percorrerPreOrdem(noAtual.esquerda);
                percorrerPreOrdem(noAtual.direita);
            }
        }
        
        
        private void percorrerEmOrdem(NoPessoa noAtual) {
            // Se o noAtual for diferente de nulo, quer dizer que existe alguma pessoa cadastrada.
            // Percorre a arvore em ordem
            if (noAtual != null) {
                percorrerEmOrdem(noAtual.esquerda);
                System.out.println(noAtual);
                percorrerEmOrdem(noAtual.direita);
            }
        }
        
        
        private void percorrerPosOrdem(NoPessoa noAtual) {
            // Se o noAtual for diferente de nulo, quer dizer que existe alguma pessoa cadastrada.
            // Percorre a arvore em pos-ordem
            if (noAtual != null) {
                percorrerPosOrdem(noAtual.esquerda);
                percorrerPosOrdem(noAtual.direita);
                System.out.println(noAtual);
            }
        }
        
        
        // Buscar pessoa por cpf.
        public NoPessoa buscarSimples(String cpfAlvo) {
            return buscarSimplesRecursivo(this.raiz, cpfAlvo);
        }
        
        
        private NoPessoa buscarSimplesRecursivo(NoPessoa noAtual, String cpfAlvo) {
            // Se o no atual for nulo, retorn nulo.
            if (noAtual == null) {
                return null;
            }
            
            // Compara o cpf alvo o no atual.
            int comparacao = cpfAlvo.compareTo(noAtual.getCpf());
            
            // Se for zero, o cpf alvo foi encontrado no No atual.
            if (comparacao == 0) {
                return noAtual;
            } 
            // Se a comparacao for menor que zero, o cpf alvo é menor que o cpf atual.
            // A busca continua na subarvore esquerda.
            else if (comparacao < 0) {
                return buscarSimplesRecursivo(noAtual.esquerda, cpfAlvo);
            } 
            // Se a comparacao for maior que zero, o cpf alvo é maior que o cpf atual.
            // A busca continua na subarvore direita.
            else {
                return buscarSimplesRecursivo(noAtual.direita, cpfAlvo);
            }
        }
        
        
        // Encontra caminho
        public List<String> encontrarCaminho(String cpfAlvo) {
            // Lista que armezenará o caminho.
            List<String> caminho = new ArrayList<>();
            
            // Chama o metodo, começa pela raiz da arvore.
            encontrarCaminhoRecursivo(this.raiz, cpfAlvo, caminho);
            
            // Retorna a lista contendo o caminho.
            return caminho;
        }
        
        
        private boolean encontrarCaminhoRecursivo(NoPessoa noAtual, String cpfAlvo, List<String> caminho) {
            // Se o noAtual for nulo, não encontrou o no.
            if (noAtual == null) {
                return false;
            }
            
            // Adiciona o cpf do no atual ao caminho.
            caminho.add(noAtual.getCpf());
            
            // Se o cpf do no atual for igual ao cpfAlvo, Retorna true.
            // Para a execução.
            if (noAtual.getCpf().equals(cpfAlvo)) {
                return true;
            }
            
            // Tenta encontrar o cpfAlvo na subarvore esquerda ou direita, retorna true.
            // Para a execução.
            if (encontrarCaminhoRecursivo(noAtual.esquerda, cpfAlvo, caminho) || encontrarCaminhoRecursivo(noAtual.direita, cpfAlvo, caminho)) {
                return true;
            }
            
            // Retorna false caso o caminho estiver vazio.
            caminho.remove(caminho.size() - 1);
            return false;
        }
        
        
        // Encontra no ancestral.
        public NoPessoa encontrarAncestral(String cpf1, String cpf2) {
            // Chama o metodo, começando pela raiz.
            return encontrarAncestralRecursivo(this.raiz, cpf1, cpf2);
        }
        
        
        private NoPessoa encontrarAncestralRecursivo(NoPessoa noAtual, String cpf1, String cpf2) {
            // Se o noAtual for nulo, retorna null.
            if (noAtual == null) {
                return null;
            }
            // Compara os cpf com o noAtual.
            int comp1 = cpf1.compareTo(noAtual.getCpf());
            int comp2 = cpf2.compareTo(noAtual.getCpf());
            
            // Se ambos os CPFs forem maiores que o no atual, o ancestral deve estar na subarvore direita.
            if (comp1 > 0 && comp2 > 0) {
                return encontrarAncestralRecursivo(noAtual.direita, cpf1, cpf2);
            }
            
            // Se ambos os CPFs forem menores que o no atual, o ancestral deve estar na subarvore esquerda.
            if (comp1 < 0 && comp2 < 0) {
                return encontrarAncestralRecursivo(noAtual.esquerda, cpf1, cpf2);
            }
            
            // Se nenhuma condição for atendidade, o noAtual é o ancestral.
            return noAtual;
        }
        
        
        // Encontra o caminho entre dois nos distintos.
        public List<String> caminhoEntreDoisNos(String cpf1, String cpf2) {
            // Encontra o ancestral comum mais próximo.
            NoPessoa ancestral = encontrarAncestral(cpf1, cpf2);
            
            // Se não existir ancestral comum, retorna uma lista vazia.
            if (ancestral == null) {
                return new ArrayList<>();
            }
            
            // Guarda o caminho do primeiro cpf.
            List<String> caminho1 = encontrarCaminho(cpf1);
            // Guarda o caminho do segundo cpf.
            List<String> caminho2 = encontrarCaminho(cpf2);
            
            // Lista que contera o caminho inteiro.
            List<String> caminhoFinal = new ArrayList<>();
            
            // Indices dos caminhos.
            int indexAncestral1 = caminho1.indexOf(ancestral.getCpf());
            int indexAncestral2 = caminho2.indexOf(ancestral.getCpf());
            
            // Primeiro caminho. Subido de cpf1 até o ancestral.
            // Começa em cpf1, que é o último indice e vai até o ancestral.
            // Adiciona em ordem inversa. Simulando a subida da arvore.
            for (int i = caminho1.size() -1; i >= indexAncestral1; i--) {
                caminhoFinal.add(caminho1.get(i));
            }
            
            // Segundo caminho. Descida do ancestral até o cpf2.
            // Começa depois do ancestral, para evitar duplicidade, e vai até o cpf2.
            for (int i = indexAncestral2 + 1; i < caminho2.size(); i++) {
                caminhoFinal.add(caminho2.get(i));
            }
            
            // Retorna o caminho final.
            return caminhoFinal;
        }       
        
        
        // Calcula a altura da arvore.
        public int calcularAltura() {
            return calcularAlturaRecursivo(this.raiz);
        }
        
        
        private int calcularAlturaRecursivo(NoPessoa noAtual) {
            // Se o no atual for nulo, chegou a um ramo vazio, a altura da subarvore é -1.
            // Ex.: Caso a raiz não possua subarvore, retorna -1 para direita e esquerda. 
            // Ocasiando em Math.max(-1, -1) + 1 = 0.
            if (noAtual == null) {
                return -1;
            }
            
            // Calcula a altura da subarvore esquerda.
            int alturaEsquerda = calcularAlturaRecursivo(noAtual.esquerda);
            // Calcula a altura da subarvore direita.
            int alturaDireita = calcularAlturaRecursivo(noAtual.direita);
            
            // A altura é o maior valor.
            return Math.max(alturaEsquerda, alturaDireita) + 1;
        }
        
        
        // Calcula a profundidade da arvore.
        public int calcularProfundidade(String cpfAlvo) {
            return calcularProfundidadeRecursivo(this.raiz, cpfAlvo, 0);
        }
        
        
        private int calcularProfundidadeRecursivo(NoPessoa noAtual, String cpfAlvo, int profundidadeAtual) {
            // No não existe.
            if (noAtual == null) {
                return -1;
            }
            
            // Compara o cpfAlvo com o cpf do noAtual.
            // Faz a navegação por toda a arvore.
            int comparacao = cpfAlvo.compareTo(noAtual.getCpf());
            
            // cpfAlvo foi encontrado, retorna a profundidade atual.
            if (comparacao == 0) {
                return profundidadeAtual;
            } 
            // Se a comparacao for menor que zero, indica que o cpfAlvo está na esquerda.
            // Chama a função novamente e acrescenta +1 a profundidade.
            else if (comparacao < 0) {
                return calcularProfundidadeRecursivo(noAtual.esquerda, cpfAlvo, profundidadeAtual + 1);
            } 
            // Se a comparacao for maior que zero, indica que o cpfAlvo está na direita.
            // Chama a função novamente e acrescente + 1 a profundidade.
            else {
                return calcularProfundidadeRecursivo(noAtual.direita, cpfAlvo, profundidadeAtual + 1);
            }    
        }  
    }
    
    // FUNÇÕES AUXILIARES
    
    // Recebe um texto para ser usado como pergunta.
    // Retorna o cpf válido.
    public static String PegaCpf(String texto) throws IOException {
        DataInputStream dado; // Será usado para fazer pergunta ao usuário
        String cpf; // String com a resposta do usuário.
        
        // Repete até o usuário digitar uma String válida.
        while (true) {
            System.out.print(texto); // Texto que será usado como pergunta.
            dado = new DataInputStream(System.in); // Inicia o DataInputStream.
            cpf = dado.readLine(); // Faz a pergunta ao usuário, salvando uma String como resposta.
            
            char caracter; // Será usado para pegar cada caracter separado em cpf.
            int numCPF; // Será usado para converter o 'caracter' em número. Fazendo a verificação se todos os caracteres são números. 
            boolean cpfValido;
            
            // Se o cpf não tiver 11 números volta para o ínicio do loop.
            if (cpf.length() != 11) {
                Linha();
                System.out.println("CPF deve conter 11 digitos.");
                Linha();
                continue;
            }
            
            cpfValido = true;
            
            // Pega cade caracter do cpf e verifica se é um número.
            for (int i = 0; i < cpf.length(); i++) {
                caracter = cpf.charAt(i); // Cada caracter do cpf.
                numCPF = Character.getNumericValue(caracter); // Converte para seu valor número.
                
                // Se o valor número não estiver entre 0 e 9. Ele não é um número.
                if (numCPF < 0 || numCPF > 9) {
                    Linha();
                    System.out.println("CPF deve ser composto somente por numeros.");
                    Linha();
                    cpfValido = false;
                    break;
                }
            }
            
            // Se o cpf for válido sai do loop.
            if (cpfValido) {
               break; 
            }
        }
        
        // Retorna o cpf.
        return cpf;
    }
    
    
    // Recebe um texto para ser usado como pergunta, numero minimo e maximo, e um texto de erro.
    // Retorna um número inteiro, filtrando o erro de conversão de String para Integer.
    public static int PegaNum(String texto, int min, int max, String erro) throws IOException {
        DataInputStream dado; // Será usado para fazer pergunta ao usuário
        String s; // String com a resposta do usuário.
        int num; // Número filtrado pelo sistema.
        
        // Repete até o usuário digitar um número válido.
        while (true) {
            System.out.print(texto); // Texto que será usado como pergunta.
            dado = new DataInputStream(System.in); // Inicia o DataInputStream.
            s = dado.readLine(); // Faz a pergunta ao usuário, salvando uma String como resposta.
            
            // Tenta converter a String para Integer.
            // Caso consiga converter, é sinal que o usuário digitou um número. Terminando o loop.
            // Caso haja algum erro, mostra a mensagem de erro e repete o loop. 
            try {
                num = Integer.parseInt(s);
                if (num >= min && num <= max) {
                    break;
                }
                else {
                    Linha();
                    System.out.println(erro);
                    Linha();
                }
            }
            catch (NumberFormatException e) {
                Linha();
                System.out.println("Digite somente numeros.");
                Linha();
            }
        } 
        
        return num; // Retorna o número.
    }
    
    
    // Recebe um texto para ser usado como pergunta, número mínimo e máximo (em double), e um texto de erro.
    // Retorna um número com ponto flutuante (double), filtrando o erro de conversão de String para Double.
    public static double PegaFloat(String texto, double min, double max, String erro) throws IOException {
        DataInputStream dado; // Será usado para fazer pergunta ao usuário
        String s; // String com a resposta do usuário.
        double num; // Número com ponto flutuante filtrado pelo sistema.

        // Repete até o usuário digitar um número de ponto flutuante válido.
        while (true) {
            System.out.print(texto); // Texto que será usado como pergunta.
            dado = new DataInputStream(System.in); // Inicia o DataInputStream.
            s = dado.readLine(); // Faz a pergunta ao usuário, salvando uma String como resposta.

            // Tenta converter a String para Double.
            try {
                // Convertendo para double.
                num = Double.parseDouble(s); 

                // Verifica se o número está dentro dos limites.
                if (num >= min && num <= max) {
                    break; // Número válido, sai do loop.
                }
                else {
                    Linha();
                    System.out.println(erro); // Mensagem de erro de limite.
                    Linha();
                }
            }
            catch (NumberFormatException e) {
                Linha();
                System.out.println("Digite somente numeros. Use o formato correto (Ex.: 1.70 ou 1,70).");
                Linha();
            }
        }

        return num; // Retorna o número double.
    }
    
    
    // Escreve uma linha para separação.
    public static void Linha() {
        System.out.println("----------------------------------------");
    }
    
    
    // Escreve uma linha para separação.
    public static void LinhaFrufru() {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
    }
}