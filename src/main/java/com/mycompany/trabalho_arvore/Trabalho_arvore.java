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
        int opcao; // Opção do usuário.
        // Iniciando o DataInputStream. Usado para fazer perguntas.
        DataInputStream dado;
        dado = new DataInputStream(System.in);
        
        //arvore.inserir(new NoPessoa("555", "Ana", "Silva", "10/02/1990", 1.70, "Solteira", 0));
        //arvore.inserir(new NoPessoa("888", "Bruno", "Souza", "09/01/1985", 1.80, "Casado", 2));
        //arvore.inserir(new NoPessoa("111", "Carlos", "Lima", "05/07/2000", 1.75, "Solteiro", 0));
        //arvore.inserir(new NoPessoa("222", "Carlos", "Lima", "05/07/2000", 1.75, "Solteiro", 0));
        //arvore.inserir(new NoPessoa("121", "Carlos", "Lima", "05/07/2000", 1.75, "Solteiro", 0));
        
        
        while (rodando) {
            Linha();
            System.out.println("SISTEMA DE GERENCIAMENTO DE ARVORE BINARIA");
            Linha();
            
            // Opções para o usuário escolher.
            System.out.println("1 - Criar e Inserir nova pessoa (Novo No)");
            System.out.println("2 - Remover elemento");
            System.out.println("3 - Buscar pessoa por CPF");
            System.out.println("4 - Executar percursos (Em ordem, pos-ordem e pre-ordem)");
            System.out.println("5 - Calcular altura da arvore");
            System.out.println("6 - Sair");
            Linha();
            
            
            // arvore.visualizarArvore();
            
            // Escolha do usuário.
            opcao = PegaNum("-> ", 1, 6, "Escolha um opcao valida.");
            
            // Se a arvore não possuir raiz, não deixa o usuário fazer remoção ou buscas.
            if (opcao != 1 && opcao != 6 && arvore.isVazia()) {
                Linha();
                System.out.println("Arvore nao possui raiz. Escolha a opcao '1'.");
                continue;
            }
            
            switch (opcao) {
                // Pronto.
                case 1 -> {
                    NoPessoa NovoNo = criarNovoNoPessoa();
                    // Se a pessoa for diferente de null, insere na arvore.
                    if (NovoNo != null) {
                        arvore.inserir(NovoNo);
                        Linha();
                        System.out.println("PESSOA INSERIDA COM SUCESSO.");
                    }
                }
                
                // Pronto.
                case 2 -> {
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
                        System.out.println("ESCOLHA UM TIPO DE BUSCA");
                        Linha();
                        opcaoBusca = PegaNum("1 - Busca simples por CPF\n2 - Mostrar caminho da Raiz ate um NO\n3 - Mostrar caminho entre dois Nos\n4 - Encontrar ancestral comum\n5 - Voltar", 1, 5, "Digite uma opcao valida.");
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
                                
                            }
                        }
                    }
                }
                
                // Pronto.
                case 4 -> { // Percorre a arvore com o percurso escolhido.
                    System.out.println("Qual o percurso?");
                    Linha();
                    arvore.percorrer(PegaNum("1 - Em ordem\n2 - Pós-ordem\n3 - Pré-ordem\n-> ", 1, 3, "Digite uma opcao valida."));
                }
                
                case 5 -> { // Altura da arvore.
                    System.out.println("A altura da arvore e: " + arvore.calcularAltura());
                }
                
                // Pronto.
                case 6 -> { // Encerra o sistema.
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
    
    
    // Pronto.
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

    
    // Pronto.
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
           return String.format("| CPF: %s | Nome: %s %s | Altura: %.2fm |", cpf, nome, sobrenome, altura);
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
        
        
        // Pronto.
        // Iniciando a raiz como nula.
        public ArvorePessoas() {
            this.raiz = null;
        }
        
        
        // Pronto.
        // Metodo para inserir a pessoa na arvore.
        public void inserir(NoPessoa novaPessoa) {
            // Passa a raiz para ser feita a comparação com novaPessoa. Fazendo com que seja inserido na ordem correta.
            this.raiz = inserirRecursivo(this.raiz, novaPessoa);
        }
        
        
        // Pronto.
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
            } else { // Se o cpf for o mesmo, retorna um erro.
                throw new IllegalArgumentException("ERRO: CPF " + novaPessoa.getCpf() + " já existe na árvore.");
            }
            
            return noAtual;
        }
        
        
        public void remover(String cpfAlvo) {
            this.raiz = removerRecursivo(this.raiz, cpfAlvo);
        }
        
        
        // Pronto.
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
        
        
        // Pronto.
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
        
        
        // Pronto.
        // Visualizar arvore binaria completa.
        public void visualizarArvore() {
            System.out.println("VISUALIZACAO COMPLETA DA ARVORE");
            Linha();
           
            // Chama a função para visualizar a arvore completa, começando pela raiz e nivel 0.
            visualizarRecursivo(this.raiz, 0);
            Linha();
        }
        
        
        // Pronto.
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
        
        
        // Pronto.
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
            Linha();
        }
        
        
        // Pronto.
        private void percorrerPreOrdem(NoPessoa noAtual) {
            // Se o noAtual for diferente de nulo, quer dizer que existe alguma pessoa cadastrada.
            // Percorre a arvore em pre-ordem
            if (noAtual != null) {
                System.out.println(noAtual);
                percorrerPreOrdem(noAtual.esquerda);
                percorrerPreOrdem(noAtual.direita);
            }
        }
        
        
        // Pronto.
        private void percorrerEmOrdem(NoPessoa noAtual) {
            // Se o noAtual for diferente de nulo, quer dizer que existe alguma pessoa cadastrada.
            // Percorre a arvore em ordem
            if (noAtual != null) {
                percorrerEmOrdem(noAtual.esquerda);
                System.out.println(noAtual);
                percorrerEmOrdem(noAtual.direita);
            }
        }
        
        
        // Pronto.
        private void percorrerPosOrdem(NoPessoa noAtual) {
            // Se o noAtual for diferente de nulo, quer dizer que existe alguma pessoa cadastrada.
            // Percorre a arvore em pos-ordem
            if (noAtual != null) {
                percorrerPosOrdem(noAtual.esquerda);
                percorrerPosOrdem(noAtual.direita);
                System.out.println(noAtual);
            }
        }
        
        // Pronto.
        // Buscar pessoa por cpf.
        public NoPessoa buscarSimples(String cpfAlvo) {
            return buscarSimplesRecursivo(this.raiz, cpfAlvo);
        }
        
        
        // Pronto.
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
        
        
        public int calcularAltura() {
            return calcularAlturaRecursivo(this.raiz);
        }
        
        
        private int calcularAlturaRecursivo(NoPessoa noAtual) {
            if (noAtual == null) {
                return -1;
            }
            
            int alturaEsquerda = calcularAlturaRecursivo(noAtual.esquerda);
            int alturaDireita = calcularAlturaRecursivo(noAtual.direita);
            
            return Math.max(alturaEsquerda, alturaDireita) + 1;
        }
        
        
        public int calcularProfundidade(String cpfAlvo) {
            return calcularProfundidadeRecursivo(this.raiz, cpfAlvo, 0);
        }
        
        
        private int calcularProfundidadeRecursivo(NoPessoa noAtual, String cpfAlvo, int profundidadeAtual) {
            if (noAtual == null) {
                return -1;
            }
            
            int comparacao = cpfAlvo.compareTo(noAtual.getCpf());
            
            if (comparacao == 0) {
                return profundidadeAtual;
            } else if (comparacao < 0) {
                return calcularProfundidadeRecursivo(noAtual.esquerda, cpfAlvo, profundidadeAtual + 1);
            } else {
                return calcularProfundidadeRecursivo(noAtual.direita, cpfAlvo, profundidadeAtual + 1);
            }    
        }
        
        
        public List<String> encontrarCaminho(String cpfAlvo) {
            List<String> caminho = new ArrayList<>();
            
            encontrarCaminhoRecursivo(this.raiz, cpfAlvo, caminho);
            return caminho;
        }
        
        
        private boolean encontrarCaminhoRecursivo(NoPessoa noAtual, String cpfAlvo, List<String> caminho) {
            if (noAtual == null) {
                return false;
            }
            
            caminho.add(noAtual.getCpf());
            
            if (noAtual.getCpf().equals(cpfAlvo)) {
                return true;
            }
            
            if (encontrarCaminhoRecursivo(noAtual.esquerda, cpfAlvo, caminho) || encontrarCaminhoRecursivo(noAtual.direita, cpfAlvo, caminho)) {
                return true;
            }
            
            caminho.remove(caminho.size() - 1);
            return false;
        }
        
        
        public NoPessoa encontrarAncestral(String cpf1, String cpf2) {
            return encontrarAncestralRecursivo(this.raiz, cpf1, cpf2);
        }
        
        
        private NoPessoa encontrarAncestralRecursivo(NoPessoa noAtual, String cpf1, String cpf2) {
            if (noAtual == null) {
                return null;
            }
            
            int comp1 = cpf1.compareTo(noAtual.getCpf());
            int comp2 = cpf2.compareTo(noAtual.getCpf());
            
            if (comp1 > 0 && comp2 > 0) {
                return encontrarAncestralRecursivo(noAtual.direita, cpf1, cpf2);
            }
            
            if (comp1 < 0 && comp2 < 0) {
                return encontrarAncestralRecursivo(noAtual.esquerda, cpf1, cpf2);
            }
            
            return noAtual;
        }
        
        
        public List<String> caminhoEntreDoisNos(String cpf1, String cpf2) {
            NoPessoa ancestral = encontrarAncestral(cpf1, cpf2);
            
            if (ancestral == null) {
                return new ArrayList<>();
            }
            
            List<String> caminho1 = encontrarCaminho(cpf1);
            List<String> caminho2 = encontrarCaminho(cpf2);
            
            List<String> caminhoFinal = new ArrayList<>();
            int indexAncestral1 = caminho1.indexOf(ancestral.getCpf());
            int indexAncestral2 = caminho2.indexOf(ancestral.getCpf());
            
            for (int i = caminho1.size() -1; i >= indexAncestral1; i--) {
                caminhoFinal.add(caminho1.get(i));
            }
            
            for (int i = caminho2.size() + 1; i < indexAncestral2; i++) {
                caminhoFinal.add(caminho2.get(i));
            }
            
            return caminhoFinal;
        }         
    }
    
    
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
}