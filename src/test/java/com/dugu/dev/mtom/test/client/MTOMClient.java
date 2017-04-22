package com.dugu.dev.mtom.test.client;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.example.uploadfile.mtom.service.UploadService;
import org.example.uploadfile.mtom.types.InputRequest;
import org.example.uploadfile.mtom.types.UploadResponse;

public class MTOMClient {

	public static void main(String[] args) {

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(UploadService.class);
		factory.setAddress("http://localhost:8181/SoapWebServiceSecurity/service/upload/UploadService");
		UploadService client = (UploadService) factory.create();

		UploadResponse output = new UploadResponse();

		/* Setting the input values */
		InputRequest input = new InputRequest();
		input.setFilePath("C:/Users/basanta.kumar.hota/Desktop");
		input.setFileType("demo.txt");
		DataSource source = new FileDataSource(new File("C:/Users/basanta.kumar.hota/Desktop/test.txt"));
		input.setFile(((new DataHandler(source))));

		try {
			output = client.upload(input);
			System.out.println(output.getResponse());
		} catch (Exception e) {

			System.out.println("EXCEPTION :" + e.getMessage());
		}

		System.out.println("Done");

	}

}
