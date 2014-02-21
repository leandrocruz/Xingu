package br.com.ibnetwork.xingu.utils;

import java.util.Random;

public class NumberUtils
{
	private static Random rnd = new Random(System.currentTimeMillis());

	/*
	 * Generates a random number between start/end
	 */
	public static long rnd(int start, int end)
	{
		int diff = end - start;
		return start + rnd.nextInt(diff + 1);
	}

    public static int toInt(String value, int defaultValue)
    {
        try
        {
            return Integer.parseInt(value);
        }
        catch(Throwable t)
        {
            return defaultValue;
        }
    }

    public static long toLong(String value, long defaultValue)
    {
    	value = StringUtils.trimToNull(value);
    	if(value == null)
    	{
    		return defaultValue;
    	}
    	
        try
        {
            return Long.parseLong(value);
        }
        catch(Throwable t)
        {
            return defaultValue;
        }
    }

}
