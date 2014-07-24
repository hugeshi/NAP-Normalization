package NAP_Normalization;

public class WAGNER_FISHER {
	public int similarity(String s1, String s2) {
		int[][] d = new int[s1.length()][s2.length()];
		for(int i=0;i<s1.length();i++)
		{
			d[i] = new int[s2.length()];
			d[i][0] = i;
			for(int j=0;j<s2.length();j++)
			{
				try
				{
				d[0][j] = j;
				}
				catch ( Exception ae){}
			}
		}
		
		for(int j=0;j<s2.length();j++)
		{
			for(int i=0;i<s1.length();i++)
			{
				if ( s1.charAt(i) == s2.charAt(j))
				{
					try{
					d[i][j] = d[i-1][j-1];
					}
					catch (Exception ae){}
				}
				else
				{
					try{
					d[i][j] = minimum (d[i-1][j]+1,d[i][j-1],d[i-1][j-1]+1);
					}
					catch (Exception ae){}
				}
			}
		}
		return d[s1.length()-1][s2.length()-1];
	}
		
	private int minimum(int i,int j , int k)
	{
		int minimum=0;
		if ( i <= j) { minimum = i; if (i>=k) minimum =k;}
		else if (i > j) { minimum = j; if (j>=k) minimum =k;}
		return minimum;
	}

}
