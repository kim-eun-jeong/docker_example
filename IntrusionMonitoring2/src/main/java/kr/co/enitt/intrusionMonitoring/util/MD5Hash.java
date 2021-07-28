package kr.co.enitt.intrusionMonitoring.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Hash {
	/**
	  * @Method Name : getMd5
	  * @작성일 : 2020. 8. 19.
	  * @작성자 : KEJ
	  * @변경이력 : 
	  * @Method 설명 : 비밀번호 암호화
	  * @param String str
	  * @return String
	  */
	public String getMd5(String str) {
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		}catch(NoSuchAlgorithmException e){
			SHA = null; 
		}
		return SHA;
	}

}