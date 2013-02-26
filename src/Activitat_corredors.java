
public class Activitat_corredors {

	public static void main(String[] args){
	
		//Pista1
		Pista pista1 = new Pista("Pista 1",1000);
		Corredor uno = new Corredor("Uno",pista1);
		Corredor dos = new Corredor("Dos",pista1);
		Corredor tres = new Corredor("Tres",pista1);
		Corredor cuatro = new Corredor("Cuatro",pista1);
		Jutge Garzon = new Jutge(pista1.panel,false);
		
		//Pista2
		Pista pista2 = new Pista("Pista 2",1000);
		Corredor cinco = new Corredor("Cinco",pista2);
		Corredor seis = new Corredor("Seis",pista2);
		Corredor siete = new Corredor("Siete",pista2);
		Corredor ocho = new Corredor("Ocho",pista2);
		Jutge Ruz = new Jutge(pista2.panel,false);
		
		//Hasta que no terminen las dos carreras anteriores
		while (!(Garzon.panel.fin_carrera && Ruz.panel.fin_carrera)){}
		
		//Se eliminan a los descalificados
		Garzon.panel.podio[2].Acaba();
		Garzon.panel.podio[3].Acaba();
		Ruz.panel.podio[2].Acaba();
		Ruz.panel.podio[3].Acaba();
		
		//Pista3
		Pista pista3 = new Pista("Pista 3",1000);
		Corredor finalista_1 = Garzon.panel.podio[0];
		Corredor finalista_2 = Garzon.panel.podio[1];
		Corredor finalista_3 = Ruz.panel.podio[0];
		Corredor finalista_4 = Ruz.panel.podio[1];
		Garzon = new Jutge(pista3.panel,true);
		
		//Arranca la segunda carrera
		finalista_1.Participa(pista3,2);
		finalista_2.Participa(pista3,1);
		finalista_3.Participa(pista3,2);
		finalista_4.Participa(pista3,1);
		
		//Mientras no termine la segunda carrera
		while (!(Garzon.panel.fin_carrera)){}
		
		//Eliminamos a los corredores restantes
		for (Corredor x : Garzon.panel.podio){
			x.Acaba();
		}
	}
}