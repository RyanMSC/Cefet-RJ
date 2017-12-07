package trabalhofinal;

import java.util.Comparator;

//Ryan Mont Serrat da Cunha
//1211538WEB - CEFET-RJ - GTSI - Organização de Estrutura de Arquivos
//Registros gerados aleatoriamente usando o site Mockaroo.com

public class Registro {

	//id,primeiro_nome,ultimo_nome,email,genero,ip,pais,fez_faculdade
	
	private Integer id;
	private String primeiro_nome;
	private String ultimo_nome;
	private String email;
	private String genero;
	private String ip;
	private String pais;
	private Boolean fez_faculdade;
	
	
	public Registro(int id, String primeiro_nome, String ultimo_nome, String email, String genero, String ip,
			String pais, Boolean fez_faculdade) {
		super();
		this.id = id;
		this.primeiro_nome = primeiro_nome;
		this.ultimo_nome = ultimo_nome;
		this.email = email;
		this.genero = genero;
		this.ip = ip;
		this.pais = pais;
		this.fez_faculdade = fez_faculdade;
	}
	
	public Registro(String linha) {
		super();
		
		String[] namesList;
		
		if(linha != null)
		{
			namesList = linha.split(",");
		}
		else
		{
			namesList = linha.split(" ");
		}

        String id = namesList[0];
        int int_id = Integer.parseInt(id);
        
        String primeiro_nome = namesList[1];
        String ultimo_nome = namesList[2];
        String email = namesList[3];
        String genero = namesList[4];
        String ip = namesList[5];
        String pais = namesList[6];
        
        String fez_faculdade = namesList[7];
        
        
        
        Boolean bol_fez = false;
        
        if (fez_faculdade.equals("true"))
        	bol_fez = true;
        
        if (fez_faculdade.equals("true;"))
        	bol_fez = true;
        
        if (fez_faculdade.equals("false"))
        	bol_fez = false;
        
        if (fez_faculdade.equals("false;"))
        	bol_fez = false;

		this.id = int_id;
		this.primeiro_nome = primeiro_nome;
		this.ultimo_nome = ultimo_nome;
		this.email = email;
		this.genero = genero;
		this.ip = ip;
		this.pais = pais;
		this.fez_faculdade = bol_fez;
	}
	
	//funcao de receber valores de outro registro, pra fazer o swap
	Registro assign(Registro other) {
		this.id = other.id;
		this.primeiro_nome = other.primeiro_nome;
		this.ultimo_nome = other.ultimo_nome;
		this.email = other.email;
		this.genero = other.genero;
		this.ip = other.ip;
		this.pais = other.pais;
		this.fez_faculdade = other.fez_faculdade;
	    
	    return this;
	}
	
	//GET e SET
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPrimeiro_nome() {
		return primeiro_nome;
	}
	public void setPrimeiro_nome(String primeiro_nome) {
		this.primeiro_nome = primeiro_nome;
	}
	public String getUltimo_nome() {
		return ultimo_nome;
	}
	public void setUltimo_nome(String ultimo_nome) {
		this.ultimo_nome = ultimo_nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public Boolean getFez_faculdade() {
		return fez_faculdade;
	}
	public void setFez_faculdade(Boolean fez_faculdade) {
		this.fez_faculdade = fez_faculdade;
	}
	
	
	@Override
	public String toString() {
		return  id + "," + primeiro_nome + "," + ultimo_nome + ","
				+ email + "," + genero + "," + ip + "," + pais + "," + fez_faculdade + ";"; 
	}
	
	//comparadores de atributo
	public static Comparator<Registro> COMPARE_BY_IP = new Comparator<Registro>() {
	        public int compare(Registro one, Registro other) {
	            return one.ip.compareTo(other.ip);
	    }
    };
    
    public static Comparator<Registro> COMPARE_BY_NOME = new Comparator<Registro>() {
        public int compare(Registro one, Registro other) {
            return one.primeiro_nome.compareTo(other.primeiro_nome);
    }
    };
    
    public static Comparator<Registro> COMPARE_BY_SOBRENOME = new Comparator<Registro>() {
        public int compare(Registro one, Registro other) {
            return one.ultimo_nome.compareTo(other.ultimo_nome);
    }
    };
    
    public static Comparator<Registro> COMPARE_BY_EMAIL = new Comparator<Registro>() {
        public int compare(Registro one, Registro other) {
            return one.email.compareTo(other.email);
    }
    };
    
    public static Comparator<Registro> COMPARE_BY_ID = new Comparator<Registro>() {
        public int compare(Registro one, Registro other) {
            return one.id.compareTo(other.id);
    }
    };
    

	
}
