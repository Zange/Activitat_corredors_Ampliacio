
public class Jutge extends Thread {
	
	//Variables importantes
	Panel_informativo panel;
	boolean carrera2,
			interesant;
	int agotado = 10,//A la m�xima prioridad
		animado = 0,//A la m�nima prioridad
		pos = 0;
	String corredorAgotado,
		   corredorAnimado;
	
	//Constructor
	Jutge(Panel_informativo panel, boolean carrera2){
		this.panel = panel;
		this.carrera2 = carrera2;
		start();
	}
	//M�tode que muesta el estado de los corredores antes de la segunda carrera
	void EstatCorredors(){
		
		for (Corredor x : panel.carril){
			//Imprimeix el corredor en el seu carril
			System.out.println(" el corredor " + x.nom + " en el carril " + pos);
			pos++;
			//Comproba qui est� m�s cansat
			if (x.getPriority() < agotado){
				agotado = x.getPriority();
				corredorAgotado = x.nom;
			}
			//Comproba qui est� m�s animat
			if (x.getPriority() > animado){
				animado = x.getPriority();
				corredorAnimado = x.nom;
			}
		}
		
		System.out.println("el corredor " + corredorAgotado + " sembla una mica esgotat despr�s de la carrera");
		System.out.println("el corredor " + corredorAnimado + " en canvi pareix plet�ric");
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
					//S� ha llegado a la mitad
					if (x.mitjaPista){
						System.out.println("el corredor " + x.nom + " va en primera posici�");
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
			System.out.println("en la pista " + panel.podio[0].nomPista + " el corredor " + panel.podio[0].nom + " ha arriba el primer classificant-se aix� per a la final.");
			System.out.println("en la pista " + panel.podio[1].nomPista + " el corredor " + panel.podio[1].nom + " ha arriba el segon classificant-se aix� per a la final.");
			System.out.println("pista " + panel.podio[2].nomPista + " el corredor " + panel.podio[2].nom + " ha acabat la carrera");
			System.out.println("pista " + panel.podio[3].nomPista + " el corredor " + panel.podio[3].nom + " ha acabat la carrera");
		} else {
			System.out.println("el corredor " + panel.podio[3].nom + " ha guanyat el campionat!");
		}
	}
}
