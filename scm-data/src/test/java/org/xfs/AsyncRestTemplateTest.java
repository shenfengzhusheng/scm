package org.xfs;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;  
import org.springframework.web.client.AsyncRestTemplate;

import com.qht.tms.car.entity.Car;


public class AsyncRestTemplateTest {

	public static void main(String[] args)throws InterruptedException {
		AsyncRestTemplate template=new AsyncRestTemplate();
		//调用完后立即返回（没有阻塞）  
		//ListenableFuture<ResponseEntity<String>>future=template.getForEntity("http://127.0.0.1/callback/test",String.class);
	    ListenableFuture<ResponseEntity<Car>> future =template.getForEntity("http://127.0.0.1/callback/query", Car.class);  
	    //设置异步回调  
	    future.addCallback(new ListenableFutureCallback<ResponseEntity<Car>>() {  
  
            @Override  
            public void onSuccess(ResponseEntity<Car> respoonse) {  
               // System.out.println("### handler OK"+respoonse);  

                System.out.println("### handler OK"+respoonse.getBody().getCarId());  
                  
            }  
  
            @Override  
            public void onFailure(Throwable arg0) {  
                System.out.println("### handler error"+arg0);  
                  
            }  
        });
	    System.out.println("=========no wait");  
      //  Thread.sleep(5L*1000); //为了防止测试提前结束，关闭程序 
        try {
			System.out.println(future.get().getBody().getCarId());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
