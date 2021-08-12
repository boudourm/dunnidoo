package dun.dunnidoo2.model;


import java.net.*;
import java.io.*;

public class SendSoap {
	public static void main(String[] args) throws Exception {
		String soapMessage = "		<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:sch=\"http://www.tibco.com/schemas/MakeWebServices/Shcema/Schema.xsd\">\r\n" + 
				"		   <soapenv:Header/>\r\n" + 
				"		   <soapenv:Body>\r\n" + 
				"		      <sch:Addition>\r\n" + 
				"		         <sch:number1>2</sch:number1>\r\n" + 
				"		         <sch:number2>7</sch:number2>\r\n" + 
				"		      </sch:Addition>\r\n" + 
				"		   </soapenv:Body>\r\n" + 
				"		</soapenv:Envelope>";


		String res = sendSOAP("http://localhost:8085/WebService/WSDL-service0.serviceagent/PortTypeEndpoint0", soapMessage);
		System.out.println(res);
	}

	public static String sendSOAP(String SOAPUrl, String soapMessage)
			throws Exception {
		URL url = new URL(SOAPUrl);
		URLConnection connection = url.openConnection();
		HttpURLConnection httpConn = (HttpURLConnection) connection;


		byte[] byteArray = soapMessage.getBytes("UTF-8");

		httpConn.setRequestProperty("Content-Length", String
				.valueOf(byteArray.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", "");
		httpConn.setRequestMethod("POST");

		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);

		OutputStream out = httpConn.getOutputStream();
		out.write(byteArray);
		out.close();
		BufferedReader in = null;
		StringBuffer resultMessage= new StringBuffer();
		try {
			InputStreamReader isr = new InputStreamReader(httpConn
					.getInputStream());
			in = new BufferedReader(isr);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				resultMessage.append(inputLine);
			}

		} finally {
			if (in != null) {
				in.close();
			}
		}
		return resultMessage.toString();
	}
}