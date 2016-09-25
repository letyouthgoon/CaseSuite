package net.asiasofti.android.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.*;

import java.util.List;
@Table(name = "Search")
public class Search extends Model
{
	@Column(name = "searchID")
	public int searchID;
	@Column(name = "searchKeyWord")
	public String searchKeyWord;

	public Search()
	{
	}

	public Search(String s)
	{
		searchKeyWord = s;
	}

	public static void deleteAll()
	{
		(new Delete()).from(net.asiasofti.android.model.Search.class).execute();
	}

	public static List searchQueryList()
	{
		return (new Select()).from(net.asiasofti.android.model.Search.class).orderBy("searchKeyWord desc").execute();
	}

	public static void searchSava(Search search)
	{
		new Search();
		search.save();
	}

	public String toString()
	{
		return (new StringBuilder("Search [searchID=")).append(searchID).append(", searchKeyWord=").append(searchKeyWord).append("]").toString();
	}
}
