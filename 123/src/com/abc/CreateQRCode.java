package com.abc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {



		public static void main(String arg[]) throws IOException{
			hfq(5,"D:/pr.png","255,0,0","0,0,255");
		}

		
		public static void hfq(int version,String path,String startRgb,String endRgb) throws IOException{ 
			Qrcode qrcode =new Qrcode();
			qrcode.setQrcodeVersion(version);
			qrcode.setQrcodeErrorCorrect('L');
			qrcode.setQrcodeEncodeMode('B');
			int imgSize=67+(version-1)*12;
			BufferedImage bufferedImage=new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
			Graphics2D gs=bufferedImage.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.setColor(Color.BLACK);
			gs.clearRect(0,0,200,200);
			String content="BEGIN:VCARD\r\n" + 
							   "FN:姓名:张亚迪\r\n"+
							   "ORG:学校:科师\r\n"+
							   "EMAIL;HOME:1730743643@qq.com\r\n" +  
							   "END:VCARD";

			int starR=0,starG=0,starB=0;
			if(null!= startRgb)
			{
				String[] startcolor=startRgb.split(",");
				starR=Integer.valueOf(startcolor[0]);
				starG=Integer.valueOf(startcolor[1]);
				starB=Integer.valueOf(startcolor[2]);			
			}
			int endR=0,endG=0,endB=0;
			if(null!= endRgb)
			{
				String[] endcolor=endRgb.split(",");
				endR=Integer.valueOf(endcolor[0]);
				endG=Integer.valueOf(endcolor[1]);
				endB=Integer.valueOf(endcolor[2]);			
			}
			boolean[][] calQrcode=qrcode.calQrcode(content.getBytes());
			int n=5;
			
			for(int i=0;i<calQrcode.length;i++){
				for(int j=0;j<calQrcode[i].length;j++){
					if(calQrcode[i][j]){
						
						int r=starR+(endR-starR)*(j+1)/calQrcode.length ;
						int g=starG+(endG-starG)*(j+1)/calQrcode.length ;
						int b=starB+(endB-starB)*(j+1)/calQrcode.length;
						
						Color color=new Color(r,g,b);
						gs.setColor(color);
						gs.fillRect(i*5+n, j*5+n, 5, 5);
					};
				}
			}
			BufferedImage logo=ImageIO.read(new File("D:/logo.jpg"));
			int logoSize=imgSize/2-20;
			int o=(imgSize-logoSize);
			gs.drawImage(logo, o, o, logoSize, logoSize, null);
			gs.dispose();
			bufferedImage.flush();
			try{
				ImageIO.write(bufferedImage,"png",new File(path));
			}catch(IOException e){
				e.printStackTrace();
			}
			System.out.println("OK");

		}

	
	}


