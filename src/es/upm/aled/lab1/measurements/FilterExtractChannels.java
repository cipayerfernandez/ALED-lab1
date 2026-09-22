package es.upm.aled.lab1.measurements;

/**
 * Filter that extracts the specified channels from an EEGModel.
 * 
 * @author mmiguel, rgarciacarmona
 *
 */
public class FilterExtractChannels implements Filter {

	/**
	 * Builds the Filter. The use from an array of valid channels.
	 * 
	 * @param validChannels The channel numbers to be extracted, starting from 0.
	 */
	
	private int[] validChannels;	// validChannels me da el ÍNDICE de los canales a escoger, no el valor de cada canal escogido
	
	public FilterExtractChannels(int[] validChannels) {
		this.validChannels = validChannels;
		
	}

	@Override
	public EEGModel applyFilter(EEGModel eeg) {
		// GUARDO EN EL ARRAY measurements LAS MEDIDAS DEL EEG QUE ENTRA COMO ARGUMENTO
		Measurement[] measurements = eeg.getMeasurements();
		// CREO OTRO ARRAY VACÍO PARA LAS MEASUREMENTS FILTRADAS. SU LONGITUD ES LA DEL NÚMERO DE CANALES VÁLIDOS
		Measurement[] filteredMeasurements = new Measurement[validChannels.length];
		float[] filteredChannels = new float[validChannels.length];	// Creo el array de floats con el que rellenaré filteredMeasurements
		
		// GUARDO LAS MEASUREMENTS FILTRADAS EN filteredMeasurements
		for (int i = 0; i < measurements.length; i++) {
			Measurement m = measurements[i];	// Guardo en la variable "m" la medida en la posición i
			int iFilteredChannels = 0;	//Creo el índice de los canales escogidos
			
			for (int iChannels = 0; iChannels < m.numChannels(); iChannels++) {	// Recorro el array de floats que compone cada medida. indexChannels es el ÍNDICE del channel
				for (int j = 0; j < validChannels.length; j++) {	// Recorro el array de validChannels. "j" es el contenido de validChannels, no el índice de los elementos de validChannels
					if (validChannels[j] == iChannels) {	// Si el índice del canal de la medida y el índice dado por validChannels coinciden, guardo
						// EN LA POSICIÓN indexFilteredChannels DE filteredChannels[], GUARDO EL CHANNEL CUYO ÍNDICE COINCIDE CON UN ELEMENTO DE validChannels
						filteredChannels[iFilteredChannels] = m.getChannel(iChannels);
						iFilteredChannels++;	// Avanzo el índice de canales filtrados
						break;
					} else {
						continue;
					}
				}
			}
			// YA HE AÑADIDO TODOS LOS CANALES ESCOGIDOS DE UNA SOLA MEDIDA AL ARRAY filteredChannels
			// AHORA, AÑADO EN LA POSICIÓN i DE filteredMeasurements LA MEDIDA COMPUESTA POR filteredChannels
			filteredMeasurements[i] = new Measurement(filteredChannels);
		}	
		EEGModel filteredModel = new EEGModel(filteredMeasurements);
		return filteredModel;
	}

}
