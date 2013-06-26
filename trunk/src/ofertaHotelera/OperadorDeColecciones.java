package ofertaHotelera;

import java.util.*;

public class OperadorDeColecciones {

	public static void concatenarReservas(List<Reserva> res1, List<Reserva> res2){
		for(Reserva each : res2){
			res1.add(each);
		}
	}
	
	public static List<List<Calendar>> mapAList(Map<List<Calendar>, Integer> map){
		List<List<Calendar>> res= new ArrayList<List<Calendar>>();
    	Iterator it = map.entrySet().iterator();
    	while (it.hasNext()) {
    		Map.Entry e = (Map.Entry)it.next();
    		res.add((List<Calendar>) e.getKey());
    	}
		return res;
	}

}