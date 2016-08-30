package com.android.lala.http.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;
import java.util.Map;

/***
 * @author sxshi
 * @category 重写Request 返回byte[]数组
 */
public class ByteRequest extends Request<byte[]> {
    private Listener<byte[]> mListener;
    private HashMap<String, String> paramers;
    private int method;

    public ByteRequest(int method, String url, Listener<byte[]> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.method = method;
        paramers = new HashMap<>();
    }

    public ByteRequest(String url, Listener<byte[]> listener, ErrorListener errorListener) {
        super(0, url, errorListener);
    }

    @Override
    protected void deliverResponse(byte[] response) {
        if (this.mListener != null) {
            this.mListener.onResponse(response);
        }
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        byte[] result = response.data;
        return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
    }

    protected void onFinish() {
        this.mListener = null;
    }

    public void setParamers(HashMap<String, String> paramers) {
        this.paramers = paramers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (method == Method.POST) {
            if (paramers != null && paramers.size() > 0) {
                return paramers;
            }
        }
        return super.getParams();
    }
}
