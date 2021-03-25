FROM ubuntu

ARG geckodriver_ver=0.29.0

RUN apt-get update \
     && apt-get upgrade -y \
     && apt-get install -y --no-install-recommends --no-install-suggests \
                ca-certificates \
     && update-ca-certificates \

# Install tools for building
 && toolDeps=" \
        curl bzip2 \
    " \
 && apt-get install -y --no-install-recommends --no-install-suggests \
            $toolDeps \
    \

# Download and install geckodriver
&& curl -fL -o /tmp/geckodriver.tar.gz \
     https://github.com/mozilla/geckodriver/releases/download/v${geckodriver_ver}/geckodriver-v${geckodriver_ver}-linux64.tar.gz \
&& tar -xzf /tmp/geckodriver.tar.gz -C /tmp/ \
&& chmod +x /tmp/geckodriver \
&& mv /tmp/geckodriver /usr/local/bin/ \

# Other install
&& apt-get -y install default-jre-headless \
&& apt-get install -y firefox \
&& apt-get clean

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]

#https://github.com/instrumentisto/geckodriver-docker-image/blob/master/Dockerfile
#https://habr.com/ru/company/ruvds/blog/439980/
#https://habr.com/ru/post/353238/
#https://github.com/mozilla/geckodriver/releases/download/v0.29.0/geckodriver-v0.29.0-linux64.tar.gz

#docker build --tag "parser:v0" .
#docker run parser:v0
