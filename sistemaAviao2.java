//Objetivo: sistema capaz de cadastrar voos e passageiros conseguindo alocar os passageiros aos voos

//Programador: Leonardo Mamede, 
//Data de criação: 15/11/21
//Ultima modificação: 26/11/21

import java.util.Scanner;

public class SistemaAviao2 {
	
	//declarando Scanner global para fácil acesso
	public static Scanner sc =new Scanner(System.in);
	//declarando Scanenr global para trabalhar com strings
	public static Scanner sc2 = new Scanner(System.in);
	
	//var global para delimitar quantidade máx de voos a serem cadastrados
	public static int tamanhoDosVetores=50;
	//var global para delimitar quantidade máx de Passageiros a serem cadastrados
	public static int qntDePassageirosCadastraveis= 50;
	//var global para delimitar quantidade de passageiro já cadastrados
	public static int qntDePassageirosCadastrados=0;
	//var global para delimitar quantidade de voos já cadastrados
	public static int qntDeVoosCadastrados=0;
	
	//Declarando 3 vetores globais de dados para representar as informações dos voos
	
	//Vetor que contem o codigo de cada voo; 
	public static int[] codigosDosVoos= new int[tamanhoDosVetores];
	
	//Vetor que contem a distancia de cada voo
	public static double[] distanciasDasViagens = new double[tamanhoDosVetores];
	
	//Vetor que contem a quantidade de assentos em cada voo
	public static int[] qntdDeAssentos = new int[tamanhoDosVetores];
	
	//Declarando vetores para dados Referentes ao passsageiros: 
	public static String[] nomesDosPassageiros= new String[qntDePassageirosCadastraveis]; 
	public static int[] codigosDosPassageiros = new int[qntDePassageirosCadastraveis];
	
	//Declarando matriz para alocamento de  passageiros nos Vôos
	public static boolean[][] voosAlocados = new boolean [qntDePassageirosCadastraveis][tamanhoDosVetores];
	
	//declarando vetor Para guardar a ocupação de cada voo
	public static int[] ocupacaoDosVoos= new int[tamanhoDosVetores];
	
	//declarando variavel global para armazenar a mediaDeOcupacaoDosvoos
	public static double mediaDeOcupacaoDosVoos=0;
	//declarando variavel global para armazenar a media de distancias dos voos
	public static double mediaDeDistanciaDosVoos=0;
	
	//Métodos para funcionamento do Sistema
	
	//método para cadastro de voos 
	public static void cadastrarVoos(int j) {
		//para relacionar as informações de cada voo em vetores diferentes
		//armazenamos cada valor referente a um voo na mesma posição nos 3 vetores
		//o que permite uma associação de que todos os dados na posição 0 dos 3 vetores correspondem a um unico voo e assim por diante
	
			int quantidadeDeAssentos=0, codigoDovoo=0;
			double distanciaViagem =0.0;
			
			System.out.println("Entre com o codigo do voo que deseja cadastrar: ");
			codigoDovoo = sc.nextInt();
			
			System.out.println("Entre com a quantidade de assentos do voo: ");
			quantidadeDeAssentos=sc.nextInt();
			
			System.out.println("Entre com a distancia a ser percorrida na viagem: ");
			distanciaViagem=sc.nextDouble();
			
			//verifica se já existe um voo com esse codigo
			if(buscarVoos(codigoDovoo)== -1) {
			//if para verificarção e validação das informações a serem cadastradas
				
				if(quantidadeDeAssentos >0 && distanciaViagem > 0 && quantidadeDeAssentos > 0 && codigoDovoo >0) {
					
					codigosDosVoos[j]= codigoDovoo;
					
					qntdDeAssentos[j]=quantidadeDeAssentos;
					
					distanciasDasViagens[j]=distanciaViagem;
	
					//espaçamento para saida
					System.out.println("");
					System.out.println("Cadastro do voo realizado com sucesso");
					System.out.println("");
					System.out.println("");
					
				}
				else {
					System.out.println("Verifique as informações erradas! ");
					qntDeVoosCadastrados--;
					
				}
			}
			else {
				System.out.println("======Codigo de voo já existente!!!======\n");
				qntDeVoosCadastrados--;
			}
		
	}
	
