
public class Pista {
	
	//Variables esenciales
	Corredor[] carril = new Corredor[4];
	Panel_informativo panel = new Panel_informativo();;
	int longitud;
	String nom;
	
	//Variables de apoyo
	private int i = 0;
	
	//Cuerpo del programa
	
	//Constructor
	Pista(String nom,int longitud){
		this.nom = nom;
		this.longitud = longitud;
	}
	
	//Metodo que coloca a los corredores en sus carriles e indica que estran preparados
	public synchronized void getCarril(Corredor corredor){
		
		if (i < 4){
		
			carril[i] = corredor;
			i++;
			if (i == 4){
				
				panel.setPreparados(true,carril);
			}
		}
	}
}
