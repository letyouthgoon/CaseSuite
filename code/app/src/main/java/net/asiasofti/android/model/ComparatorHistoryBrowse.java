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

import java.util.Comparator;



class ComparatorHistoryBrowse
	implements Comparator
{

	ComparatorHistoryBrowse()
	{
	}

	public int compare(Object obj, Object obj1)
	{
		HistoryBrowse historybrowse = (HistoryBrowse)obj;
		HistoryBrowse historybrowse1 = (HistoryBrowse)obj1;
		return historybrowse.getId().compareTo(historybrowse1.getId());
	}
}
