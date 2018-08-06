

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormatDecimalValue {

	public static void main(String[] args) {

		String value = "3.1416789";
		System.out.println("Formatted value for " + value + "  is:  "
				+ formattedDecimalValue(value, 5));

		value = "5.5";
		System.out.println("Formatted value for " + value + "  is:  "
				+ formattedDecimalValue(value, 5));
	}

	/**
	 * @param value
	 *            {@code String}
	 * @param scale
	 *            {@code int} decimal limit
	 * @return Returns decimal value up to scale if it is greater than scale.
	 *         Otherwise original String.
	 */
	public static String formattedDecimalValue(String value, int scale) {

		/*
		 * If value contains whitespace, empty or null then returning empty
		 * string
		 */
		if (isNullOrEmpty(value)) {
			return "";
		}

		String formatedValue = value;

		try {
			/* Get the Fractional part length of decimal value */
			int fractionPartLength = 0;
			if (value.contains(".")) {
				fractionPartLength = value.split("\\.")[1].length();
			}

			Double dValue = Double.valueOf(value);

			/*
			 * If Fractional part length of decimal value is greater than scale
			 * then we are going to rounding the value up to scale.
			 */
			if (scale > 0 && fractionPartLength > scale) {

				StringBuilder decimalPattern = new StringBuilder("#.");
				for (int i = 0; i < scale; i++) {
					decimalPattern.append("#");
				}
				/*
				 * Here pattern is #.#####. You may read this docs -
				 * https://docs
				 * .oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
				 */
				DecimalFormat df = new DecimalFormat(decimalPattern.toString());
				/* By default roundingMode is HALF_EVEN */
				df.setRoundingMode(RoundingMode.CEILING);
				formatedValue = df.format(dValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatedValue;
	}

	/**
	 * This method checks a string is whitespace, empty or null.
	 * 
	 * @param str
	 *            {@code String}
	 * @return Returns {@code true} if String is null or empty or contains only
	 *         whitespace. Otherwise {@code false}.
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		} else {
			for (int i = 0; i < str.length(); ++i) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}

}
