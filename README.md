# TelegramNotificationService

sudo apt-get install default-jdk (java -version / javac -version )

sudo nano /etc/environment 

PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games" 
JAVA_HOME=/usr/lib/jvm/default-java 

source /etc/environment (echo $JAVA_HOME)

sudo apt install maven (mvn -version)

# docker build --tag "bot:0" .
# docker run -p 8080:8080 -p 5005:5005 bot:0

# default for postman test is:
# http://localhost:8080 or http://127.0.0.1:8080

upd:
docker save -o mf.tar mf:0

