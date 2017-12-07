package trabalhofinal;

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Comparator;


public class Main {
	
    public static void main(String[] args) throws IOException{	
   	
        List<Registro> l = new LinkedList<Registro>();
        int counter = 0;
     
        Scanner input = new Scanner(System.in);
        RandomAccessFile f = new RandomAccessFile("registros.txt", "rw");
        String line = "";
        
        while (line != null) 
        {
        	line = f.readLine();
        	if(line == null)
        	{
        		//System.out.println("Fim da Leitura");
        	}
        	else{counter++;}
        }
        
        f.seek(0);
        //Termina de ler o número de registros e aponta para o início do arquivo---------
        
        
        int x = 0;
        while (x != counter) {
        	line = f.readLine();
        	Registro r2 = new Registro(line);
        	l.add(r2);
        	x++;
        }	

        int y = counter;

        Boolean flag_one = true;
		Boolean flag_split = true;
		
		System.out.println("Bem-vindo ao trabalho final da disciplina Organização de Estrutura de Arquivos.");
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("Nosso arquivo registros.txt está organizado por ID. Ele segue a seguinte estrutura:");
    	System.out.println("ID - NOME - SOBRENOME - EMAIL - GENERO - IP - PAIS - FEZ_FACULDADE");
    	System.out.println("-------------------------------------------------------------------------------------");
    	System.out.println("Número de registros no arquivo: " + counter);
        do {
        	System.out.println("Deseja ordená-lo usando outro atributo?(1-Sim, 2-Não)");
        	String answer = input.nextLine();
           
        	if(answer.equals("1")){
        		
        		Pergunta_Ordenar(f,l,y);
                flag_one = false;
                
        	}else if(answer.equals("2")){
                System.out.println("Okay. Vamos para próxima fase.");
                flag_one = false;
            }else{
                System.out.println("Resposta inválida, responda 1 para Sim ou 2 para Não.");
            }
         }while(flag_one);
        
        do {
        	Pergunta_Separar();
        	String answer = input.nextLine();
           
        	if(answer.equals("1")){
        		
        		Pergunta_Separando(f, l, y);
        		flag_split = false;
                
        	}else if(answer.equals("2")){
                System.out.println("Okay.");
                flag_split = false;
            }else{
                System.out.println("Resposta inválida, responda 1 para Sim ou 2 para Não.");
            }
         }while(flag_split);
        
        System.out.println("Acho que terminamos por aqui! Obrigado e Feliz Natal!");
        
        
        
     
        f.close();
    }    
    
