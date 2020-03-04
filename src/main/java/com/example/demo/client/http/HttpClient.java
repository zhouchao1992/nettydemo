package com.example.demo.client.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HttpClient {
    private static  final String ip ="127.0.0.1";
    private static  final int port =6666;
    public void start() throws Exception {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            /**
             *EventLoop的组
             */
            b.group(worker);
            /**
             * 用于构造socketchannel工厂
             */
            b.channel(NioSocketChannel.class);
            /**设置选项
             * 参数：Socket的标准参数（key，value），可自行百度
             保持呼吸，不要断气！
             * */
            b.option(ChannelOption.SO_KEEPALIVE, true);
            /**
             * 自定义客户端Handle（客户端在这里搞事情）
             */
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HttpClientHandler());
                }
            });
            /** 开启客户端监听*/
            ChannelFuture f = b.connect(this.ip, this.port).sync();
            /**等待数据直到客户端关闭*/
            f.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        client.start();
    }
}
