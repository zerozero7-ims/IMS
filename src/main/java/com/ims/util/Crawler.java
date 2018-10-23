package com.ims.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.ims.dao.INewsDAO;
import com.ims.entity.Works;

public class Crawler {
	
	
//	public static List<Map<String,Object>> getCrawlerList(String url){
//		int i=0;
//		List<News> newslist = new ArrayList<News>();
//		boolean flag = true;
//		
//		WebClient webClient = new WebClient(BrowserVersion.CHROME);
//		try{
//			webClient.getOptions().setCssEnabled(false);
//			webClient.getOptions().setJavaScriptEnabled(true);
//			webClient.getOptions().setThrowExceptionOnScriptError(false);
//			HtmlPage htmlPage = (HtmlPage)webClient.getPage(url);
//			List<News> newsl = getInfoByJsoup(htmlPage.getUrl().toString());
//			for(Map<String,Object> map : infolist){
//				String targetdate = map.get("data").toString();
//				if(startdate.compareTo(targetdate)>0){
//					flag = false;
//					break;
//				}
//				if(enddate.compareTo(targetdate)>0){
//					String imgUrl = map.get("tu").toString();
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//					String imgName = df.format(new Date())+(i++)+imgUrl.substring(imgUrl.lastIndexOf('.')+1,imgUrl.length());
//					String filePath = "D:/MyEclipse 2015/Workspaces/kjhb/WebContent/upload/pic";
//				//	downImages(filePath,imgUrl,imgName);
//				//	map.put("tu",imgName);
//				
//					map.put("id", i);
//					crawlerlist.add(map);
//				}
//				
//			}
//			
//			
//			while(flag){
//				HtmlAnchor ach = htmlPage.getAnchorByText("下一页 »");
//				htmlPage = ach.click();
//				infolist = getInfoByJsoup(htmlPage.getUrl().toString(), "国家");
//				for(Map<String, Object> map :infolist){
//					String targetdate = map.get("data").toString();
//					if(startdate.compareTo(targetdate)>0){
//						flag = false;
//						break;
//					}
//					if(enddate.compareTo(targetdate)>0){
//						String imgUrl = map.get("tu").toString();
//						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//						String imgName = df.format(new Date())+(i++)+imgUrl.substring(imgUrl.lastIndexOf('.')+1,imgUrl.length());
//						String filePath = "D:/MyEclipse 2015/Workspaces/kjhb/WebContent/upload/pic";
//					//	downImages(filePath,imgUrl,imgName);
//					//	map.put("tu",imgName);
//					
//						map.put("id", i);
//						crawlerlist.add(map);
//					}
//				}
//			}
//			
//		}catch(FailingHttpStatusCodeException e){
//			e.printStackTrace();
//		}catch(MalformedURLException e){
//			e.printStackTrace();
//		}catch(IOException e){
//			e.printStackTrace();
//		}finally{
//			webClient.close();
//		}
//		
//		
//		return crawlerlist;
//	}
	
	
	/**
	 * @description 通过Jsoup来抓取网页信息
	 * @param introUrl
	 * @param jb
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Works> getInfoByJsoup(String introUrl){
		List<Works> workslist = new ArrayList<Works>();
		Map<String,String> month = new HashMap<String,String>();
		month.put("January", "01");
		month.put("February", "02");
		month.put("March", "03");
		month.put("April", "04");
		month.put("May", "05");
		month.put("June", "06");
		month.put("July", "07");
		month.put("August", "08");
		month.put("September", "09");
		month.put("October", "10");
		month.put("November", "11");
		month.put("December", "12");
		try{
			for(int i=1;i<=5;i++){
				String url = introUrl+i+"/";
				Document doc = Jsoup.connect(url).get();
				Elements elements=doc.getElementsByAttributeValue("class","blog-wrapper");
				for(Element element:elements){	
//					Map<String,Object> map = new HashMap<String, Object>();
//					
//					map.put("tu",element.getElementsByTag("img").attr("abs:src"));//获取每个img标签URL，"abs:"表示绝对路径
//					map.put("title",element.getElementsByTag("a").attr("title"));
//					map.put("data",element.getElementsByTag("i").text());
//					map.put("zy",element.getElementsByTag("p").text());//文章摘要
//					map.put("jb",jb);//政策级别
//					map.put("content", getContentByJsoup(element.getElementsByTag("a").attr("abs:href")));//获取文章具体内容
//					infolist.add(map);
					
					Works news = new Works();
					news.setCid(14);
					news.setTitle(element.getElementsByTag("h4").text());
					news.setAuthor("省产研院");
					
//					String imgUrl = element.getElementsByTag("img").attr("abs:src");
//					long datetime = new Date().getTime();
//					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//					String imgName = datetime+(j++)+imgUrl.substring(imgUrl.lastIndexOf('.'),imgUrl.length());
//					String filePath = "D:/MyEclipse 2015/Workspaces/IMS Maven Webapp/src/main/webapp/images/news";
//					downImages(filePath,imgUrl,imgName);					
//					news.setThumbnail(imgName);
					Elements e1=element.getElementsByClass("blog-time");
					String day = e1.get(0).getElementsByTag("h3").text();
					String ym = e1.get(0).getElementsByTag("p").text();
					String[] yms = ym.split(",");
					String month1 = month.get(yms[0].trim());
					String year = yms[1].trim();
					String date=year+"-"+month1+"-"+day;
					System.out.println("day: "+day+"  month: "+month1+"    year: "+year+"   date:"+date);
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
					try {
						news.setCreatetime(sdf.parse(date));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					news.setAbst(element.getElementsByAttributeValue("style","font-size:0.9em; line-height:1.4em;").text());
					//map.put("content", getContentByJsoup(element.getElementsByTag("a").attr("abs:href")));//获取文章具体内容
					news.setContent(getContentByJsoup(element.getElementsByAttributeValue("style","font-size:0.8em;").attr("abs:href")));
					news.setType(1);
					news.setRd(100);
					news.setUpdatetime(new Date());
					news.setDisplay(1);
					workslist.add(news);
					
				}
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return workslist;
		
	}
	
	/**
	 * @description 获取带文本格式的文章具体内容
	 * @param introUrl
	 * @return
	 */
	public static String getContentByJsoup(String introUrl){
		String content="";
		try{
			Document doc = Jsoup.connect(introUrl).get();
			Element element=doc.getElementsByAttributeValue("class","blog-title").get(0);
		//	Elements elements=doc.getElementsByAttributeValue("class","ccontent center");
			Elements elements = element.getElementsByTag("p");
			for(Element e:elements){
				
				
				content+= "<p>"+e.html()+"</p>";
				
	
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return content;
	}
	
	
	/**
	 * @description 保存图片到本地
	 * @param filePath 保存图片路径
	 * @param imgUrl 下载图片的url
	 */
	public static void downImages(String filePath,String imgUrl,String imgName){
		//若指定文件夹不存在，则先创建
		File dir = new File(filePath); 
		if(!dir.exists()){
			dir.mkdirs();
		}
		//截取图片文件名
	//	String fileName = imgUrl.substring(imgUrl.lastIndexOf('/')+1, imgUrl.length());
		// 写出的路径
		File file = new File(filePath + File.separator+imgName);
		try{
			// 获取图片URL
			URL url = new URL(imgUrl);
			// 获得连接
			URLConnection connection = url.openConnection();
			// 设置10秒的相应时间
			connection.setConnectTimeout(10*1000);
			// 获得输入流
			InputStream in = connection.getInputStream();
			 // 获得输出流
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			// 构建缓冲区
			byte[] buf = new byte[1024];
			int size;
			// 写入到文件
			while(-1!=(size=in.read(buf))){
				out.write(buf,0,size);
			}
			out.close();
			in.close();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

	
	@Autowired
	private INewsDAO newsDAO;
	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		
		
		
		
	} 

}
