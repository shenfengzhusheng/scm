package org.xfs.test.proxy;


public class ProxyDemo {

    public static void main(String[] args) throws Exception {
        proxyInoke();
    }

    // public static CloseableHttpResponse send(CloseableHttpClient client, HttpPost method) throws ClientProtocolException, IOException {
    // CloseableHttpResponse response = null;
    // response = client.execute(method);
    // return response;
    // }

    public static void normalInvoke() {
        ServiceI service = new ServiceImpl();
        System.out.println(service.getData("db"));
    }

    public static void proxyInoke() throws Exception {
        ServiceI service = ProxyInvoke.getProxy(ServiceI.class, ServiceImpl.class);
        System.out.println(service.getData("redis"));
    }

}
