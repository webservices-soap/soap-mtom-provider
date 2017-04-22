package com.dugu.dev.acc.mtom.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;
import javax.jws.WebService;

import org.example.uploadfile.mtom.service.UploadService;
import org.example.uploadfile.mtom.types.InputRequest;
import org.example.uploadfile.mtom.types.UploadResponse;

@WebService(targetNamespace = "http://www.example.org/UploadFile/mtom/service", portName = "UploadServiceSOAPPort", serviceName = "UploadFile", endpointInterface = "org.example.uploadfile.mtom.service.UploadService")
public class UploadServiceImpl implements UploadService {

	@Override
	public UploadResponse upload(InputRequest request) {
		UploadResponse response = new UploadResponse();
		/* DataHandler – The object which contains the File Data */
		DataHandler handler = request.getFile();
		try {

			/* Creating an InputStream from the DataHandler */
			InputStream is = handler.getInputStream();
			String fileName = "/" + request.getFilePath() + "/"
					+ request.getFileType();

			/* Creating an Output stream and writing to the target file */
			OutputStream os = new FileOutputStream(new File(fileName));
			byte[] b = new byte[100000];
			int bytesRead = 0;
			while ((bytesRead = is.read(b)) != -1) {
				os.write(b, 0, bytesRead);
			}
			response.setResponse("File Uploaded Successfully..");
		/*	os.flush();
			os.close();
			is.close();*/

		} catch (Exception e) {
			System.out.println("Exception in webservice : "
					+ e.getLocalizedMessage());

			response.setResponse("File Uploaded Failed..");
		}

		return response;
	}

}
