Feature: To test the upload and download to a gcs bucket 

Background: 

Given url 'https://storage.googleapis.com/storage/'
And def READ_METHOD = Java.type('com.api.automation.Utility.GCPUtility')
#* def endpoint = "https://storage.googleapis.com/storage"
* def BUCKET_NAME = "bits-wilp-bucket"
* def PROJECT_ID = "vivid-nomad-348811"
* def DOWNLOAD_DIR = "\\src\\test\\java\\com\\api\\automation\\Downloads\\"
* def UPLOAD_DIR = "\\src\\test\\java\\com\\api\\automation\\Upload\\"
* def DWNLD_FILE_NAME = 'inputfile20220508.txt'
* def UPLD_FILE_NAME = "contacts_input_file_20220508200956.txt"



Scenario: Download the file present in the gcs bucket

When def testString = READ_METHOD.downloadFile(PROJECT_ID,BUCKET_NAME,DWNLD_FILE_NAME,DOWNLOAD_DIR)
And print testString


Scenario: Upload the file to the gcs bucket

When def testString = READ_METHOD.uploadFile(PROJECT_ID,BUCKET_NAME,UPLD_FILE_NAME,UPLOAD_DIR)
And print testString