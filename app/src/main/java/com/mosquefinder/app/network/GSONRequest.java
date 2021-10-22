package com.mosquefinder.app.network;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GSONRequest<T> extends JsonRequest<T> {

    public enum REQ_TYPE{
        DAILY,
        MONTHLY
    }

    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Listener<T> listener;

    private GSONRequest(int requestType, String url, String requestBody, Class<T> clazz, Map<String, String> headers, Listener<T> listener, Response.ErrorListener errorListener) {
        super(requestType,url,requestBody,listener,errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    public static <T> GSONRequest<T> getGsonRequest(REQ_TYPE req_type, String requestBody, Class<T> clazz, Listener<T> listener, Response.ErrorListener errorListener){
        int httpRequestType=0;
        String url=null;

        Map<String, String> headers =new HashMap<String, String>();
        headers.put("Content-Type","application/json");

        switch (req_type){
            case DAILY:
                httpRequestType = Method.GET;
                url = "https://muslimsalat.com/cape-town.json?key=e7e6e40fc282866c47cda3e819fc9f04";
                break;
            case MONTHLY:
                httpRequestType = Method.GET;
                url = "https://muslimsalat.com/cape-town/monthly.json?key=e7e6e40fc282866c47cda3e819fc9f04";
        }

        GSONRequest<T> gsonRequest = new GSONRequest(httpRequestType, url,requestBody, clazz, headers, listener, errorListener);
        return gsonRequest;
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }
}
