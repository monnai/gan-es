package com.gu.ganes.config;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gu.sc
 */
@Configuration
public class ElasticSearchConfig {

  @Value("${elasticsearch.host}")
  private String esHost;

  @Value("${elasticsearch.port}")
  private int esPort;

  @Value("${elasticsearch.cluster.name}")
  private String esName;

  /*@Bean
  public TransportClient esClient() throws UnknownHostException {
    Settings settings = Settings.builder()
        .put("cluster.name", this.esName)
//                .put("cluster.name", "elasticsearch")
        //设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中。这样做的好处是，
        // 一般你不用手动设置集群里所有集群的ip到连接客户端，它会自动帮你添加，并且自动发现新加入集群的机器。
        .put("client.transport.sniff", true)
        .build();

    InetSocketTransportAddress master = new InetSocketTransportAddress(
        InetAddress.getByName(esHost), esPort
//          InetAddress.getByName("10.99.207.76"), 8999
    );

    return new PreBuiltTransportClient(settings).addTransportAddress(master);
  }*/

  @Bean
  public RestHighLevelClient restHighLevelClient() {
    return new RestHighLevelClient(
        RestClient.builder(new HttpHost(esHost, 9200, "http")));
  }

  @PreDestroy
  public void destroy() {
    System.out.println("断开es连接");
    try {
      restHighLevelClient().close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @PostConstruct
  void init() {
    System.setProperty("es.set.netty.runtime.available.processors", "false");
  }
}
