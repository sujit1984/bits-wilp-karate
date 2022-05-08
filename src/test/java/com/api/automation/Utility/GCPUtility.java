package com.api.automation.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

public class GCPUtility {

	public static String downloadFile(String prodId, String bucketNm, String objectNm, String destFilePth) throws IOException {
		String projectId = prodId; 
		String objectName = objectNm;
		String bucketName = bucketNm;
		String destFilePath = destFilePth + objectName;
		
		//find the current working directory for 
	    String localDir = System.getProperty("user.dir");
	    File authFile = new File(localDir + "\\src\\test\\java\\com\\api\\automation\\Utility\\google_auth.json");
		String jsonPath = authFile.toString();
		//GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("
				//D:\\Mtech\\Sem4\\Karate\\karateframework\\src\\test\\java\\com\\api\\automation\\Utility\\google_auth.json")).createScoped(projectId, objectName, bucketName);
		
		  GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
			        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
		
		//printing out the values for the project, the destFilePath 
		System.out.println("The destination directory is: " +destFilePath);
		System.out.println("The project name is : " +projectId);
		System.out.println("The bucket name is : " +bucketName);		

		
		
		Blob blob = storage.get(BlobId.of(bucketName,objectName));
		
		System.out.println("The current Directory is:" +localDir);
		File file = new File(localDir + "\\src\\test\\java\\com\\api\\automation\\Downloads\\" + objectName);
		blob.downloadTo(Paths.get(file.getPath()));
		    
		    
		return "PASS";
	}
	
	public static String uploadFile(String projId, String bucketname, String localFileName, String localPath) throws IOException {
		String ResultValue;
		String localDir = System.getProperty("user.dir");
		String projectId = projId; 
		String objectName = localFileName;
		String bucketName = bucketname;
		String localFilePath = localPath;
		//String filePath = "D:\\Mtech\\Sem4\\Karate\\karateframework\\src\\test\\java\\com\\api\\automation\\Features\\contacts_input_file_20220507200956.txt";
		String fileName = localFileName;
		String localPathSource = System.getProperty("user.dir")+"" + localFilePath + localFileName +"";
		
		System.out.println("Location of the file to be uploaded:" +localPathSource);
		
		//System.out.println(localPathSource);
		//String objectName = objectname + fileName;
		String jsonPath = localDir + "\\src\\test\\java\\com\\api\\automation\\Utility\\google_auth.json";
		
		//authorizing the user to access the google objects
		GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
		        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
		
       try {
    	   
    	   
    	   Storage storage = StorageOptions.newBuilder().setCredentials(credentials).setProjectId(projectId).build().getService();
    	    BlobId blobId = BlobId.of(bucketName, objectName);
    	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
    	    storage.create(blobInfo, Files.readAllBytes(Paths.get(localPathSource)));

    	    System.out.println(
    	        "File " + fileName + " uploaded to bucket " + bucketName + " as " + fileName);
 
		
		return "PASS";
       }
       catch (Throwable exception) {
    	   ResultValue = "Upload File method exception:" + exception + "";
    	   System.out.println(ResultValue);
    	   
       }
       
       return fileName;
	}
	
	
}
