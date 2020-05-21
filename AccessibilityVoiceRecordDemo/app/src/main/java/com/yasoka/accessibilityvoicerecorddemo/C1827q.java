package com.yasoka.accessibilityvoicerecorddemo;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.catalinagroup.callrecorder.j.q */
public class C1827q {

    /* renamed from: a */
    public static final Executor f5466a;

    /* renamed from: b */
    public static final Executor f5467b;

    /* renamed from: c */
    public static final Executor f5468c;

    /* renamed from: d */
    private static final int f5469d = Runtime.getRuntime().availableProcessors();

    /* renamed from: e */
    private static final int f5470e = Math.max(2, Math.min(f5469d - 1, 4));

    /* renamed from: f */
    private static final int f5471f = ((f5469d * 2) + 1);

    /* renamed from: com.catalinagroup.callrecorder.j.q$a */
    static class C1828a implements ThreadFactory {

        /* renamed from: a */
        private final AtomicInteger f5472a = new AtomicInteger(1);

        C1828a() {
        }

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("AT Recording #");
            sb.append(this.f5472a.getAndIncrement());
            Thread thread = new Thread(runnable, sb.toString());
            try {
                thread.checkAccess();
                thread.setPriority(6);
            } catch (Exception unused) {
            }
            return thread;
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.q$b */
    static class C1829b implements ThreadFactory {

        /* renamed from: a */
        private final AtomicInteger f5473a = new AtomicInteger(1);

        C1829b() {
        }

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("AT Backup #");
            sb.append(this.f5473a.getAndIncrement());
            return new Thread(runnable, sb.toString());
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.q$c */
    static class C1830c implements ThreadFactory {

        /* renamed from: a */
        private final AtomicInteger f5474a = new AtomicInteger(1);

        C1830c() {
        }

        public Thread newThread(Runnable runnable) {
            StringBuilder sb = new StringBuilder();
            sb.append("AT UI #");
            sb.append(this.f5474a.getAndIncrement());
            return new Thread(runnable, sb.toString());
        }
    }

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new C1828a());
        f5466a = threadPoolExecutor;
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(f5470e, f5471f, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new C1829b());
        threadPoolExecutor2.allowCoreThreadTimeOut(true);
        f5468c = threadPoolExecutor2;
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(f5470e, f5471f, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new C1830c());
        threadPoolExecutor3.allowCoreThreadTimeOut(true);
        f5467b = threadPoolExecutor3;
    }
}
