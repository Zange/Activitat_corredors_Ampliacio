
public class Jutge extends Thread {
	
	//Variables importantes
	Panel_informativo panel;
	boolean carrera2,
			interesant;
	int agotado = 10,//A la máxima prioridad
		animado = 0,//A la mínima prioridad
		pos = 0;
	String corredorAgotado,
		   corredorAnimado;
	
	//Constructor
	Jutge(Panel_informativo panel, boolean carrera2){
		this.panel = panel;
		this.carrera2 = carrera2;
		start();
	}
	//Métode que muesta el estado de los corredores antes de la segunda carrera
	void EstatCorredors(){
		
		for (Corredor x : panel.carril){
			//Imprimeix el corredor en el seu carril
			System.out.println(" el corredor " + x.nom + " en el carril " + pos);
			pos++;
			//Comproba qui está més cansat
			if (x.getPriority() < agotado){
				agotado = x.getPriority();
				corredorAgotado = x.nom;
			}
			//Comproba qui está més animat
			if (x.getPriority() > animado){
				animado = x.getPriority();
				corredorAnimado = x.nom;
			}
		}
		
		System.out.println("el corredor " + corredorAgotado + " sembla una mica esgotat després de la carrera");
		System.out.println("el corredor " + corredorAnimado + " en canvi pareix pletòric");
	}
	
	public void run(){
		
		//Mientras no esten preparados
		while (!panel.preparados){
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Estado de los corredores
		if (carrera2){
			EstatCorredors();
		}
		
		interesant = false;
		//Disparo de salida
		panel.comenzada = true;
		
		//Mientras el panel no marque todas las llegadas
		while (!panel.fin_carrera){
			
			//Si nadia a llegado a la mitad
			if (!(interesant && carrera2)){
				//Por cada corredor
				for (Corredor x : panel.carril){
					//Sí ha llegado a la mitad
					if (x.mitjaPista){
						System.out.println("el corredor " + x.nom + " va en primera posició");
						interesant = true;
					}
				}
			}
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//Imprime los resultados
		if (!carrera2){
			System.out.println("en la pista " + panel.podio[0].nomPista + " el corredor " + panel.podio[0].nom + " ha arriba el primer classificant-se així per a la final.");
			System.out.println("en la pista " + panel.podio[1].nomPista + " el corredor " + panel.podio[1].nom + " ha arriba el segon classificant-se així per a la final.");
			System.out.println("pista " + panel.podio[2].nomPista + " el corredor " + panel.podio[2].nom + " ha acabat la carrera");
			System.out.println("pista " + panel.podio[3].nomPista + " el corredor " + panel.podio[3].nom + " ha acabat la carrera");
		} else {
			System.out.println("el corredor " + panel.podio[3].nom + " ha guanyat el campionat!");
		}
	}
}
