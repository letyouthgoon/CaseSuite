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

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BBCodeHelper
{

	private static Pattern bbcode = Pattern.compile("\\[[^\\[\\]]*\\]");
	private static String img_html = "<img src=''{0}''/>";
	private static String link_html = "<a href=''{0}''>{1}</a>";
	private static Pattern pattern_attach = Pattern.compile("\\[attach\\]([^\\[\\]]*)\\[/attach\\]", 8);
	private static Pattern pattern_href = Pattern.compile("^http://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$");
	private static Pattern pattern_link = Pattern.compile("\\[url=([^\\[\\]]*)\\]([^\\[\\]]*)\\[/url\\]", 8);
	private static Pattern pattern_ncsmiley = Pattern.compile("\\[ncsmiley\\]([^\\[\\]]*)\\[/ncsmiley\\]", 8);
	private static Pattern pattern_pic = Pattern.compile("\\[img\\]([^\\[\\]]*)\\[/img\\]", 8);

	public BBCodeHelper()
	{
	}

	public static String ignoreBBCode(String s)
	{
		return bbcode.matcher(s).replaceAll("");
	}

	public static void main(String args[])
	{
		LinkedHashSet linkedhashset = new LinkedHashSet();
		System.out.println(processBBCode("a[ncsmiley]http://www.tjitcast.com/image/cc.gif[/ncsmiley]a ad[url=aaaa]aaaa[/url]fad  adsfad[ncsmiley]http://www.tjitcast.com/image/dd.gif[/ncsmiley] opwer[img]http://www.phpbb.com/images/phplogo.gif[/img]sadf[img]http://www.phpbb.com/images/phplogo.gif[/img]sa[img]http://www.phpbb.com/images/111.gif[/img]df[url=bbb]bbb[/url]", linkedhashset));
		System.out.println(linkedhashset);
	}

	public static String parseHtml(String s)
	{
		String as[];
		String s1;
		int i;
		if (s.indexOf("[/img]") > -1)
		{
			as = s.replaceAll("\\[/img\\]", "' />///").split("\\///");
			s1 = "";
			i = 0;
		} else
		{
			return s;
		}
		do
		{
			if (i >= as.length)
				return s1;
			int j = as[i].indexOf("[img");
			if (j > -1)
			{
				String s2 = as[i].substring(j);
				String s3 = as[i].replace(s2, "");
				int k = s2.indexOf("]");
				if (k > -1)
					s2 = s2.replace(s2.substring(0, k + 1), "<img src='");
				s1 = (new StringBuilder(String.valueOf(s1))).append(s3).append(s2).toString();
			} else
			{
				s1 = (new StringBuilder(String.valueOf(s1))).append(as[i]).toString();
			}
			i++;
		} while (true);
	}



	public static String processAttach(String s)
	{
		return s.replaceAll(pattern_attach.pattern(), "");
	}

	public static String processBBCode(String s)
	{
		System.out.println((new StringBuilder("src==>")).append(s).toString());
		return ignoreBBCode(processAttach(processNcsmiley(processURL(parseHtml(s.replaceAll("\\x0a|\\x0d", ""))))));
	}

	public static String processBBCode(String s, LinkedHashSet linkedhashset)
	{
		return ignoreBBCode(processAttach(processNcsmiley(processURL(parseHtml(processImage(s.replaceAll("\\x0a|\\x0d", ""), linkedhashset))))));
	}

	public static String processImage(String s, LinkedHashSet linkedhashset)
	{
		Matcher matcher = pattern_pic.matcher(s);
		do
		{
			String s1;
			do
			{
				if (!matcher.find())
					return matcher.replaceAll("");
				s1 = matcher.group(1);
			} while (!pattern_href.matcher(s1).matches());
			linkedhashset.add(s1);
		} while (true);
	}

	public static String processNcsmiley(String s)
	{
		Matcher matcher = pattern_ncsmiley.matcher(s);
		do
		{
			if (!matcher.find())
				return s;
			String s1 = matcher.group(1);
			s = matcher.replaceFirst(MessageFormat.format(img_html, new Object[] {
				s1
			}).replaceAll("\\\\", "/"));
			matcher = pattern_ncsmiley.matcher(s);
		} while (true);
	}

	public static String processURL(String s)
	{
		Matcher matcher = pattern_link.matcher(s);
		do
		{
			if (!matcher.find())
				return s;
			String s1 = matcher.group(1);
			String s2 = matcher.group(2);
			s = matcher.replaceFirst(MessageFormat.format(link_html, new Object[] {
				s1, s2
			}));
			matcher = pattern_link.matcher(s);
		} while (true);
	}

}
