package trab2renato2;


//Trabalho feito por Ryan Mont Serrat da Cunha
//Matricula: 1211538WEB

import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;



public class Main {

	public static void main(String[] args) throws Exception 
	{	
	    Scanner scanner = new Scanner(System.in);  
        int t_bloco, x = 0;
	    
	    RandomAccessFile f = new RandomAccessFile("cep.dat", "r"); //para ler o cep desordenado
	    RandomAccessFile w = new RandomAccessFile("cep_bloco.dat", "rw"); //para gerar o cep ordenado em bloco
		
	    Endereco e = new Endereco();
	    Endereco t1 = new Endereco();
	    
		//seta o tamanho do bloco
	    System.out.println("Digite o tamanho do bloco que pretende organizar:");
	    t_bloco=scanner.nextInt();
	    x = t_bloco;
	    
	    //le o numero de registros no arquivo
	    System.out.println("Você está separando e ordenando os registros em blocos de " + x);
	    
	    int counter = 0;
	    String line;
	    
	    while((line = f.readLine()) != null)
	    {
	        counter++; 
	    }
	    
	    f.seek(0);
	    
	    System.out.println("Número de registros no arquivo: " + counter);
	    
	    
	    //TRECHO PRA ARRUMAR O ULTIMO BLOCO
	    int blocos_inteiros, ultimo_bloco = 0;
	    int numero_de_blocos;
	   
	    blocos_inteiros = counter/t_bloco;
	    ultimo_bloco = counter % t_bloco;
	    
       
	    System.out.println("número de blocos inteiros: "  + blocos_inteiros);
	    System.out.println("número de linhas do último bloco: " + ultimo_bloco);
	    
	    boolean leultimo = false;
	    
	    numero_de_blocos = blocos_inteiros;
	    
	    if(ultimo_bloco != 0)
	    {
	    	leultimo = true;
	    	numero_de_blocos = blocos_inteiros + 1;
	    }
	    
	    System.out.println("numero_de_blocos: "  + numero_de_blocos);
	    
	    
	    List<Endereco> listEndereco = new ArrayList<Endereco>(t_bloco+1);
	    Endereco t2 = new Endereco();
	    
	    
	    counter = 0;
	    int counter2 = 1;
	    
	    boolean flag = true;
	    
	    
	    //trata todos os blocos inteiros
	    for(int j = 0; j < blocos_inteiros; j = j + 1) {
	    
	    System.out.println("________BLOCO: " + (j+1) +  "__________");
	    w.writeBytes("________BLOCO: " + (j+1) +  "__________" + "\n" );
	 	    
		    for(int i = 0; i < x; i = i + 1) {
		    	Endereco t3 = new Endereco();
		    	t3.leEndereco(f);
		    	listEndereco.add(t3);
	
				System.out.println("Gerando bloco no .dat");
				
	
		     }
		     System.out.println("__________________");
		     
		     //Ordena
	         Collections.sort(listEndereco, Endereco.COMPARE_BY_CEP);
	         
	         //Printa no arquivo
	         for(int i = 0; i < x; i = i + 1) {
	        	 w.writeBytes( "CEP: "  + listEndereco.get(i).getCep() 
			 			 + "/Rua: " + listEndereco.get(i).getLogradouro()
			 			 + "/Bairro: " + listEndereco.get(i).getBairro() 
			 			 + "/Cidade: " + listEndereco.get(i).getCidade()
			 			 + "/Sigla Estado: " + listEndereco.get(i).getSigla()
			 			 + "\n" );
	        	 
	         }
	         
	         listEndereco.clear();
	    
	    }
	    
	    //trata a ultima linha
	    if(leultimo)
	    {
	    	
	    	System.out.println("________BLOCO: " + (numero_de_blocos) +  "__________");
	 	    w.writeBytes("________BLOCO: " + (numero_de_blocos) +  "__________" + "\n" );
	 	    
	    	for(int i = 0; i < ultimo_bloco; i = i + 1) {
		    	Endereco t3 = new Endereco();
		    	t3.leEndereco(f);
		    	listEndereco.add(t3);
	
				System.out.println("CEP: " + (i+1) + " "  + listEndereco.get(i).getCep() + listEndereco.get(i).getSigla());
				
	
		     }
	    
	         Collections.sort(listEndereco, Endereco.COMPARE_BY_CEP);
	         
	         for(int i = 0; i < ultimo_bloco; i = i + 1) {
	        	 w.writeBytes( "CEP: " + listEndereco.get(i).getCep() 
	        			 			 + "/Rua: " + listEndereco.get(i).getLogradouro()
	        			 			 + "/Bairro: " + listEndereco.get(i).getBairro() 
	        			 			 + "/Cidade: " + listEndereco.get(i).getCidade()
	        			 			 + "/Sigla Estado: " + listEndereco.get(i).getSigla()
	        			 			 + "\n" );
	        	 
	         }
	    }

	    System.out.println("fim---------------------------");
	   

		f.close();
		w.close();
	}

}