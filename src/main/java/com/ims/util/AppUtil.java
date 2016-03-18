package com.ims.util;

import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppUtil {

	private static String pattern = "dd-MM-yyyy";
	private static final String YES="Yes";
	private static final String NO="No";
	public static final Logger logger = Logger.getLogger(AppUtil.class);

	public static String getCurrentLoggedinUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null)
			return Constants.ANONYMOUS_USER;
		else
			return authentication.getName();
	}

	public static Calendar strToCal(String s) {
		try {
			DateFormat df = new SimpleDateFormat(pattern);
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(s));
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Calendar strToCal(String s,String modifiedPattern) {
		try {
			DateFormat df = new SimpleDateFormat(modifiedPattern);
			Calendar cal = Calendar.getInstance();
			cal.setTime(df.parse(s));
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void validateImage(MultipartFile image) {
		if (!(image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/png"))) {
			throw new RuntimeException("Only JPG images are accepted");
		}
		if (image.getSize() > 5000000) {
			throw new RuntimeException("Size is more than 5MB");
		}
	}

	public static String encodeToString(byte[] imageInByte, String type) {
		InputStream in = new ByteArrayInputStream(imageInByte);
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			BufferedImage image = ImageIO.read(in);
			ImageIO.write(image, type, bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			logger.error("Error in Image IO  ", e);
		}
		return imageString;
	}

	public static String generateThumbnailViewString(byte[] imageInByte) {
		InputStream in = new ByteArrayInputStream(imageInByte);
		String imageString = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			BufferedImage image = ImageIO.read(in);
			BufferedImage thumbImg = Scalr.resize(image, Method.QUALITY, Mode.AUTOMATIC, 50, 50, Scalr.OP_ANTIALIAS);

			ImageIO.write(thumbImg, "jpg", bos);
			byte[] imageBytes = bos.toByteArray();

			BASE64Encoder encoder = new BASE64Encoder();
			imageString = encoder.encode(imageBytes);

			bos.close();
		} catch (IOException e) {
			logger.error("Error in Image IO  ", e);
		}
		return imageString;
	}

	public static ImsError handleApplicationRuntimeError(Exception e) {
		ImsError error = new ImsError();
		error.setErrorCode(ErrorCodes.INTERNAL_ERROR);
		error.setMessage("Ooops Something wrong happened " + e.getMessage());
		logger.fatal(e.getMessage(), e);
		return error;
	}
	
	public static int getAge(Calendar dob) 
	{
	    Calendar now = Calendar.getInstance();
	     

	    if (dob.after(now)) 
	    {
	        throw new IllegalArgumentException("Can't be born in the future");
	    }

	    int age = now.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

	    if (now.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) 
	    {
	        age--;
	    }

	    return age;
	}
	
	public static Calendar getRequiredDate(String option,Calendar refDate){
		if(option.equalsIgnoreCase("next91Days")){
			Calendar next91Days = Calendar.getInstance();
			next91Days.setTimeInMillis(refDate.getTimeInMillis());
			next91Days.add(Calendar.DATE,91);
			return next91Days;
			
		}else if(option.equalsIgnoreCase("next181Days")){
			Calendar next181Days = Calendar.getInstance();
			next181Days.setTimeInMillis(refDate.getTimeInMillis());
			next181Days.add(Calendar.DATE,181);	
			return next181Days;
		}
		
		return null; 
		
	}
	
	public static long getPeriodInterval(Calendar dayOne,Calendar dayTwo){
		
		if(dayOne != null && dayTwo != null){
			return (dayOne.getTimeInMillis() - dayTwo.getTimeInMillis())/(24*60*60*1000);		
		}
		return 0;
		
	}
	
	public static String isEligibleForNextStudy(Calendar studyCompletionDate){
		
		
		Calendar today = Calendar.getInstance();
		Calendar day181 = Calendar.getInstance(); 		
		long difference  =  AppUtil.getPeriodInterval(today, studyCompletionDate);
 		if(studyCompletionDate == null || difference > 89 ){
 			return YES;
		} else{
			return NO;
		}	
				
		  
	}

	public static String isEligibleFor181Days(Calendar studyCompletionDate){ 
		
 		Calendar today = Calendar.getInstance();
		Calendar day181 = Calendar.getInstance(); 		
		long difference  =  AppUtil.getPeriodInterval(today, studyCompletionDate);
 		if(studyCompletionDate == null ){
 			return YES;
		}else if(difference < 181){
			return NO; 
		} 
 		return YES;
		 
	}
	
	public static Calendar convertString2Date(String dateString, String format) {
		//SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {

			if (dateString != null) {
				Calendar  cal = Calendar.getInstance();
				  cal.setTime(formatter.parse(dateString));
				  return cal;
				 
			}

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public static String convertCalToStr(Calendar cal){
		if(cal == null){
			return "Date Not Available";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		dateFormat.setCalendar(cal); 
		return (dateFormat.format(cal.getTime())).toUpperCase();

		
	}
}
