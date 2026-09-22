package es.upm.aled.lab1.measurements;

import java.io.IOException;

import javax.imageio.metadata.IIOMetadata;

/**
 * Filter that extracts the specified period from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractPeriod implements Filter {

	/**
	 * Builds the Filter from the [min, max] range defining the period that needs to
	 * be extracted. min and max are the indexes of the first and last measurements
	 * of the array obtained by calling the getMeasurements() method of EEGModel,
	 * and represent the starting and ending point of the period to be extracted.
	 * Both indexes are included and max-min must be less than the length of the
	 * Measurements array of the EGG Model.
	 * 
	 * @param min Start of the period to be extracted.
	 * @param max End of the period to be extracted.
	 */
	
	private int min;
	private int max;
	
	public FilterExtractPeriod(int min, int max) {
		this.min = min;
		this.max = max;
		
	}

	@Override
	// FILTEREXTRACTPERIOD DEVUELVE UN EEG CON LAS MEDIDAS COMPRENDIDAS ENTRE LOS ÍNDICES MIN Y MAX
	public EEGModel applyFilter(EEGModel eeg) {
		// TODO
		Measurement[] m = eeg.getMeasurements();
		Measurement[] filteredMeasurements = new Measurement[max - min];	// Longitud del array obtenida de solución del profe

		if ((max-min) < m.length) {	// Condición del enunciado
			for (int iMeasurements = 0; iMeasurements < m.length; ) { 
				int iFilteredMeasurements = 0;
				if (iMeasurements >= min && iMeasurements <= max) {	// Compruebo si el índice de cada medida está en el intervalo (min, max) 
					filteredMeasurements[iFilteredMeasurements] = m[iMeasurements];
					iFilteredMeasurements++;
					break;
				}
			}
		} else {
			System.out.println("El intervalo [min, max] debe ser menor que el array de medidas del EEGModel proporcionado");
		}
		
		EEGModel filteredModel = new EEGModel(filteredMeasurements);
		return filteredModel;
	}
}

