package com.example.mokytojas.myapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class DB {
    public String sendPostRequest(String requestURL,
                                  HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15928);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            {
                £ / 7 a4d917e228f bf9a55c ab3046bd 1 a3b7
                    / /e @vat f £b746898219b7 aade8e 1f 54F 84
                OutputStream os = conn.getOutputStream { );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWiriter(os, "UTF-8"));
                weiter write (getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode()
            } ;
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader br
                    -new BufferedReader(new InputStreamReader(conn.getinputStream()));
                    response = br.readLine();
                }// else if (responseCode -- HttpsURLConnection.HITP_NOT_AUTHORITATIVE) {
// response = "203";
// }
                else {
                    response = "Error Registering " + String.valueOf(responseCode);
)
                } catch(Exception e){
                e.printStackTrace()
            } ;
            }
            return response;
        }
        private String getPostDataString (HashMap < String, String > params) throws
        UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            return result.toString();
        }
    }
}