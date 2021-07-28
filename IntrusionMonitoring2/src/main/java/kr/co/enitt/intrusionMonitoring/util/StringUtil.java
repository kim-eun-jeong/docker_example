package kr.co.enitt.intrusionMonitoring.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class StringUtil {
	/**
	 * 문자열중 지정한 문자열을 찾아서 새로운 문자열로 바꾸는 함수
	 * @param original - 대상 문자열
	 * @param oldstr   - 찾을 문자열
	 * @param newstr   - 바꿀 문자열
	 * @return         - 바뀐 결과
	 */
	public static String replace(String original, String oldstr, String newstr) {
		String convert = new String();
		int pos = 0;
		int begin = 0;
		pos = original.indexOf(oldstr);

		if (pos == -1)
			return original;

		while (pos != -1) {
			convert = convert + original.substring(begin, pos) + newstr;
			begin = pos + oldstr.length();
			pos = original.indexOf(oldstr, begin);
		}
		convert = convert + original.substring(begin);

		return convert;
	}

	/**
	 * 내용중 HTML 툭수기호인 문자를 HTML 특수기호 형식으로 변환합니다.
	 * @param htmlstr - 바꿀 대상인 문자열
	 * @return        - 바뀐 결과
	 */
	public static String convertHtmlchars(String htmlstr) {
		String convert = new String();
		convert = replace(htmlstr, "&nbsp;", " ");
		convert = replace(convert, "&amp;", "&");
		convert = replace(convert, "&", "&amp;");
		convert = replace(convert, "<", "&lt;");
		convert = replace(convert, ">", "&gt;");
		convert = replace(convert, "(", "&#40;");
		convert = replace(convert, ")", "&#41;");
		convert = replace(convert, "\"", "&quot;");
		convert = replace(convert, "SCRIPT", "");
		convert = replace(convert, "script", "");
		convert = replace(convert, "Script", "");
		return convert;
	}

	/**
	 * 
	 */
	public static String reverseHtmlchars(String htmlstr) {
		String convert = new String();
		convert = replace(htmlstr, "&lt;", "<");
		convert = replace(convert, "&gt;", ">");
		convert = replace(convert, "&amp;", "&");
		convert = replace(convert, "&#40;", "(");
		convert = replace(convert, "&#41;", ")");
		convert = replace(convert, "&quot;", "\"");
		return convert;
	}
	
	public static String SearchXSS(String str) {
		if(str != null){
			str = str.trim();
			str = str.replace("<", "&lt;"); 
			str = str.replace(">", "&gt;"); 
			str = str.replace("\"", "&quot;");
			str = str.replace("(", "&#40;");
			str = str.replace(")", "&#41;");
			str = str.replace("#", "&#35;");
			str = str.replace("&", "&amp;");
			str = str.replace("alert", "");
		}else{
			str = "";
		}
		return str;
	}
	
	//
	public static String convertXss(String str) {
		if(str != null){
			str = str.trim();
			str = str.replace("<", "&lt;"); 
			str = str.replace(">", "&gt;"); 
		}else{
			str = "";
		}
		return str;
	}

	public String setResultHtml(String str) {
		str = str.toLowerCase();
		str = str.replaceAll("<br>", "<br/>");
		return str;
	}
	
	public String nl2br(String str) {
		str = str.replaceAll("\r\n", "<br/>");
		str = str.replaceAll("\r", "<br/>");
		str = str.replaceAll("\n", "<br/>");
		return str;
	}
	
	public static String nl2brStatic(String str) {
		str = str.replaceAll("\r\n", "<br/>");
		str = str.replaceAll("\r", "<br/>");
		str = str.replaceAll("\n", "<br/>");
		return str;
	}
	
	public String convertLinkURL(String htmlstr)
	{
		return replace(htmlstr, "&amp;", "&");
	}
	
	public String convertURL(String htmlstr)
	{
		return replace(htmlstr, "&", "&amp;");
	}
	
	public String convertAmpURL(String htmlstr){
		htmlstr = htmlstr.replaceAll("&amp;", "&");
		htmlstr = htmlstr.replaceAll("&", "&amp;");
		return htmlstr;
	}

	// HTML 소스 지우기 시작
	public static String setHtmlDelete(String str) {
		str = reverseHtmlchars(str);
		return str.replaceAll("<[^<|>]*>", "");
	}
	
	// 확장자 추출
	public String fileExpansion(String str){
		String fileExpansion = "";
		if(str.lastIndexOf(".") > -1){
			fileExpansion = str.substring(str.lastIndexOf(".")+1);
			fileExpansion = fileExpansion.toLowerCase();
		}
		return fileExpansion;		
	}
	
	//
	public String getFileSize(String context, String b_type, String bid, String file_name) {
		File file = new File(context+"upload/"+b_type+"/"+bid+"/"+file_name);
		String format = "###,###,###,###";
		java.text.DecimalFormat df = new java.text.DecimalFormat(format);
		long size = file.length();
		String type = "";
		String str;
		
		if(size > 999999){
			size = size / 1000000;
			type = "MByte";
		}else if(size > 999){
			size = size / 1000;
			type = "KByte";
		}else{
			size = size;
			type = "Byte";
		}
		str = "("+df.format(size)+type+")";
		
		return str;
	}

	public static String changeColor(String all_str, String str) {
		return replace(all_str, str, "<strong>"+str+"</strong>");
	}
	
	public static String changeStr(String all_str, String str) {
		return replace(all_str, str, "</li><li>");
	}
	public static String changeString(String all_str, String str, String newString) {
		return replace(all_str, str, newString);
	}

	public static String urlEncoder(String str) throws Exception {
		return URLEncoder.encode(str, "UTF-8");
	}

	public static String urlDecoder(String str) throws Exception {
		return URLDecoder.decode(str, "UTF-8");
	}
	
	/**
	 * 
	 * @param strData
	 * @param iStartPos
	 * @param iByteLength
	 * @return
	 */
	public static String subString(String strData, int iStartPos, int iByteLength) {
		StringUtil stringUtils = new StringUtil();
		byte[] bytTemp = null;
		int iRealStart = 0;
		int iRealEnd = 0;
		int iLength = 0;
		int iChar = 0;
		
		try {
			// UTF-8로 변환하는경우 한글 2Byte, 기타 1Byte로 떨어짐
			bytTemp = strData.getBytes("EUC-KR");
			iLength = bytTemp.length;

			for(int iIndex = 0; iIndex < iLength; iIndex++) {
				if(iStartPos <= iIndex) {
					break;
				}
				iChar = (int)bytTemp[iIndex];
				if((iChar > 127)|| (iChar < 0)) {
					// 한글의 경우(2byte 통과처리)
					// 한글은 2Byte이기 때문에 다음 글자는 볼것도 없이 스킵한다
					iRealStart++;
					iIndex++;
				} else {
					// 기타 글씨(1Byte 통과처리)
					iRealStart++;
				}
			}
			
			iRealEnd = iRealStart;
			int iEndLength = iRealStart + iByteLength;
			for(int iIndex = iRealStart; iIndex < iEndLength; iIndex++)
			{	
				iChar = (int)bytTemp[iIndex];
				if((iChar > 127)|| (iChar < 0)) {
					// 한글의 경우(2byte 통과처리)
					// 한글은 2Byte이기 때문에 다음 글자는 볼것도 없이 스킵한다
					iRealEnd++;
					iIndex++;
				} else {
					// 기타 글씨(1Byte 통과처리)
					iRealEnd++;
				}
			}
		} catch(IOException e) { ObjectUtil.log(e.getMessage());}
		catch (RuntimeException e) { ObjectUtil.log(e.getMessage()); }
		
		String shortNm = "";
		if(strData.length() > iRealEnd){
			shortNm = "..";
		}

		return strData.substring(iRealStart, iRealEnd) + shortNm;
	}
	
	//특수기호 처리
	public static String StringSignReplace(String str) {       
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		str = str.replaceAll(match, "");
		return str;
	}
	
	//대소문자 처리
	public static String StringLetterReplace(String str) {       
		String match = "[^a-zA-Z]";
		str = str.replaceAll(match, "");
		return str;
	}

	//숫자 처리
	public static String StringNumbericReplace(String str) {       
		String match = "[^\\d]";
		str = str.replaceAll(match, "");
		return str;
	}
	
	//숫자 처리 (기본값 지정)
	public static String StringNumbericReplace(String str, int def) {       
		String match = "[^\\d]";
		str = str.replaceAll(match, "");
		if(str.equals("")||str.equals(null)) {
			str = String.valueOf(def);
		}
		return str;
	}
	
	//한글 처리
	public static String StringKoreanReplace(String str) {       
		String match = "[^ㄱ-ㅎㅏ-ㅣ가-힣]";
		str =str.replaceAll(match, "");
		return str;
	}
	
	public static String getCommaSplit(String obj){ 
		String str = "";
		if(!ObjectUtil.isEmpty(obj)) {
			String strArr[] = obj.replaceAll("'","").split(",");
			for(int i = 0; i < strArr.length; i++) {
				if(i > 0) {
					str += ",";
				}
				str += "'"+strArr[i]+"'";
			}
		}
		return str;
	}	
	
	public static Boolean getComparison(String obj, String compVal, Boolean nullChk){
		if(nullChk == null) {
			nullChk = true;
		}
		
		if(!"".equals(ObjectUtil.toString(obj, ""))) {
			if(obj.equals(compVal)) {
				return true;
			}else {
				return false;
			}
		}else { 
			if(nullChk) {
				return false;
			}else {
				return true;
			}
		}
	}
	
	public static int getInt(String obj){
		int size = 0;
		if(obj != null){
			size = Integer.parseInt(obj);
			if (size < 0) {
				return 0;
			}
		}
		return size;
	}
}
