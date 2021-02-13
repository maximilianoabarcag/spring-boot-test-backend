package cl.ionix.test.util;

import java.util.regex.Pattern;

import cl.ionix.test.exception.InvalidRutFormatException;
import cl.ionix.test.exception.InvalidRutNumberException;

public class RutNumber {
	public static void isValid(String rut) throws InvalidRutFormatException, InvalidRutNumberException {
		boolean isValid = false;

		try {
			rut = rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			rut = rut.toUpperCase();
			if(!Pattern.matches("^[0-9]+[0-9K]{1}$", rut)) {
				throw new InvalidRutFormatException("Invalid RUT Format!");
			}
			int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

			char dv = rut.charAt(rut.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				isValid = true;
			}

		} catch (NumberFormatException e) {
			throw new InvalidRutFormatException("Invalid RUT Format!");
		}

		if(!isValid) {
			throw new InvalidRutNumberException("Invalid RUT Number!");
		}
	}
}
