



#https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D
#启用此控制台进行流量监控
java -Dserver.port=9090 -Dcsp.sentinel.dashboard.server=localhost:9090 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar