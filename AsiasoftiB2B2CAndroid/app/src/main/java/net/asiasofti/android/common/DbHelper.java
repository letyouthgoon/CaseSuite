 /**
 * 
 *
 *
 * @copyright  Copyright (c) 2013-2014 Asiasofti Inc. (http://www.asiasofti.com)
 * @license    http://www.asiasofti.com
 * @link       http://www.asiasofti.com
 * @since      File available since Release v1.1
 */ 
package net.asiasofti.android.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.PrintStream;

public class DbHelper extends SQLiteOpenHelper
{

	private static String db_name = "b2b2c_shop.db";

	public DbHelper(Context context)
	{
		super(context, db_name, null, 2);
	}

	public DbHelper(Context context, String s, android.database.sqlite.SQLiteDatabase.CursorFactory cursorfactory, int i)
	{
		super(context, s, cursorfactory, i);
	}

	public SQLiteDatabase getSQLiteDatabase()
	{
		SQLiteDatabase sqlitedatabase;
		try
		{
			sqlitedatabase = getWritableDatabase();
		}
		catch (Exception exception)
		{
			return getReadableDatabase();
		}
		return sqlitedatabase;
	}

	public void onCreate(SQLiteDatabase sqlitedatabase)
	{
		System.out.println("DbHelper   oncreate-------");
	}

	public void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
	{
		System.out.println("DbHelper   onUpgrade-------");
		onCreate(sqlitedatabase);
	}

}
