package Helpers;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.LocalizedMessage;
import org.apache.struts2.dispatcher.multipart.MultiPartRequest;
import org.apache.struts2.dispatcher.multipart.UploadedFile;

public class MockMultipartRequest implements MultiPartRequest{

	public MockMultipartRequest(String propertyName, String fileName, String contentType, File file) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parse(HttpServletRequest request, String saveDir) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enumeration<String> getFileParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getContentType(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UploadedFile[] getFile(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFileNames(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFilesystemName(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getParameterValues(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LocalizedMessage> getErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}