	//método para cadastro de passageiros
	public static void cadastrarPassageiros(int j) {

	//MESMA RELAÇÃO UTILIZADA PARA OS CADASTROS DE VOO OU SEJA UTILIZAMOS DOIS VETORES UM CONTENDO O CODIGO DOS PASSAGEIROS E OUTRO OS NOMES
	//RELACIONAMOS ELES FAZENDO COM QUE O SISTEMA ASSOCIE AS POSIÇOES ENTRE OS  VETORE PARA OS RESPECTIVOS CODIGO E NOME:
		int newCode=0;
		String nomePass="";
		//entrada de dados
		System.out.println("Entre com o nome do passageiro: ");
		nomePass=sc2.nextLine();
		
		System.out.println("Entre com um codigo referente ao passageiro que deseja cadastrar: (Apenas números Inteiros E maiores que 0): ");
		newCode=sc.nextInt();
		
		//verifica se já existe esse codigo cadastrado
		if(buscarPassageiro(newCode)==-1 && newCode>0)
			{
			codigosDosPassageiros[j]=newCode;
			nomesDosPassageiros[j] = nomePass;
			
			//espaçamento para saida
			System.out.println("");
			System.out.println("Cadastro do passageiro realizado com sucesso");
			System.out.println("");
			System.out.println("");
			}
		else {
			System.out.println("Codigo de Passageiro já existente ou codigo invalido");
			qntDePassageirosCadastrados--;
		}
		
		
	}
	
	//método para buscar posição do passageiro pelo codigo
	public static int buscarPassageiro(int codPass) {
		
	        for (int i = 0; i < codigosDosPassageiros.length; i++) {
	            if(codigosDosPassageiros[i] == codPass) {
	                return i;
	            }
	        }
	        return -1;
	    }
	
	//método para mostrar o nome de todos os passageiros de um voo
	
	public static void mostrarTodosPassageirosDeUmVoo(int codigoDoVoo) {
		
	int posicaoDoVoo =	buscarVoos(codigoDoVoo);
	
	
	if(posicaoDoVoo!= -1) {
		
	System.out.println("====== Voo "+codigosDosVoos[posicaoDoVoo]);
	
	for(int i=0; i<qntDePassageirosCadastraveis; i++) {
	
		if(	voosAlocados[i][posicaoDoVoo] == true) {
			
			System.out.println("\nPassageiro: "+codigosDosPassageiros[i]+"\nNome: "+nomesDosPassageiros[i]);
		}
		
	}
	
	}
	else {
		System.out.println("voo não existente");
	}
	
	}
	
	//método para pegar qual voo o passageiro está alocado
	
	public static int buscarVooDoPassageiro(int posicaoPassageiro) {
		
		for(int i=0; i< tamanhoDosVetores;i++) {
			if(voosAlocados[posicaoPassageiro][i]==true) {
				return i;
			}
			
		}
		
		return -1;
	}
	
	//método para buscar posição do voo no vetor de CodigosDosVoos
	public static int buscarVoos(int codeFly) {
	    for (int i = 0; i < codigosDosVoos.length; i++) {
            if(codigosDosVoos[i] == codeFly) {
                return i;
            }
        }
        return -1;
    
		
	}
	
	//método para verificar se passageiro já foi alocado a um voo
	public static int verificarPassageiroNosVoos(int posicaoPassageiro) {
		
	for(int j=0; j<tamanhoDosVetores; j++) {
		
		if(voosAlocados[posicaoPassageiro][j] == true) {
			
			return -1;
		}
			
	}
	return 1;
	
	}
	
	//método para exibir todos os voos
	public static void exibirTodosOsvoos() {
		
		//PERCORRE E MOSTRA OS TRES VETORES TRAZENDO A INFORMAÇÃO DE CADA VOO
		//ESSE IF GARANTE E FAZ O TRATAMENTO CASO NENHUM VOO TENHA SIDO CADASTRADO AINDA;
		if(qntdDeAssentos[0]!=0) {
			
			System.out.println("=============VOOS CADASTRADOS=========");
		for(int j=0; j<tamanhoDosVetores; j++) {
			
			if(codigosDosVoos[j] !=0) {
				
			System.out.println("\nVôo de codigo: "+codigosDosVoos[j]+"\nQuantidade Total de assentos: "+qntdDeAssentos[j]+"\nAssentos ocupados: "+ocupacaoDosVoos[j]+"\nDistancia: "+distanciasDasViagens[j]+"Km" );
			}
			else {
				
				System.out.println("");
			}
		}
		System.out.println(" ");	
		System.out.println(" ");	
		System.out.println(" ");
		System.out.println(" ");	
		}
		else {
			System.out.println("Não possui nenhum vôo cadastrado! ");
		}
		
		
	}
	
