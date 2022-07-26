package com.celi.cii.base.utils;


public class DeptCodeUtils {
	
	/**
	 * Auto code.
	 * 
	 * @param inputCode
	 *            the input code
	 * @param digitdisc
	 *            the digitdisc
	 * @param lvllength
	 *            the lvllength
	 * @return the string
	 */
	public static String autoCode(String inputCode, int digitdisc, int lvllength){
		try {
			String parent = inputCode.substring(0, inputCode.length() - lvllength);
			String child = inputCode.substring(parent.length(), inputCode.length());
			String result = "";
			if (lvllength < 3) {
				char[] ch = new char[lvllength];
				switch (lvllength) {
				case 1:
					ch[0] = child.charAt(0);
					break;
				case 2:
					ch[0] = child.charAt(0);
					ch[1] = child.charAt(1);
					break;
				default:
					break;
				}
				
				ch[lvllength - 1] = (char) ((int) ch[lvllength - 1] + 1);

				switch (lvllength) {
				case 1:
					if ((int) ch[lvllength - 1] > 57 && (int) ch[lvllength - 1] < 65 ) {
						ch[lvllength - 1] = 'A';
					} else if ((int) ch[lvllength - 1] > 90) {
						return null;
					}
					result = parent + ch[0];
					break;
				case 2:
					if ((int) ch[1] > 57 && (int) ch[1] < 65) {
						if ((int) ch[0] < 57) {
							ch[0] = (char) ((int) ch[0] + 1);
							ch[1] = '0';
						} else if ((int) ch[0] == 57) {
							ch[0] = 'A';
							ch[1] = '1';
						} else if ((int) ch[0] > 57 ) {
							ch[1] = 'A';
						}
					} else if ((int) ch[1] > 90) {
						if ((int) ch[0] < 57) {
							ch[0] = (char) ((int) ch[0] + 1);
							ch[1] = 'A';
						} else if ((int) ch[0] == 57) {
							ch[0] = 'A';
							ch[1] = '1';
						} else if ((int) ch[0] > 57 && (int) ch[0] >= 65
								&& (int) ch[0] < 90) {
							ch[0] = (char) ((int) ch[0] + 1);
							ch[1] = '1';
						} else if ((int) ch[0] == 90)
							return null;
					}
					result = parent + ch[0] + ch[1];
				}
			} else {
				String maxValue = "0000000000";
				child = (Integer.parseInt(child) + 1) + "";

				if (child.length() > lvllength)
					return null;
				else if (child.length() < lvllength) {
					child = maxValue.substring(0, (lvllength - child.length()))
							+ child;
				}
				result = parent + child;
			}

			return result;
		} catch (Exception ex) {
			return "overflowError";
		}
	}
}
