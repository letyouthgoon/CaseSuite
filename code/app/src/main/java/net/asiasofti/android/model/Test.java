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


public class Test
{

	private String name;

	public Test()
	{
	}

	public Test(String s)
	{
		name = s;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String s)
	{
		name = s;
	}

	public String toString()
	{
		return (new StringBuilder("Test [name=")).append(name).append("]").toString();
	}
}
