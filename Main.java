//Trabalho feito por Ryan Mont Serrat da Cunha
//Matricula: 1211538WEB

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception 
	{	
	    Scanner scanner = new Scanner(System.in);
	    String ecep, x, aux;    
            int low = 0;
            int high = 699307 - 1; //numero total de registros no dat é 699307
            int mid, achou;
	    
	    RandomAccessFile f = new RandomAccessFile("cep_ordenado.dat", "r");
		
	    Endereco e = new Endereco();
	    Endereco t = new Endereco();
		
	    System.out.println("Digite o CEP que está procurando:");
	    ecep=scanner.nextLine();
	    x = ecep;
		
	    System.out.println("Você está procurando o cep: " + x);
	    System.out.println("-----Percorrendo a lista através de busca binária---------");
	    
	    //Busca Binária cortando de metade em metade
	    while (low <= high)
	    {
		mid = (low + high) / 2;
		f.seek(mid*300);
    		t.leEndereco(f);
    		System.out.println("CEP da linha achado nessa iteração: " + t.getCep());
    		aux = t.getCep();

		if (aux.compareTo(x) < 0)
		{low = mid + 1;} 
		else if (aux.compareTo(x) > 0)
		{high = mid - 1;} 
		else 
		{
		   achou = mid;
		   System.out.println("----------------------Achou!-----------------------------");
		   break;
		} 
            }
		
		//Informações recuperadas do CEP buscado
		System.out.println("Logradouro: " + t.getLogradouro());
		System.out.println("Rua: " + t.getBairro());
		System.out.println("Cidade: " + t.getCidade());
		System.out.println("Estado: " + t.getEstado());
		System.out.println("Sigla: " + t.getSigla());
		System.out.println("CEP: " + t.getCep());
		f.close();
	}
}