	//método para Alocar Passageiros aos voos
	public static void alocarPassageiros() {
		
		 int codigoDoPassageiro =0, codigoDoVoo=0;
		 
		 System.out.println("Entre com o codigo do passageiro que deseja alocar: ");
		 codigoDoPassageiro =sc.nextInt();
		 
		int posicaoPassageiro = buscarPassageiro(codigoDoPassageiro);
		 
		
		System.out.println("Entre com o codigo do voo a qual deseja alocar o passageiro: ");
		codigoDoVoo=sc.nextInt();
		
		
	
		int posicaoVoo = buscarVoos(codigoDoVoo);
		
		if(verificarPassageiroNosVoos(posicaoPassageiro)!= -1 && codigosDosPassageiros[0]!=0) {
			
			if(posicaoVoo!= -1 && codigoDoPassageiro!= -1) {
					
			
					voosAlocados[posicaoPassageiro][posicaoVoo] = true;
					
					
					System.out.println("Passageiro alocado com sucesso! ");
					
				
			}
			else {
				System.out.println("dados invalidos ou ocupação maxima atingida!!! porfavor verifique as informações");
			}
		}
		else {
			System.out.println("Passageiro já alocado em um voo! Se deseja realocar por favor exclua o passageiro e cadastre de novo!!");
		}	
		
	}
	
	//método para encontrar a quantidade de passsageiros cadastrados em cada voo e alucar na posição correspondente ao voo
	public static void buscarQuantidadeDePassageirosPorVoo(int flyCode){
		
		int qntPassageiros=0;
		
		
		for(int i=0; i<qntDePassageirosCadastraveis; i++) {
			
			
			if(voosAlocados[i][flyCode] == true) {
				
				qntPassageiros++;
			}
			
		}
		ocupacaoDosVoos[flyCode] = qntPassageiros;
		}
	
	//método modificador De nome de passageiros.
	public static void modificarNomePassageiro(int idPass, String novoNome){
		int posicaoDoPassageiro = buscarPassageiro(idPass);
		nomesDosPassageiros[posicaoDoPassageiro]= novoNome;
	}
	
	//método modificador De codigo do passageiro
	public static void modificarCodigoPassageiro(int idPass, int novoCodigo) {
		
		int posicaoDoPassageiro = buscarPassageiro(idPass);
		
		codigosDosPassageiros[idPass] = novoCodigo;
		
	}
	
	//método excluir passageiros
	public static void excluirPassageiros(int idPass) {
		
		if(idPass!=-1) {
			
			System.out.println("Apagando Passageiro: "+nomesDosPassageiros[idPass]);
			//retirando o passageiro do voo em que foi alocado
			
			int vooDoPassageiro = (idPass);
			if(vooDoPassageiro!=-1) {
				
				voosAlocados[idPass][vooDoPassageiro]=false;
				ocupacaoDosVoos[vooDoPassageiro]--;
			}
			//apagando dados do passageiro
			codigosDosPassageiros[idPass]= 0;
			nomesDosPassageiros[idPass] = "";
			//atualizando numeros de passageiros já cadastrados
			qntDePassageirosCadastrados--;
		}
		else {
			System.out.println("Passageiro não existente!");
		}
		
		
		
	}
	
