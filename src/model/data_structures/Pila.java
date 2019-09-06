package src.model.data_structures;

public class Pila <R extends Comparable <R>>
{

	private double sourceid;
	private double dstid;
	private double month;
	private double mean_travel_time;
	private double standard_deviation_travel_time;
	private double geometric_standard_deviation_travel_time;
	private double geometric_mean_travel_time;

	Pila(String Psourceid, String Pdstid,String Pmonth, String Pmean_travel_time, String Pstandard_deviation_travel_time, String Pgeometric_standard_deviation_travel_time,String Pgeometric_mean_travel_time )
	{
		sourceid=Double.parseDouble(Psourceid);
		dstid = Double.parseDouble(Pdstid);
		month =Double.parseDouble(Pmonth);
		mean_travel_time =Double.parseDouble(Pmean_travel_time);
		standard_deviation_travel_time =Double.parseDouble(Pstandard_deviation_travel_time);
		geometric_mean_travel_time = Double.parseDouble(Pgeometric_mean_travel_time);
		geometric_standard_deviation_travel_time = Double.parseDouble(Pgeometric_standard_deviation_travel_time);
		

	}
	
	public double darTercerDato()
	{
		return month;
	}
}
