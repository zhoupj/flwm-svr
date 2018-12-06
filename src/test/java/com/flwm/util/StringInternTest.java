package com.flwm.util;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StringInternTest {


    public void visitId(String id) {

        /**
         * synchronized理解 https://www.jianshu.com/p/d53bf830fa09
         */

        synchronized (id.intern()) {

            System.out.println("hello," + id + ",threadId:" + Thread.currentThread().getId());

            try {

                long stop = 5000;
                if (id.equals("zhoupj")) {
                    stop = 10000;
                }

                Thread.sleep(stop);

            } catch (Exception e) {

            }
            System.out.println("over," + id + ",threadId:" + Thread.currentThread().getId());

        }
    }


    @Test
    public void testVisit() {

        StringInternTest test = new StringInternTest();

        ExecutorService service = Executors.newFixedThreadPool(4);


        List<String> ids = Arrays.asList("zhoupj", "yundian", "yizhi", "zhoupj");

        List<Future> fs = new ArrayList<>();
        for (String id : ids) {

            Future f = service.submit(new Runnable() {
                @Override
                public void run() {

                    test.visitId(id);
                }
            });

            fs.add(f);
        }

        for (Future f : fs) {
            try {
                f.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }


}
