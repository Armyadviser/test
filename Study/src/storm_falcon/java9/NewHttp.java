package storm_falcon.java9;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.Authenticator;
import java.net.CookieManager;
import java.net.ProxySelector;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;

/**
 * @author gewp
 */
public class NewHttp {

    static HttpClient buildClient() throws NoSuchAlgorithmException {
        // 默认配置
        HttpClient.newHttpClient();

        // 自定义配置
        Authenticator authenticator = new Authenticator() {
        };
        return HttpClient.newBuilder()
                .authenticator(authenticator)
                .sslContext(SSLContext.getDefault())
                .sslParameters(new SSLParameters())
                .proxy(ProxySelector.getDefault())
                .executor(Executors.newCachedThreadPool())
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .cookieManager(new CookieManager())
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    static HttpResponse<String> get(HttpClient client) throws IOException, InterruptedException {
        return client.send(
                HttpRequest.newBuilder(URI.create("http://www.baidu.com?ie=utf-8&wd=abc"))
                .headers("key1", "value1", "key2", "value2")
                .GET()
                .build(),
                HttpResponse.BodyHandler.asString()
        );
    }

    static HttpResponse<Path> post(HttpClient client) throws IOException, InterruptedException {
        return client.send(
            HttpRequest.newBuilder(URI.create("http://www.baidu.com"))
                .headers("key1", "value1", "key2", "value2")
                .POST(HttpRequest.BodyProcessor.fromString("wd=abc", Charset.forName("utf-8")))
                .build(),

                // 将返回体保存在文件里
                HttpResponse.BodyHandler.asFile(Paths.get("/home/falcon/test/test.txt"))
        );
    }

    public static void main(String[] args) throws Exception {
        HttpClient httpClient = buildClient();
        HttpResponse<String> getResponse = get(httpClient);

        int code = getResponse.statusCode();
        System.out.println(code);
        Object body = getResponse.body();
        System.out.println(body);

        HttpResponse<Path> postResponse = post(httpClient);
        code = postResponse.statusCode();
        System.out.println(code);
        body = postResponse.body();
        System.out.println(body);
    }
}
