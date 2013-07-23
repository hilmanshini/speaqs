package speaqs.hilmanshini.tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.*;
import android.widget.ImageView;
import java.io.*;
import java.nio.CharBuffer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.*;
import org.json.JSONObject;


public class HttpTool {

    public static DefaultHttpClient httpClient;

    static {
        httpClient = new DefaultHttpClient();
    }
    public static String cookie;
    public static JSONObject userObjectJSON;
    public static Bitmap userPic;

    public static DefaultHttpClient getHttpClient() {
        return httpClient;
    }

    public static String postDataWithCookie(String Uri, List<NameValuePair> nameValuePairs) {
        return postData(Uri, nameValuePairs, true);
    }

    public static String postData(String Uri, List<NameValuePair> nameValuePairs, boolean withCookie) {
        Log.d("fetching", Uri);
        try {
            // Add your data
            synchronized (httpClient) {
                InputStream is = postDataGetStream(Uri, nameValuePairs, withCookie);

                InputStreamReader str = new InputStreamReader(is);
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i != -1) {
                    i = str.read();
                    if (i != -1) {
                        sb.append((char) i);
                    }
                }
                System.err.println(sb.toString());
                return sb.toString();
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream postDataFileGetStream(String Uri, List<NameValuePair> nameValuePairs, InputStream is, boolean withCookie) {
        // Create a new HttpClient and Post Header
        DefaultHttpClient httpclient = getHttpClient();

        HttpPost httppost = new HttpPost(Uri);
        try {

            httppost.setEntity(new InputStreamEntity(is, is.available()));
        } catch (IOException ex) {
            Logger.getLogger(HttpTool.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Add your data

            Hashtable q = new Hashtable();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            if (withCookie) {
                httppost.addHeader("Cookie", "usersess=" + cookie + ";");
            }
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            return response.getEntity().getContent();



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static InputStream postDataGetStream(String Uri, List<NameValuePair> nameValuePairs, boolean withCookie) {
        // Create a new HttpClient and Post Header
        DefaultHttpClient httpclient = getHttpClient();

        HttpPost httppost = new HttpPost(Uri);


        try {
            // Add your data

            Hashtable q = new Hashtable();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            if (withCookie) {
                httppost.addHeader("Cookie", "usersess=" + cookie + ";");
            }
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
            return response.getEntity().getContent();



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    static Hashtable<String, Bitmap> cache = new Hashtable<String, Bitmap>();

    public static Bitmap postDataApplygetBitmap(String Uri, List<NameValuePair> nameValuePairs, boolean withCookie) {
        // Create a new HttpClient and Post Header
        System.err.println("cache " + cache.toString() + " has a key " + Uri + " result " + cache.containsKey(Uri));
        if (cache.containsKey(Uri)) {
            return cache.get(Uri);
        } else {
            DefaultHttpClient httpclient = getHttpClient();
            System.err.println("fetch " + Uri);
            HttpPost httppost = new HttpPost(Uri);


            try {
                // Add your data

                Hashtable q = new Hashtable();
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                if (withCookie) {
                    httppost.addHeader("Cookie", "usersess=" + cookie + ";");
                }
                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                Bitmap b = BitmapFactory.decodeStream(response.getEntity().getContent());
                b = Bitmap.createScaledBitmap(b, 60, 60, true);
                //b = ImageTool.getCroppedBitmap(b);
                cache.put(Uri, b);
                System.err.println("cache " + cache.size());
                return b;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
    }

    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        int b;
        while ((b = is.read()) != -1) {
            outStream.write(b);
        }
        byte[] r = outStream.toByteArray();
        outStream.close();

        return r;
    }

    public static byte[] postDataGetByte(String Uri, List<NameValuePair> nameValuePairs, boolean withCookie) {
        // Create a new HttpClient and Post Header
        DefaultHttpClient httpclient = getHttpClient();

        HttpPost httppost = new HttpPost(Uri);


        try {
            // Add your data

            Hashtable q = new Hashtable();
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            if (withCookie) {
                httppost.addHeader("Cookie", "usersess=" + cookie + ";");
            }
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            return getBytesFromInputStream(response.getEntity().getContent());



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String postDataWithFile(String url,InputStream is, List<NameValuePair> all, boolean withCookie) {
        HttpClient q = getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntity entity = new MultipartEntity();
        try {
            System.err.println("sending "+is.available());
        } catch (IOException ex) {
            Logger.getLogger(HttpTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        entity.addPart("fileData", new InputStreamBody(is, "audio/x-wav", "a.wav"));
        try {
            for(int i = 0 ; i < all.size(); i ++){
                NameValuePair ss = all.get(i);
                entity.addPart(ss.getName() , new StringBody(ss.getValue()));
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HttpTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (withCookie) {
                httpPost.addHeader("Cookie", "usersess=" + cookie + ";");
            }
        httpPost.setEntity(entity);
        try {
            HttpResponse servletResponse = q.execute(httpPost);
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HttpTool.class.getName()).log(Level.SEVERE, null, ex);
            }
            InputStreamReader ir = new InputStreamReader(servletResponse.getEntity().getContent());

                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i != -1) {
                    i = ir.read();
                    if (i != -1) {
                        sb.append((char) i);
                    }
                }
            return sb.toString();
        } catch (IOException ex) {
            return  null;
        }
    }
}
