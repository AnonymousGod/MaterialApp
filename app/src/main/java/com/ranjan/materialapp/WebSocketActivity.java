package com.ranjan.materialapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class WebSocketActivity extends Activity {

    Button sendData, fetchData, dataRV;
    WebSocketConnector webSocketConnector;
    OkHttpClient client;
    String TAG = "WebSocketActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);

        sendData = findViewById(R.id.send_data);
        fetchData = findViewById(R.id.fetch_data);
        dataRV = findViewById(R.id.data_rv);

        client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://echo.websocket.org").build();
        webSocketConnector = new WebSocketConnector();
        WebSocket ws = client.newWebSocket(request, webSocketConnector);
        client.dispatcher().executorService().shutdown();
    }

    private void output(String message) {
        Log.d(TAG, "output: " + message);
    }

    private final class WebSocketConnector extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            webSocket.send("Hello from the other side!");
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            output("Receiving : " + text);
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            output("Receiving bytes : " + bytes.hex());
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            output("Closing : " + code + " / " + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            output("Error : " + t.getMessage());
        }
    }
}
