 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.model;


public class Spec
{

	private String SpecID;
	private String SpecName;

	public Spec()
	{
	}

	public Spec(String s, String s1)
	{
		SpecID = s;
		SpecName = s1;
	}

	public String getSpecID()
	{
		return SpecID;
	}

	public String getSpecName()
	{
		return SpecName;
	}

	public void setSpecID(String s)
	{
		SpecID = s;
	}

	public void setSpecName(String s)
	{
		SpecName = s;
	}

	public String toString()
	{
		return (new StringBuilder("Spec [SpecID=")).append(SpecID).append(", SpecName=").append(SpecName).append("]").toString();
	}
}
