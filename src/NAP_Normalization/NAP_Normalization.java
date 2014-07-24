package NAP_Normalization;

import java.io.IOException;
import java.util.*;
public class NAP_Normalization {
	
	
	public enum STRING_METRICS {
	JARO_WINKLER, WAGNER_FISHER, DAMERAU_LAVENSHTEIN_DISTANCE,SIMPLE_FUZZY     
	}
	
	
	public static String normalize_business_name(String business_name,STRING_METRICS string_metric){
		switch(string_metric)
		{
		case JARO_WINKLER:
			break;
		case WAGNER_FISHER:
			break;
		case DAMERAU_LAVENSHTEIN_DISTANCE:
			break;
		case SIMPLE_FUZZY:
			break;
		}
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	// Validates Mobile number using regex and Normalize function.
	public static boolean isMobileNumber(String s) {
		    if (s.matches("\\d{10}"))
		    	return true;
		    //matches phone number separated by -,.,spaces
		    else if (s.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
		    	return true;
		    //matches +91 and the 10 digit number
		    else if (s.matches("\\+91\\d{10}"))
		    	return true;
		    //matches 0 and the 10 digit number
		    else if (s.matches("0\\d{10}"))
		    	return true;
		    //matches 0091 and the 10 digit number
		    else if (s.matches("0091\\d{10}"))
		    	return true;
		    else
		    	return false;
	 }

	@SuppressWarnings("null")
	public static String normalize_MobileNumber(String s)
	{
		if (s.matches("\\d{10}"))
	    	return "+91" + s;
	    //matches phone number separated by -,.,spaces
	    else if (s.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")){
	    	s = s.replaceAll("[-\\.\\s]", "");
	    	return "+91" + s; }
	    //matches +91 and the 10 digit number
	    else if (s.matches("\\+91\\d{10}"))
	    	return s;
	    //matches 0 and the 10 digit number
	    else if (s.matches("0\\d{10}")){
	    	StringBuilder sphone = new StringBuilder(s);
	    	sphone.deleteCharAt(0);
	    	return "+91" + sphone;}
	    //matches 0091 and the 10 digit number
	    else if (s.matches("0091\\d{10}")){
	    	StringBuilder sphone = new StringBuilder(s);
	    	sphone.deleteCharAt(0);sphone.deleteCharAt(0);
	    	return "+"+sphone;
	    }
	    else
	    	return s;
	}
	
	
	
	
	// Generic Fuzzy match.
	public static boolean fuzzyMatch(String str1,String str2)
	{
		ArrayList<String> a = new ArrayList<String> (Arrays.asList(str1.split(" ")));
		ArrayList<String> b = new ArrayList<String> (Arrays.asList(str2.split(" ")));
		boolean are_equal = true;
		boolean word_found = false;
		if (a.size() < b.size())
		{
		    for (String a_word : a)
		    {
		        word_found = false;
		        for (int i=0; i < b.size(); i++)
		        {
		            String b_word = b.get(i);
		            if (is_similar_enough (a_word, b_word))
		            {
		                word_found = true;
		                b.remove(i);
		                break;
		            }
		        }
		        if (!word_found)
		        {
		            are_equal = false;
		            break;
		        }
		    }
		}
		return are_equal;
	}
	
	public static boolean is_similar_enough (String a, String b)
	{
	    int equivlantChars = 0;

	    if (a.length() != b.length())
	        return false;

	    for (int i=0; i<a.length(); i++)
	        if (a.charAt(i) == b.charAt(i))
	            equivlantChars ++;

	    return ((((double)equivlantChars) / ((double)a.length())) >= 0.75);
	}
	

	public static void main(String[] args) throws IOException {
		String[] phoneNumbers = {"9000109998","900-010-9998","900.010.9998","900 010 9998",
				"+919000109998",
				"09000109998",
				"00919000109998"};
		for (int i=0;i<phoneNumbers.length;i++) {
		if (isMobileNumber(phoneNumbers[i]))
			System.out.println("True");System.out.println(normalize_MobileNumber(phoneNumbers[i]));
		}
		System.in.read();
		JARO_WINKLER xjw = new JARO_WINKLER();
		String s1="PHD - Pizza Hut Delivery"; String s2="Pizza Hut Delivery";
		System.out.println(xjw.similarity(s1.toUpperCase().replaceAll("[\\s]", ""), s2.toUpperCase().replaceAll("[\\s]", "")));
		WAGNER_FISHER xwf = new WAGNER_FISHER();
		System.out.println(xwf.similarity(s1.toUpperCase().replaceAll("[\\s]", ""), s2.toUpperCase().replaceAll("[\\s]", "")));
		Damerau_Lavenshtein_Distance dld = new Damerau_Lavenshtein_Distance(s1.toUpperCase().replaceAll("[\\s]", ""), s2.toUpperCase().replaceAll("[\\s]", ""));
		System.out.println(dld.getSimilarity());
	}
}
