import java.util.Random;

public class Corredor extends Thread{
	
	//Variables importantes
	public String nom;
	private Pista pista;
	private Panel_informativo panel;
	String nomPista;
	boolean eliminado,
			recomenzar,
			mitjaPista;
	private int esgotament = 0,
				esprint;
	
	//Constructor
	Corredor(String nom, Pista pista){
		this.nom = nom;
		this.pista = pista;
		panel = pista.panel;
		nomPista = pista.nom;
		start();
	}
	//Metode per a reactivar el corredor
	void Participa(Pista pista, int estimulant){
		this.pista = pista;
		panel = pista.panel;
		nomPista = pista.nom;
		this.setPriority(this.getPriority() + estimulant);
		Recomenzar();
	}
	//Métode que permet reactivar el corredor
	void Recomenzar(){
		recomenzar = true; 
	}
	//Métode que expulsa el corredor
	void Acaba(){
		eliminado = true;
		recomenzar = true; 
	}
	//Métode que Esgota als corredors
	void Esgotament(){
		esgotament += 1;
		this.setPriority(this.getPriority() - esgotament);
		System.out.println("en la pista " + nomPista + " el corredor " + nom + " ha fet una esprintada que passarà factura.");
	}
	
	public void run(){
		
		eliminado = false;
		mitjaPista = false;
		
		while (!eliminado){
			//Inicializamos el tamaño de la pista, la distancia recorrida inicial, etc...
			recomenzar = false;
			mitjaPista = false;
			int meta = pista.longitud;
			int distancia = 0;
			Random ran = new Random();
			
			//El corredor se coloca en la pista
			pista.getCarril(this);
	
			//Si no ha comenzado la carrera
			while(!panel.comenzada){
				try {
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Mientras no haya llegado a la meta
			while(distancia < meta){
				//Distancia a recorrer
				esprint = ran.nextInt(100);
				distancia += esprint;
				//Si ha recorrido la más del 80% del máximo que puede recorrer en un turno
				if (esprint > ((meta/100)*80)){
					Esgotament();
				}
				
				System.out.println("Pista: " + nomPista + "\tCorredor: " + nom + " " + distancia + " metros");
				
				//Si ha llegado a la mitad de la pista
				if (distancia == (meta/distancia)){
					mitjaPista = true;
				}
				
				//Siguiente Corredor
				try {
					sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Le dice al panel que ha llegado
			panel.llegadas(this);
			Esgotament();

			//Tenemos al corredor en espera
			while (!recomenzar){
				try {
					sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}