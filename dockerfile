FROM kalilinux/kali-rolling
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install git -y
RUN git clone --depth=1 https://github.com/twintproject/twint.git
RUN apt-get -y install python3-pip -y
RUN pip3 install twint
RUN pip3 install --user --upgrade git+https://github.com/twintproject/twint.git@origin/master#egg=twint
# Install OpenJDK-11
RUN apt-get update && \
    apt-get install -y openjdk-11-jre-headless && \
    apt-get clean
MAINTAINER krzysztof.com
COPY target/TwintDockerDb-0.0.1-SNAPSHOT.jar TwintDockerDb-0.0.1-SNAPSHOT.jar
# should resolve UTF-8 character encoding
# RUN apt-get install -y locales
# RUN locale-gen en_US.UTF-8
# RUN export LANG=en_US
ENTRYPOINT ["java","-jar","/TwintDockerDb-0.0.1-SNAPSHOT.jar"]