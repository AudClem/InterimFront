package com.example.myapplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Request {

    public static class Body {
        private final JSONObject m_data;

        public Body() {
            m_data = new JSONObject();
        }

        public JSONObject getData() {
            return m_data;
        }

        public void put(String name, String value ){
            try {
                getData().put( name, value );
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        public String getString( String name ){
            try {
                return getData().getString( name );
            } catch (JSONException e) {
                return null;
            }
        }
    }
    public static class Response {

        private final int m_code;
        private final JSONArray m_data;

        public Response( int code, JSONArray message ){
            m_code = code;
            m_data = message;
        }

        public int length() { return m_data.length(); }
        public int getCode() {
            return m_code;
        }

        public JSONObject getData( int index ) {
            try {
                return m_data.getJSONObject( index );
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        public String getString( int index, String name ){
            try {
                return getData( index ).getString( name );
            } catch (JSONException e) {
                return null;
            }
        }
    }


    static public Response post( String url, Request.Body data ){
        try {
            URL object = new URL( url );

            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput( true );
            con.setDoInput( true );
            con.setRequestProperty( "Content-Type", "application/json; charset=UTF-8" );
            con.setRequestMethod( "POST" );

            DataOutputStream localDataOutputStream = new DataOutputStream( con.getOutputStream() );
            localDataOutputStream.writeBytes( data.getData().toString() );
            localDataOutputStream.flush();
            localDataOutputStream.close();

            int responseCode = con.getResponseCode();
            InputStream IS = ( responseCode == 200 || responseCode == 201 ) ? con.getInputStream() : con.getErrorStream();

            try( BufferedReader br = new BufferedReader( new InputStreamReader( IS, StandardCharsets.UTF_8) ) ) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
                return new Response( responseCode, new JSONArray( response.toString() ) );

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static public Response get( String url ){
        try {
            URL object = new URL( url );

            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            int responseCode = con.getResponseCode();
            if( responseCode == 204 ) return new Response( responseCode, null );

            try( BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream(), StandardCharsets.UTF_8) ) ) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while( (responseLine = br.readLine()) != null ){
                    response.append(responseLine.trim());
                }
                return new Response( responseCode, new JSONArray( response.toString() ) );

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
