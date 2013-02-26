
public class Panel_informativo {
	
	//Variables importantes
	Corredor[] podio = new Corredor[4],
			   carril;
	int posicion = 0;
	boolean fin_carrera = false,
			preparados = false,
			comenzada = false;

	//Constructor
	Panel_informativo(){}
	
	//Dice si están listos para empezar a correr
	void setPreparados(boolean preparados, Corredor[] carril){
		this.preparados = preparados;
		this.carril = carril; //Esto se lo paso para que el Juez tenga acceso a los corredores
	}
	
	//Controla la llegada de los corredores y los coloca en el podio
	public synchronized void llegadas(Corredor corredor){

		podio[posicion] = corredor;
		posicion++;
		
		if (posicion == 4){
			fin_carrera = true;
		}
	}
	
	
	
}
