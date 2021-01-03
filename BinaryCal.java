import java.util.Locale;
import java.util.Scanner;

public class BinaryCal {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		int[] numbers =    {10,   11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  21,  22,  23,  24,  25,  26,  27,  28,  29,  30,  31,  32,  33,  34,  35};
		int base = 1;
		String number = "";
		int targetBase = 1;
		try {
			base = sc.nextInt();
			number = sc.next();
			targetBase = sc.nextInt();
			if (base == 0 || targetBase == 0 || base < 1 || targetBase < 1 || base > 36 || targetBase > 36) {
				System.out.println("error");
				System.exit(0);
			}
		} catch (Exception e) {
			System.out.println("error");
			System.exit(0);
		}


		if (number.contains(".")) {
			
			String[] splitNumber = number.split("\\.");
			String[] fracArr = splitNumber[1].split("");
			String[] finalFloat = new String[5];
			String finalFrac = ".";

			if (thereIsLetter(number, letters)) {
				
				Double fracPar = frationalValueWithLetter(splitNumber[1], base, letters, numbers);
				
				for (int i = 0; i < 5; i++) {
					fracPar = fracPar * targetBase;
					String aux = fracPar + "";
					Integer auxN = (int) Math.floor(fracPar);
					String let = "";
					if (auxN > 9) {
						for (int j = 0; j < numbers.length; j++) {
							if (numbers[j] == auxN) {
								let = letters[j];
							}
						}
					}	
					String[] auxArr = aux.split("\\.");
					if (Integer.parseInt(auxArr[0]) > 9) {
						auxArr[0] = let;
					}
					finalFloat[i] = auxArr[0];
					aux = "0." + auxArr[1];
					fracPar = Double.parseDouble(aux);
				}
				
				for (int i = 0; i < 5; i++) {
					finalFrac += finalFloat[i];
				}
					
				Integer converting = Integer.parseInt(splitNumber[0], base);
				String finalInt = Integer.toString(converting, targetBase);
				String finalResult = finalInt + finalFrac;
				System.out.println(finalResult);
			}
			else {

				Double fracPar = Double.parseDouble(number) - Double.parseDouble(splitNumber[0]);
		
				if (base != 10) {
					fracPar = frationalValueNoLetter(splitNumber[1], targetBase);
				}
		
					for (int i = 0; i < 5; i++) {
						fracPar = fracPar * targetBase;
						String aux = fracPar + "";
						String[] auxArr = aux.split("\\.");
						finalFloat[i] = auxArr[0];
						aux = "0." + auxArr[1];
						fracPar = Double.parseDouble(aux);
					}
					
					for (int i = 0; i < 5; i++) {
						finalFrac += finalFloat[i];
					}
					
					Integer converting = Integer.parseInt(splitNumber[0], base);
					String finalInt = Integer.toString(converting, targetBase);
					String finalResult = finalInt + finalFrac;
					System.out.println(finalResult);
			}
			
		}
		else {
			if (base == 1) {
				String s = number.length() + "";
				long l = Long.parseLong(s, 10);
				System.out.println(Integer.toString((int) l, targetBase));
			}
			else if (targetBase == 1) {
				for (int i = 0; i < Integer.parseInt(number); i++) {
					System.out.print(1);
				}
			}
			else {
				Integer converting = Integer.parseInt(number, base);
				System.out.println(Integer.toString(converting, targetBase));
			}
		}

	}
	
	public static double frationalValueNoLetter(String s, int base) {
		String[] values = s.split("");
		double result = 0.0;
		int exponent = 1;
		for (int i = 0; i < values.length; i++) {
			Double aux = Double.parseDouble(values[i]);
			result += aux / Math.pow(base, exponent);
			exponent++;
		}
		return result;
	}
	
	public static double frationalValueWithLetter(String s, int base, String[] letters, int[] numbers) {
		String[] values = s.split("");
		double result = 0.0;
		int exponent = 1;
		Double aux = 0.0;
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < letters.length; j++) {
				if (values[i].contains(letters[j])) {
					aux += numbers[j];
				}
			}
			if (aux == 0.0) {
				aux = Double.parseDouble(values[i]);
			}
			result += aux / Math.pow(base, exponent);
			exponent++;
			aux = 0.0;
		}
		return result;
	}
	
	public static boolean thereIsLetter(String number, String[] letters) {
		String[] arrNum = number.split("");
		for (int i = 0; i < arrNum.length; i++) {
			for (int j = 0; j < letters.length; j++) {
				if (arrNum[i].contains(letters[j])) {
					return true;
				}
			}
		}
		return false;
	}
}