	//método para Alterar dados do voo
	public static void alterarVoo (int idFly, int optMenu) {
		
		
		int posicaoVoo= buscarVoos(idFly);
		
		if(posicaoVoo!=-1) {
			
		
			switch(optMenu) {
			
			case 1:
				
				System.out.println("Entre com o novo codigo do voo: "+codigosDosVoos[posicaoVoo]);
				codigosDosVoos[posicaoVoo]=sc.nextInt();
				break;
			case 2:
				System.out.println("Entre com a nova quantidade de Assentos do voo: "+codigosDosVoos[posicaoVoo]);
				qntdDeAssentos[posicaoVoo]=sc.nextInt();
				break;
			case 3:
				System.out.println("Entre com a nova distancia de viagem do voo: "+codigosDosVoos[posicaoVoo]);
				distanciasDasViagens[posicaoVoo]=sc.nextDouble();
				break;
				
			default:
				System.out.println("Opção invalida!!");
			
			}
			
		}
		else {
			System.out.println("Codigo do voo invalido!!");
		}
		
		
		
	}
	
	//Método para excluir dados De um voo
	public static void excluirVoo(int idFly) {
		
		int posicaoVoo= buscarVoos(idFly);
		
		if(posicaoVoo != -1) {
			
			//retirando passageiros alocados do Voo
			for(int i=0; i<qntDePassageirosCadastraveis;i++) {
				
				voosAlocados[i][posicaoVoo]=false;
			}
			//excluindo informações do voo
			codigosDosVoos[posicaoVoo] = 0;
			distanciasDasViagens[posicaoVoo]=0.0;
			qntdDeAssentos[posicaoVoo]=0;
			//diminuido a qnt de voos Já cadastrados
			qntDeVoosCadastrados--;
			
			
			System.out.println("Voo excluido com sucesso");
			
		}
		else {
			System.out.println("Codigo de voo Invalido!!");
		}
		
	}
	
	//método para mostarVoos com mais ocupação de passageiros
	
	public static void mostrarVooComMaisPassageiros() {
		
	
	//Após buscar a quantidade de passageiros pra cada voo e armazenalas em um vetor de ocupacaoDosVoos
	//realizamos um calculo para pegar a media de ocupação entre os voos
	//para podermos apresentar apenas os voos iguais ou acima das médias de ocupação
	
		if(qntdDeAssentos[0]!=0 && codigosDosPassageiros[0] != 0 && ocupacaoDosVoos[0] != 0) {
				for(int x=0; x<tamanhoDosVetores;x++) {
					
					if(ocupacaoDosVoos[x] >= mediaDeOcupacaoDosVoos && ocupacaoDosVoos[x]!=0 && ocupacaoDosVoos[0] != 0) {
						
						System.out.println("\nVoo: "+codigosDosVoos[x]+"\nAssentos Ocupados: "+ocupacaoDosVoos[x]);
						
					}
				
				}
		}
		
		else {
			System.out.println("Nenhum voo ou passageiro cadastrado/alocado...");
		}
	}
	
	//método para mostarVoos com menos ocupação de passageiros
	public static void mostrarVoosComMenosPassageiros() {
		if(qntdDeAssentos[0]!=0 && codigosDosPassageiros[0] != 0 && ocupacaoDosVoos[0] != 0) {
			for(int x=0; x<tamanhoDosVetores;x++) {
				
				if(ocupacaoDosVoos[x] <= mediaDeOcupacaoDosVoos && ocupacaoDosVoos[x]!=0) {
					
					System.out.println("\nVoo: "+codigosDosVoos[x]+"\nAssentos Ocupados: "+ocupacaoDosVoos[x]);
					
				}
			
			}
		}
		else {
			System.out.println("Nenhum voo ou passageiro cadastrado/alocado...");
		}
	}
	
	//método para calcular a media entre as distancias dos voos cadastrados
	public static void mediaDeDistanciaDosVoos() {
		
		for(int i=0; i<tamanhoDosVetores; i++) {
		mediaDeDistanciaDosVoos+=distanciasDasViagens[i];
		}
		
		mediaDeDistanciaDosVoos=mediaDeDistanciaDosVoos/tamanhoDosVetores;
	}
	
	//método para mostrar voos com maiores distancias
	public static void mostrarVoosComMaioresDistancias() {
		
		if(qntdDeAssentos[0]!=0) {
		
			for(int i=0; i<tamanhoDosVetores; i++) {
				if( distanciasDasViagens[i] >= mediaDeDistanciaDosVoos && codigosDosVoos[i]!=0) {
					
					System.out.println("\nVoo : "+codigosDosVoos[i]+"\nDistancia : "+distanciasDasViagens[i]);
				}
			}
		}
		else {
			System.out.println("Nenhnum voo cadastrado...");
		}	
	}
	
