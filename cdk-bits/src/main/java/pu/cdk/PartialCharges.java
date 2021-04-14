package pu.cdk;



import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.openscience.cdk.charges.GasteigerMarsiliPartialCharges;
import org.openscience.cdk.charges.IChargeCalculator;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IAtomContainer;

public class PartialCharges 
{
	
	public static String getPartialChargesAsString(IAtomContainer mol, IChargeCalculator chargeCalc) throws Exception
	{
		StringBuffer sb = new StringBuffer();
		DecimalFormat df = new DecimalFormat("0.000", new DecimalFormatSymbols(new Locale("en-US")));
		
		chargeCalc.calculateCharges(mol);
		
		for (int i = 0; i < mol.getAtomCount(); i++)
		{	
			IAtom at = mol.getAtom(i);
			sb.append((i+1) + "  " + at.getSymbol() + "  " + df.format(at.getCharge()));
		}	
		
		return sb.toString();
	}
	
	
	
}
