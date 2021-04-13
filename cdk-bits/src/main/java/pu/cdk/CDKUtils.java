package pu.cdk;

import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.silent.SilentChemObjectBuilder;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.AtomContainerManipulator;

public class CDKUtils 
{
	public static IAtomContainer getMoleculeFromSmiles(String smi)
			throws Exception {
		IAtomContainer mol = null;
		SmilesParser sp = new SmilesParser(
				SilentChemObjectBuilder.getInstance());
		mol = sp.parseSmiles(smi);
		return mol;
	}

	public static IAtomContainer getMoleculeFromSmiles(String smi,
			boolean FlagExplicitHatoms) throws Exception {
		IAtomContainer mol = null;
		SmilesParser sp = new SmilesParser(
				SilentChemObjectBuilder.getInstance());
		mol = sp.parseSmiles(smi);

		// TODO
		// !!!! aromaticity might be lost in the preprocessing phase

		// some pre-processing is done
		AtomContainerManipulator.percieveAtomTypesAndConfigureAtoms(mol);
		CDKHydrogenAdder adder = CDKHydrogenAdder
				.getInstance(SilentChemObjectBuilder.getInstance());
		adder.addImplicitHydrogens(mol);
		
		if (FlagExplicitHatoms)
			AtomContainerManipulator.convertImplicitToExplicitHydrogens(mol);
			//HydrogenAdderProcessor.convertImplicitToExplicitHydrogens(mol);

		return mol;
	}
}