	//método para mostrar voos com menores distancias
	public static void mostarVoosComMenoresDistancias() {
		if(qntdDeAssentos[0]!=0) {	
			for(int i=0; i<tamanhoDosVetores; i++) {
				if( distanciasDasViagens[i] < mediaDeDistanciaDosVoos && codigosDosVoos[i]!=0) {
				
					System.out.println("\nVoo : "+codigosDosVoos[i]+"\nDistancia : "+distanciasDasViagens[i]);
				}
			}
		}	
		else {
			System.out.println("Nenhum voo cadastrado...");
		}
	}
	
	//Método main
	public static void main(String[] args) {
		
		//Criando variavel do tipo inteira para receber a opção do menu para o switch case
		int optMenu=0;
	
		//Estrutura de repeticação para o programa rodar enquanto a opção de parada "optMenu" não for igual a 10(opção escolhida para saida do programa)
		
		while(optMenu!=10) {
			
		//Mostrando as opções do menu na tela para os Usuarios
			
			System.out.println("========================  Menu ========================");
			
			System.out.println("\nOpções>: \nCadastrar vôos (1)\nCadastrar Passageiros (2)\nAlocar Passageiro ao voo(3) \nVer vôos(4)\nVer Passageiros(5)\nAlterar um passageiro(6)\r\n"
					+ "Excluir passageiro(7)\r\n"
					+ "Alterar um vôo(8)\r\n"
					+ "Excluir vôo(9)\r\n"
					+ "Sair do Programa(10) ");
			
			System.out.println("Entre com a opção desejada: ");
			optMenu=sc.nextInt();
			
		//Menu principal feito com switch para os valores da variavel int optMenu.
			
		switch(optMenu) {
		//cadastrarVoos
		case 1:
			
			//int qntVooasAcadastrar representa quantos voos o usuario deseja cadastrar de uma vez 
			int qntVoosAcadastrar=0;
			//a variavel x serve como um contador dentro do while que está abaixo, permitindo a quantidaded de ciclos de execução do cadastro
			//de acordo com o que o usuario desejar
			int x=0;
			
			//entrada da quantidade de voos a cadastrar
			System.out.println("Entre com a quantidade de voos que deseja cadastrar: ");
			qntVoosAcadastrar=sc.nextInt();
			//fazemos a verifição se a quantidade de voos que o usuario deseja cadastrar de uma vez é maior do que a capacidade máxima de cadastro de voos
			if(qntVoosAcadastrar <=tamanhoDosVetores-qntDeVoosCadastrados && qntVoosAcadastrar>0)
			{	
				while(x<qntVoosAcadastrar) {
				
					//Verifica se a quantidade de voos já cadastrados no sistema é menor que o limite do sistema
				if(qntDeVoosCadastrados<tamanhoDosVetores ) {
					//chama o método cadastrarVoos passando como parametro a qntDeVoosCadastrados caso o cadastro ocorra em sucesso
					//e tambem para poder acessar a posição nos vetores dentro do método
				cadastrarVoos(qntDeVoosCadastrados);
				
				//quantidade de voos cadastrados é aumentada após o cadastro ser concluido
				qntDeVoosCadastrados++;
				
				//media da distancia de todos os voos cadastrados é calculada
				mediaDeDistanciaDosVoos();
				
				}
				
		
				else {
					System.out.println("Capacidade maxima de voos cadastrados!!");
					qntDeVoosCadastrados--;
					//caso 
				}
				 x++;
				
				}
			}
			else {
				System.out.println("\nEssa quantidade é maior do que a capacidade de cadastro!!!");
			}
			
			break;
			
		//Cadastrar Passageiros	
		case 2:
			//chama o método de cadastro de Passageiros
			int qntPassageirosAcadastrar=0, z=0;
			
			System.out.println("Entre com a quantidade de passageiros que deseja cadastrar: ");
			qntPassageirosAcadastrar=sc.nextInt();
			
			if(qntPassageirosAcadastrar<=qntDePassageirosCadastraveis-qntDePassageirosCadastrados && qntPassageirosAcadastrar>0) {
			
				while(z<qntPassageirosAcadastrar) {
				
					if(qntDePassageirosCadastrados<qntDePassageirosCadastraveis) {
						
					cadastrarPassageiros(qntDePassageirosCadastrados);
					qntDePassageirosCadastrados++;
					
					}
					else {
						System.out.println("Capacidade maxima de cadastro de passageiros atingida!!!");
						qntDePassageirosCadastrados--;
					}
					z++;
					
				}
			}
			else {
				System.out.println("Essa quantidade é maior do que a capacidade de cadastro!!!");
			}

				break;
				
			
		//Alocar Passageiros ao voo		
		case 3:
			//chama o método para alocamento de passageiros
			if(qntDeVoosCadastrados>0 && qntDePassageirosCadastrados>0) {
			alocarPassageiros();
			
			//Armazenando a quantidade de passageiros de cadaa voo em um vetor
			for(int j=0; j<tamanhoDosVetores; j++) {
				//o for serve para varrer todos os voos
				buscarQuantidadeDePassageirosPorVoo(j);
				}
			//realizando a conta para descobrir a média de ocupação de todos os voos
			for(int j=0; j<tamanhoDosVetores;j++) {
				
				
				mediaDeOcupacaoDosVoos += ocupacaoDosVoos[j];
			}
			
			
			mediaDeOcupacaoDosVoos = mediaDeOcupacaoDosVoos/tamanhoDosVetores;}
			else {
				System.out.println("Nenhum voo ou passageiro cadastrados!!!");
			}
			break;
				
		//Ver voos		
		case 4:
			
			//Criando variavel do tipo inteira para receber a opção do sub-menu para o switch case
			int optSubMenu=0;
			
			
		//Mostrando as opções do sub-menu na tela para os Usuarios
		
			
		System.out.println("========================  Ver Voos ========================");
			
			System.out.println("\nOpções>: \nVer  todos  os  vôos (1)\n Ver os vôos \r\n"
					+ "com mais passageiros (2)\n Vôos com menos passageiros(3)\nVer vôos com maior distância(4)\nVer vôos com menor distância(5)\r\n"
					+ "Ver ocupação média dos vôos(6)\r\n");
				
			
			System.out.println("Entre com uma opção: ");
			optSubMenu=sc.nextInt();
			
			switch(optSubMenu) {
			
			case 1:
				//chama método que exibe todos os voos 
				exibirTodosOsvoos();
				break;
			
			
			case 2:
				//chama método que exibe os voos com maiores numeros de passageiros alocados
				mostrarVooComMaisPassageiros();	
				break;
				
				
			case 3:
				///chama o metodo que exibe os voos com menor numero de passageiros alocados
				mostrarVoosComMenosPassageiros();
				break;
			
			case 4:
				//chama o método que exibe os voos com maiores distancias cadastradas
				mostrarVoosComMaioresDistancias();
				break;
			case 5:
				//chama o método que exibe os voos com menores distancias cadastradas
				mostarVoosComMenoresDistancias();
				break;
				
			case 6:
				if(qntdDeAssentos[0]!=0) {
				System.out.println("Media de Ocupação dos Voos:"+mediaDeOcupacaoDosVoos);
				}
				else {
					System.out.println("Nenhum voo cadastrado...");
				}
				break;
				
			default:
				System.out.println("Por favor entre com uma opção valida!!");
				
			}
			break;
			
		//Ver Passageiros		
		case 5:
			//declarando variaveis para o uso local
			int subOptmenu=0, idpassageiro=0, idVoo=0;
			
			System.out.println("Ver passageiro por codigo(1)\nVer todos os Passageiros de um voo(2)");
			subOptmenu=sc.nextInt();
				
			switch(subOptmenu) {
			//Ver passageiro por codigo
			case 1:
				System.out.println("Entre com o codigo do Passageiro que deseja procurar: ");
				idpassageiro=sc.nextInt();
				
				int passPosition = buscarPassageiro(idpassageiro);
				//verifica se o passageiro foi cadastrado pegando o resultado que retorna do método buscarPassageiro
				if(passPosition!= -1) {
				System.out.println("Passageiro de id: "+idpassageiro+"\nNome: "+nomesDosPassageiros[passPosition]);
				}
				else {
					
					System.out.println("Usuario não cadastrado...");
				}
				
				break;
				//Ver todos Passageiros de um voo
				case 2:
					System.out.println("Entre com o codigo do Voo que deseja ver a listagem de passageiros:  ");
					idVoo =sc.nextInt();
					
					mostrarTodosPassageirosDeUmVoo(idVoo);
					
					break;
			
			default:
			System.out.println("Opção Invalida!!!");
			}
		break;
		
		//Editar Passageiros		
		case 6:
			int idPass=0;
			String optionMenu="";
			
			System.out.println("entre com o codigo do passageiro que deseja alterar: ");
			idPass=sc.nextInt();
			
			System.out.println("deseja alterar nome(n) ou codigo (c) do passageiro? ");
			optionMenu=sc.next();
			
			optionMenu.toLowerCase();
			
			if(buscarPassageiro(idPass)!=-1) {
				
			if(optionMenu.equals("n")) {
			// caso a opção do submenu seja para mudar o nome 
			//declaramos a variavel name para receber o novo nome passar como parametro
					String name="";
					System.out.println("Entre com o novo nome");
					name=sc2.nextLine();
					//chamando a função para modificar o nome do passageiro passando como parametro o seu id
					modificarNomePassageiro(idPass, name);
		
					System.out.println("\nNome modificado com sucesso!!");
				}
				
				else if(optionMenu.equals("c")) {
					
					// caso a opção do submenu seja para mudar o codigo 
					//declaramos a variavel newCode para receber o novo codigo e passar como parametro para o metodo
					
						int newCode =0;
						System.out.println("Entre com um novo codigo: ");
						newCode=sc.nextInt();
					
						modificarCodigoPassageiro(idPass, newCode);
						System.out.println("\nCodigo modificado com sucesso!!");
				}
				else {
					System.out.println("Entre com uma opção valida!!");
				}
			
			}
			
			else {
				
				System.out.println("Codigo de passageiro invalido!");
			}
			
			
			break;		
			//Excluir Passageiros
		case 7:
			
			int idpassageiroo=0;
			
			System.out.println("entre Com o id do passageiro que deseja excluir: ");
			idpassageiro=sc.nextInt();
			
			int posicaoPass= buscarPassageiro(idpassageiro);
			//verifica se existem passageiros cadastrados
			if(codigosDosPassageiros[0] != 0) 
			{
			excluirPassageiros(posicaoPass);
			}
			else {
				System.out.println("Ainda não possui passageiros cadastrados!!!\n\n");
			}
			
			break;	
		//Alterar voos	
		case 8:
			
			int idFly=0;
			int optsMenu=0;
			//verifica se existem voos ja cadastrrados para então continuar com o menu
			if(codigosDosVoos[0]!=0) {
				
			System.out.println("Entre com o codigo do Voo que deseja alterar: ");
			idFly=sc.nextInt();	
			
			System.out.println("Editar codigo do voo(1)\nEditar quantidade de Assentos totais(2)\nEditar Distancia da viagem(3) ");
			optsMenu=sc.nextInt();
			
			alterarVoo(idFly, optsMenu);
			}
			else {
				System.out.println("Ainda não possui voos cadastrados!!!\n\n");
			}
			break;
			
		//Excluir Voos	
		case 9:
		int idFlys=0;
		//verifica se existem voos cadastrados para poder excluir
		if(codigosDosVoos[0]!=0) {
		//caso exista pedimos então o codigo do voo e passamos como parametro para o método de exclusão de voos
		System.out.println("Entre com o codigo do voo a ser excluido: ");
		idFlys=sc.nextInt();
			//chamando método excluirVoo com o parametro do voo a ser excluido
			excluirVoo(idFlys);
		}
		else {
			System.out.println("Ainda não possui voos cadastrados!!!\n\n");
		}
		break;
		//Sair	
		case 10:
			System.out.println("Saindo...");
			break;
			
		default:
			System.out.println("Opção Invalida!");		
		//fim do switch
		}
			
		//fim do While
		}
		
		//fechando os Scanners utilizados: 		
		sc.close();
		sc2.close();
		
		//fim do main
	}
	
//fim da classe
}
