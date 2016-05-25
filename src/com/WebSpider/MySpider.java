package com.WebSpider;

import java.util.ArrayList;

import com.Excel.ExcelOperate;

public class MySpider {

	public void MySourceforgeSpider() throws Exception {
		SourceforgeSpider s = new SourceforgeSpider();
		String[] urls = { "https://sourceforge.net/p/camstudio/bugs/76/" };
		s.setWords("<div class=\"view_holder\">", "\"ticket_content\"");
		s.setUrls(urls);
		s.setBeginNum(76);
		s.start();
		System.out.println("Success");
	}
	
	public void MyApacheSpider() throws Exception {
		ApacheSpider s = new ApacheSpider();
		String[] urls = { "https://issues.apache.org/jira/si/jira.issueviews:issue-html/AMQ-6303/AMQ-6303" };
		String beginWords="formtitle";
		String endWords="Remaining Estimate";
		s.setWords(beginWords,endWords);
		s.setUrls(urls);
		s.setBeginNum(6303);
		s.start();
		System.out.println("Success");
	}
	
	
	public static void main(String[] args) throws Exception {
		MySpider m=new MySpider();
		m.MyApacheSpider();
	}
}

class ApacheSpider extends WebSpider {

	@Override
	public void start() throws Exception {
		ExcelOperate e = new ExcelOperate();
		StringBuffer contents;
		String url;
		for (int i = 0; i < urls.length; i++) {
			rows = 0;
			map.clear();
			url = urls[i];
			String[] tmp = url.split("/");
			String numtmp=tmp[tmp.length - 1].split("-")[1];
			int sum = Integer.parseInt(numtmp);
			url = url.replaceAll(numtmp, beginwith+"");
			String fileName = tmp[tmp.length - 1].split("-")[0] + ".xls";
			
			//System.out.println(fileName);
			//System.out.println(sum);
			contents = MySpider(url, sum);
			e.writeNewExcel(fileName, contents);
		}
	}

	@Override
	public String getLine(String s, int num) {
		s=s.replaceAll("<[^>]*>", "");
		s=s.replaceAll("	", "");
		String[] lines=s.split("\n|&nbsp;");
		ArrayList<String> usefulLine = new ArrayList<String>();
		String line="";
		boolean mark=false;
		for(int i=0;i<lines.length;i++){
			if(lines[i].contains("Created")) mark=true;
			if(lines[i].contains("Estimate")) mark=false;
			
			if(mark&&lines[i].length()>0){
				usefulLine.add(lines[i]);
			}
		}
		if(line.length()<1){
			line+="number#Created#Updated#Resolved#Status#Project#Components#Affects Versions#Fix Versions#Type#Priority#Reporter#Assignee#Resolution#Votes#Labels\n";
		}
		
		
		
		for(int i=0;i<usefulLine.size();i++){
			System.out.println(i+": "+usefulLine.get(i));
		}
		
		
		
		
		return null;
	}

	@Override
	public String getNextUrl(String url, int num) {
		return url.replaceAll((num-1)+"", num+"");
	}

}

class SourceforgeSpider extends WebSpider {

