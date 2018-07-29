package login.com.girish.nsdclient;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private  NsdManager nsdManager;
    private String SERVICE_TYPE ="_http._tcp.";
    private NsdManager.ResolveListener mResolveListener = new NsdManager.ResolveListener() {
        @Override
        public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {

        }

        @Override
        public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
            //data exchange...
            Log.i("NSDCLIENT","onServiceResolved()  Name :"+nsdServiceInfo.getServiceName());
            Log.i("NSDCLIENT","onServiceResolved()  Port :"+nsdServiceInfo.getPort());


            ///useful code by using ServerSocket class of java
            //Using assigned port
        }
    };
    private NsdManager.DiscoveryListener mDiscoveryListener = new NsdManager.DiscoveryListener() {
        @Override
        public void onStartDiscoveryFailed(String s, int i) {
            Log.i("NSDCLIENT","onSrtDisFailed() Error "+i);

        }

        @Override
        public void onStopDiscoveryFailed(String s, int i) {
            Log.i("NSDCLIENT","onStopDisFailed() Error "+i);
        }

        @Override
        public void onDiscoveryStarted(String s) {
            Log.i("NSDCLIENT","onDisStarted()  "+s);
        }

        @Override
        public void onDiscoveryStopped(String s) {
            Log.i("NSDCLIENT","onDisStopped() "+s);
        }

        @Override
        public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
            Log.i("NSDCLIENT","onServiceFound()  "+nsdServiceInfo.getServiceName());
            nsdManager.resolveService(nsdServiceInfo,mResolveListener);


        }

        @Override
        public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
            Log.i("NSDCLIENT","onServiceLost()  "+nsdServiceInfo.getServiceName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nsdManager = (NsdManager) getSystemService(NSD_SERVICE);
    }

    public void discover(View view) {
        nsdManager.discoverServices(SERVICE_TYPE,NsdManager.PROTOCOL_DNS_SD,mDiscoveryListener);
    }
}
