package ofertaHotelera;

import java.util.*;

public class OperadorDeColecciones {

	public static void concatenarReservas(List<Reserva> res1, List<Reserva> res2){
		for(Reserva each : res2){
			res1.add(each);
		}
	}

}