	@SuppressWarnings("unused")
	private class theUrls {
		String[] urls = { "https://sourceforge.net/p/winpython/tickets/126/",
				"https://sourceforge.net/p/ffdshow-tryout/bugs/362/",
				"https://sourceforge.net/p/camstudio/bugs/76/",
				"https://sourceforge.net/p/dvdstyler/bugs/613/",
				"https://sourceforge.net/p/pdfsam/bugs/104/",
				"https://sourceforge.net/p/libjpeg-turbo/bugs/89/",
				"https://sourceforge.net/p/avisynth2/bugs/227/",
				"https://sourceforge.net/p/bonkenc/bugs/121/",
				"https://sourceforge.net/p/smplayer/bugs/737/",
				"https://sourceforge.net/p/keepass/bugs/1451/",
				"https://sourceforge.net/p/gnuwin32/bugs/562/",
				"https://sourceforge.net/p/freemind/bugs/1249/",
				"https://sourceforge.net/p/bom/bugs/549/",
				"https://sourceforge.net/p/aresgalaxy/bugs/234/",
				"https://sourceforge.net/p/googlesyncmod/bugs/811/",
				"https://sourceforge.net/p/elastix/bugs/132/",
				"https://sourceforge.net/p/gnuarmeclipse/bugs/195/",
				"https://sourceforge.net/p/gnuwin32/bugs/562/",
				"https://sourceforge.net/p/npp-plugins/bugs/306/",
				"https://sourceforge.net/p/codeblocks/tickets/317/",
				"https://sourceforge.net/p/keepass/bugs/1451/",
				"https://sourceforge.net/p/freeplane/bugs/1527/",
				"https://sourceforge.net/p/gsoap2/bugs/1039/",
				"https://sourceforge.net/p/eclipse-cs/bugs/401/",
				"https://sourceforge.net/p/jmol/bugs/573/",
				"https://sourceforge.net/p/italc/bugs/282/",
				"https://sourceforge.net/p/qucs/bugs/188/",
				"https://sourceforge.net/p/tcpdf/bugs/1097/",
				"https://sourceforge.net/p/celestia/bugs/266/",
				"https://sourceforge.net/p/texniccenter/bugs/356/",
				"https://sourceforge.net/p/crengine/bugs/366/",
				"https://sourceforge.net/p/gnuplot/bugs/1712/",
				"https://sourceforge.net/p/circuit/bugs/138/",
				"https://sourceforge.net/p/tuxpaint/bugs/209/",
				"https://sourceforge.net/p/mumble/bugs/1055/",
				"https://sourceforge.net/p/nestopia/bugs/149/",
				"https://sourceforge.net/p/celestia/bugs/266/",
				"https://sourceforge.net/p/vba/bugs/258/",
				"https://sourceforge.net/p/vbam/bugs/206/",
				"https://sourceforge.net/p/tuxpaint/bugs/209/",
				"https://sourceforge.net/p/dosbox/bugs/435/",
				"https://sourceforge.net/p/desmume/bugs/1539/",
				"https://sourceforge.net/p/ultrastardx/bugs/239/",
				"https://sourceforge.net/p/celestia/bugs/266/",
				"https://sourceforge.net/p/jfreechart/bugs/1147/",
				"https://sourceforge.net/p/djvu/bugs/272/",
				"https://sourceforge.net/p/crengine/bugs/366/",
				"https://sourceforge.net/p/glew/bugs/254/",
				"https://sourceforge.net/p/meshlab/bugs/452/",
				"https://sourceforge.net/p/gnuplot/bugs/1712/",
				"https://sourceforge.net/p/tuxpaint/bugs/209/",
				"https://sourceforge.net/p/freemind/bugs/1249/",
				"https://sourceforge.net/p/sweethome3d/bugs/652/",
				"https://sourceforge.net/p/celestia/bugs/1/",
				"https://sourceforge.net/p/jfreechart/bugs/1/",
				"https://sourceforge.net/p/jtds/bugs/751/",
				"https://sourceforge.net/p/gretl/bugs/199/",
				"https://sourceforge.net/p/maxima/bugs/3098/",
				"https://sourceforge.net/p/librecad/bugs/575/",
				"https://sourceforge.net/p/gnuplot/bugs/1/",
				"https://sourceforge.net/p/freemind/bugs/1/",
				"https://sourceforge.net/p/sweethome3d/bugs/1/",
				"https://sourceforge.net/p/awstats/bugs/947/",
				"https://sourceforge.net/p/jxplorer/bugs/138/",
				"https://sourceforge.net/p/passwordsafe/bugs/1327/",
				"https://sourceforge.net/p/clamwin/bugs/420/",
				"https://sourceforge.net/p/keepass/bugs/1/",
				"https://sourceforge.net/p/ultradefrag/bugs/216/",
				"https://sourceforge.net/p/vba/bugs/1/",
				"https://sourceforge.net/p/winmerge/bugs/2179/",
				"https://sourceforge.net/p/desmume/bugs/1/",
				"https://sourceforge.net/p/sevenzip/bugs/1644/",

		};
	}

	@Override
	public void start() throws Exception {

		ExcelOperate e = new ExcelOperate();
		StringBuffer contents;
		String url;
		for (int i = 0; i < urls.length; i++) {
			rows = 0;
			map.clear();
			url = urls[i];
			String[] tmp = url.split("/");
			int sum = Integer.parseInt(tmp[tmp.length - 1]);
			url = url.replaceAll(tmp[tmp.length - 1], "");
			String fileName = tmp[4] + ".xls";
			System.out.println(fileName);
			System.out.println(sum);
			contents = MySpider(url, sum);
			e.writeNewExcel(fileName, contents);
		}

	}

	@Override
	public String getLine(String s, int num) {
		String line = "";
		String[] lines = s.split("\n");
		int i;
		ArrayList<String> usefulLine = new ArrayList<String>();

		for (i = 0; i < lines.length; i++) {
			if (!lines[i].contains("UTC"))
				lines[i] = lines[i].replaceAll("<[^>]*>", "");
			if (lines[i].length() > 0)
				usefulLine.add(lines[i].replaceAll("<[^\"]*\"", "").replaceAll(
						"\">", ""));
		}
		// getLabel
		if (num == beginwith || map.size() == 0) {
			line += "number:";
			for (i = 0; i < usefulLine.size(); i++) {
				if (usefulLine.get(i).contains(":")
						&& !usefulLine.get(i).contains("UTC")) {
					line += usefulLine.get(i);
					rows++;
					map.put(usefulLine.get(i).replaceAll(" ", ""), rows);
				}
			}
			line += "\n";
		}
		line = line.replaceAll(":", "#");
		// getData
		line += num + "#";
		String[] tmp = new String[rows + 1];
		for (i = 0; i < tmp.length; i++)
			tmp[i] = "";
		String title = "";
		for (i = 0; i < usefulLine.size(); i++) {
			if (usefulLine.get(i).contains(":")
					&& !usefulLine.get(i).contains("UTC")) {
				title = usefulLine.get(i).replaceAll(" ", "");
				continue;
			}

			if (i < usefulLine.size()) {
				if (usefulLine.get(i).contains(":")
						&& usefulLine.get(i).contains("UTC")) {
					if (map.containsKey(title))
						tmp[map.get(title)] += usefulLine.get(i).replaceAll(
								"UTC", "");
					i++;
				} else if (!usefulLine.get(i).contains(":")) {
					if (map.containsKey(title))
						tmp[map.get(title)] += usefulLine.get(i) + " ";
				}

			}

		}
		for (i = 1; i < tmp.length; i++) {
			line += tmp[i] + "#";
		}
		return line;

	}

	
	@Override
	public String getNextUrl(String url, int num) {
		return url + num;
	}

}

class ShutDown {
	public void doShutDown() {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("shutdown.exe -s -t 40");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}