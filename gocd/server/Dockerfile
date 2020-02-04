FROM gocd/gocd-server:v20.1.0

USER root
RUN mkdir -p /etc/service/go-server
ADD go-common-scripts.sh ./
ADD go-server-start.sh ./

CMD ["/bin/bash","./go-server-start.sh"]
CMD ["/docker-entrypoint.sh"]