  //-------------------------------------------------------------------------------
  //modo de organizar primeira pergunta
  	public static void Pergunta_Ordenar(RandomAccessFile f, List<Registro> l, int y) throws IOException  {
  		Boolean flag_two = true;
  		
          System.out.println("Você escolheu sim. Qual atributo deseja ordenar:");
          System.out.println("1-Nome");
          System.out.println("2-Sobrenome");
          System.out.println("3-IP");
          System.out.println("4-E-mail");
          System.out.println("5-ID");
          
          do {
          	Scanner input = new Scanner(System.in);
              String answer = input.nextLine();
              
              if(answer.equals("1")){
              	System.out.println("Você escolheu Nome.");
              	String nome = "Nome";
              	
              	Collections.sort(l, Registro.COMPARE_BY_NOME);
              	escreverLista(f,l,y);
              	System.out.println("Ordenado com sucesso!");
              	flag_two = false;
          	}else if(answer.equals("2")){
       
          		System.out.println("Você escolheu Sobrenome.");
          		Collections.sort(l, Registro.COMPARE_BY_SOBRENOME);
          		escreverLista(f,l,y);
              	System.out.println("Ordenado com sucesso!");
              	flag_two = false;
          	}else if(answer.equals("3")){
          	     
          		System.out.println("Você escolheu IP.");
          		Collections.sort(l, Registro.COMPARE_BY_IP);
          		escreverLista(f,l,y);
              	System.out.println("Ordenado com sucesso!");
              	flag_two = false;
          	}else if(answer.equals("4")){
          	     
          		System.out.println("Você escolheu E-mail.");
          		Collections.sort(l, Registro.COMPARE_BY_EMAIL);
          		escreverLista(f,l,y);
              	System.out.println("Ordenado com sucesso!");
              	flag_two = false;
          	}else if(answer.equals("5")){
         	     
          		System.out.println("Você escolheu ID.");
          		Collections.sort(l, Registro.COMPARE_BY_ID);
          		escreverLista(f,l,y);
              	System.out.println("Ordenado com sucesso!");
              	flag_two = false;
              	
              }else{
                  System.out.println("Resposta inválida, responda 1,2,3 ou 4.");
              }
          }while(flag_two);
  	 }
  	//-------------------------------------------------------------------------------
  	
  	
  	
  	
  	public static void Pergunta_Separar() {
  		System.out.println("Deseja separar o arquivo por um atributo específico?(1-Sim, 2-Não)");
  	 }
  	
  	
  	//modo de organizar segunda pergunta
  	public static void Pergunta_Separando(RandomAccessFile f, List<Registro> l, int y) throws IOException {	 
  		
  		Boolean flag_choice = true;
  		
          do {
          	System.out.println("Você escolheu Sim. Escolha o atributo:");
      		System.out.println("1-Genero (gera um Male.txt e Female.txt)");
      		System.out.println("2-País   (gera arquivos: BR.txt, US.txt, SK.txt, CA.txt e AR.txt)");
      		System.out.println("3-Fez Faculdade (gera arquivo graduados.txt e nao_graduados.txt)");	
      		Scanner input = new Scanner(System.in);
          	String answer = input.nextLine();
             
          	if(answer.equals("1")){
          		
          		System.out.println("Você escolheu Genero, gerando arquivos...");
          		SplitGenero(l, y);
          		System.out.println("Separado com sucesso!");
                flag_choice = false;	
          	}else if(answer.equals("2")){
          		
          		System.out.println("Você escolheu Pais, gerando arquivos...");
          		SplitPais(l, y);
              	System.out.println("Separado com sucesso!");
                 flag_choice = false;
                  
          	}else if(answer.equals("3")){
          		System.out.println("Você escolheu Fez Faculdade, gerando arquivos...");
          		SplitFaculdade(l, y);
              	System.out.println("Separado com sucesso!");
              	 flag_choice = false;
              }else{
                  System.out.println("Resposta inválida, responda 1, 2 ou 3.");
              }
           }while(flag_choice);
  			
  	 }
  	
  	
  	public static void Pergunta_TipoOrdenacao() {
  		System.out.println("Deseja separar o arquivo por um atributo específico?(1-Sim, 2-Não)");
  	 }

	//funçoes para gerar novos arquivos separados pelo atributo escolhido	
	public static void SplitGenero(List<Registro> l, int y) throws IOException {
		
		RandomAccessFile gm = new RandomAccessFile("Male.txt", "rw");
		RandomAccessFile fm = new RandomAccessFile("Female.txt", "rw");
		
        int x = 0;
        String aux, linha;
        while (x != y) {
    		linha = l.get(x).toString();
    		aux = l.get(x).getGenero();
    		
    		if(aux.equals("Male"))
    		{
    			gm.writeBytes(linha);
    			gm.writeBytes(System.getProperty("line.separator"));
    		}
    		else
    		{
    			fm.writeBytes(linha);
    			fm.writeBytes(System.getProperty("line.separator"));
    		}	
        	x++;	
        }	
		gm.close();
	  	fm.close();
	}
	
	public static void SplitPais(List<Registro> l, int y) throws IOException {
		
		RandomAccessFile br = new RandomAccessFile("BR.txt", "rw");
		RandomAccessFile us = new RandomAccessFile("US.txt", "rw");
		RandomAccessFile sk = new RandomAccessFile("SK.txt", "rw");
		RandomAccessFile ar = new RandomAccessFile("AR.txt", "rw");
		RandomAccessFile ca = new RandomAccessFile("CA.txt", "rw");
		
        int x = 0;
        String aux, linha;
        while (x != y) {
    		linha = l.get(x).toString();
    		aux = l.get(x).getPais();
    		
    		if(aux.equals("Brazil"))
    		{
    			br.writeBytes(linha);
    			br.writeBytes(System.getProperty("line.separator"));
    		}
    		else if(aux.equals("Argentina"))
    		{
    			ar.writeBytes(linha);
    			ar.writeBytes(System.getProperty("line.separator"));
    		}	
    		else if(aux.equals("Canada"))
    		{
    			ca.writeBytes(linha);
    			ca.writeBytes(System.getProperty("line.separator"));
    		}	
    		else if(aux.equals("South Korea"))
    		{
    			sk.writeBytes(linha);
    			sk.writeBytes(System.getProperty("line.separator"));
    		}	
    		else if(aux.equals("United States"))
    		{
    			us.writeBytes(linha);
    			us.writeBytes(System.getProperty("line.separator"));
    		}
        	x++;	
        }	
		br.close();
	  	ar.close();
	  	us.close();
	  	sk.close();
	  	ca.close();
	}	
	
	
	public static void SplitFaculdade(List<Registro> l, int y) throws IOException {
		
		RandomAccessFile ff = new RandomAccessFile("graduados.txt", "rw");
		RandomAccessFile nf = new RandomAccessFile("nao_graduados.txt", "rw");
		
        int x = 0;
        String linha;
        Boolean aux;
        while (x != y) {
        	
    		linha = l.get(x).toString();
    		aux = l.get(x).getFez_faculdade();
    		
    		if(aux.equals(true))
    		{
    			ff.writeBytes(linha);
    			ff.writeBytes(System.getProperty("line.separator"));
    		}
    		else
    		{
    			nf.writeBytes(linha);
    			nf.writeBytes(System.getProperty("line.separator"));
    		}	
        	x++;	
        }	
        ff.close();
		nf.close();
	}	
	
	//sobrescreve txt com o array novo
    public static void escreverLista( RandomAccessFile f, List<Registro> l, int y) throws IOException {
    	
        int x = 0;
        f.seek(0);
        while (x != y) {

        	f.writeBytes(l.get(x).toString());
			f.writeBytes(System.getProperty("line.separator"));
        	x++;
        }	
        
    }
    
    
    
    
    //resquicios de código de quando eu ia oferecer ao usuário ordenar por Bubble, Merge, Quick ou Heap Sort.
    
    /*
	public static List<Registro> bubbleSort(List<Registro> l, int y, String criterio) {

		if(criterio.equals("Nome"))
		{
			System.out.println("PING.");
		    for (int a = 1; a < y; a++) {
		        for (int b = 0; b < y - a; b++) {
		            if (((l.get(b).getPrimeiro_nome())
		                    .compareTo((l.get(b).getPrimeiro_nome()))) > 0) {
		                // troca
		                Registro temp = l.get(b);
		                l.get(b).assign(l.get(b+1));//l.get(b) = l.get(b+1);
		                l.get(b+1).assign(temp); //l.get(b+1) = temp;
		                System.out.println("PING.");
		            }
		        }
		    }
		}
		else if(criterio.equals("Sobrenome"))
		{
			 for (int a = 1; a < y; a++) {
			        for (int b = 0; b < y - a; b++) {
			            if (((l.get(b).getUltimo_nome())
			                    .compareTo((l.get(b).getUltimo_nome()))) > 0) {
			                // troca
			                Registro temp = l.get(b);
			                l.get(b).assign(l.get(b+1));//l.get(b) = l.get(b+1);
			                l.get(b+1).assign(temp); //l.get(b+1) = temp;
			            }
			        }
			    }
		}	
		else if(criterio.equals("IP"))
		{
			for (int a = 1; a < y; a++) {
		        for (int b = 0; b < y - a; b++) {
		            if (((l.get(b).getIp())
		                    .compareTo((l.get(b).getIp()))) > 0) {
		                // troca
		                Registro temp = l.get(b);
		                l.get(b).assign(l.get(b+1));//l.get(b) = l.get(b+1);
		                l.get(b+1).assign(temp); //l.get(b+1) = temp;
		            }
		        }
		    }
		}	
		else if(criterio.equals("E-mail"))
		{
			for (int a = 1; a < y; a++) {
		        for (int b = 0; b < y - a; b++) {
		            if (((l.get(b).getEmail())
		                    .compareTo((l.get(b).getEmail()))) > 0) {
		                // troca
		                Registro temp = l.get(b);
		                l.get(b).assign(l.get(b+1));//l.get(b) = l.get(b+1);
		                l.get(b+1).assign(temp); //l.get(b+1) = temp;
		            }
		        }
		    }
		}
		else if(criterio.equals("ID"))
		{
			for (int a = 1; a < y; a++) {
		        for (int b = 0; b < y - a; b++) {
		            if (((l.get(b).getId())
		                    .compareTo((l.get(b).getId()))) > 0) {
		                // troca
		                Registro temp = l.get(b);
		                l.get(b).assign(l.get(b+1));//l.get(b) = l.get(b+1);
		                l.get(b+1).assign(temp); //l.get(b+1) = temp;
		            }
		        }
		    }
		}
		else
		{
			System.out.println("PINGotario.");
		}
		
		return l;

	}
	*/
	
	
	
	
	
}
